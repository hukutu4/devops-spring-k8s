package com.example.springbootsecurityapp.support.security;

import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

import java.util.Base64;

public class RandomTokenGenerator implements TokenGenerator {
  private final StringKeyGenerator generator = new Base64StringKeyGenerator(Base64.getUrlEncoder());

  @Override
  public String generate(final Object principal) {
    return generator.generateKey();
  }
}
