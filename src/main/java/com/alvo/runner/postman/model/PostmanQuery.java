
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "value",
    "equals",
    "warning",
    "disabled"
})
public class PostmanQuery implements Serializable {

  @JsonProperty("key")
  private String key;
  @JsonProperty("value")
  private String value;
  @JsonProperty("equals")
  private Boolean equals;
  @JsonProperty("warning")
  private String warning;
  @JsonProperty("disabled")
  private Boolean disabled;

  /**
   * No args constructor for use in serialization
   */
  public PostmanQuery() {
  }

  public PostmanQuery(String key, String value, Boolean equals, String warning, Boolean disabled) {
    super();
    this.key = key;
    this.value = value;
    this.equals = equals;
    this.warning = warning;
    this.disabled = disabled;
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

  @JsonProperty("equals")
  public Boolean getEquals() {
    return equals;
  }

  @JsonProperty("equals")
  public void setEquals(Boolean equals) {
    this.equals = equals;
  }

  @JsonProperty("warning")
  public String getWarning() {
    return warning;
  }

  @JsonProperty("warning")
  public void setWarning(String warning) {
    this.warning = warning;
  }

  @JsonProperty("disabled")
  public Boolean getDisabled() {
    return disabled;
  }

  @JsonProperty("disabled")
  public void setDisabled(Boolean disabled) {
    this.disabled = disabled;
  }
}
