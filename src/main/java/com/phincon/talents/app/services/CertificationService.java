package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.CertificationRepository;
import com.phincon.talents.app.model.hr.Certification;
import com.phincon.talents.app.model.hr.Employee;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class CertificationService {

	@Autowired
	CertificationRepository certificationRepository;

	@Transactional
	public Certification findById(Long id) {
		return certificationRepository.findOne(id);
	}
	
	
	@Transactional
	public Iterable<Certification> findAll() {
		return certificationRepository.findAll();
	}
	
	@Transactional
	public Iterable<Certification> findByEmployee(Employee employee) {
		return certificationRepository.findByEmployee(employee);
	}

	@Transactional
	public void save(Certification obj) {
		certificationRepository.save(obj);
	}

	
	

}
