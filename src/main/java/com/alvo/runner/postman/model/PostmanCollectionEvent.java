
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "listen",
    "script"
})
public class PostmanCollectionEvent implements Serializable {

  private String listen;
  private PostmanScript script;
  private String errorReason;
  private String errorResult;

  /**
   * No args constructor for use in serialization
   */
  public PostmanCollectionEvent() {
  }

  public PostmanCollectionEvent(String listen, PostmanScript script) {
    super();
    this.listen = listen;
    this.script = script;
  }

  @JsonProperty("listen")
  public String getListen() {
    return listen;
  }

  @JsonProperty("listen")
  public void setListen(String listen) {
    this.listen = listen;
  }

  @JsonProperty("script")
  public PostmanScript getScript() {
    return script;
  }

  @JsonProperty("script")
  public void setScript(PostmanScript script) {
    this.script = script;
  }

  @JsonProperty("errorReason")
  public String getErrorReason() {
    return errorReason;
  }

  @JsonProperty("errorReason")
  public void setErrorReason(String errorReason) {
    this.errorReason = errorReason;
  }

  @JsonProperty("errorResult")
  public String getErrorResult() {
    return errorResult;
  }

  @JsonProperty("errorResult")
  public void setErrorResult(String errorResult) {
    this.errorResult = errorResult;
  }
}
