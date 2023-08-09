package com.emregvn.mobilizbackendcase.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Region;
import com.emregvn.mobilizbackendcase.repository.RegionRepository;
import com.emregvn.mobilizbackendcase.service.RegionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
	private final RegionRepository regionRepository;

	@Override
	public List<Region> getByName(String name) {
		return regionRepository.findByName(name);
	}
	

}
