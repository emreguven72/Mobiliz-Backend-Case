package com.emregvn.mobilizbackendcase.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Company;
import com.emregvn.mobilizbackendcase.entity.Vehicle;
import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.requests.UpdateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;
import com.emregvn.mobilizbackendcase.repository.CompanyRepository;
import com.emregvn.mobilizbackendcase.repository.VehicleRepository;
import com.emregvn.mobilizbackendcase.security.UserPrincipal;
import com.emregvn.mobilizbackendcase.service.VehicleService;
import com.emregvn.mobilizbackendcase.service.business.VehicleServiceBusinessRules;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
	private final VehicleRepository vehicleRepository;
	private final CompanyRepository companyRepository;
	private final VehicleServiceBusinessRules vehicleServiceBusinessRules;
	
	@Override
	public Optional<GetVehicleResponse> getByPlateNumber(String plateNumber) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber).orElseThrow();
		
		vehicleServiceBusinessRules.checkIfUserAuthorized(principal, vehicle);
		
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
				.companyName(vehicle.getCompany().getCompanyName())
				.build();
		
		return Optional.of(vehicleResponse);
	}

	@Override
	public List<GetVehicleResponse> getByGroup(String group) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Vehicle> vehicles = vehicleRepository.findByGroup(group);
		
		List<Vehicle> filteredVehicles = vehicles.stream().filter(vehicle -> vehicle.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		List<GetVehicleResponse> vehicleResponse = filteredVehicles.stream()
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
						.companyName(vehicle.getCompany().getCompanyName())
						.build()).toList();
		
		
		return vehicleResponse;
	}

	@Override
	public List<GetVehicleResponse> getByFleet(String fleet) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Vehicle> vehicles = vehicleRepository.findByFleet(fleet);
		
		List<Vehicle> filteredVehicles = vehicles.stream().filter(vehicle -> vehicle.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		List<GetVehicleResponse> vehicleResponse = filteredVehicles.stream()
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
						.companyName(vehicle.getCompany().getCompanyName())
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
						.companyName(vehicle.getCompany().getCompanyName())
						.build()).toList();
		
		return vehicleResponse;
	}

	@Override
	public void create(CreateVehicleRequest createVehicleRequest) {
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

	@Override
	public void update(UpdateVehicleRequest updateVehicleRequest) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vehicle vehicle = vehicleRepository.findById(updateVehicleRequest.getVehicleId()).orElseThrow();
		
		vehicleServiceBusinessRules.checkIfUserAuthorized(principal, vehicle);
		
		if(vehicle != null) {
			vehicle.setPlateNumber(updateVehicleRequest.getPlateNumber());
			vehicle.setChassisNumber(updateVehicleRequest.getChassisNumber());
			vehicle.setLabel(updateVehicleRequest.getLabel());
			vehicle.setBrand(updateVehicleRequest.getBrand());
			vehicle.setModel(updateVehicleRequest.getModel());
			vehicle.setModelYear(updateVehicleRequest.getModelYear());
			vehicle.setGroup(updateVehicleRequest.getGroup());
			vehicle.setFleet(updateVehicleRequest.getFleet());
			vehicleRepository.save(vehicle);
		}
		
	}

	@Override
	public void delete(int vehicleId) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow();
		
		vehicleServiceBusinessRules.checkIfUserAuthorized(principal, vehicle);
		
		vehicleRepository.delete(vehicle);
		
	}

}
