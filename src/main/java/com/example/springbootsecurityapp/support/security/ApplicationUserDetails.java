package com.example.springbootsecurityapp.support.security;

import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class ApplicationUserDetails implements UserDetails, CredentialsContainer {
  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities = Collections.emptySet();
  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;
  private boolean enabled = true;

  @Override
  public void eraseCredentials() {
    password = "***";
  }

  @Getter
  @Value
  @EqualsAndHashCode(callSuper = false)
  public static class Anonymous extends ApplicationUserDetails {
    private static final Anonymous instance = new Anonymous();
    private final String username = "anonymous";
    private final String password = "***";
    private final Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ANONYMOUS"));
    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled = true;
    private Anonymous() {
    }

    public static Anonymous getInstance() {
      return instance;
    }

    @Override
    public void setUsername(final String username) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void setPassword(final String password) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void setAuthorities(final Collection<? extends GrantedAuthority> authorities) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void setAccountNonExpired(final boolean accountNonExpired) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void setAccountNonLocked(final boolean accountNonLocked) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void setCredentialsNonExpired(final boolean credentialsNonExpired) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void setEnabled(final boolean enabled) {
      throw new UnsupportedOperationException();
    }
  }
}
