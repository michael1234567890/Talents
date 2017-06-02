package com.phincon.talents.app.controllers.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;

@RestController
@RequestMapping("api")
public class CompanyReferenceController {
	@Autowired
	UserRepository userRepository;

	
	
	@RequestMapping(value = "/user/companyreference", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DataApproval>> getCompanyReference(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		Long companyId = user.getCompany();
		// get workflow record with task name
		
		List<DataApproval> listDataApproval =  null; //dataApprovalService.findByEmployee(user.getEmployee());
		return new ResponseEntity<List<DataApproval>>(listDataApproval, HttpStatus.OK);

	}
	

}
