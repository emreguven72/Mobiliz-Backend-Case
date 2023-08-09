package com.emregvn.mobilizbackendcase.service;

import java.util.List;
import java.util.Optional;

import com.emregvn.mobilizbackendcase.entity.Region;

public interface RegionService {

	List<Region> getByName(String name);
	
}
