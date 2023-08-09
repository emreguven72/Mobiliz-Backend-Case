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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.requests.UpdateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;
import com.emregvn.mobilizbackendcase.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
	private final VehicleService vehicleService;
	
	@GetMapping("/get-all-by-company")
	public List<GetVehicleResponse> getAllByCompany() {
		return vehicleService.getByCompany();
	}
	
	@GetMapping("/get-by-plate-number")
	public GetVehicleResponse getByPlateNumber(@RequestParam @Validated String plateNumber) {
		return vehicleService.getByPlateNumber(plateNumber).orElseThrow();
	}
	
	@GetMapping("/get-by-region")
	public List<GetVehicleResponse> getByRegion() {
		return vehicleService.getByRegion();
	}
	
	@GetMapping("/get-by-fleet")
	public List<GetVehicleResponse> getByFleet() {
		return vehicleService.getByFleet();
	}
	
	@GetMapping("/get-by-group")
	public List<GetVehicleResponse> getByGroup() {
		return vehicleService.getByGroup();
	}
	
	@GetMapping("/get-vehicle-tree")
	public String getVehicleTreeByCompany() {
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
