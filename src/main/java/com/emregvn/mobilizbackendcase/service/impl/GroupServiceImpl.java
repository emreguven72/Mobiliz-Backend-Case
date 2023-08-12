package com.emregvn.mobilizbackendcase.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Group;
import com.emregvn.mobilizbackendcase.repository.GroupRepository;
import com.emregvn.mobilizbackendcase.service.GroupService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
	private final GroupRepository groupRepository;

	@Override
	public List<Group> getByName(String name) {
		return groupRepository.findByName(name);
	}

	@Override
	public Optional<Group> getById(int id) {
		return Optional.of(groupRepository.findById(id).orElseThrow());
	}

}
