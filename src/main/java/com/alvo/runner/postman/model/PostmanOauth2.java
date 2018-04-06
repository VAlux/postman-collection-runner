
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "value",
    "type"
})
public class PostmanOauth2 implements Serializable {

  @JsonProperty("key")
  private String key;
  @JsonProperty("value")
  private String value;
  @JsonProperty("type")
  private String type;

  /**
   * No args constructor for use in serialization
   */
  public PostmanOauth2() {
  }

  public PostmanOauth2(String key, String value, String type) {
    super();
    this.key = key;
    this.value = value;
    this.type = type;
  }

  @JsonProperty("key")
  public String getKey() {
    return key;
  }

  @JsonProperty("key")
  public void setKey(String key) {
    this.key = key;
  }

  @JsonProperty("value")
  public String getValue() {
    return value;
  }

  @JsonProperty("value")
  public void setValue(String value) {
    this.value = value;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }
}
