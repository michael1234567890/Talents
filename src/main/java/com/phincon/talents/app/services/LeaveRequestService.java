package com.phincon.talents.app.services;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.LeaveRequestRepository;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.hr.LeaveRequest;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class LeaveRequestService {

	@Autowired
	LeaveRequestRepository leaveRequestRepository;

	@Transactional
	public LeaveRequest findById(Long id) {
		return leaveRequestRepository.findOne(id);
	}

	@Transactional
	public Iterable<LeaveRequest> findAll() {
		return leaveRequestRepository.findAll();
	}

	@Transactional
	public List<LeaveRequest> findByEmployee(Long employee) {
		return leaveRequestRepository.findByEmployee(employee);
	}

	@Transactional
	public List<LeaveRequest> findByEmployeeAndStatus(Long employee,
			String status) {
		return leaveRequestRepository.findByEmployeeAndStatus(employee, status);
	}

	@Transactional
	public void save(LeaveRequest obj) {
		leaveRequestRepository.save(obj);
	}

	@Transactional
	public void approved(DataApproval dataApproval,String status) {
		Long leaveRequestId = dataApproval.getObjectRef();
		leaveRequestRepository.updateStatus(status,leaveRequestId);

	}

	public void rejected(DataApproval dataApproval, String rejected) {
		// TODO Auto-generated method stub
		
	}

}
