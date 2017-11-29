package com.phincon.talents.app.controllers.api.user;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dao.DataApprovalRepository;
import com.phincon.talents.app.dao.EmploymentRepository;
import com.phincon.talents.app.dao.RequestCategoryTypeRepository;
import com.phincon.talents.app.dao.RequestTypeRepository;
import com.phincon.talents.app.dao.TMRequestHeaderRepository;
import com.phincon.talents.app.dao.TMRequestRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.BenefitDTO;
import com.phincon.talents.app.dto.TotalCategoryDTO;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.RequestCategoryType;
import com.phincon.talents.app.model.hr.RequestType;
import com.phincon.talents.app.model.hr.TMRequestHeader;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.TMRequestHeaderService;
import com.phincon.talents.app.services.TMRequestService;
import com.phincon.talents.app.services.VwEmpAssignmentService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class TMRequestController {
	@Autowired
	TMRequestHeaderService tmRequestHeaderService;
	
	@Autowired
	TMRequestService tmRequestService;

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
	DataApprovalRepository dataApprovalRepository;
	
	@Autowired
	CompanySettingsRepository companySettingRepository;
	
	@Autowired
	DataApprovalService dataApprovalService;
	

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
			throw new CustomException(
					"Your Employment ID is not Found.");

		Employment employment = listEmployment.get(0);
		Employment requester = listEmployment.get(0);

		tmRequestHeaderService.createBenefit(request, user, employment,
				requester);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Your request submitted successfully", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/tmrequestheader/verificationleave", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BenefitDTO> verificationAttendance(
			@RequestBody BenefitDTO request, OAuth2Authentication authentication) {

//		if(true)
//			throw new CustomException("This Feature Will Available Soon");
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<Employment> listEmployment = employmentRepository
				.findByEmployee(user.getEmployee());
		if (listEmployment == null || listEmployment.size() == 0)
			throw new CustomException(
					"Your Employment ID is not Found.");

		Employment employment = listEmployment.get(0);
		Employment requester = listEmployment.get(0);
		tmRequestHeaderService.verificationAttendance(request, user, employment, requester);
		return new ResponseEntity<BenefitDTO>(request, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/tmrequestheader/leave", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> createAttendance(
			@RequestBody BenefitDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<Employment> listEmployment = employmentRepository
				.findByEmployee(user.getEmployee());
		
		if (listEmployment.size() == 0)
			throw new CustomException(
					"Your Employment ID is not Found.");
		
		if(request.getEmployee() == null) {
			throw new CustomException(
					"Employee Param is  not Found.");
		}
		
		if(request.getWorkflow() == null) {
			throw new CustomException(
					"Workflow Param is  not Found.");
		}
		
		
		List<Employment> listEmploymentEmployee = employmentRepository
				.findByEmployee(request.getEmployee());
		
		if (listEmploymentEmployee.size() == 0)
			throw new CustomException(
					"Your Employment Employee ID is not Found.");
		
		Employment employment = listEmploymentEmployee.get(0);
		Employment requester = listEmployment.get(0);

		tmRequestHeaderService.createTimeAttendance(request, user, employment, requester);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Your request submitted successfully", false), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/user/tmrequestheader/verificationbenefit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BenefitDTO> verificationBenefit(
			@RequestBody BenefitDTO request, OAuth2Authentication authentication) {

//		if(true)
//			throw new CustomException("This Feature Will Available Soon");
//		
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<Employment> listEmployment = employmentRepository
				.findByEmployee(user.getEmployee());
		if (listEmployment == null || listEmployment.size() == 0)
			throw new CustomException(
					"Your Employment ID is not Found.");

		Employment employment = listEmployment.get(0);
		Employment requester = listEmployment.get(0);

		tmRequestHeaderService.verificationBenefit(request, user, employment,
				requester);
		return new ResponseEntity<BenefitDTO>(request, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/user/tmrequest/category", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<RequestCategoryType>> getRequestCategory(@RequestParam(value = "module", required = false) String module,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		module = module.toLowerCase();
		List<RequestCategoryType> listCategorySelected = new ArrayList<RequestCategoryType>();
		
		if(module.equals("benefit")) {
			// get grade and position
			VwEmpAssignment assignment = assignmentService
					.findAssignmentWithGradeByEmployee(user.getEmployee());
			if (assignment != null) {
				// Load List RequestCategoryType By Company
				List<RequestCategoryType> listCategory = requestCategoryTypeRepository
						.findByCompanyAndModule(user.getCompany(),module);
				// Load List RequestType by Company
				List<RequestType> listRequestTypes = requestTypeRepository
						.findByCompanyAndModuleAndActive(user.getCompany(),module,true);
				for (RequestCategoryType requestCategoryType : listCategory) {
					// find request type based on criteria and added to
					int grade = 0;
					if (assignment.getGradeNominal() != null)
						grade = assignment.getGradeNominal();

					addRequestTypeToCategory(requestCategoryType, listRequestTypes,
							grade, assignment.getPositionName(),false);
					if (requestCategoryType.getListRequestType().size() > 0)
						listCategorySelected.add(requestCategoryType);
				}
			}
		}else{
			List<RequestCategoryType> listCategory =requestCategoryTypeRepository
					.findByCompanyAndModule(user.getCompany(),module);
			List<RequestType> listRequestTypes = requestTypeRepository
					.findByCompanyAndModuleAndActive(user.getCompany(),module,true);
			for (RequestCategoryType requestCategoryType : listCategory) {
				// find request type based on criteria and added to
				int grade = 0;
				addRequestTypeToCategory(requestCategoryType, listRequestTypes,
						grade, null,true);
				if (requestCategoryType.getListRequestType().size() > 0)
					listCategorySelected.add(requestCategoryType);
			}
		}
		
		return new ResponseEntity<List<RequestCategoryType>>(
				listCategorySelected, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/tmrequest/type", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<RequestType>> getRequestType(@RequestParam(value = "module", required = true) String module,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<RequestType> listRequestTypes = requestTypeRepository
					.findByCompanyAndModuleAndActive(user.getCompany(),module,true);
			
		return new ResponseEntity<List<RequestType>>(
				listRequestTypes, HttpStatus.OK);
	}


	private void addRequestTypeToCategory(
			RequestCategoryType requestCategoryType,
			List<RequestType> listRequestTypes, Integer grade, String position, boolean autoAdded) {

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
				
				if(autoAdded)
					added = true;

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
	
	@RequestMapping(value = "/user/tmrequest/totalamount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TotalCategoryDTO>> sumTotalAmount(
			@RequestParam(value = "module", required = false) String module,@RequestParam(value = "fromdate", required = false) String fromDateStr,@RequestParam(value = "todate", required = false) String toDateStr,
			OAuth2Authentication authentication) {
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		Date fromDate = null;Utils.convertStringToDate(fromDateStr);
		Date toDate = null; Utils.convertStringToDate(toDateStr);
		if(fromDateStr != null && toDateStr != null) {
			fromDate = Utils.convertStringToDate(fromDateStr);
			toDate = Utils.convertStringToDate(toDateStr);
		}else {
			List<CompanySettings> listCompanySettings = companySettingRepository.findByCompany(user.getCompany());
			if(listCompanySettings == null || listCompanySettings.size()==0)
				throw new CustomException(
						"Company Setting is not found.");
			CompanySettings companySettings = listCompanySettings.get(0);
			fromDate = companySettings.getPeriodStartDate();
			toDate = companySettings.getPeriodEndDate();
			
		}
		
		if(fromDate == null || toDate == null) {
			throw new CustomException(
					"Param fromDate and toDate can't be empty.");

		}
		
		List<TotalCategoryDTO> result = null;
		if (module == null) {
			result = tmRequestHeaderRepository.sumTotalAmountByCategoryAndRangeDate(user.getCompany(), user.getEmployee(),fromDate,toDate);
		} else {
			result = tmRequestHeaderRepository.sumTotalAmountByModuleAndCategoryAndRangeDate(user.getCompany(), user.getEmployee(), module, fromDate, toDate);
		}

		return new ResponseEntity<List<TotalCategoryDTO>>(result,
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/tmrequest/bystatus", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TMRequestHeader>> listRequestByStatus(
			@RequestParam(value = "module", required = false) String module,@RequestParam(value = "status", required = true) String status,
			OAuth2Authentication authentication) {
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<TMRequestHeader> listRequest = null;

		if (module == null) {
			listRequest = tmRequestHeaderRepository.findByCompanyAndEmployeeAndStatus(
					user.getCompany(), user.getEmployee(),status);
		} else {
			listRequest = tmRequestHeaderRepository
					.findByCompanyAndEmployeeAndModuleAndStatus(user.getCompany(),
							user.getEmployee(), module, status);
		}

		return new ResponseEntity<List<TMRequestHeader>>(listRequest,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/tmrequest/findRequestNo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TMRequestHeader>> findByRequestNo(
			@RequestParam(value = "module", required = false) String module,@RequestParam(value = "requestNo", required = true) String requestNo,
			OAuth2Authentication authentication, HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<TMRequestHeader> listRequest = null;
		if (module == null) {
			listRequest = tmRequestHeaderRepository.findByCompanyAndRequestNoLike(user.getCompany(), requestNo);
		} else {
			listRequest = tmRequestHeaderRepository
					.findByCompanyAndModuleAndRequestNoLike(user.getCompany(),requestNo, module);
		}
		
		
		
		if(listRequest!= null && listRequest.size() > 0) {
			for (TMRequestHeader tmRequestHeader : listRequest) {
				 // find data Approval based on request
				List<DataApproval> listDataApproval = dataApprovalService.findByCompanyAndObjectNameAndObjectRef(user.getCompany(), TMRequestHeader.class.getSimpleName(), tmRequestHeader.getId(), request);
				if(listDataApproval != null && listDataApproval.size() > 0) {
					DataApproval dataApproval = listDataApproval.get(0);
					tmRequestHeader.setDataApproval(dataApproval);
				}
			}
		}

		return new ResponseEntity<List<TMRequestHeader>>(listRequest,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/user/tmrequest/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<TMRequestHeader> detailRequest(
			@PathVariable("id") Long id, OAuth2Authentication authentication, HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		TMRequestHeader objRequest ;
		if((user.getIsAdmin()!=null && user.getIsAdmin()) || (user.getIsLeader() != null && user.getIsLeader())) {
				objRequest = tmRequestHeaderService.findByIdWithDetail(id,null);
		}else {
			objRequest = tmRequestHeaderService.findByIdWithDetail(id,user.getEmployee());
		}
		
		List<DataApproval> listDataApproval = dataApprovalService.findByCompanyAndObjectNameAndObjectRef(user.getCompany(), TMRequestHeader.class.getSimpleName(), objRequest.getId(), request);
		if(listDataApproval != null && listDataApproval.size() > 0) {
			DataApproval dataApproval = listDataApproval.get(0);
			objRequest.setDataApproval(dataApproval);
		}
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
