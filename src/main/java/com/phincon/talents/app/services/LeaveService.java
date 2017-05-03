package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.LeaveRepository;
import com.phincon.talents.app.model.hr.Leave;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class LeaveService {

	@Autowired
	LeaveRepository leaveRepository;

	@Transactional
	public Leave findById(Long id) {
		return leaveRepository.findOne(id);
	}

	

	@Transactional
	public void save(Leave obj) {
		leaveRepository.save(obj);
	}

}
