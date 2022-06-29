package com.example.springbootsecurityapp.support.security;

public interface TokenGenerator {
  String generate(final Object principal);
}
