package com.emregvn.mobilizbackendcase.model.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateVehicleRequest {

	@NotNull
	private int vehicleId;
	
	@NotNull
	@NotBlank
	private String plateNumber;
	
	private String chassisNumber;
	
	private String label;
	
	@NotNull
	@NotBlank
	private String brand;
	
	@NotNull
	@NotBlank
	private String model;
	
	@NotNull
	private int modelYear;
	
	private String groupName;
	
}
