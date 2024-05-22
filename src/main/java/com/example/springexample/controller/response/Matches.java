package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Matches {
  @JsonProperty("title")
  private int title[];

  public int[] getTitle() {
    return title;
  }

  public void setTitle(int[] title) {
    this.title = title;
  }
}
