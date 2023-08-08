package com.emregvn.mobilizbackendcase.model;

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
	
}
