package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.VwEmpAssignmentRepository;
import com.phincon.talents.app.model.hr.Assignment;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.VwEmpAssignment;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class VwEmpAssignmentService {

	@Autowired
	VwEmpAssignmentRepository reportToRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public Employee findReportTo(Long employee) {
		List<VwEmpAssignment> listReportTo = reportToRepository.findByEmployee(employee);
		if(listReportTo != null && listReportTo.size() > 0){
			VwEmpAssignment reportToSelected = listReportTo.get(0);
			Employee reportTo =  employeeRepository.findOne(reportToSelected.getDirectEmployee());
			return reportTo;
			
		}
		
		return null;
	}
	
	
	@Transactional
	public List<Employee> findEmployee(Long reportTo) {
		List<VwEmpAssignment> listReportTo = reportToRepository.findByDirectEmployee(reportTo);
		if(listReportTo != null && listReportTo.size() > 0){
			List<Long> employeeIds = new ArrayList<Long>();
			for (VwEmpAssignment reportToObj : listReportTo) {
				employeeIds.add(reportToObj.getEmployee());
			}
			List<Employee> listEmployee = employeeRepository.findByIdIn(employeeIds);
			return listEmployee;
			
		}
		
		return null;
	}
	
	@Transactional
	public VwEmpAssignment findAssignmentByEmployee(Long employee) {
		List<VwEmpAssignment> listAssignMent = reportToRepository.findByEmployee(employee);
		if(listAssignMent != null && listAssignMent.size() > 0){
			return listAssignMent.get(0); 
		}
		
		return null;
	}
	
	
	
}
