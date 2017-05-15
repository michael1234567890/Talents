package com.phincon.talents.app.controllers.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.PayrollElementHeaderRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.PayrollRequestDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.PayrollElementHeader;
import com.phincon.talents.app.services.PayrollElementHeaderService;
import com.phincon.talents.app.services.UserService;

@RestController
@RequestMapping("api")
public class PayrollController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PayrollElementHeaderRepository payrollElementHeaderRepository;
	
	@Autowired
	PayrollElementHeaderService payrollElementHeaderService;

	/*
	 * month = "2017-05"
	 */

	@RequestMapping(value = "/user/payroll", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<PayrollElementHeader>>  getPayroll( @RequestBody PayrollRequestDTO request,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
	
		List<PayrollElementHeader> listElementHeaders = payrollElementHeaderService.findByMonthAndEmployee(request.getMonth(), user.getEmployee());
		return new ResponseEntity<List<PayrollElementHeader> >(listElementHeaders, HttpStatus.OK);

	}

	

}
