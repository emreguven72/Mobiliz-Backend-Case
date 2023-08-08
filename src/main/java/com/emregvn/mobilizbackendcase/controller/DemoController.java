package com.emregvn.mobilizbackendcase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.mobilizbackendcase.model.User;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
	
	@GetMapping("/public")
	public String publicMessage() {
		return "Public Message";
	}
	
	@GetMapping("/secured")
	public String securedMessage() {
		return "Secured area! if you see this message you signed in";
	}
	
	@GetMapping("/admin")
	public String adminMessage() {
		return "Admin area! if you see this message you are an admin";
	}
	
}
