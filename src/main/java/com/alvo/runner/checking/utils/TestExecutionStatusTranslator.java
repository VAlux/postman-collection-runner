package com.alvo.runner.checking.utils;

import com.alvo.runner.checking.TestExecutionStatus;
import com.alvo.runner.checking.result.IndividualCheckResult;
import com.alvo.runner.checking.result.MonitoringExecutionResult;
import com.alvo.runner.checking.result.TestResult;

import java.util.List;

public class TestExecutionStatusTranslator {

  private TestExecutionStatusTranslator() {
    // Utility class
  }

  /**
   * Iterate over the each individual check result of each test result
   * and get overall test execution status.
   * E.G.: if at least one of the statuses is FAILURE -> overall status will be FAILURE.
   *
   * @return Overall test execution status.
   */
  public static TestExecutionStatus fromTestResults(List<TestResult> results) {
    final boolean overallExecutionStatus = results.stream()
        .map(TestResult::getCheckResults)
        .flatMap(List::stream)
        .map(IndividualCheckResult::getStatus)
        .allMatch(status -> status == TestExecutionStatus.SUCCESS);

    return TestExecutionStatus.from(overallExecutionStatus);
  }

  /**
   * Next level over the reduceTestExecutionResultStatus(List<TestResult>)
   * Iterate over the each individual check result of each test result of each {@link MonitoringExecutionResult}
   * and get overall test execution status.
   * E.G.: if at least one of the statuses is FAILURE -> overall status will be FAILURE.
   *
   * @return Overall test execution status.
   */
  public static TestExecutionStatus fromMonitoringExecutionResults(List<MonitoringExecutionResult> results) {
    final boolean overallExecutionStatus = results.stream()
        .map(result -> fromTestResults(result.getTests()))
        .allMatch(status -> status == TestExecutionStatus.SUCCESS);

    return TestExecutionStatus.from(overallExecutionStatus);
  }
}
