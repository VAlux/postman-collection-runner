
package com.alvo.runner.postman.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "raw",
    "protocol",
    "host",
    "path",
    "query"
})
public class PostmanUrl implements Serializable {

  @JsonProperty("raw")
  private String raw;
  @JsonProperty("protocol")
  private String protocol;
  @JsonProperty("host")
  private List<String> host = null;
  @JsonProperty("path")
  private List<String> path = null;
  @JsonProperty("query")
  private List<PostmanQuery> query = null;

  /**
   * No args constructor for use in serialization
   */
  public PostmanUrl() {
  }

  public PostmanUrl(String raw, String protocol, List<String> host, List<String> path, List<PostmanQuery> query) {
    super();
    this.raw = raw;
    this.protocol = protocol;
    this.host = host;
    this.path = path;
    this.query = query;
  }

  @JsonProperty("raw")
  public String getRaw() {
    return raw;
  }

  @JsonProperty("raw")
  public void setRaw(String raw) {
    this.raw = raw;
  }

  @JsonProperty("protocol")
  public String getProtocol() {
    return protocol;
  }

  @JsonProperty("protocol")
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  @JsonProperty("host")
  public List<String> getHost() {
    return host;
  }

  @JsonProperty("host")
  public void setHost(List<String> host) {
    this.host = host;
  }

  @JsonProperty("path")
  public List<String> getPath() {
    return path;
  }

  @JsonProperty("path")
  public void setPath(List<String> path) {
    this.path = path;
  }

  @JsonProperty("query")
  public List<PostmanQuery> getQuery() {
    return query;
  }

  @JsonProperty("query")
  public void setQuery(List<PostmanQuery> query) {
    this.query = query;
  }

}
