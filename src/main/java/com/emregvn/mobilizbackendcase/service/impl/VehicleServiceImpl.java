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
	//Dependency Injection
	private final VehicleRepository vehicleRepository;
	private final CompanyService companyService;
	private final RegionService regionService;
	private final FleetService fleetService;
	private final GroupService groupService;
	private final VehicleServiceBusinessRules vehicleServiceBusinessRules;
	String globalVehicleTree = "";
	
	//Kullanıcının verdiği plaka numarasına ait aracı bulup kullanıcıya döndüren fonksiyon.
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

	//Önce kullanıcının hangi gruplarda yetkili olduğunu bulup sonrasında bu gruplara ait tüm araçların kayıtlarını döndüren fonksiyon.
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

	//Önce kullanıcının hangi filolarda yetkili olduğunu bulup sonrasında bu filolara ait tüm araçların kayıtlarını döndüren fonksiyon.
	@Override
	public List<GetVehicleResponse> getByFleet() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Fleet> authorizedFleets = new ArrayList<Fleet>();
		principal.getFleetAuthorizations().forEach(fleetText -> {
			List<Fleet> fleet = fleetService.getByName(fleetText)
					.stream().filter(r -> r.getCompany().getCompanyId() == principal.getCompanyId()).toList();
			authorizedFleets.add(fleet.get(0));
		});
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		authorizedFleets.forEach(fleet -> {
			vehicleRepository.findByFleet(fleet).forEach(v -> {
				vehicles.add(v);
			});
		});
		
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
						.regionName(vehicle.getRegion().getName())
						.fleetName(vehicle.getFleet().getName())
						.groupName(vehicle.getGroup() == null ? null : vehicle.getGroup().getName())
						.build()).toList();
		
		return vehicleResponse;
	}
	
	//Önce kullanıcının hangi bölgelerde yetkili olduğunu bulup sonrasında bu bölgelere ait tüm araçların kayıtlarını döndüren fonksiyon.
	@Override
	public List<GetVehicleResponse> getByRegion() {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<Region> authorizedRegions = new ArrayList<Region>();
		principal.getRegionAuthorizations().forEach(regionText -> {
			List<Region> region = regionService.getByName(regionText)
					.stream().filter(r -> r.getCompany().getCompanyId() == principal.getCompanyId()).toList();
			authorizedRegions.add(region.get(0));
		});
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		authorizedRegions.forEach(region -> {
			vehicleRepository.findByRegion(region).forEach(v -> {
				vehicles.add(v);
			});
		});
		
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
						.regionName(vehicle.getRegion().getName())
						.fleetName(vehicle.getFleet().getName())
						.groupName(vehicle.getGroup() == null ? null : vehicle.getGroup().getName())
						.build()).toList();
		
		return vehicleResponse;
	}

	//Kullanıcının hangi şirkete kayıtlı olduğunu bulduktan sonra o şirkete ait tüm araçları döndüren fonksiyon.
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
	
	//Önce kullanıcının hangi bölgelerde,filolarda,gruplarda ve araçlarda yetkili olduğunu bulup sonrasında bu bilgileri 
	//bir ağaç yapısına dönüştürüp bir String içerisinde tutan ve kullanıcıya döndüren fonksiyon.
	@Override
	public String getVehicleTreeByCompany() {
		//202 - 203 satırları arasında hangi kullanıcının giriş yaptığını(Authentication) SecurityContextHolder nesnesinden çekip
		//kullanıcının tüm bilgilerini ve ayrıca kayıtlı olduğu şirkete dair tüm bilgileri buluyorum.
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = companyService.getByCompanyName(principal.getCompanyName()).orElseThrow();
		
		//207 - 233 satırları arasında request'in X-User headerı içerisinden çektiğim değerleri kullanarak kullanıcının
		//bölge,filo,grup ve araçlardan hangilerinde yetkili olduğunu buluyorum.
		List<Region> authorizedRegions = new ArrayList<Region>();
		principal.getRegionAuthorizations().forEach(regionText -> {
			List<Region> region = regionService.getByName(regionText)
					.stream().filter(r -> r.getCompany().getCompanyId() == principal.getCompanyId()).toList();
			authorizedRegions.add(region.get(0));
		});
		
		List<Fleet> authorizedFleets = new ArrayList<Fleet>();
		principal.getFleetAuthorizations().forEach(fleetText -> {
			List<Fleet> fleet = fleetService.getByName(fleetText)
					.stream().filter(f -> f.getCompany().getCompanyId() == principal.getCompanyId()).toList();
			authorizedFleets.add(fleet.get(0));
		});
		
		List<Group> authorizedGroups = new ArrayList<Group>();
		principal.getGroupAuthorizations().forEach(groupText -> {
			List<Group> group = groupService.getByName(groupText)
					.stream().filter(g -> g.getCompany().getCompanyId() == principal.getCompanyId()).toList();
			authorizedGroups.add(group.get(0));
		});
		
		List<Vehicle> authorizedVehicles = new ArrayList<Vehicle>();
		principal.getVehicleAuthorizations().forEach(vehicleText -> {
			List<Vehicle> vehicle = vehicleRepository.findByPlateNumber(vehicleText)
					.stream().filter(v -> v.getCompany().getCompanyId() == principal.getCompanyId()).toList();
			authorizedVehicles.add(vehicle.get(0));
		});
		
		//237 - 275 satırları arasında yukarıda bulduğum bölge,filo,grup,araç yetkilendirmelerini kullanarak kullanıcının
		//hangi araç kayıtlarına erişebileceğini belirledikten sonra bu araç kayıtlarını bir ağaç şeklinde kullanıcıya döndürüyorum.
		globalVehicleTree += company.getCompanyName() + "\r";
		company.getRegions().forEach(r -> {
			boolean isUserAuthorizedToThisRegion = !r.getVehicles().isEmpty() && (principal.getRole().equals("CompanyAdmin") || authorizedRegions.contains(r));
			if(isUserAuthorizedToThisRegion) {
				globalVehicleTree += " |-" + r.getName() + "\r";
			}
			r.getVehicles().forEach(v -> {
				if(v.getFleet() == null && v.getGroup() == null && (principal.getRole().equals("CompanyAdmin") || authorizedRegions.contains(r) || authorizedVehicles.contains(v))) {
					globalVehicleTree += " |   |-" + v.getPlateNumber() + "\r";
				}
			});
			r.getFleets().forEach(f -> {
				boolean isUserAuthorizedToThisFleet = !f.getVehicles().isEmpty() && (principal.getRole().equals("CompanyAdmin") || authorizedRegions.contains(r) || authorizedFleets.contains(f));
				if(isUserAuthorizedToThisFleet) {					
					globalVehicleTree += " |   |-" + f.getName() + "\r";
				}
				f.getVehicles().forEach(v -> {
					if(v.getGroup() == null && (principal.getRole().equals("CompanyAdmin") || authorizedRegions.contains(r) || authorizedFleets.contains(f) || authorizedVehicles.contains(v))) {
						globalVehicleTree += " |      |-" + v.getPlateNumber() + "\r";
					}
				});
				f.getGroups().forEach(g -> {
					boolean isUserAuthorizedToThisGroup = !g.getVehicles().isEmpty() && (principal.getRole().equals("CompanyAdmin") || authorizedRegions.contains(r) || authorizedFleets.contains(f) || authorizedGroups.contains(g));
					if(isUserAuthorizedToThisGroup) {						
						globalVehicleTree += " |      |-" + g.getName() + "\r";
					}
					g.getVehicles().forEach(v -> {
						if(principal.getRole().equals("CompanyAdmin") || authorizedRegions.contains(r) || authorizedFleets.contains(f) || authorizedGroups.contains(g) || authorizedVehicles.contains(v)) {
							globalVehicleTree += " |         |-" + v.getPlateNumber() + "\r";
						}
					});
				});
			});
		});
		company.getVehicles().forEach(v -> {
			if(v.getRegion() == null && v.getFleet() == null && v.getGroup() == null && principal.getRole().equals("CompanyAdmin")) {
				globalVehicleTree += " |-" + v.getPlateNumber() + "\r";
			}
		});
		
		String vehicleTree = globalVehicleTree;
		globalVehicleTree = "";
		
		return vehicleTree;
	}

	//Kullanıcının yeni araç yaratmasını sağlayan fonksiyon.
	//WebSecurityConfiguration classında yazdığım kodlar sayesinde buraya sadece CompanyAdmin rolü bulunan kullanıcılar erişebilir.
	@Override
	public void create(CreateVehicleRequest createVehicleRequest) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = companyService.getByCompanyName(principal.getCompanyName()).orElseThrow();
		
		//293 - 300 satırları arasında yaratılan aracın bölge,filo ve grup değerlerini veritabanından çekme işlemini yapıyorum.
		//tüm bunları liste şeklinde çekmemin nedeni veritabanında her şirket için aynı isimlerde bölgeler,filolar ve gruplar olabilir.
		//Önce liste şeklinde bütün aynı isme ait değerleri çekip sonrasında kullanıcının kayıtlı olduğu şirkete göre filtreliyorum.
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

	//Kullanıcının kayıtlı bir aracın bilgilerini değiştirmesini sağlayan fonksiyon.
	//WebSecurityConfiguration classında yazdığım kodlar sayesinde buraya sadece CompanyAdmin rolü bulunan kullanıcılar erişebilir.
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

	//Kullanıcının kayıtlı bir aracı silmesini sağlayan fonksiyon.
	//WebSecurityConfiguration classında yazdığım kodlar sayesinde buraya sadece CompanyAdmin rolü bulunan kullanıcılar erişebilir.
	@Override
	public void delete(int vehicleId) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow();
		
		//Kullanıcının araç üzerinde yetkisi olup olmadığını sorgulayan fonksiyon.
		vehicleServiceBusinessRules.checkIfUserAuthorized(principal, vehicle);
		
		vehicleRepository.delete(vehicle);
		
	}

}
