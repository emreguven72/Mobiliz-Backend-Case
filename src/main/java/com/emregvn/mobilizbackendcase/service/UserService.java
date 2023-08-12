package com.emregvn.mobilizbackendcase.service;

import java.util.Optional;

import com.emregvn.mobilizbackendcase.entity.User;

public interface UserService {

	Optional<User> getById(int id);
	
}
