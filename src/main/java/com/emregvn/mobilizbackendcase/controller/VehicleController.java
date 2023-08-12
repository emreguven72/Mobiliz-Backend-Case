package com.emregvn.mobilizbackendcase.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.requests.UpdateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleTreeResponse;
import com.emregvn.mobilizbackendcase.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
	private final VehicleService vehicleService;
	
	@GetMapping("/vehicles")
	public List<GetVehicleResponse> getByAuthorizations() {
		return vehicleService.getByAuthorizations();
	}
	
	@GetMapping("/vehicle-tree")
	public List<GetVehicleTreeResponse> getVehicleTreeByCompany() {
		return vehicleService.getVehicleTreeByCompany();
	}
	
	@PostMapping("/create")
	public void create(@RequestBody @Validated CreateVehicleRequest createVehicleRequest) {
		vehicleService.create(createVehicleRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody @Validated UpdateVehicleRequest updateVehicleRequest) {
		vehicleService.update(updateVehicleRequest);
	}
	
	@DeleteMapping("/delete/{vehicleId}")
	public void delete(@PathVariable @Validated int vehicleId) {
		vehicleService.delete(vehicleId);
	}

}
