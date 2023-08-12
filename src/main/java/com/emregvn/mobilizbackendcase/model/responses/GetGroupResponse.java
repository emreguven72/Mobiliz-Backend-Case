package com.emregvn.mobilizbackendcase.model.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetGroupResponse {

	private int groupId;
	private String name;
	
}
