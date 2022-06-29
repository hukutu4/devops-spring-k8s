package com.example.springbootsecurityapp.repository;

import com.example.springbootsecurityapp.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
}
