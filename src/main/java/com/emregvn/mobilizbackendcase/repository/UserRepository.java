package com.emregvn.mobilizbackendcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emregvn.mobilizbackendcase.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
}
