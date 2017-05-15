package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.FamilyHistRepository;
import com.phincon.talents.app.model.hr.FamilyHist;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class FamilyHistService {

	@Autowired
	FamilyHistRepository familyHistRepository;

	
	
	@Transactional
	public Iterable<FamilyHist> findAll() {
		return familyHistRepository.findAll();
	}
	
	@Transactional
	public FamilyHist findById(Long id) {
		return familyHistRepository.findOne(id);
	}

	@Transactional
	public void save(FamilyHist family) {
		familyHistRepository.save(family);
	}
	
	public List<FamilyHist> findByFamilyIdRef(Long familyIdRef){
		return familyHistRepository.findByFamilyIdRef(familyIdRef);
	}

}
