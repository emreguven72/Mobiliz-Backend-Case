package com.emregvn.mobilizbackendcase.model.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCompanyResponse {
	
	private int companyId;
	private String companyName;

}
