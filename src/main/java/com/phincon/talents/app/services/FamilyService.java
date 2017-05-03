package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.FamilyRepository;
import com.phincon.talents.app.model.hr.Family;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class FamilyService {

	@Autowired
	FamilyRepository familyRepository;

	
	
	@Transactional
	public Iterable<Family> findAll() {
		return familyRepository.findAll();
	}

	@Transactional
	public void save(Family family) {
		familyRepository.save(family);
	}
	
	public Iterable<Family> findByEmployee(Long employeeId){
		return familyRepository.findByEmployee(employeeId);
	}
}
