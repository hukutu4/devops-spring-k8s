package com.example.springbootsecurityapp.support.security;

import lombok.RequiredArgsConstructor;
import org.springframework.core.log.LogMessage;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class XTokenAuthenticationFilter extends OncePerRequestFilter {
  private final AuthenticationManager manager;
  private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
    final String token = request.getHeader("X-Token");
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    if (!authenticationIsRequired(token)) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      final XTokenAuthenticationToken authRequest = new XTokenAuthenticationToken(token, "");
      authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
      final Authentication authResult = manager.authenticate(authRequest);

      final SecurityContext context = SecurityContextHolder.createEmptyContext();
      context.setAuthentication(authResult);
      SecurityContextHolder.setContext(context);
      if (this.logger.isDebugEnabled()) {
        this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
      }
    } catch (AuthenticationException e) {
      SecurityContextHolder.clearContext();
      this.logger.debug("Failed to process authentication request", e);
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    filterChain.doFilter(request, response);
  }

  private boolean authenticationIsRequired(final String token) {
    // Only reauthenticate if username doesn't match SecurityContextHolder and user
    // isn't authenticated (see SEC-53)
    final Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();
    if (existingAuth == null || !existingAuth.isAuthenticated()) {
      return true;
    }
    // Limit username comparison to providers which use usernames (ie
    // UsernamePasswordAuthenticationToken) (see SEC-348)
    if (existingAuth instanceof XTokenAuthenticationToken && !existingAuth.getPrincipal().equals(token)) {
      return true;
    }
    // Handle unusual condition where an AnonymousAuthenticationToken is already
    // present. This shouldn't happen very often, as XTokenAuthenticationFilter is meant to
    // be earlier in the filter chain than AnonymousAuthenticationFilter.
    // Nevertheless, presence of both an AnonymousAuthenticationToken together with a
    // BASIC authentication request header should indicate reauthentication using the
    // BASIC protocol is desirable. This behaviour is also consistent with that
    // provided by form and digest, both of which force re-authentication if the
    // respective header is detected (and in doing so replace/ any existing
    // AnonymousAuthenticationToken). See SEC-610.
    return (existingAuth instanceof AnonymousAuthenticationToken);
  }
}
