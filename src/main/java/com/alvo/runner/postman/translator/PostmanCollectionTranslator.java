package com.alvo.runner.postman.translator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.alvo.runner.postman.model.PostmanCollection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class PostmanCollectionTranslator {

  private static final ObjectMapper mapper;

  private PostmanCollectionTranslator() {
    // Utility class
  }

  static {
    mapper = new ObjectMapper();
  }

  public static PostmanCollection from(InputStream inputStream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    return mapper.readValue(reader, PostmanCollection.class);
  }
}
