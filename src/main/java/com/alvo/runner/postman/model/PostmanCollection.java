
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "info",
    "item",
    "auth",
    "event"
})
public class PostmanCollection implements Serializable {

  @JsonProperty("info")
  private PostmanCollectionInfo info;
  @JsonProperty("item")
  private List<PostmanCollectionItem> items = null;
  @JsonProperty("auth")
  private PostmanAuth auth;
  @JsonProperty("event")
  private List<PostmanCollectionEvent> events = null;

  /**
   * No args constructor for use in serialization
   */
  public PostmanCollection() {
  }

  public PostmanCollection(PostmanCollectionInfo info, List<PostmanCollectionItem> items, PostmanAuth auth, List<PostmanCollectionEvent> events) {
    super();
    this.info = info;
    this.items = items;
    this.auth = auth;
    this.events = events;
  }

  @JsonProperty("info")
  public PostmanCollectionInfo getInfo() {
    return info;
  }

  @JsonProperty("info")
  public void setInfo(PostmanCollectionInfo info) {
    this.info = info;
  }

  @JsonProperty("item")
  public List<PostmanCollectionItem> getItems() {
    return items;
  }

  @JsonProperty("item")
  public void setItems(List<PostmanCollectionItem> items) {
    this.items = items;
  }

  @JsonProperty("auth")
  public PostmanAuth getAuth() {
    return auth;
  }

  @JsonProperty("auth")
  public void setAuth(PostmanAuth auth) {
    this.auth = auth;
  }

  @JsonProperty("event")
  public List<PostmanCollectionEvent> getEvents() {
    return events;
  }

  @JsonProperty("event")
  public void setEvents(List<PostmanCollectionEvent> events) {
    this.events = events;
  }

}
