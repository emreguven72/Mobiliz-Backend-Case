package com.emregvn.mobilizbackendcase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emregvn.mobilizbackendcase.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	Optional<Company> findByCompanyName(String companyName);

}
