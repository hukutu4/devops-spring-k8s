package com.example.springbootsecurityapp.controller;

import com.example.springbootsecurityapp.dto.CardRS;
import com.example.springbootsecurityapp.service.CardService;
import com.example.springbootsecurityapp.support.security.ApplicationUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {
  private final CardService service;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<CardRS> getAll(
      @AuthenticationPrincipal ApplicationUserDetails principal
  ) {
    return service.getAll(principal);
  }
}
