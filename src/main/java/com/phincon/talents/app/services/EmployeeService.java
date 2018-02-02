package com.phincon.talents.app.services;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.utils.Utils;

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
	
	@Autowired
	VwEmpAssignmentService assignmentService;

	@Transactional
	public Employee findEmployee(Long id) {
		return employeeRepository.findOne(id);
	}
	
	@Transactional
	public Employee findEmployeeWithAssignment(Long id) {
		Employee obj = employeeRepository.findOne(id);
		if(obj == null)
			return null;
		VwEmpAssignment assignment = assignmentService
				.findAssignmentByEmployee(obj.getId());
		if (assignment != null) {
			obj.setAssignment(assignment);
		} else {
			obj.setAssignment(null);
		}
		return obj;
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
			throw new CustomException("Error :  Problem with convert Data");
		}

		employeeRepository.updateMaritalStatus(employeeId,
				(String) paramsMap.get("maritalStatus"), null, null, true);

	}

	@Transactional
	public void requestMaritalStatus(DataApproval dataApproval) {
		Long employeeId = dataApproval.getObjectRef();
		String strJson = dataApproval.getData();

		Map<String, Object> paramsMap = Utils.convertStrJsonToMap(strJson);
		if (paramsMap == null) {
			throw new CustomException("Error :  Problem with convert Data");
		}

		employeeRepository.requestMaritalStatus(
				(String) paramsMap.get("maritalStatus"), dataApproval.getId(),
				employeeId);

	}
	
	
	public void requestNPWP(DataApproval dataApproval){
		Long employeeId = dataApproval.getObjectRef();
		String strJson = dataApproval.getData();
		
		Map<String, Object> paramsMap = Utils.convertStrJsonToMap(strJson);
		if(paramsMap == null){
			throw new CustomException("Error :  Problem with convert Data");
		}
		
		employeeRepository.requestMaritalStatus((String) paramsMap.get("npwpNo"), dataApproval.getId(), employeeId);
	}

	@Transactional
	public Employee findById(Long id) {
		return employeeRepository.findOne(id);
	}

	@Transactional
	public void rejectedChangeMaritalStatus(DataApproval dataApproval) {
		Long employeeId = dataApproval.getObjectRef();
		employeeRepository.rejectedMaritalStatus(employeeId);
	}

}
