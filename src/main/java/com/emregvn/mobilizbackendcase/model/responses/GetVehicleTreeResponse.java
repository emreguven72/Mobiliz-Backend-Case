package com.emregvn.mobilizbackendcase.model.responses;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetVehicleTreeResponse {

	private String name;
	private List<GetVehicleResponse> vehicles;
	private List<GetVehicleTreeResponse> groups;
	
}
