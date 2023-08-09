package com.emregvn.mobilizbackendcase.service.business;

import org.springframework.stereotype.Component;

import com.emregvn.mobilizbackendcase.entity.Vehicle;
import com.emregvn.mobilizbackendcase.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehicleServiceBusinessRules {

	public void checkIfUserAuthorized(UserPrincipal principal, Vehicle vehicle) {
		if(principal.getCompanyId() != vehicle.getCompany().getCompanyId()) {
			throw new RuntimeException("You are not able to see this vehicle or do any changes on this vehicle");
		}
	}
	
}
