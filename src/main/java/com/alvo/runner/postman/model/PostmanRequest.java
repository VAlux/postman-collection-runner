
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "method",
    "header",
    "body",
    "url",
    "description"
})
public class PostmanRequest implements Serializable {

  @JsonProperty("method")
  private String method;
  @JsonProperty("header")
  private List<Object> headers = null;
  @JsonProperty("body")
  private PostmanCollectionBody body;
  @JsonProperty("url")
  private PostmanUrl url;
  @JsonProperty("description")
  private String description;

  /**
   * No args constructor for use in serialization
   */
  public PostmanRequest() {
  }

  public PostmanRequest(String method, List<Object> headers, PostmanCollectionBody body, PostmanUrl url, String description) {
    super();
    this.method = method;
    this.headers = headers;
    this.body = body;
    this.url = url;
    this.description = description;
  }

  @JsonProperty("method")
  public String getMethod() {
    return method;
  }

  @JsonProperty("method")
  public void setMethod(String method) {
    this.method = method;
  }

  @JsonProperty("header")
  public List<Object> getHeaders() {
    return headers;
  }

  @JsonProperty("header")
  public void setHeaders(List<Object> headers) {
    this.headers = headers;
  }

  @JsonProperty("body")
  public PostmanCollectionBody getBody() {
    return body;
  }

  @JsonProperty("body")
  public void setBody(PostmanCollectionBody body) {
    this.body = body;
  }

  @JsonProperty("url")
  public PostmanUrl getUrl() {
    return url;
  }

  @JsonProperty("url")
  public void setUrl(PostmanUrl url) {
    this.url = url;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }
}
