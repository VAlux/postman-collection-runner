package com.alvo.runner.checking.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.alvo.runner.checking.MonitoringException;
import com.alvo.runner.checking.CheckType;
import com.alvo.runner.checking.Either;
import com.alvo.runner.checking.TestExecutionStatus;

import java.util.function.Function;

/**
 * Result of the individual test executed on the model.
 * Execution result can be either [MonitoringException] or [Message] about successful execution.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualCheckResult {

  private String name;
  private CheckType type;
  private Either<MonitoringException, String> executionResult;
  private TestExecutionStatus status;
  private String errorReason;
  private String errorResult;
  private String message;

  public IndividualCheckResult() {
    // Serialization purposes
  }

  public IndividualCheckResult(String name, CheckType type, Either<MonitoringException, String> executionResult) {
    this.name = name;
    this.type = type;
    this.executionResult = executionResult;
    this.status = executionResult.map(exception -> TestExecutionStatus.FAILURE, msg -> TestExecutionStatus.SUCCESS);
    this.errorReason = null;
    this.errorResult = null;
    this.message = null;
  }

  public IndividualCheckResult(String name,
                               CheckType type,
                               Either<MonitoringException, String> executionResult,
                               String errorReason,
                               String errorResult) {
    this.name = name;
    this.type = type;
    this.executionResult = executionResult;
    this.errorReason = errorReason;
    this.errorResult = errorResult;
    this.status = executionResult.map(exception -> TestExecutionStatus.FAILURE, msg -> TestExecutionStatus.SUCCESS);
    this.message = executionResult.map(Throwable::getMessage, Function.identity());
  }

  @JsonProperty("status")
  public TestExecutionStatus getStatus() {
    return status;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  @JsonProperty("type")
  public CheckType getType() {
    return type;
  }

  @JsonProperty("errorReason")
  public String getErrorReason() {
    return errorReason;
  }

  @JsonProperty("errorResult")
  public String getErrorResult() {
    return errorResult;
  }

  @JsonIgnore
  public Either<MonitoringException, String> getExecutionResult() {
    return executionResult;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("type")
  public void setType(CheckType type) {
    this.type = type;
  }

  @JsonIgnore
  public void setExecutionResult(Either<MonitoringException, String> executionResult) {
    this.executionResult = executionResult;
  }

  @JsonProperty("status")
  public void setStatus(TestExecutionStatus status) {
    this.status = status;
  }

  @JsonProperty("errorReason")
  public void setErrorReason(String errorReason) {
    this.errorReason = errorReason;
  }

  @JsonProperty("errorResult")
  public void setErrorResult(String errorResult) {
    this.errorResult = errorResult;
  }

  @JsonProperty("message")
  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IndividualCheckResult that = (IndividualCheckResult) o;
    if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
    if (getType() != that.getType()) return false;
    return getStatus() != null ? getStatus().equals(that.getStatus()) : that.getStatus() == null;
  }

  @Override
  public int hashCode() {
    int result = getName() != null ? getName().hashCode() : 0;
    result = 31 * result + (getType() != null ? getType().hashCode() : 0);
    result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
    return result;
  }
}
