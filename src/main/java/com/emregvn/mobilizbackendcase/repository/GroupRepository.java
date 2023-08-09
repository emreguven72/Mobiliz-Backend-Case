package com.emregvn.mobilizbackendcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emregvn.mobilizbackendcase.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
	
	List<Group> findByName(String name);
	
}
