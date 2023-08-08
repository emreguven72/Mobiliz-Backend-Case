package com.emregvn.mobilizbackendcase.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
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
	
	@PostMapping("/create")
	public void create(@RequestBody @Validated CreateVehicleRequest createVehicleRequest) {
		vehicleService.create(createVehicleRequest);
	}

}
