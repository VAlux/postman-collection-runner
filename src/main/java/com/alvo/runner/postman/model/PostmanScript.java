
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "type",
    "exec"
})
public class PostmanScript implements Serializable {

  @JsonProperty("id")
  private String id;
  @JsonProperty("type")
  private String type;
  @JsonProperty("exec")
  private List<String> exec = null;

  /**
   * No args constructor for use in serialization
   */
  public PostmanScript() {
  }

  public PostmanScript(String id, String type, List<String> exec) {
    super();
    this.id = id;
    this.type = type;
    this.exec = exec;
  }

  @JsonProperty("id")
  public String getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }

  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  @JsonProperty("exec")
  public List<String> getExec() {
    return exec;
  }

  @JsonProperty("exec")
  public void setExec(List<String> exec) {
    this.exec = exec;
  }
}
