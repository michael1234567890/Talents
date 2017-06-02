package com.phincon.talents.app.controllers.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dto.UserInfoDTO;
import com.phincon.talents.app.model.Company;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.services.CompanyService;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.UserService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.PasswordValidator;
import com.phincon.talents.app.utils.Utils;

@Controller
@RequestMapping("api")
public class PublicRestApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanySettingsRepository companySettingsRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CompanyService companyService;
	
    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CustomMessage> register(@RequestBody UserInfoDTO userInfo) {
    	// get request body
    	if(!userInfo.getPassword().equals(userInfo.getRepassword())) {
    		throw new RuntimeException("Error : Password and Confirm Password is not same");
    	}
    	
    	// cek company code is registered or not
    	Company company = companyService.findByCode(userInfo.getCompanyCode());
    	if(company == null) {
    		throw new RuntimeException("Error : company code is not registered");
    	}
    	
    	// Load company Settings
    	List<CompanySettings> listCompanySettings = companySettingsRepository.findByCompany(company.getId());
    	CompanySettings companySettings = null;
    	if(listCompanySettings != null && listCompanySettings.size() > 0) {
    		companySettings = listCompanySettings.get(0);
    		// checking password policy (regex)
    		if(companySettings.getIsRegexPasswordActive() && companySettings.getRegexPassword() != null && !companySettings.getRegexPassword().equals("")) {
    			PasswordValidator passwordValidator = new PasswordValidator(companySettings.getRegexPassword());
    			if(!passwordValidator.validate(userInfo.getPassword())){
    				throw new RuntimeException("Error : Your password must contains " + companySettings.getMsgErrorRegexPassword());
    			}
    		}
    	}
    	
    	
    	
    	// check mail and company code in employee repo
    	Employee employee = employeeService.findByWorkEmail(userInfo.getEmail());
    	if(employee == null) {
    		throw new RuntimeException("Error : Employee not registered with this email");
    	}
    	
    	if(employee.getCompany() != company.getId()) {
    		throw new RuntimeException("Error : Employee and Company code does not match");
    	}
    	
    	
    	
    	
    	
    	// check email already register or not
    	User checkUser = userService.findByEmail(userInfo.getEmail());
    	if(checkUser != null) {
    		// send message user already registered with this email
    		throw new RuntimeException("Error : This email is already registered please choose another one");
    	}
    	
    	userInfo.setEmployeeId(employee.getId());
    	userInfo.setFirstName(employee.getFirstName());
    	userInfo.setLastName(employee.getLastName());
    	userInfo.setCompany(company.getId());
    	userInfo.setEmployeeExtId(employee.getExtId());
    	userInfo.setFullName(employee.getName());
    	
    	userService.createUser(userInfo);
    	
    	return new ResponseEntity<CustomMessage>(new CustomMessage("Registration Successfully. Please Check your email", false), HttpStatus.OK);
    	// save into server with status activation is false
    	
    	// send email for activation 
    	
    	// send result registration
    	
       // return "Register API!";
    }
    
    @RequestMapping(value="/resetpassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CustomMessage> forgot(@RequestBody Map<String,Object> request) {
    	String email = (String) request.get("email");
    	User user = userService.findByEmail(email);
    	if(user == null) {
    		throw new RuntimeException("This email " + email + " is not registed. Please try again");
    	}
    	
    	Utils.sendEmail();
    	return new ResponseEntity<CustomMessage>(new CustomMessage("An email has been sent to " + email + " for create a new password", false), HttpStatus.OK);
    }

}
