package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class HelloController {

	@GetMapping("/hello")
	@ResponseBody
	public String getHello() {
		return "hello";
	}
}
