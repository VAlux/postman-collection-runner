package com.alvo.runner.checking.utils;

import com.alvo.runner.checking.MonitoringException;
import com.alvo.runner.checking.result.IndividualCheckResult;
import com.alvo.runner.checking.result.MonitoringExecutionResult;
import com.alvo.runner.checking.result.TestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Some useful tools for manipulations with {@link TestResult} data.
 */
public final class TestResultProcessor {

  private TestResultProcessor() { }

  /**
   * Obtain  distinct list of {@link TestResult} from a given list of {@link TestResult}.
   * Usage intend: for deduplication of the list of {@link TestResult}
   *
   * @param results non-distinct source list of {@link TestResult}.
   * @return list of test results without repetitions.
   */
  public static List<TestResult> distinctTestResults(List<TestResult> results) {
    return results.stream()
        .distinct()
        .collect(Collectors.toList());
  }

  /**
   * Obtain the {@link MonitoringExecutionResult} with distinct list of {@link TestResult}.
   * <br>Usage intend: for deduplication of the {@link TestResult} list of the {@link MonitoringExecutionResult}
   *
   * @param result source {@link MonitoringExecutionResult} with non-distinct list of {@link TestResult}.
   * @return {@link MonitoringExecutionResult} with list of {@link TestResult} without repetitions.
   */
  public static MonitoringExecutionResult distinctTestResults(MonitoringExecutionResult result) {
    return new MonitoringExecutionResult(result.getName(), result.getStatus(), distinctTestResults(result.getTests()));
  }

  /**
   * Extract the list of {@link MonitoringException} from the {@link MonitoringExecutionResult}
   *
   * @param result {@link MonitoringExecutionResult} with the payload of the executed tests.
   * @return extracted list of {@link MonitoringException} from specified {@link MonitoringExecutionResult}
   */
  public static List<MonitoringException> extractMonitoringExceptions(MonitoringExecutionResult result) {
    List<MonitoringException> errors = new ArrayList<>();

    result.getTests().stream()
        .flatMap(t -> t.getCheckResults().stream())
        .map(IndividualCheckResult::getExecutionResult)
        .forEach(e -> e.apply(errors::add, msg -> { /*skip non-error payload*/ }));

    return errors;
  }

  /**
   * Extract the list of {@link IndividualCheckResult} from the {@link MonitoringExecutionResult}
   * @param result {@link MonitoringExecutionResult} with the payload of the executed tests.
   * @return extracted list of {@link IndividualCheckResult} from specified {@link MonitoringExecutionResult}
   */
  public static List<IndividualCheckResult> extractIndividualCheckResults(MonitoringExecutionResult result) {
    return result.getTests()
        .stream()
        .flatMap(testResult -> testResult.getCheckResults().stream())
        .collect(Collectors.toList());
  }
}
