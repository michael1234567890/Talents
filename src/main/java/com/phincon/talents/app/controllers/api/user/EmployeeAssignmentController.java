package com.phincon.talents.app.controllers.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dao.VwEmpAssignmentRepository;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.utils.LoadResult;

@RestController
@RequestMapping("api")
public class EmployeeAssignmentController {
	private final static String PARAM_ORGANIZATION = "organization"; 
	private final static String PARAM_DIVISION = "division"; 
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	VwEmpAssignmentRepository vwEmpAssignmentRepository;
	
	@Autowired
	private Environment env;
	
	
	@RequestMapping(value = "/user/empassignment/byparam", method = RequestMethod.GET)
	public ResponseEntity<LoadResult<List<VwEmpAssignment>> > listByparam(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,@RequestParam(value = "param", required = true) String param,@RequestParam(value = "value", required = false) String value,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<VwEmpAssignment> listAssignment = vwEmpAssignmentRepository.findByEmployee(user.getEmployee());
		if(listAssignment.size() ==0) {
			throw new CustomException("Employee Assignment is not found");
		}
		VwEmpAssignment assignment = listAssignment.get(0);
		List<VwEmpAssignment> listEmployeeAssignment = null;
		int pageData = Integer.valueOf(env.getProperty("talents.startpage"));
		int sizeData = Integer.valueOf(env.getProperty("talents.size"));
		if (size != null)
			sizeData = size;
		if (page != null)
			pageData = page;
		Long totalRecord = 0L;
		LoadResult<List<VwEmpAssignment>> loadResult = new LoadResult<List<VwEmpAssignment>>();
		PageRequest pageRequest = new PageRequest(pageData, sizeData);
		loadResult.setPage(pageData);
		loadResult.setSize(sizeData);
		List<Long> listTotalRecord = null;
		
		
		if(param.toLowerCase().equals(PARAM_ORGANIZATION)) {
			listTotalRecord = vwEmpAssignmentRepository.countByOrganizationAndCompany(assignment.getOrganization(), user.getCompany());
			listEmployeeAssignment = vwEmpAssignmentRepository.findByOrganizationAndCompany(assignment.getOrganization(),user.getCompany(),pageRequest);
		}else if(param.toLowerCase().equals(PARAM_DIVISION)){
			listTotalRecord = vwEmpAssignmentRepository.countByDivisionAndCompany(value, user.getCompany());
			listEmployeeAssignment = vwEmpAssignmentRepository.findByDivisionAndCompany(value, user.getCompany(),pageRequest);
		}else {
			throw new CustomException("Your param is not registered");
		}
		
		if (listTotalRecord != null && listTotalRecord.size() > 0) {
			for (Long objects : listTotalRecord) {
				totalRecord = objects;
			}
		}
		
		loadResult.setData(listEmployeeAssignment);
		loadResult.setTotalRecord(totalRecord);
		return new ResponseEntity<LoadResult<List<VwEmpAssignment>> >(loadResult, HttpStatus.OK);
	}
	
	
}
