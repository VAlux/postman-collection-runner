package com.alvo.runner.postman;

import com.alvo.runner.checking.MonitoringException;
import com.alvo.runner.http.HttpRequestExecutor;
import com.alvo.runner.checking.CheckType;
import com.alvo.runner.checking.Either;
import com.alvo.runner.checking.TestExecutionStatus;
import com.alvo.runner.checking.result.IndividualCheckResult;
import com.alvo.runner.checking.result.MonitoringExecutionResult;
import com.alvo.runner.checking.result.TestResult;
import com.alvo.runner.checking.utils.TestExecutionStatusTranslator;
import com.alvo.runner.postman.model.PostmanCollection;
import com.alvo.runner.postman.model.PostmanScript;
import com.alvo.runner.postman.translator.HttpRequestBaseTranslator;
import com.alvo.runner.postman.translator.PostmanCollectionTranslator;
import com.alvo.runner.postman.translator.PostmanTestcaseTranslator;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class PostmanCollectionRunnerServiceImpl implements PostmanCollectionRunnerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostmanCollectionRunnerServiceImpl.class);
  private static final int CONNECTION_TIMEOUT = 5000;

  private final HttpRequestExecutor httpRequestExecutor;
  private final RequestConfig defaultRequestConfig;
  private final PostmanResponseHandler responseHandler;

  public PostmanCollectionRunnerServiceImpl(HttpRequestExecutor executor) {
    this.httpRequestExecutor = executor;
    this.defaultRequestConfig = RequestConfig.custom()
        .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
        .setSocketTimeout(CONNECTION_TIMEOUT)
        .setConnectTimeout(CONNECTION_TIMEOUT)
        .build();

    this.responseHandler = new PostmanResponseHandler();
  }

  @Override
  public MonitoringExecutionResult executePostmanCollectionResource(InputStream collectionInputStream)
      throws IOException {

    return executePostmanCollection(PostmanCollectionTranslator.from(collectionInputStream));
  }

  public MonitoringExecutionResult executePostmanCollection(PostmanCollection collection) {
    List<TestResult> testResults = collection.getItems()
        .stream()
        .map(item -> executePostmanTestcase(PostmanTestcaseTranslator.from(item)))
        .flatMap(testResult -> testResult.map(Stream::of).orElseGet(Stream::empty))
        .collect(Collectors.toList());

    String collectionName = collection.getInfo().getName();
    TestExecutionStatus executionStatus = TestExecutionStatusTranslator.fromTestResults(testResults);
    return new MonitoringExecutionResult(collectionName, executionStatus, testResults);
  }

  /**
   * Execute single {@link PostmanTestCase}
   * <li>Form a {@link HttpRequestBase} from the raw url in the testcase</li>
   * <li>Evaluate test case scripts with {@link #evaluateTestCaseScripts(PostmanTestCase, HttpRequestBase)}</li>
   * <li>Return evaluation result as {@link TestResult}</li>
   * @param testCase Target postman testcase to execute
   * @return Postman test case scripts evaluation result.
   */
  private Optional<TestResult> executePostmanTestcase(PostmanTestCase testCase) {
    try {
      return HttpRequestBaseTranslator
          .from(testCase.getRequest())
          .flatMap(requestBase -> evaluateTestCaseScripts(testCase, requestBase));
    } catch (UnsupportedEncodingException | URISyntaxException | MalformedURLException e) {
      LOGGER.error("Malformed URL occurred in Postman test request: {}", e);
      return Optional.empty();
    }
  }

  /**
   * Perform evaluation of individual postman test case scripts
   * <li>Execute the test http request and obtain the {@link PostmanTestScriptEvaluator} as a result</li>
   * <li>Evaluate test case scripts with {@link #evaluateTestCaseScripts(PostmanTestCase, PostmanTestScriptEvaluator)}</li>
   * <li>Form evaluation result as a list of {@link IndividualCheckResult}</li>
   * <li>Pack and return evaluation result as {@link TestResult}</li>
   * @param testCase Target postman test case for evaluation
   * @param requestBase Postman test case http request base
   * @return Postman test case scripts evaluation result.
   */
  private Optional<TestResult> evaluateTestCaseScripts(PostmanTestCase testCase, HttpRequestBase requestBase) {
    requestBase.setConfig(defaultRequestConfig);
    try {
      PostmanTestScriptEvaluator evaluator = httpRequestExecutor.execute(requestBase, responseHandler);
      evaluateTestCaseScripts(testCase, evaluator);

      List<IndividualCheckResult> checkResults = evaluator.getTestResults().entrySet()
          .stream()
          .map(testResult -> createIndividualCheckResult(testResult.getKey(), (Boolean) testResult.getValue()))
          .collect(Collectors.toList());

      return Optional.of(new TestResult(testCase.hashCode(), checkResults));
    } catch (IOException e) {
      LOGGER.error("Error executing postman test request: {}", e);
      return Optional.empty();
    }
  }

  /**
   * Evaluate postman test case scripts using the {@link PostmanTestScriptEvaluator}
   * Evaluation process is a process of executing pieces of postman test js code in the
   * js sandbox context using Nashorn js engine implementation.
   * Results are stored in the 'tests' variable inside the js sandbox context encapsulated in the evaluator.
   * @param testCase Postman test case for evaluation.
   * @param evaluator Initialized Postman js sandbox running environment for test scripts evaluation.
   */
  private void evaluateTestCaseScripts(PostmanTestCase testCase, PostmanTestScriptEvaluator evaluator) {
    testCase.getScripts().forEach(script -> {
      try {
        evaluator.evaluate(extractScriptContent(script));
      } catch (ScriptException e) {
        LOGGER.error("Error executing postman test script: {}", e);
      }
    });
  }

  /**
   * Build {@link IndividualCheckResult} basing on the name of the testcase
   * and the result of individual test script evaluation.
   * @param testName Name of the postman test case.
   * @param isSuccessful Defines whether the postman test case script evaluation is successful.
   * @return result of individual test script evaluation as {@link IndividualCheckResult}
   */
  private IndividualCheckResult createIndividualCheckResult(String testName, Boolean isSuccessful) {
    IndividualCheckResult check;
    if (isSuccessful) {
      check = new IndividualCheckResult(testName, CheckType.CONFORMITY, Either.right(isSuccessful.toString()));
    } else {
      MonitoringException monitoringException = new MonitoringException(String.format("Test %s failed", testName));
      check = new IndividualCheckResult(testName, CheckType.CONFORMITY, Either.left(monitoringException));
    }
    return check;
  }

  /**
   * Scripts in postman test collection are stored as the json array of strings.
   * This is a shortcut for merging it in single string to be ready for evaluation in js sandbox.
   * @param script Postman script for processing.
   * @return Processed postman test script as a single string object.
   */
  private String extractScriptContent(PostmanScript script) {
    return script.getExec().stream().collect(Collectors.joining("\n"));
  }
}