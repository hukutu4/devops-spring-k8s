package com.example.springbootsecurityapp.exception;

public class MediaSaveException extends RuntimeException {
  public MediaSaveException() {
  }

  public MediaSaveException(final String message) {
    super(message);
  }

  public MediaSaveException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public MediaSaveException(final Throwable cause) {
    super(cause);
  }

  public MediaSaveException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
