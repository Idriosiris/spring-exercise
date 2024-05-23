package com.example.companylookup.lookup.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Links {
  @JsonProperty("self")
  private String self;

  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Links links = (Links) o;
    return Objects.equals(self, links.self);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self);
  }
}
