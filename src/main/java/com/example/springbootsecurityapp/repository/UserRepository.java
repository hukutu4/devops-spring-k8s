package com.example.springbootsecurityapp.repository;

import com.example.springbootsecurityapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByLogin(final String username);
}
