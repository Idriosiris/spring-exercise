package com.example.springexample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/healthcheck")
public class HealthCheckController {

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Healthy!";
	}

}