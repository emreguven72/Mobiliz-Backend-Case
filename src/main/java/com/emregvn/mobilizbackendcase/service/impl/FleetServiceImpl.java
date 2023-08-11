package com.emregvn.mobilizbackendcase.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Fleet;
import com.emregvn.mobilizbackendcase.repository.FleetRepository;
import com.emregvn.mobilizbackendcase.service.FleetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FleetServiceImpl implements FleetService {
	private final FleetRepository fleetRepository;

	@Override
	public List<Fleet> getByName(String name) {
		return fleetRepository.findByName(name);
	}
	
}
