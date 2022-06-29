package com.example.springbootsecurityapp.mapper;

import com.example.springbootsecurityapp.dto.UserRS;
import com.example.springbootsecurityapp.entity.UserEntity;
import com.example.springbootsecurityapp.support.security.ApplicationUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface UserEntityMapper {
  List<UserRS> toRS(final List<UserEntity> entities);

  @Mapping(target = "username", source = "login")
  @Mapping(target = "authorities", ignore = true)
  @Mapping(target = "accountNonLocked", ignore = true)
  @Mapping(target = "accountNonExpired", ignore = true)
  @Mapping(target = "credentialsNonExpired", ignore = true)
  @Mapping(target = "enabled", ignore = true)
  ApplicationUserDetails toUserDetails(final UserEntity saved);
}
