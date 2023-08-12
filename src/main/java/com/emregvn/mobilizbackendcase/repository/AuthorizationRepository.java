package com.emregvn.mobilizbackendcase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emregvn.mobilizbackendcase.entity.Authorization;
import com.emregvn.mobilizbackendcase.entity.User;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, Integer> {
	
	List<Authorization> findByUser(User user);
	
}
