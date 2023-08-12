package com.emregvn.mobilizbackendcase.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.mobilizbackendcase.model.requests.CreateAuthorizationRequest;
import com.emregvn.mobilizbackendcase.service.AuthorizationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/authorization")
@RequiredArgsConstructor
public class AuthorizationController {
	private final AuthorizationService authorizationService;

	@PostMapping("/create")
	public void create(@RequestBody @Validated CreateAuthorizationRequest createAuthorizationRequest) {
		authorizationService.create(createAuthorizationRequest);
	}
	
	@DeleteMapping("/delete/{authorizationId}")
	public void delete(@PathVariable @Validated int authorizationId) {
		authorizationService.delete(authorizationId);
	}
	
}
