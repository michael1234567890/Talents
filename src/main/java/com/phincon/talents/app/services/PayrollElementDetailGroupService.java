package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.PayrollElementDetailGroupRepository;
import com.phincon.talents.app.model.hr.PayrollElementDetailGroup;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementDetailGroupService {

	@Autowired
	PayrollElementDetailGroupRepository payrollElementDetailGroupRepository;

	
	
	@Transactional
	public Iterable<PayrollElementDetailGroup> findAll() {
		return payrollElementDetailGroupRepository.findAll();
	}

	
	
}
