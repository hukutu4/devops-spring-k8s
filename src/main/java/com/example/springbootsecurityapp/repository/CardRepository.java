package com.example.springbootsecurityapp.repository;

import com.example.springbootsecurityapp.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, String> {
  List<CardEntity> findAllByOwner(final String owner);
}
