package com.example.springbootsecurityapp.controller;

import com.example.springbootsecurityapp.dto.TokenRS;
import com.example.springbootsecurityapp.dto.UserRS;
import com.example.springbootsecurityapp.service.UserService;
import com.example.springbootsecurityapp.support.security.ApplicationUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService service;

  @GetMapping
  @PreAuthorize("isAuthenticated()")
  public List<UserRS> getAll(
      @AuthenticationPrincipal ApplicationUserDetails principal,
      @RequestParam(defaultValue = "0") int page
  ) {
    return service.getAll(principal, page);
  }

  @PostMapping("/login")
  @PreAuthorize("isAnonymous()")
  public TokenRS login(@RequestParam final String username, @RequestParam final String password) {
    return service.login(username, password);
  }
}
