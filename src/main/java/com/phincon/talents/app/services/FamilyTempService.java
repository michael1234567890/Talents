package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.FamilyHistRepository;
import com.phincon.talents.app.dao.FamilyTempRepository;
import com.phincon.talents.app.model.hr.FamilyTemp;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class FamilyTempService {

	@Autowired
	FamilyTempRepository familyRepository;

	@Autowired
	FamilyHistRepository familyHistRepository;
	
	@Transactional
	public Iterable<FamilyTemp> findAll() {
		return familyRepository.findAll();
	}
	
	@Transactional
	public FamilyTemp findById(Long id) {
		return familyRepository.findOne(id);
	}

	@Transactional
	public void save(FamilyTemp family) {
		familyRepository.save(family);
	}
	
	public Iterable<FamilyTemp> findByEmployee(Long employeeId){
		return familyRepository.findByEmployee(employeeId);
	}
}
