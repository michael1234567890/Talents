package com.phincon.talents.app.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.AssignmentRepository;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Assignment;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class AssignmentService {

	@Autowired
	AssignmentRepository reportToRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public Employee findReportTo(Long employee) {
		List<Assignment> listReportTo = reportToRepository.findByEmployee(employee);
		if(listReportTo != null && listReportTo.size() > 0){
			Assignment reportToSelected = listReportTo.get(0);
			Employee reportTo =  employeeRepository.findOne(reportToSelected.getReportTo());
			return reportTo;
			
		}
		
		return null;
	}
	
	
	@Transactional
	public List<Employee> findEmployee(Long reportTo) {
		List<Assignment> listReportTo = reportToRepository.findByReportTo(reportTo);
		if(listReportTo != null && listReportTo.size() > 0){
			List<Long> employeeIds = new ArrayList<Long>();
			for (Assignment reportToObj : listReportTo) {
				employeeIds.add(reportToObj.getEmployee());
			}
			List<Employee> listEmployee = employeeRepository.findByIdIn(employeeIds);
			return listEmployee;
			
		}
		
		return null;
	}
	
	@Transactional
	public Assignment findAssignmentByEmployee(Long employee) {
		List<Assignment> listAssignMent = reportToRepository.findByEmployee(employee);
		if(listAssignMent != null && listAssignMent.size() > 0){
			return listAssignMent.get(0); 
		}
		
		return null;
	}
	
	
	
}
