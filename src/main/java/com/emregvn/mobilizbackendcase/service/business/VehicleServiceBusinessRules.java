package com.emregvn.mobilizbackendcase.service.business;

import org.springframework.stereotype.Component;

import com.emregvn.mobilizbackendcase.entity.Group;
import com.emregvn.mobilizbackendcase.entity.Vehicle;
import com.emregvn.mobilizbackendcase.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehicleServiceBusinessRules {

	//Kullanıcının üzerinde işlem yapmaya çalıştığı araç ile kullanıcının kayıtlı olduğu şirketi karşılaştırıp ona göre işleme izin veren fonksiyon
	public void checkIfUserAuthorizedForTheCompany(UserPrincipal user, Vehicle vehicle) {
		if(user.getCompany().getCompanyId() != vehicle.getCompany().getCompanyId()) {
			throw new RuntimeException("You are not able to see this vehicle or do any changes on this vehicle");
		}
	}

	//Kullanıcının üzerinde işlem yapmaya çalıştığı araç ile kullanıcının kayıtlı olduğu şirketi karşılaştırıp ona göre işleme izin veren fonksiyon
	public void checkIfUserAuthorizedForTheGroup(UserPrincipal user, Group group) {
		if(user.getCompany().getCompanyId() != group.getCompany().getCompanyId()) {
			throw new RuntimeException("You are not able to see this vehicle or do any changes on this vehicle");
		}
	}
	
}
