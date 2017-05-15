package com.phincon.talents.app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.PayrollElementDetailRepository;
import com.phincon.talents.app.model.hr.PayrollElementDetail;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class PayrollElementDetailService {

	@Autowired
	PayrollElementDetailRepository payrollElementDetailRepository;

	
	
	@Transactional
	public Iterable<PayrollElementDetail> findAll() {
		return payrollElementDetailRepository.findAll();
	}

	@Transactional
	public void save(PayrollElementDetail obj) {
		payrollElementDetailRepository.save(obj);
	}
	
}
