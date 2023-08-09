package com.emregvn.mobilizbackendcase.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Company;
import com.emregvn.mobilizbackendcase.entity.Fleet;
import com.emregvn.mobilizbackendcase.entity.Group;
import com.emregvn.mobilizbackendcase.entity.Region;
import com.emregvn.mobilizbackendcase.entity.Vehicle;
import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.requests.UpdateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;
import com.emregvn.mobilizbackendcase.repository.VehicleRepository;
import com.emregvn.mobilizbackendcase.security.UserPrincipal;
import com.emregvn.mobilizbackendcase.service.CompanyService;
import com.emregvn.mobilizbackendcase.service.FleetService;
import com.emregvn.mobilizbackendcase.service.GroupService;
import com.emregvn.mobilizbackendcase.service.RegionService;
import com.emregvn.mobilizbackendcase.service.VehicleService;
import com.emregvn.mobilizbackendcase.service.business.VehicleServiceBusinessRules;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
	private final VehicleRepository vehicleRepository;
	private final CompanyService companyService;
	private final RegionService regionService;
	private final FleetService fleetService;
	private final GroupService groupService;
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
				.companyName(vehicle.getCompany().getCompanyName())
				.regionName(vehicle.getRegion().getName())
				.fleetName(vehicle.getFleet().getName())
				.groupName(vehicle.getGroup().getName())
				.build();
		
		return Optional.of(vehicleResponse);
	}

	@Override
	public List<GetVehicleResponse> getByGroup() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Group> group = groupService.getByName(principal.getGroupAuthorizations().get(0))
				.stream().filter(g -> g.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		List<Vehicle> vehicles = vehicleRepository.findByGroup(group.get(0));
		
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
						.companyName(vehicle.getCompany().getCompanyName())
						.regionName(vehicle.getRegion().getName())
						.fleetName(vehicle.getFleet().getName())
						.groupName(vehicle.getGroup() == null ? null : vehicle.getGroup().getName())
						.build()).toList();
		
		return vehicleResponse;
	}

	@Override
	public List<GetVehicleResponse> getByFleet() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Fleet> fleet = fleetService.getByName(principal.getFleetAuthorizations().get(0))
				.stream().filter(f -> f.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		List<Vehicle> vehicles = vehicleRepository.findByFleet(fleet.get(0));
		
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
						.companyName(vehicle.getCompany().getCompanyName())
						.regionName(vehicle.getRegion().getName())
						.fleetName(vehicle.getFleet().getName())
						.groupName(vehicle.getGroup() == null ? null : vehicle.getGroup().getName())
						.build()).toList();
		
		return vehicleResponse;
	}
	
	@Override
	public List<GetVehicleResponse> getByRegion() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Region> region = regionService.getByName(principal.getRegionAuthorizations().get(0))
				.stream().filter(r -> r.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		List<Vehicle> vehicles = vehicleRepository.findByRegion(region.get(0));
		
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
						.companyName(vehicle.getCompany().getCompanyName())
						.regionName(vehicle.getRegion().getName())
						.fleetName(vehicle.getFleet().getName())
						.groupName(vehicle.getGroup() == null ? null : vehicle.getGroup().getName())
						.build()).toList();
		
		return vehicleResponse;
	}

	@Override
	public List<GetVehicleResponse> getByCompany() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = companyService.getByCompanyName(principal.getCompanyName()).orElseThrow();
		
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
						.companyName(vehicle.getCompany().getCompanyName())
						.regionName(vehicle.getRegion() == null ? null : vehicle.getRegion().getName())
						.fleetName(vehicle.getFleet() == null ? null : vehicle.getFleet().getName())
						.groupName(vehicle.getGroup() == null ? null : vehicle.getGroup().getName())
						.build()).toList();
		
		return vehicleResponse;
	}
	
	@Override
	public String getVehicleTreeByCompany() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = companyService.getByCompanyName(principal.getCompanyName()).orElseThrow();
		
		System.out.print(company.getCompanyName() + "\r");
		company.getRegions().forEach(r -> {
			System.out.print(" |-" + r.getName() + "\r");
			r.getVehicles().forEach(v -> {
				if(v.getFleet() == null && v.getGroup() == null) {
					System.out.print(" |   |-" + v.getPlateNumber() + "\r");
				}
			});
			r.getFleets().forEach(f -> {
				System.out.print(" |   |-" + f.getName() + "\r");
				f.getVehicles().forEach(v -> {
					if(v.getGroup() == null) {
						System.out.print(" |      |-" + v.getPlateNumber() + "\r");
					}
				});
				f.getGroups().forEach(g -> {
					System.out.print(" |      |-" + g.getName() + "\r");
					g.getVehicles().forEach(v -> {
						System.out.print(" |         |-" + v.getPlateNumber() + "\r");
					});
				});
			});
		});
		company.getVehicles().forEach(v -> {
			if(v.getRegion() == null && v.getFleet() == null && v.getGroup() == null) {
				System.out.print(" |-" + v.getPlateNumber() + "\r");
			}
		});
		System.out.print("\r");
		
		
		
