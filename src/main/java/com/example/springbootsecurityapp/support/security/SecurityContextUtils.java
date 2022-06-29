package com.example.springbootsecurityapp.support.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityContextUtils {
  private SecurityContextUtils() {
  }

  public static Optional<ApplicationUserDetails> principal() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .map(Authentication::getPrincipal)
        .filter(ApplicationUserDetails.class::isInstance)
        .map(ApplicationUserDetails.class::cast);
  }
}
