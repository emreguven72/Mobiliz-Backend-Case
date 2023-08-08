package com.emregvn.mobilizbackendcase.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Company;
import com.emregvn.mobilizbackendcase.entity.Vehicle;
import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;
import com.emregvn.mobilizbackendcase.repository.CompanyRepository;
import com.emregvn.mobilizbackendcase.repository.VehicleRepository;
import com.emregvn.mobilizbackendcase.security.UserPrincipal;
import com.emregvn.mobilizbackendcase.service.VehicleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
	private final VehicleRepository vehicleRepository;
	private final CompanyRepository companyRepository;
	
	@Override
	public List<GetVehicleResponse> getAll() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		
		List<GetVehicleResponse> vehicleResponse = vehicles.stream()
				.map(vehicle -> GetVehicleResponse.builder()
						.vehicleId(vehicle.getVehicleId())
						.plateNumber(vehicle.getPlateNumber())
						.chassisNumber(vehicle.getChassisNumber())
						.label(vehicle.getLabel())
						.brand(vehicle.getBrand())
						.model(vehicle.getModel())
						.modelYear(vehicle.getModelYear())
						.group(vehicle.getGroup())
						.fleet(vehicle.getFleet())
						.build()).toList();
		
		return vehicleResponse;
	}

	@Override
	public Optional<GetVehicleResponse> getByPlateNumber(String plateNumber) {
		Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber).orElseThrow();
		
		GetVehicleResponse vehicleResponse = GetVehicleResponse.builder()
				.vehicleId(vehicle.getVehicleId())
				.plateNumber(vehicle.getPlateNumber())
				.chassisNumber(vehicle.getChassisNumber())
				.label(vehicle.getLabel())
				.brand(vehicle.getBrand())
				.model(vehicle.getModel())
				.modelYear(vehicle.getModelYear())
				.group(vehicle.getGroup())
				.fleet(vehicle.getFleet())
				.build();
		
		return Optional.of(vehicleResponse);
	}

	@Override
	public List<GetVehicleResponse> getByGroup(String group) {
		List<Vehicle> vehicles = vehicleRepository.findByGroup(group);
		
		List<GetVehicleResponse> vehicleResponse = vehicles.stream()
				.map(vehicle -> GetVehicleResponse.builder()
						.vehicleId(vehicle.getVehicleId())
						.plateNumber(vehicle.getPlateNumber())
						.chassisNumber(vehicle.getChassisNumber())
						.label(vehicle.getLabel())
						.brand(vehicle.getBrand())
						.model(vehicle.getModel())
						.modelYear(vehicle.getModelYear())
						.group(vehicle.getGroup())
						.fleet(vehicle.getFleet())
						.build()).toList();
		
		return vehicleResponse;
	}

	@Override
	public List<GetVehicleResponse> getByFleet(String fleet) {
		List<Vehicle> vehicles = vehicleRepository.findByFleet(fleet);
		
		List<GetVehicleResponse> vehicleResponse = vehicles.stream()
				.map(vehicle -> GetVehicleResponse.builder()
						.vehicleId(vehicle.getVehicleId())
						.plateNumber(vehicle.getPlateNumber())
						.chassisNumber(vehicle.getChassisNumber())
						.label(vehicle.getLabel())
						.brand(vehicle.getBrand())
						.model(vehicle.getModel())
						.modelYear(vehicle.getModelYear())
						.group(vehicle.getGroup())
						.fleet(vehicle.getFleet())
						.build()).toList();
		
		return vehicleResponse;
	}

	@Override
	public List<GetVehicleResponse> getByCompany() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = companyRepository.findByCompanyName(principal.getCompanyName()).orElseThrow();
		
		List<Vehicle> vehicles = vehicleRepository.findByCompany(company);
		
		List<GetVehicleResponse> vehicleResponse = vehicles.stream()
				.map(vehicle -> GetVehicleResponse.builder()
						.vehicleId(vehicle.getVehicleId())
						.plateNumber(vehicle.getPlateNumber())
						.chassisNumber(vehicle.getChassisNumber())
						.label(vehicle.getLabel())
						.brand(vehicle.getBrand())
						.model(vehicle.getModel())
						.modelYear(vehicle.getModelYear())
						.group(vehicle.getGroup())
						.fleet(vehicle.getFleet())
						.build()).toList();
		
		return vehicleResponse;
	}

	@Override
	public void create(CreateVehicleRequest createVehicleRequest) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = companyRepository.findByCompanyName(principal.getCompanyName()).orElseThrow();
		
		Vehicle vehicle = Vehicle.builder()
				.plateNumber(createVehicleRequest.getPlateNumber())
				.chassisNumber(createVehicleRequest.getChassisNumber())
				.label(createVehicleRequest.getLabel())
				.brand(createVehicleRequest.getBrand())
				.model(createVehicleRequest.getModel())
				.modelYear(createVehicleRequest.getModelYear())
				.group(createVehicleRequest.getGroup())
				.fleet(createVehicleRequest.getFleet())
				.company(company)
				.build();
		
		vehicleRepository.save(vehicle);
	}

}
