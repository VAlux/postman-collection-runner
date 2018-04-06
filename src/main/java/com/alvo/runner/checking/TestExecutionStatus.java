package com.alvo.runner.checking;

public enum TestExecutionStatus {
  SUCCESS(true),
  FAILURE(false);

  private final boolean statusValue;

  TestExecutionStatus(boolean statusValue) {
    this.statusValue = statusValue;
  }

  public boolean getValue() {
    return statusValue;
  }

  public static TestExecutionStatus from(boolean statusValue) {
    if (statusValue) {
      return SUCCESS;
    }
    return FAILURE;
  }
}
