package com.phincon.talents.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.EntitlementRepository;
import com.phincon.talents.app.dao.LeaveRequestRepository;
import com.phincon.talents.app.model.hr.Entitlement;
import com.phincon.talents.app.model.hr.LeaveRequest;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class EntitlementService {

	@Autowired
	EntitlementRepository entitlementRepository;

	@Transactional
	public Entitlement findById(Long id) {
		return entitlementRepository.findOne(id);
	}

	@Transactional
	public Iterable<Entitlement> findAll() {
		return entitlementRepository.findAll();
	}

	@Transactional
	public List<Entitlement> findByEmployeeAndLeaveType(Long employee,
			Long leaveType) {
		return entitlementRepository.findByEmployeeAndLeaveType(employee,
				leaveType);
	}
	
	@Transactional
	public List<Entitlement> findByEmployee(Long employee) {
		return entitlementRepository.findByEmployee(employee);
	}

	@Transactional
	public void save(Entitlement obj) {
		entitlementRepository.save(obj);
	}

}
