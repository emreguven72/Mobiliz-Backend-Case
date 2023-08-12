package com.emregvn.mobilizbackendcase.service;

import java.util.List;
import java.util.Optional;

import com.emregvn.mobilizbackendcase.entity.Company;

public interface CompanyService {

	List<Company> getAll();
	
	Optional<Company> getById(int id);
	
	Optional<Company> getByCompanyName(String name);
	
}
