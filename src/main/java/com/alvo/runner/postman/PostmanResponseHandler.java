package com.alvo.runner.postman;

import com.alvo.runner.postman.model.PostmanResponseCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.impl.client.AbstractResponseHandler;

import java.io.IOException;

public class PostmanResponseHandler extends AbstractResponseHandler<PostmanTestScriptEvaluator> {

  private final PostmanTestScriptEvaluator evaluator;

  public PostmanResponseHandler() {
    this.evaluator = new PostmanTestScriptEvaluator();
  }

  @Override
  public PostmanTestScriptEvaluator handleResponse(HttpResponse response) throws IOException {
    handleEntity(response.getEntity());
    final StatusLine status = response.getStatusLine();
    return evaluator
        .withResponseCode(new PostmanResponseCode(status.getStatusCode(), status.getReasonPhrase(), null));
  }

  @Override
  public PostmanTestScriptEvaluator handleEntity(HttpEntity entity) throws IOException {
    return evaluator.withResponseBody(entity);
  }
}
