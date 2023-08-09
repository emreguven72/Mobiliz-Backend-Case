package com.emregvn.mobilizbackendcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emregvn.mobilizbackendcase.entity.Fleet;

@Repository
public interface FleetRepository extends JpaRepository<Fleet, Integer> {

	List<Fleet> findByName(String name);
	
}
