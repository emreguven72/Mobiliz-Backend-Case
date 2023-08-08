package com.emregvn.mobilizbackendcase.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emregvn.mobilizbackendcase.entity.Company;
import com.emregvn.mobilizbackendcase.repository.CompanyRepository;
import com.emregvn.mobilizbackendcase.service.CompanyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
	private final CompanyRepository companyRepository;
	
	@Override
	public List<Company> getAll() {
		return companyRepository.findAll();
	}

	@Override
	public Optional<Company> getByCompanyName(String name) {
		return Optional.of(companyRepository.findByCompanyName(name).orElseThrow());
	}

	
	
}
