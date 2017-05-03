package com.phincon.talents.app.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.hr.Employee;

/**
 *
 * Business service for User entity related operations
 *
 */
@Service
public class EmployeeService {
	protected ObjectMapper mapper = new ObjectMapper();
	@Autowired
	EmployeeRepository employeeRepository;

	@Transactional
	public Employee findEmployee(Long id) {
		return employeeRepository.findOne(id);
	}
	
	@Transactional
	public Employee findByWorkEmail(String email) {
		return employeeRepository.findByOfficeMail(email);
	}
	
	@Transactional
	public Iterable<Employee> findAllEmployee() {
		return employeeRepository.findAll();
	}

	@Transactional
	public void save(Employee obj) {
		employeeRepository.save(obj);
	}
	
	
	@Transactional
	public List<Employee> findByIdIn(List<Long> ids) {
		return employeeRepository.findByIdIn(ids);
	}

	@Transactional
	public void approvedChangeMaritalStatus(DataApproval dataApproval) {
		Long employeeId = dataApproval.getObjectRef();
		String strJson = dataApproval.getData();
		
		System.out.println("strJson " + strJson);
		Map<String, String> paramsMap = null;
		try {
			paramsMap = mapper.readValue(strJson, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error :  Problem with convert Data");
		}
		
		employeeRepository.updateMaritalStatus(employeeId, paramsMap.get("maritalStatus"));
		
	}
	@Transactional
	public Employee findById(Long id) {
		return employeeRepository.findOne(id);
	}



}
