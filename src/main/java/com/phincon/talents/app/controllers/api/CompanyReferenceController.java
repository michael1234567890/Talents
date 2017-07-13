package com.phincon.talents.app.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.CompanyReferenceRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.CompanyReference;
import com.phincon.talents.app.model.User;

@RestController
@RequestMapping("api")
public class CompanyReferenceController {
	@Autowired
	CompanyReferenceRepository companyReferenceRepository;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/user/companyreference", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<CompanyReference>> getListCategoryName(
			@RequestParam(value = "category", required = false) String category,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<CompanyReference> listCategoryType = null;
		if (category == null)
			listCategoryType = companyReferenceRepository.findByCompany(user
					.getCompany());
		else
			listCategoryType = companyReferenceRepository
					.findByCompanyAndCategory(user.getCompany(), category);

		return new ResponseEntity<List<CompanyReference>>(listCategoryType,
				HttpStatus.OK);
	}

}
