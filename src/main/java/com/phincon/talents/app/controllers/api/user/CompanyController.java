package com.phincon.talents.app.controllers.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.BranchCompanyRepository;
import com.phincon.talents.app.dao.CompanyRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.BranchCompany;
import com.phincon.talents.app.model.Company;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.services.CompanyService;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class CompanyController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	BranchCompanyRepository branchCompanyRepository;
	
	@RequestMapping(value = "/user/company", method = RequestMethod.GET)
	public ResponseEntity<Company> listCompany(
			OAuth2Authentication authentication){
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		Company company = companyRepository.findOne(user.getCompany());
		if(company.getLogo()!= null && !company.getLogo().equals("")) {
			String logo = Utils
					.convertImageToBase64(company.getLogo());
			company.setLogo(logo);
		}
		if(company != null){
			List<BranchCompany> listBranchCompany = branchCompanyRepository.findByCompany(company.getId());
			company.setListBranchCompany(listBranchCompany);
		}
		
		return new ResponseEntity<Company>(company, HttpStatus.OK);

	}
	
}
