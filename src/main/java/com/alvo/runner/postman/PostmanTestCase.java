package com.alvo.runner.postman;

import com.alvo.runner.postman.model.PostmanRequest;
import com.alvo.runner.postman.model.PostmanScript;

import java.util.List;
import java.util.Objects;

public class PostmanTestCase {

  private final String name;
  private final PostmanRequest request;
  private final List<PostmanScript> scripts;

  public PostmanTestCase(String name, PostmanRequest request, List<PostmanScript> scripts) {
    this.name = name;
    this.request = request;
    this.scripts = scripts;
  }

  public String getName() {
    return name;
  }

  public PostmanRequest getRequest() {
    return request;
  }

  public List<PostmanScript> getScripts() {
    return scripts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostmanTestCase that = (PostmanTestCase) o;
    return Objects.equals(getName(), that.getName()) &&
        Objects.equals(getRequest(), that.getRequest()) &&
        Objects.equals(getScripts(), that.getScripts());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getRequest(), getScripts());
  }
}
