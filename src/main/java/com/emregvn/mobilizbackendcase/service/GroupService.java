package com.emregvn.mobilizbackendcase.service;

import java.util.List;
import java.util.Optional;

import com.emregvn.mobilizbackendcase.entity.Group;

public interface GroupService {

	Optional<Group> getById(int id);
	
	List<Group> getByName(String name);
	
}
