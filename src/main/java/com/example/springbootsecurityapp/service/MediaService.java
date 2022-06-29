package com.example.springbootsecurityapp.service;

import com.example.springbootsecurityapp.dto.MediaUploadRS;
import com.example.springbootsecurityapp.exception.MediaGetException;
import com.example.springbootsecurityapp.exception.MediaNotFoundException;
import com.example.springbootsecurityapp.exception.MediaSaveException;
import com.example.springbootsecurityapp.exception.UnsupportedMediaTypeException;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class MediaService {
  private final Map<String, String> mediaTypeExtensions = Map.of("image/png", ".png", "image/jpeg", ".jpg");
  private final Path mediaPath;
  private final Tika tika;

  public MediaService(@Value("${app.media-path}") final String path, Tika tika) throws IOException {
    this.mediaPath = Paths.get(path);
    this.tika = tika;

    init();
  }

  private void init() throws IOException {
    Files.createDirectories(mediaPath);
  }

  public MediaUploadRS upload(final byte[] data) {
    final String mime = tika.detect(data);
    final String extension = Optional.ofNullable(mediaTypeExtensions.get(mime))
        .orElseThrow(() -> new UnsupportedMediaTypeException(mime));

    final String name = UUID.randomUUID() + extension;
    final Path path = mediaPath.resolve(name);
    try {
      Files.write(path, data);
      return new MediaUploadRS(name);
    } catch (IOException e) {
      throw new MediaSaveException(e);
    }
  }

  public MediaUploadRS upload(final MultipartFile image) {
    try (
        final InputStream in = image.getInputStream()
    ) {
      final String mime = tika.detect(in);
      final String extension = Optional.ofNullable(mediaTypeExtensions.get(mime))
          .orElseThrow(() -> new UnsupportedMediaTypeException(mime));

      final String name = UUID.randomUUID() + extension;
      final Path path = mediaPath.resolve(name);
      image.transferTo(path);
      return new MediaUploadRS(name);
    } catch (IOException e) {
      throw new MediaSaveException(e);
    }
  }

  public byte[] file(final String name) {
    try {
      return Files.readAllBytes(mediaPath.resolve(name));
    } catch (FileNotFoundException e) {
      throw new MediaNotFoundException(e);
    } catch (IOException e) {
      throw new MediaGetException(e);
    }
  }
}