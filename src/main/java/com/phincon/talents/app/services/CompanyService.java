package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.CompanyRepository;
import com.phincon.talents.app.model.Company;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Transactional
	public Company findByCode(String code) {
		return companyRepository.findByCode(code);
	}
	
	@Transactional
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	@Transactional
	public void save(Company company) {
		companyRepository.save(company);
	}
}
