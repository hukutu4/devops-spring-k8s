package com.example.springbootsecurityapp.service;

import com.example.springbootsecurityapp.dto.CardRS;
import com.example.springbootsecurityapp.entity.CardEntity;
import com.example.springbootsecurityapp.mapper.CardEntityMapper;
import com.example.springbootsecurityapp.repository.CardRepository;
import com.example.springbootsecurityapp.support.security.ApplicationUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CardService {
  private final CardRepository cardRepository;
  private final CardEntityMapper cardEntityMapper;

  public List<CardRS> getAll(final ApplicationUserDetails principal) {
    final List<CardEntity> entities = cardRepository.findAllByOwner(principal.getUsername());
    return cardEntityMapper.toRS(entities);
  }
}
