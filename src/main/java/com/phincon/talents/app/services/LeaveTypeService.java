package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.LeaveTypeRepository;
import com.phincon.talents.app.model.hr.LeaveRequest;
import com.phincon.talents.app.model.hr.LeaveType;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class LeaveTypeService {

	@Autowired
	LeaveTypeRepository leaveTypeRepository;

	@Transactional
	public LeaveType findById(Long id) {
		return leaveTypeRepository.findOne(id);
	}
	
	@Transactional
	public Iterable<LeaveType> findAll() {
		return leaveTypeRepository.findAll();
	}
	
	@Transactional
	public List<LeaveType> findByCompany(Long company) {
		return leaveTypeRepository.findByCompany(company);
	}
	
	

	@Transactional
	public void save(LeaveType obj) {
		leaveTypeRepository.save(obj);
	}

}
