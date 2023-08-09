package com.emregvn.mobilizbackendcase.service;

import java.util.List;

import com.emregvn.mobilizbackendcase.entity.Group;

public interface GroupService {

	List<Group> getByName(String name);
	
}
