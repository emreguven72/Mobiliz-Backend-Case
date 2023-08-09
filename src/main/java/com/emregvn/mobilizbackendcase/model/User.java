package com.emregvn.mobilizbackendcase.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

	private int userId;
	private String name;
	private String surname;
	private int companyId;
	private String companyName;
	private String role;
	private List<String> regionAuthorizations;
	private List<String> fleetAuthorizations;
	private List<String> groupAuthorizations;
	private List<String> vehicleAuthorizations;
	
}
