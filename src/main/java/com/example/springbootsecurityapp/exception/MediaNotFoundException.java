package com.example.springbootsecurityapp.exception;

public class MediaNotFoundException extends RuntimeException {
  public MediaNotFoundException() {
  }

  public MediaNotFoundException(final String message) {
    super(message);
  }

  public MediaNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public MediaNotFoundException(final Throwable cause) {
    super(cause);
  }

  public MediaNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
