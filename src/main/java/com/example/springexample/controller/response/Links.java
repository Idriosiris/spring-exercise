package com.example.springexample.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links {
    @JsonProperty("self")
    private String self;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
