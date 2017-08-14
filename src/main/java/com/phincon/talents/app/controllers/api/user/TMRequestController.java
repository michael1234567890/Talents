package com.phincon.talents.app.controllers.api.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.EmploymentRepository;
import com.phincon.talents.app.dao.RequestCategoryTypeRepository;
import com.phincon.talents.app.dao.RequestTypeRepository;
import com.phincon.talents.app.dao.TMRequestHeaderRepository;
import com.phincon.talents.app.dao.TMRequestRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.BenefitDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.RequestCategoryType;
import com.phincon.talents.app.model.hr.RequestType;
import com.phincon.talents.app.model.hr.TMRequestHeader;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.services.TMRequestHeaderService;
import com.phincon.talents.app.services.VwEmpAssignmentService;
import com.phincon.talents.app.utils.CustomMessage;

@RestController
@RequestMapping("api")
public class TMRequestController {
	@Autowired
	TMRequestHeaderService tmRequestHeaderService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	VwEmpAssignmentService assignmentService;

	@Autowired
	EmploymentRepository employmentRepository;

	@Autowired
	TMRequestRepository tmRequestRepository;

	@Autowired
	TMRequestHeaderRepository tmRequestHeaderRepository;

	@Autowired
	RequestCategoryTypeRepository requestCategoryTypeRepository;

	@Autowired
	RequestTypeRepository requestTypeRepository;

	@RequestMapping(value = "/user/tmrequestheader/benefit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> createBenefit(
			@RequestBody BenefitDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<Employment> listEmployment = employmentRepository
				.findByEmployee(user.getEmployee());
		if (listEmployment == null && listEmployment.size() == 0)
			throw new RuntimeException(
					"Your Employment ID is not Found.");

		Employment employment = listEmployment.get(0);
		Employment requester = listEmployment.get(0);

		tmRequestHeaderService.createBenefit(request, user, employment,
				requester);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Your request submitted successfully", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/tmrequestheader/verificationbenefit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BenefitDTO> verificationBenefit(
			@RequestBody BenefitDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<Employment> listEmployment = employmentRepository
				.findByEmployee(user.getEmployee());
		if (listEmployment == null || listEmployment.size() == 0)
			throw new RuntimeException(
					"Your Employment ID is not Found.");

		Employment employment = listEmployment.get(0);
		Employment requester = listEmployment.get(0);

		tmRequestHeaderService.verificationBenefit(request, user, employment,
				requester);
		return new ResponseEntity<BenefitDTO>(request, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/tmrequest/type", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<RequestCategoryType>> getRequestType(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		// get grade and position
		VwEmpAssignment assignment = assignmentService
				.findAssignmentWithGradeByEmployee(user.getEmployee());

		List<RequestCategoryType> listCategorySelected = new ArrayList<RequestCategoryType>();
		if (assignment != null) {
			// Load List RequestCategoryType By Company
			List<RequestCategoryType> listCategory = requestCategoryTypeRepository
					.findByCompany(user.getCompany());

			// Load List RequestType by Company
			List<RequestType> listRequestTypes = requestTypeRepository
					.findByCompany(user.getCompany());
			for (RequestCategoryType requestCategoryType : listCategory) {
				// find request type based on criteria and added to
				// listRequestTypesSelected
				int grade = 0;
				if (assignment.getGradeNominal() != null)
					grade = assignment.getGradeNominal();

				addRequestTypeToCategory(requestCategoryType, listRequestTypes,
						grade, assignment.getPositionName());
				if (requestCategoryType.getListRequestType().size() > 0)
					listCategorySelected.add(requestCategoryType);

			}
		}

		return new ResponseEntity<List<RequestCategoryType>>(
				listCategorySelected, HttpStatus.OK);
	}

	private void addRequestTypeToCategory(
			RequestCategoryType requestCategoryType,
			List<RequestType> listRequestTypes, Integer grade, String position) {

		List<RequestType> listRequestTypeSelected = new ArrayList<RequestType>();
		for (RequestType requestType : listRequestTypes) {
			if (requestCategoryType.getId() == requestType
					.getRequestCategoryType()) {
				boolean added = false;
				if (requestType.getGrade() != null
						&& requestType.getGrade() > 0) {
					if (grade >= requestType.getGrade())
						added = true;
					else
						added = false;
				}

				if (requestType.getPosition() != null
						&& !requestType.getPosition().equals("")) {
					if (position != null) {
						String upperPosition = position.toUpperCase();
						if (requestType.getPosition().contains(upperPosition))
							added = true;
						else
							added = false;
					}

				}

				if (added)
					listRequestTypeSelected.add(requestType);
			}
		}
		requestCategoryType.setListRequestType(listRequestTypeSelected);
	}

	@RequestMapping(value = "/user/tmrequest", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TMRequestHeader>> listRequest(
			@RequestParam(value = "module", required = false) String module,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<TMRequestHeader> listRequest = null;

		if (module == null) {
			listRequest = tmRequestHeaderRepository.findByCompanyAndEmployee(
					user.getCompany(), user.getEmployee());
		} else {
			listRequest = tmRequestHeaderRepository
					.findByCompanyAndEmployeeAndModule(user.getCompany(),
							user.getEmployee(), module);
		}

		return new ResponseEntity<List<TMRequestHeader>>(listRequest,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/user/tmrequest/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<TMRequestHeader> detailRequest(
			@PathVariable("id") Long id, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		TMRequestHeader objRequest = tmRequestHeaderService.findByIdWithDetail(id,user.getEmployee());
		return new ResponseEntity<TMRequestHeader>(objRequest, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/tmrequest/needreport", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TMRequestHeader>> listNeedReport(
			@RequestParam(value = "module", required = false) String module,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<TMRequestHeader> listRequest = tmRequestHeaderRepository
				.findByCompanyAndEmployeeAndModuleAndNeedReport(
						user.getCompany(), user.getEmployee(), module);

		if (module == null) {
			listRequest = tmRequestHeaderRepository
					.findByCompanyAndEmployeeAndNeedReport(user.getCompany(),
							user.getEmployee());
		} else {
			listRequest = tmRequestHeaderRepository
					.findByCompanyAndEmployeeAndModuleAndNeedReport(
							user.getCompany(), user.getEmployee(), module);
		}

		return new ResponseEntity<List<TMRequestHeader>>(listRequest,
				HttpStatus.OK);
	}

}
