package com.example.springbootsecurityapp.service;

import com.example.springbootsecurityapp.dto.TokenRS;
import com.example.springbootsecurityapp.dto.UserRS;
import com.example.springbootsecurityapp.entity.TokenEntity;
import com.example.springbootsecurityapp.entity.UserEntity;
import com.example.springbootsecurityapp.support.security.ForbiddenException;
import com.example.springbootsecurityapp.mapper.TokenEntityMapper;
import com.example.springbootsecurityapp.mapper.UserEntityMapper;
import com.example.springbootsecurityapp.repository.TokenRepository;
import com.example.springbootsecurityapp.repository.UserRepository;
import com.example.springbootsecurityapp.support.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService implements InitializingBean, UserDetailsService, UserDetailsTokenService {
  public static final int PAGE_SIZE = 50;
  public static final String SORT_PROPERTY = "created";
  private final UserRepository userRepository;
  private final UserEntityMapper userEntityMapper;

  private final TokenRepository tokenRepository;
  private final TokenEntityMapper tokenEntityMapper;
  private final PasswordEncoder passwordEncoder;

  private final TokenGenerator tokenGenerator;
  private UserEntity notFoundUserEntity;

  @Override
  public void afterPropertiesSet() throws Exception {
    notFoundUserEntity = new UserEntity("notfound", passwordEncoder.encode("dummy password"));
  }

  public List<UserRS> getAll(final ApplicationUserDetails principal, final int page) {
    if (!principal.getUsername().equals("admin")) {
      throw new ForbiddenException();
    }

    final Page<UserEntity> entities = userRepository.findAll(PageRequest.of(page, PAGE_SIZE, Sort.Direction.ASC, SORT_PROPERTY));
    return userEntityMapper.toRS(entities.toList());
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final UserEntity saved = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    return userEntityMapper.toUserDetails(saved);
  }

  @Override
  public UserDetails loadUserByToken(final String token) throws TokenNotFoundException {
    final TokenEntity saved = tokenRepository.findById(token).orElseThrow(() -> new TokenNotFoundException("token not found"));
    return tokenEntityMapper.toUserDetails(saved);
  }

  @Transactional(propagation = Propagation.NEVER)
  public TokenRS login(final String username, final String password) {
    final UserEntity saved = userRepository.findByLogin(username).orElse(notFoundUserEntity);
    if (!passwordEncoder.matches(password, saved.getPassword())) {
      throw new BadCredentialsException("bad credentials");
    }
    if (saved.equals(notFoundUserEntity)) {
      throw new BadCredentialsException("bad credentials");
    }

    final ApplicationUserDetails principal = userEntityMapper.toUserDetails(saved);

    final String token = tokenGenerator.generate(principal);
    final TokenEntity savedToken = tokenRepository.save(tokenEntityMapper.fromUserEntityAndToken(saved, token));

    return tokenEntityMapper.toRS(savedToken);
  }
}
