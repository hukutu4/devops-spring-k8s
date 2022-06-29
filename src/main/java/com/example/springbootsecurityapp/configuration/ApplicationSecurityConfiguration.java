package com.example.springbootsecurityapp.configuration;

import com.example.springbootsecurityapp.support.security.ApplicationUserDetails;
import com.example.springbootsecurityapp.support.security.XTokenAuthenticationFilter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Setter(onMethod_ = @Autowired)
  private List<AuthenticationProvider> providers;
  @Setter(onMethod_ = @Autowired)
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    providers.forEach(auth::authenticationProvider);
  }

  @Override
  protected UserDetailsService userDetailsService() {
    return userDetailsService;
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
        .csrf()
        .disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .httpBasic()
        .and()
        .x509()
        .and()
        .logout()
        .disable()
        .anonymous()
        .principal(ApplicationUserDetails.Anonymous.getInstance())
        .and()
        .addFilterAfter(new XTokenAuthenticationFilter(authenticationManager(), new WebAuthenticationDetailsSource()), BasicAuthenticationFilter.class)
        .authorizeRequests()
        .anyRequest().permitAll()
    ;
  }
}
