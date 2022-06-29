package com.example.springbootsecurityapp.exception;

public class MediaGetException extends RuntimeException {
  public MediaGetException() {
  }

  public MediaGetException(final String message) {
    super(message);
  }

  public MediaGetException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public MediaGetException(final Throwable cause) {
    super(cause);
  }

  public MediaGetException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
