package com.example.springbootsecurityapp.configuration;

import com.example.springbootsecurityapp.support.security.RandomTokenGenerator;
import com.example.springbootsecurityapp.support.security.TokenGenerator;
import com.example.springbootsecurityapp.support.security.UserDetailsTokenService;
import com.example.springbootsecurityapp.support.security.XTokenAuthenticationProvider;
import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class ApplicationConfiguration {
  @Bean
  public PasswordEncoder passwordEncoder() {
    final String idForEncode = "argon2";
    final Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put(idForEncode, new Argon2PasswordEncoder());
    return new DelegatingPasswordEncoder(idForEncode, encoders);
  }

  @Bean
  public TokenGenerator tokenGenerator() {
    return new RandomTokenGenerator();
  }

  @Bean
  public UserDetailsChecker userDetailsChecker() {
    return new AccountStatusUserDetailsChecker();
  }

  @Bean
  public XTokenAuthenticationProvider xTokenAuthenticationProvider(
      final UserDetailsTokenService userDetailsTokenService,
      final UserDetailsChecker userDetailsChecker
  ) {
    return new XTokenAuthenticationProvider(userDetailsTokenService, userDetailsChecker);
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider(
      final UserDetailsService userDetailsService,
      final PasswordEncoder passwordEncoder
  ) {
    final DaoAuthenticationProvider bean = new DaoAuthenticationProvider();
    bean.setUserDetailsService(userDetailsService);
    bean.setPasswordEncoder(passwordEncoder);
    return bean;
  }

  @Bean
  public Tika tika() {
    return new Tika();
  }
}
