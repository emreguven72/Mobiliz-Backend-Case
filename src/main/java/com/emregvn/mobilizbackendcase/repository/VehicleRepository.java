package com.emregvn.mobilizbackendcase.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emregvn.mobilizbackendcase.entity.Company;
import com.emregvn.mobilizbackendcase.entity.Group;
import com.emregvn.mobilizbackendcase.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	Optional<Vehicle> findByPlateNumber(String plateNumber);
	
	List<Vehicle> findByCompany(Company company);
	
	List<Vehicle> findByGroup(Group group);
	
}
