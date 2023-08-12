package com.emregvn.mobilizbackendcase.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Group;
import com.emregvn.mobilizbackendcase.entity.Vehicle;
import com.emregvn.mobilizbackendcase.model.requests.CreateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.requests.UpdateVehicleRequest;
import com.emregvn.mobilizbackendcase.model.responses.GetCompanyResponse;
import com.emregvn.mobilizbackendcase.model.responses.GetGroupResponse;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleResponse;
import com.emregvn.mobilizbackendcase.model.responses.GetVehicleTreeResponse;
import com.emregvn.mobilizbackendcase.repository.VehicleRepository;
import com.emregvn.mobilizbackendcase.security.UserPrincipal;
import com.emregvn.mobilizbackendcase.service.GroupService;
import com.emregvn.mobilizbackendcase.service.VehicleService;
import com.emregvn.mobilizbackendcase.service.business.VehicleServiceBusinessRules;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
	//Dependency Injection
	private final VehicleRepository vehicleRepository;
	private final VehicleServiceBusinessRules vehicleServiceBusinessRules;
	private final GroupService groupService;
	
	//Grupların alt gruplarını boş olmadıkça recursive bir şekilde dolaşan ve her alt gruptaki araçları listeye ekleyen fonksiyon
	//getByAuthorizations fonksıyonu için gerekli.
	public void addSubGroupVehicles(List<Vehicle> authorizedVehicles, Group group) {
		if(!group.getSubGroups().isEmpty()) {
			group.getSubGroups().forEach(g -> {
				authorizedVehicles.addAll(g.getVehicles());
				addSubGroupVehicles(authorizedVehicles, g);
			});
		}
	}
	
	//Kullanıcının yetkili olduğu tüm araçları bularak sonucu kullanıcıya döndüren fonksiyon.
	@Override
	public List<GetVehicleResponse> getByAuthorizations() {
		UserPrincipal user = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		List<Vehicle> authorizedVehicles = new ArrayList<Vehicle>();
		//İsteği yapan kullanıcı Standart rolüne sahipse kullanıcıysa bu kullanıcının yetkili olduğu tüm 
		//araçlar bu if bloğu içerisinde bulunur ve kullanıcıya döndürülür.
		if(user.getRole().equals("Standart")) {
			user.getAuthorizations().forEach(a -> {
				authorizedVehicles.addAll(a.getGroup().getVehicles());
				addSubGroupVehicles(authorizedVehicles, a.getGroup());
			});
			
			return authorizedVehicles.stream().map(v -> GetVehicleResponse.builder()
					.vehicleId(v.getVehicleId())
					.plateNumber(v.getPlateNumber())
					.chassisNumber(v.getChassisNumber())
					.label(v.getLabel())
					.brand(v.getBrand())
					.model(v.getModel())
					.modelYear(v.getModelYear())
					.company(GetCompanyResponse.builder()
							.companyId(v.getCompany().getCompanyId())
							.companyName(v.getCompany().getCompanyName())
							.build())
					.group(GetGroupResponse.builder()
							.groupId(v.getGroup().getGroupId())
							.name(v.getGroup().getName())
							.build())
					.build()).toList();
			
		}
		
		//İsteği yapan kullanıcı Admin rolüne sahipse yetkiye bakılmaksızın şirketteki tüm araçlar bulunur ve kullanıcıya döndürülür.
		user.getCompany().getGroups().forEach(g -> {
			authorizedVehicles.addAll(g.getVehicles());
		});
		
		return authorizedVehicles.stream().map(v -> GetVehicleResponse.builder()
				.vehicleId(v.getVehicleId())
				.plateNumber(v.getPlateNumber())
				.chassisNumber(v.getChassisNumber())
				.label(v.getLabel())
				.brand(v.getBrand())
				.model(v.getModel())
				.modelYear(v.getModelYear())
				.company(GetCompanyResponse.builder()
						.companyId(v.getCompany().getCompanyId())
						.companyName(v.getCompany().getCompanyName())
						.build())
				.group(GetGroupResponse.builder()
						.groupId(v.getGroup().getGroupId())
						.name(v.getGroup().getName())
						.build())
				.build()).toList();
	}
	
	//İsteği yapan kullanıcının yetkili olduğu tüm bölgeleri, bu bölgelerdeki araçları ve bu bölgelerin alt bölgelerini
	//sırasıyla bularak kullanıcıya bu bilgileri bir ağaç yapısında döndüren fonksiyon
	public GetVehicleTreeResponse getVehicleTreeGroupsRecursively(Group group) {
		GetVehicleTreeResponse vehicleTreeResponse =  GetVehicleTreeResponse.builder()
				.name(group.getName())
				.vehicles(group.getVehicles().stream().map(v -> GetVehicleResponse.builder()
						.vehicleId(v.getVehicleId())
						.plateNumber(v.getPlateNumber())
						.chassisNumber(v.getChassisNumber())
						.label(v.getLabel())
						.brand(v.getBrand())
						.model(v.getModel())
						.modelYear(v.getModelYear())
						.company(GetCompanyResponse.builder()
								.companyId(v.getCompany().getCompanyId())
								.companyName(v.getCompany().getCompanyName())
								.build())
						.group(GetGroupResponse.builder()
								.groupId(v.getGroup().getGroupId())
								.name(v.getGroup().getName())
								.build())
						.build()).toList())
				.groups(group.getSubGroups().stream().map(g -> getVehicleTreeGroupsRecursively(g)).toList())
				.build();
		
		return vehicleTreeResponse;
	}
	
	@Override
	public List<GetVehicleTreeResponse> getVehicleTreeByCompany() {
		UserPrincipal user = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user.getRole().equals("Standart")) {
			List<GetVehicleTreeResponse> tree = user.getAuthorizations().stream().map(a -> 
				getVehicleTreeGroupsRecursively(a.getGroup())).toList();
			
			return tree;
		}
		
		List<GetVehicleTreeResponse> tree = user.getCompany().getGroups().stream().filter(g -> g.getParentGroup() == null).map(g ->
				getVehicleTreeGroupsRecursively(g)).toList();
		
		return tree;
	}
	
	//Kullanıcının yeni araç yaratmasını sağlayan fonksiyon.
	//WebSecurityConfiguration classında yazdığım kodlar sayesinde buraya sadece CompanyAdmin rolü bulunan kullanıcılar erişebilir.
	@Override
	public void create(CreateVehicleRequest createVehicleRequest) {
		UserPrincipal user = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Group group = groupService.getByName(createVehicleRequest.getGroupName())
				.stream().filter(g -> g.getCompany().getCompanyId() == user.getCompany().getCompanyId()).toList().get(0);
		
		Vehicle vehicle = Vehicle.builder()
				.plateNumber(createVehicleRequest.getPlateNumber())
				.chassisNumber(createVehicleRequest.getChassisNumber())
				.label(createVehicleRequest.getLabel())
				.brand(createVehicleRequest.getBrand())
				.model(createVehicleRequest.getModel())
				.modelYear(createVehicleRequest.getModelYear())
				.company(user.getCompany())
				.group(group)
				.build();
		
		vehicleRepository.save(vehicle);
	}

	//Kullanıcının kayıtlı bir aracın bilgilerini değiştirmesini sağlayan fonksiyon.
	//WebSecurityConfiguration classında yazdığım kodlar sayesinde buraya sadece CompanyAdmin rolü bulunan kullanıcılar erişebilir.
	@Override
	public void update(UpdateVehicleRequest updateVehicleRequest) {
		UserPrincipal user = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vehicle vehicle = vehicleRepository.findById(updateVehicleRequest.getVehicleId()).orElseThrow();
		
		Group group = groupService.getByName(updateVehicleRequest.getGroupName())
				.stream().filter(g -> g.getCompany().getCompanyId() == user.getCompany().getCompanyId()).toList().get(0);
		
		if(vehicle != null) {
			vehicle.setPlateNumber(updateVehicleRequest.getPlateNumber());
			vehicle.setChassisNumber(updateVehicleRequest.getChassisNumber());
			vehicle.setLabel(updateVehicleRequest.getLabel());
			vehicle.setBrand(updateVehicleRequest.getBrand());
			vehicle.setModel(updateVehicleRequest.getModel());
			vehicle.setModelYear(updateVehicleRequest.getModelYear());
			vehicle.setGroup(group != null ? group : null);
			vehicleRepository.save(vehicle);
		}
	}

	//Kullanıcının kayıtlı bir aracı silmesini sağlayan fonksiyon.
	//WebSecurityConfiguration classında yazdığım kodlar sayesinde buraya sadece CompanyAdmin rolü bulunan kullanıcılar erişebilir.
	@Override
	public void delete(int vehicleId) {
		UserPrincipal user = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow();
		
		//Kullanıcının araç üzerinde yetkisi olup olmadığını sorgulayan fonksiyon.
		vehicleServiceBusinessRules.checkIfUserAuthorizedForTheCompany(user, vehicle);
		
		vehicleRepository.delete(vehicle);
		
	}

}
