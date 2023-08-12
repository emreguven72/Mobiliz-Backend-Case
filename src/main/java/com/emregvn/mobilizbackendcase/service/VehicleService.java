package com.emregvn.mobilizbackendcase.service;

import java.util.List;

import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.requests.UpdateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleTreeResponse;

public interface VehicleService {
	
	List<GetVehicleResponse> getByAuthorizations(); 
	
	List<GetVehicleTreeResponse> getVehicleTreeByCompany();
	
	void create(CreateVehicleRequest createVehicleRequest);
	
	void update(UpdateVehicleRequest updateVehicleRequest);
	
	void delete(int vehicleId);
	
}
