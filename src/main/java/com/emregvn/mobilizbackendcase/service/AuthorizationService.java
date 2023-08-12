package com.emregvn.mobilizbackendcase.service;

import com.emregvn.mobilizbackendcase.model.requests.CreateAuthorizationRequest;

public interface AuthorizationService {

	void create(CreateAuthorizationRequest createVehicleRequest);
	
	void delete(int authorizationId);
	
}
