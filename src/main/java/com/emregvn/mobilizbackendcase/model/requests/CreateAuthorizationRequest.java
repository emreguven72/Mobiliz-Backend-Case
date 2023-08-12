package com.emregvn.mobilizbackendcase.model.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAuthorizationRequest {

	private int userId;
	private int groupId;
	
}
