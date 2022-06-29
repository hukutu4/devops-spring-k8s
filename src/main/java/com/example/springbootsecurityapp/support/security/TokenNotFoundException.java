package com.example.springbootsecurityapp.support.security;

import org.springframework.security.core.AuthenticationException;

public class TokenNotFoundException extends AuthenticationException {
  public TokenNotFoundException(final String msg, final Throwable cause) {
    super(msg, cause);
  }

  public TokenNotFoundException(final String msg) {
    super(msg);
  }
}
