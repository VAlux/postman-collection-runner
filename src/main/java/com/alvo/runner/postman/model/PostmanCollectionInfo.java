
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "_postman_id",
    "description",
    "schema"
})
public class PostmanCollectionInfo implements Serializable {

  @JsonProperty("name")
  private String name;
  @JsonProperty("_postman_id")
  private String postmanId;
  @JsonProperty("description")
  private String description;
  @JsonProperty("schema")
  private String schema;

  /**
   * No args constructor for use in serialization
   */
  public PostmanCollectionInfo() {
  }

  public PostmanCollectionInfo(String name, String postmanId, String description, String schema) {
    super();
    this.name = name;
    this.postmanId = postmanId;
    this.description = description;
    this.schema = schema;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("_postman_id")
  public String getPostmanId() {
    return postmanId;
  }

  @JsonProperty("_postman_id")
  public void setPostmanId(String postmanId) {
    this.postmanId = postmanId;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  @JsonProperty("description")
  public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("schema")
  public String getSchema() {
    return schema;
  }

  @JsonProperty("schema")
  public void setSchema(String schema) {
    this.schema = schema;
  }

}
