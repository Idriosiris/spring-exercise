package com.example.springexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/healthcheck")
public class HealthCheckController {

  @GetMapping("/")
  public @ResponseBody String greeting() {
    return "Healthy!";
  }
}