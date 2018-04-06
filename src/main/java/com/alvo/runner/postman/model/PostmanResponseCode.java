package com.alvo.runner.postman.model;

public class PostmanResponseCode {
  private Integer code;
  private String name;
  private String detail;

  public PostmanResponseCode() {}

  public PostmanResponseCode(Integer code, String name, String detail) {
    this.code = code;
    this.name = name;
    this.detail = detail;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
}