//		Mulakat A.S.
//		 |-Marmara
//		 |   |-Istanbul Filo
//		 |      |-Avrupa Grubu
//		 |         |-34BE030
//		 |		   |-34EM141
//		 |   |-Edirne Filo
//		 |      |-08EC421
//		 |      |-08AB576
//		 |-Ege
//		    |-27AB321
		
		String vehicleTree = """
			%s
			 |-%s
			 |   |-%s
			 |      |-%s
			 |         |-34BE030
			 |		   |-34EM141
			 |   |-Edirne Filo
			 |      |-08EC421
			 |      |-08AB576
			 |-Ege
			    |-27AB321
		""";
		
		return String.format(vehicleTree, "Mulakat A.S.", "Marmara", "Istanbul Filo", "Avrupa Grubu");
	}

	@Override
	public void create(CreateVehicleRequest createVehicleRequest) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = companyService.getByCompanyName(principal.getCompanyName()).orElseThrow();
		
		List<Region> region = regionService.getByName(createVehicleRequest.getRegionName())
				.stream().filter(r -> r.getCompany().getCompanyId() == principal.getCompanyId()).toList();
				
		List<Fleet> fleet = fleetService.getByName(createVehicleRequest.getFleetName())
				.stream().filter(f -> f.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		List<Group> group = groupService.getByName(createVehicleRequest.getGroupName())
				.stream().filter(g -> g.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		Vehicle vehicle = Vehicle.builder()
				.plateNumber(createVehicleRequest.getPlateNumber())
				.chassisNumber(createVehicleRequest.getChassisNumber())
				.label(createVehicleRequest.getLabel())
				.brand(createVehicleRequest.getBrand())
				.model(createVehicleRequest.getModel())
				.modelYear(createVehicleRequest.getModelYear())
				.company(company)
				.region(region.isEmpty() ? null : region.get(0))
				.fleet(fleet.isEmpty() ? null : fleet.get(0))
				.group(group.isEmpty() ? null : group.get(0))
				.build();
		
		vehicleRepository.save(vehicle);
	}

	@Override
	public void update(UpdateVehicleRequest updateVehicleRequest) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vehicle vehicle = vehicleRepository.findById(updateVehicleRequest.getVehicleId()).orElseThrow();
		
		List<Region> region = regionService.getByName(updateVehicleRequest.getRegionName())
				.stream().filter(r -> r.getCompany().getCompanyId() == principal.getCompanyId()).toList();
				
		List<Fleet> fleet = fleetService.getByName(updateVehicleRequest.getFleetName())
				.stream().filter(f -> f.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		List<Group> group = groupService.getByName(updateVehicleRequest.getGroupName())
				.stream().filter(g -> g.getCompany().getCompanyId() == principal.getCompanyId()).toList();
		
		vehicleServiceBusinessRules.checkIfUserAuthorized(principal, vehicle);
		
		if(vehicle != null) {
			vehicle.setPlateNumber(updateVehicleRequest.getPlateNumber());
			vehicle.setChassisNumber(updateVehicleRequest.getChassisNumber());
			vehicle.setLabel(updateVehicleRequest.getLabel());
			vehicle.setBrand(updateVehicleRequest.getBrand());
			vehicle.setModel(updateVehicleRequest.getModel());
			vehicle.setModelYear(updateVehicleRequest.getModelYear());
			vehicle.setRegion(region.isEmpty() ? null : region.get(0));
			vehicle.setFleet(fleet.isEmpty() ? null : fleet.get(0));
			vehicle.setGroup(group.isEmpty() ? null : group.get(0));
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
