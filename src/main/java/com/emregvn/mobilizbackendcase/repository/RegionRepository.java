package com.emregvn.mobilizbackendcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emregvn.mobilizbackendcase.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
	
	List<Region> findByName(String regionName);

}
