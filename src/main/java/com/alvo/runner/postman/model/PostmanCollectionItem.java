
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "event",
    "request",
    "response"
})
public class PostmanCollectionItem implements Serializable {

  @JsonProperty("name")
  private String name;
  @JsonProperty("event")
  private List<PostmanCollectionEvent> events = null;
  @JsonProperty("request")
  private PostmanRequest request;
  @JsonProperty("response")
  private List<Object> response = null;

  /**
   * No args constructor for use in serialization
   */
  public PostmanCollectionItem() {
  }

  public PostmanCollectionItem(String name, List<PostmanCollectionEvent> events, PostmanRequest request, List<Object> response) {
    super();
    this.name = name;
    this.events = events;
    this.request = request;
    this.response = response;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("event")
  public List<PostmanCollectionEvent> getEvents() {
    return events;
  }

  @JsonProperty("event")
  public void setEvents(List<PostmanCollectionEvent> events) {
    this.events = events;
  }

  @JsonProperty("request")
  public PostmanRequest getRequest() {
    return request;
  }

  @JsonProperty("request")
  public void setRequest(PostmanRequest request) {
    this.request = request;
  }

  @JsonProperty("response")
  public List<Object> getResponse() {
    return response;
  }

  @JsonProperty("response")
  public void setResponse(List<Object> response) {
    this.response = response;
  }
}
