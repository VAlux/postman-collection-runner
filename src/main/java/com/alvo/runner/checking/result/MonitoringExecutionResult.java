package com.alvo.runner.checking.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.alvo.runner.checking.TestExecutionStatus;

import java.util.Collections;
import java.util.List;

/**
 * Root of the test results data model hierarchy.
 * Serves as a container for the test results
 * with an ability to reduce all of the individual check results on each test result to single execution status value.
 */
public class MonitoringExecutionResult {

  private static final String DEFAULT_NAME = "Monitoring execution result";

  protected String name;
  protected TestExecutionStatus status;
  protected List<TestResult> tests;
  protected String message;

  public MonitoringExecutionResult() {
    // Serialization purposes
  }

  public MonitoringExecutionResult(String name, TestExecutionStatus status, List<TestResult> tests) {
    this.name = name;
    this.status = status;
    this.tests = tests;
    this.message = null;
  }

  public MonitoringExecutionResult(String name,
                                   TestExecutionStatus status,
                                   List<TestResult> tests,
                                   String message) {
    this.name = name;
    this.status = status;
    this.tests = tests;
    this.message = message;
  }

  @JsonProperty("status")
  public TestExecutionStatus getStatus() {
    return status;
  }

  @JsonProperty("tests")
  public List<TestResult> getTests() {
    return tests;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("message")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public String getMessage() {
    return message;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("status")
  public void setStatus(TestExecutionStatus status) {
    this.status = status;
  }

  @JsonProperty("tests")
  public void setTests(List<TestResult> tests) {
    this.tests = tests;
  }

  @JsonProperty("message")
  public void setMessage(String message) {
    this.message = message;
  }

  @JsonIgnore
  public static MonitoringExecutionResult empty(String serviceName) {
    return new MonitoringExecutionResult(serviceName, TestExecutionStatus.SUCCESS, Collections.emptyList());
  }

  @JsonIgnore
  public static MonitoringExecutionResult empty() {
    return new MonitoringExecutionResult(DEFAULT_NAME, TestExecutionStatus.SUCCESS, Collections.emptyList());
  }
}