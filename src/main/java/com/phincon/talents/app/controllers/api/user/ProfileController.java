package com.phincon.talents.app.controllers.api.user;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.CompanyReferenceRepository;
import com.phincon.talents.app.dao.CompanyRepository;
import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dao.DataApprovalRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.ProfileDTO;
import com.phincon.talents.app.model.Company;
import com.phincon.talents.app.model.CompanyReference;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.VwEmpAssignmentService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class ProfileController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	EmployeeService employeeService;

	@Autowired
	VwEmpAssignmentService assignmentService;

	@Autowired
	CompanyReferenceRepository companyReferenceRepository;

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	DataApprovalRepository dataApprovalRepository;
	
	@Autowired
	CompanySettingsRepository companySettingsRepository;

	@RequestMapping(value = "/user/profile/changeprofile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> changePhotoProfile(
			@RequestBody ProfileDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		if (request.getImage() == null) {
			throw new RuntimeException("Image can't empty");
		}
		String pathname = "profile/" + RandomStringUtils.randomAlphanumeric(10)
				+ "." + Utils.UPLOAD_IMAGE_TYPE;
		Utils.createImage(request.getImage(), pathname);
		
		user.setPhotoProfile(pathname);
		userRepository.save(user);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Photo Profile successfully to save", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/info", method = RequestMethod.GET)
	public ResponseEntity<ProfileDTO> myInfo(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		ProfileDTO profile = new ProfileDTO();
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());

		if (user.getEmployee() != null) {
			Employee employee = employeeService
					.findEmployee(user.getEmployee());
			VwEmpAssignment assignment = assignmentService
					.findAssignmentByEmployee(employee.getId());
			if (assignment != null)
				employee.setAssignment(assignment);
			profile.setEmployeeTransient(employee);
		}

		

		return new ResponseEntity<ProfileDTO>(profile, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/profile", method = RequestMethod.GET)
	public ResponseEntity<ProfileDTO> myprofile(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		ProfileDTO profile = new ProfileDTO();
		profile.setFirstName(user.getFirstName());
		profile.setLastName(user.getLastName());

		if (user.getEmployee() != null) {
			Employee employee = employeeService
					.findEmployee(user.getEmployee());
			VwEmpAssignment assignment = assignmentService
					.findAssignmentByEmployee(employee.getId());
			if (assignment != null)
				employee.setAssignment(assignment);

			// user.setEmployeeTransient(employee);
			profile.setEmployeeTransient(employee);
		}

		List<CompanyReference> listCompanyReference = companyReferenceRepository
				.findByCompany(user.getCompany());

		if (listCompanyReference != null)
			profile.setCompanyReference(listCompanyReference);

		List<CompanySettings> listCompanySettings = companySettingsRepository.findByCompany(user.getCompany());

		if (listCompanySettings != null && listCompanySettings.size() > 0){
			CompanySettings companySettings = listCompanySettings.get(0);
			profile.setCompanySettings(companySettings);
		}
	
		String emp = "#"+user.getEmployee()+"#";
		List<Long> countNeedApproval = dataApprovalRepository.countNeedApproval(emp, DataApproval.NOT_COMPLETED, user.getCompany());
		if(countNeedApproval != null && countNeedApproval.size() > 0) {
			for (Long objects : countNeedApproval) {
				profile.setNeedApproval(objects);
				
			}
		}
	
		Company company = companyRepository.findOne(user.getCompany());
		profile.setCompany(company);
		String image = null;
		if(user.getPhotoProfile() != null && !user.getPhotoProfile().equals("")) {
			image = Utils
					.convertImageToBase64(user.getPhotoProfile());
		}
		profile.setImage(image);

		return new ResponseEntity<ProfileDTO>(profile, HttpStatus.OK);
	}

}
