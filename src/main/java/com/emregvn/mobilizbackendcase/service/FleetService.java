package com.emregvn.mobilizbackendcase.service;

import java.util.List;

import com.emregvn.mobilizbackendcase.entity.Fleet;

public interface FleetService {

	List<Fleet> getByName(String name);
	
}
