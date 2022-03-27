package com.tejnal.springapps.exception;


public class ExternalApiIntegrationException extends RuntimeException {
  public ExternalApiIntegrationException(String message, Throwable cause ) {
    super(message, cause);
  }
}
