package com.emregvn.mobilizbackendcase.service;

import java.util.List;
import java.util.Optional;

import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.requests.UpdateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;

public interface VehicleService {

	Optional<GetVehicleResponse> getByPlateNumber(String plateNumber);
	
	List<GetVehicleResponse> getByCompany();
	
	List<GetVehicleResponse> getByRegion();
	
	List<GetVehicleResponse> getByFleet();
	
	List<GetVehicleResponse> getByGroup();
	
	void create(CreateVehicleRequest createVehicleRequest);
	
	void update(UpdateVehicleRequest updateVehicleRequest);
	
	void delete(int vehicleId);
	
}
