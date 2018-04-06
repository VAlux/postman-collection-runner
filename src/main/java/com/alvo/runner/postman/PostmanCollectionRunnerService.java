package com.alvo.runner.postman;

import com.alvo.runner.checking.result.MonitoringExecutionResult;

import java.io.IOException;
import java.io.InputStream;

/**
 * Top-level service interface,
 * declaring contract for executing postman collection evaluation routine
 */
public interface PostmanCollectionRunnerService {
  /**
   * <li>Accept input stream to the postman collection</li>
   * <li>Evaluate collection</li>
   * <li>Form and return {@link MonitoringExecutionResult} as a product of collection evaluation</li>
   * @param collectionInputStream postman collection input stream
   * @return Postman collection evaluation result as a {@link MonitoringExecutionResult}
   * @throws IOException in case of problems with reading/pre-processing/parsing collection input stream
   */
  MonitoringExecutionResult executePostmanCollectionResource(InputStream collectionInputStream) throws IOException;
}
