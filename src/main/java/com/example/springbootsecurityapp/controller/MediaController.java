package com.example.springbootsecurityapp.controller;

import com.example.springbootsecurityapp.dto.MediaUploadRS;
import com.example.springbootsecurityapp.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
  private final MediaService service;

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/bytes")
  public MediaUploadRS upload(@RequestBody final byte[] data) {
    return service.upload(data);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/multipart")
  public MediaUploadRS upload(@RequestParam final MultipartFile image) {
    return service.upload(image);
  }

  @GetMapping("/files/{name}")
  public ResponseEntity<byte[]> file(@PathVariable final String name) {
    return ResponseEntity.ok(service.file(name));
  }

}