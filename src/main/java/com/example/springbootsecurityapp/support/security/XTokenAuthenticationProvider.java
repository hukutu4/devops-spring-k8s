package com.example.springbootsecurityapp.support.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.Assert;

@RequiredArgsConstructor
public class XTokenAuthenticationProvider implements AuthenticationProvider {
  private final UserDetailsTokenService userDetailsTokenService;
  private final UserDetailsChecker userDetailsChecker;

  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    Assert.isInstanceOf(XTokenAuthenticationToken.class, authentication, () -> "Only XTokenAuthenticationToken is supported");

    final XTokenAuthenticationToken token = (XTokenAuthenticationToken) authentication;
    final String principal = (String) token.getPrincipal();
    final UserDetails userDetails = userDetailsTokenService.loadUserByToken(principal);

    userDetailsChecker.check(userDetails);

    final XTokenAuthenticationToken result = new XTokenAuthenticationToken(
        userDetails,
        userDetails.getPassword(),
        userDetails.getAuthorities()
    );
    result.setDetails(authentication.getDetails());
    return result;
  }

  @Override
  public boolean supports(final Class<?> authentication) {
    return XTokenAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
