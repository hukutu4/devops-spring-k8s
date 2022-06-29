package com.example.springbootsecurityapp.mapper;

import com.example.springbootsecurityapp.dto.TokenRS;
import com.example.springbootsecurityapp.entity.TokenEntity;
import com.example.springbootsecurityapp.entity.UserEntity;
import com.example.springbootsecurityapp.support.security.ApplicationUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TokenEntityMapper {
  TokenRS toRS(final TokenEntity entity);

  TokenEntity fromUserEntityAndToken(final UserEntity user, final String token);

  @Mapping(target = "username", source = "user.login")
  @Mapping(target = "authorities", ignore = true)
  @Mapping(target = "accountNonLocked", ignore = true)
  @Mapping(target = "accountNonExpired", ignore = true)
  @Mapping(target = "credentialsNonExpired", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  ApplicationUserDetails toUserDetails(final TokenEntity saved);
}
