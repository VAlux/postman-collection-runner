package com.alvo.runner.checking;

public class MonitoringException extends RuntimeException {

  public MonitoringException(String message) {
    super(message);
  }

  public MonitoringException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
