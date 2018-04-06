
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "oauth2"
})
public class PostmanAuth implements Serializable {

  @JsonProperty("type")
  private String type;
  @JsonProperty("oauth2")
  private List<PostmanOauth2> oauth2 = null;

  /**
   * No args constructor for use in serialization
   */
  public PostmanAuth() {
  }

  public PostmanAuth(String type, List<PostmanOauth2> oauth2) {
    super();
    this.type = type;
    this.oauth2 = oauth2;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  @JsonProperty("oauth2")
  public List<PostmanOauth2> getOauth2() {
    return oauth2;
  }

  @JsonProperty("oauth2")
  public void setOauth2(List<PostmanOauth2> oauth2) {
    this.oauth2 = oauth2;
  }

}
