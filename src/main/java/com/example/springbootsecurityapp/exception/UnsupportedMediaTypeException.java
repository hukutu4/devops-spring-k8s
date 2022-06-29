package com.example.springbootsecurityapp.exception;

public class UnsupportedMediaTypeException extends RuntimeException {
  public UnsupportedMediaTypeException() {
  }

  public UnsupportedMediaTypeException(final String message) {
    super(message);
  }

  public UnsupportedMediaTypeException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public UnsupportedMediaTypeException(final Throwable cause) {
    super(cause);
  }

  public UnsupportedMediaTypeException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
