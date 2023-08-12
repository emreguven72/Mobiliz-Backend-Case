package com.emregvn.mobilizbackendcase.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.User;
import com.emregvn.mobilizbackendcase.repository.UserRepository;
import com.emregvn.mobilizbackendcase.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public Optional<User> getById(int id) {
		return Optional.of(userRepository.findById(id).orElseThrow());
	}
	
}
