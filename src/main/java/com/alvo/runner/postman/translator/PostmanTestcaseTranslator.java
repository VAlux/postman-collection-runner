package com.alvo.runner.postman.translator;

import com.alvo.runner.postman.PostmanTestCase;
import com.alvo.runner.postman.model.PostmanCollectionEvent;
import com.alvo.runner.postman.model.PostmanCollectionItem;
import com.alvo.runner.postman.model.PostmanRequest;
import com.alvo.runner.postman.model.PostmanScript;

import java.util.List;
import java.util.stream.Collectors;

public final class PostmanTestcaseTranslator {

  private PostmanTestcaseTranslator() {
  }

  public static PostmanTestCase from(PostmanCollectionItem item) {
    String name = item.getName();
    PostmanRequest request = item.getRequest();
    List<PostmanScript> scripts = item.getEvents()
        .stream()
        .map(PostmanCollectionEvent::getScript)
        .collect(Collectors.toList());

    return new PostmanTestCase(name, request, scripts);
  }
}
