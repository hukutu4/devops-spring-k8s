package com.example.springbootsecurityapp.support.error;

import com.example.springbootsecurityapp.dto.ErrorRS;
import com.example.springbootsecurityapp.support.security.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApplicationControllerAdvice {
  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorRS error(final Throwable e) {
    log.error("error", e);
    return new ErrorRS("internal");
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorRS error(final MethodArgumentNotValidException e) {
    log.error("error", e);
    return new ErrorRS("invalid_input");
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorRS error(final ForbiddenException e) {
    log.error("error", e);
    return new ErrorRS("forbidden");
  }
}