package com.alvo.runner.checking.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Basically a container for the list of individual check checkResults,
 * which were executed on the single specific model.
 */
public class TestResult {

  private Integer id;
  private List<IndividualCheckResult> checkResults;

  public TestResult() {
    // Serialization purposes
  }

  public TestResult(Integer id, List<IndividualCheckResult> checkResults) {
    this.id = id;
    this.checkResults = checkResults;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("checks")
  public List<IndividualCheckResult> getCheckResults() {
    return checkResults;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("checks")
  public void setCheckResults(List<IndividualCheckResult> checkResults) {
    this.checkResults = checkResults;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TestResult that = (TestResult) o;
    return Objects.equals(getCheckResults(), that.getCheckResults());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCheckResults());
  }
}
