package com.emregvn.mobilizbackendcase.model.responses;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetVehicleResponse {
	
	private int vehicleId;
	private String plateNumber;
	private String chassisNumber;
	private String label;
	private String brand;
	private String model;
	private int modelYear;
	private GetCompanyResponse company;
	private GetGroupResponse group;

}
