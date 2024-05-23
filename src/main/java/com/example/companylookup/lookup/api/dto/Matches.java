package com.example.companylookup.lookup.api.dto;

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
