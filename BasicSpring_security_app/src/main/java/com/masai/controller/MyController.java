package com.masai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/Hello")
	public String getRequest()
	{
		return "Welcome to Spring security";
	}
	
	@GetMapping("/welcome")
	public String getRequestforUser()
	{
		return "Welcome to Spring security for learning purpose";
	}
	
}
