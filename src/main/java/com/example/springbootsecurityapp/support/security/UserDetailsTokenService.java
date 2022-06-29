package com.example.springbootsecurityapp.support.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsTokenService {
  UserDetails loadUserByToken(final String token) throws TokenNotFoundException;
}
