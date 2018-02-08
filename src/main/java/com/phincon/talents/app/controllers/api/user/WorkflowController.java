package com.phincon.talents.app.controllers.api.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
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
import com.phincon.talents.app.dao.DataApprovalRepository;
import com.phincon.talents.app.dao.FamilyRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dao.VwHistDataApprovalTMRequestHeaderRepository;
import com.phincon.talents.app.dto.ApprovalWorkflowDTO;
import com.phincon.talents.app.dto.CountObjectDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.AttachmentDataApproval;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.VwHistDataApprovalTMRequestHeader;
import com.phincon.talents.app.services.AttachmentDataApprovalService;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.FamilyService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.LoadResult;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class WorkflowController {
	private final int PAGE_START = 0;
	private final int SIZE = 25;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FamilyService familyService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	DataApprovalService dataApprovalService;

	@Autowired
	DataApprovalRepository dataApprovalRepository;
	
	@Autowired
	VwHistDataApprovalTMRequestHeaderRepository vwHistoryApprovalRepository;

	@Autowired
	AttachmentDataApprovalService attachmentDataApprovalService;
	
	@Autowired
	FamilyRepository familyRepository;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/user/workflow/dataapproval", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> submitDataApproval(
			@RequestBody DataApprovalDTO request,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		// get workflow record with task name
		String taskName = request.getTask();
		Workflow workflow = workflowService.findByCodeAndCompanyAndActive(
				taskName, user.getCompany(), true);
		if (workflow == null) {
			throw new CustomException(
					"Your workflow activity is Not Registered.");
		}
		dataApprovalService.save(request, user, workflow);
		return new ResponseEntity<CustomMessage>(
				new CustomMessage(
						"Submit Activity successfully. Please waiting approval",
						false), HttpStatus.OK);

	}

	@RequestMapping(value = "/user/workflow/myrequest", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<LoadResult<List<DataApproval>>> myRequest(
			@RequestParam(value = "module", required = false) String module,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			OAuth2Authentication authentication, HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		LoadResult<List<DataApproval>> loadResult = new LoadResult<List<DataApproval>>();

		int pageData = PAGE_START;
		int sizeData = SIZE;

		if (size != null)
			sizeData = size;

		if (page != null)
			pageData = page;

		PageRequest pageRequest = new PageRequest(pageData, sizeData);
		loadResult.setPage(pageData);
		loadResult.setSize(sizeData);

		List<DataApproval> listDataApproval = null;
		Long totalRecord = 0L;
		List<Long> listTotalRecord = null;

		if (module == null) {
			listDataApproval = dataApprovalService.findByEmployee(
					user.getEmployee(), request,pageRequest);
			listTotalRecord = dataApprovalRepository.countByEmpRequest(user.getEmployee());
			
		} else {
			listDataApproval = dataApprovalService.findByEmployeeAndModule(
					user.getEmployee(), module, request,pageRequest);
			listTotalRecord = dataApprovalRepository
					.countByEmpRequestAndModule(user.getEmployee(), module);
		}
		
		if (listTotalRecord != null && listTotalRecord.size() > 0) {
			for (Long objects : listTotalRecord) {
				totalRecord = objects;
			}
		}

		loadResult.setData(listDataApproval);
		loadResult.setTotalRecord(totalRecord);

		return new ResponseEntity<LoadResult<List<DataApproval>>>(loadResult,
				HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/workflow/needapproval", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<LoadResult> listDataApproval(
			@RequestParam(value = "module", required = false) String module,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			OAuth2Authentication authentication, HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		LoadResult loadResult = new LoadResult<List<DataApproval>>();

		int pageData = PAGE_START;
		int sizeData = SIZE;

		if (size != null)
			sizeData = size;

		if (page != null)
			pageData = page;

		PageRequest pageRequest = new PageRequest(pageData, sizeData);
		loadResult.setPage(pageData);
		loadResult.setSize(sizeData);

		// get workflow record with task name
		String strEmployee = "#" + user.getEmployee() + "#";
		List<DataApproval> listDataApproval = null;
		Long totalRecord = 0L;
		List<Long> listTotalRecord = null;
		if (module == null) {
			listDataApproval = dataApprovalService.findNeedApproval(
					strEmployee, DataApproval.NOT_COMPLETED, user.getCompany(),
					null, pageRequest, request);
			listTotalRecord = dataApprovalRepository.countNeedApproval(
					strEmployee, DataApproval.NOT_COMPLETED, user.getCompany());

		} else {
			listDataApproval = dataApprovalService.findNeedApproval(
					strEmployee, DataApproval.NOT_COMPLETED, user.getCompany(),
					module, pageRequest, request);
			listTotalRecord = dataApprovalRepository
					.countNeedApprovalAndModule(strEmployee,
							DataApproval.NOT_COMPLETED, user.getCompany(),
							module);
		}

		if (listTotalRecord != null && listTotalRecord.size() > 0) {
			for (Long objects : listTotalRecord) {
				totalRecord = objects;
			}
		}

		loadResult.setData(listDataApproval);
		loadResult.setTotalRecord(totalRecord);
		return new ResponseEntity<LoadResult>(loadResult, HttpStatus.OK);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/workflow/allapproval", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<LoadResult> listDataApprovalAll(
			@RequestParam(value = "module", required = false) String module,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			OAuth2Authentication authentication, HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		LoadResult loadResult = new LoadResult<List<DataApproval>>();

		int pageData = PAGE_START;
		int sizeData = SIZE;

		if (size != null)
			sizeData = size;

		if (page != null)
			pageData = page;

		PageRequest pageRequest = new PageRequest(pageData, sizeData);
		loadResult.setPage(pageData);
		loadResult.setSize(sizeData);

		// get workflow record with task name
		String strEmployee = "#" + user.getEmployee() + "#";
		List<DataApproval> listDataApproval = null;
		Long totalRecord = 0L;
		List<Long> listTotalRecord = null;
		if (module == null) {
			listDataApproval = dataApprovalService.findAll(
					strEmployee, user.getCompany(),
					null, pageRequest, request);
			listTotalRecord = dataApprovalRepository.countAll(
					strEmployee, user.getCompany());

		} else {
			listDataApproval = dataApprovalService.findAll(
					strEmployee, user.getCompany(),
					module, pageRequest, request);
			listTotalRecord = dataApprovalRepository
					.countAllModule(strEmployee,
							user.getCompany(),
							module);
		}

		if (listTotalRecord != null && listTotalRecord.size() > 0) {
			for (Long objects : listTotalRecord) {
				totalRecord = objects;
			}
		}

		loadResult.setData(listDataApproval);
		loadResult.setTotalRecord(totalRecord);
		return new ResponseEntity<LoadResult>(loadResult, HttpStatus.OK);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/user/workflow/alldataapproval", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<LoadResult> listDataApprovalAll2(
			@RequestParam(value = "module", required = false) String module,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			OAuth2Authentication authentication, HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		LoadResult loadResult = new LoadResult<List<DataApproval>>();

		int pageData = PAGE_START;
		int sizeData = SIZE;

		if (size != null)
			sizeData = size;

		if (page != null)
			pageData = page;

		PageRequest pageRequest = new PageRequest(pageData, sizeData);
		loadResult.setPage(pageData);
		loadResult.setSize(sizeData);

		// get workflow record with task name
//		String strEmployee = "#" + user.getEmployee() + "#";
		List<DataApproval> listDataApproval = null;
		Long totalRecord = 0L;
		List<Long> listTotalRecord = null;
		if (module == null) {
			listDataApproval = dataApprovalService.findAllApproval(
					user.getCompany(),
					null, pageRequest, request);
			listTotalRecord = dataApprovalRepository.countAllApproval(
					user.getCompany());

		} else {
			listDataApproval = dataApprovalService.findAllApproval(
					user.getCompany(),
					module, pageRequest, request);
			listTotalRecord = dataApprovalRepository
					.countAllApprovalModule(
							user.getCompany(),
							module);
		}

		if (listTotalRecord != null && listTotalRecord.size() > 0) {
			for (Long objects : listTotalRecord) {
				totalRecord = objects;
			}
		}

		loadResult.setData(listDataApproval);
		loadResult.setTotalRecord(totalRecord);
		return new ResponseEntity<LoadResult>(loadResult, HttpStatus.OK);

	}

	@RequestMapping(value = "/user/workflow/countneedapproval", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CountObjectDTO> CountDataApproval(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		CountObjectDTO obj = new CountObjectDTO();
		obj.setName("count need approval");
		obj.setCount(0L);
		String emp = "#" + user.getEmployee() + "#";
		List<Long> countNeedApproval = dataApprovalRepository
				.countNeedApproval(emp, DataApproval.NOT_COMPLETED,
						user.getCompany());
		if (countNeedApproval != null && countNeedApproval.size() > 0) {
			for (Long objects : countNeedApproval) {
				obj.setCount(objects);
			}
		}

		return new ResponseEntity<CountObjectDTO>(obj, HttpStatus.OK);

	}

	@RequestMapping(value = "/user/workflow/dataapproval/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DataApproval> detailApproval(
			@PathVariable("id") Long id, OAuth2Authentication authentication,
			HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		boolean bypass = false;
		if((user.getIsAdmin() != null && user.getIsAdmin()) || (user.getIsLeader()!=null&& user.getIsLeader()) || (user.getIsHr() != null && user.getIsHr())) {
			bypass = true;
		}
		
		DataApproval dataApproval = dataApprovalService.findById(id, user, bypass);
		
		if(dataApproval == null) {
			throw new CustomException("Data Approval is not found.");
		}
		
		
		// User
		User userEmployee = userRepository.findByEmployee(dataApproval.getEmpRequest());
		// ambil foto profile
		String imageProfile = null;
		if (userEmployee != null && userEmployee.getPhotoProfile() != null && !userEmployee.getPhotoProfile().equals("")) {
			String http = env.getProperty("talents.protocol");
			imageProfile = Utils.getUrlAttachment(http, request, userEmployee.getPhotoProfile());
		}

		dataApproval.setEmployeeRequestPhotoProfile(imageProfile);
		List<AttachmentDataApproval> listAttachmentDataApproval = attachmentDataApprovalService
				.findByDataApproval(dataApproval.getId());
		List<AttachmentDataApproval> tempAttachmentDataApproval = new ArrayList<AttachmentDataApproval>();
		if (listAttachmentDataApproval != null
				&& listAttachmentDataApproval.size() > 0)
			for (AttachmentDataApproval attachmentDataApproval : listAttachmentDataApproval) {
				if (attachmentDataApproval.getPath() != null) {
					// String base64String =
					// Utils.convertImageToBase64(attachmentDataApproval.getPath());
					// attachmentDataApproval.setImage(base64String);
					String http = env.getProperty("talents.protocol");
					String image = Utils.getUrlAttachment(http, request,
							attachmentDataApproval.getPath());
					attachmentDataApproval.setImage(image);
				}
				tempAttachmentDataApproval.add(attachmentDataApproval);
			}
		dataApproval.setAttachments(tempAttachmentDataApproval);
		
		if(dataApproval.getRequestForFamily() != null){
			Family family = familyRepository.findOne(dataApproval.getRequestForFamily());
			
			dataApproval.setRequestForFamilyName(family.getName());
			dataApproval.setRequestForFamilyRelationhip(family.getRelationship());
		}


		return new ResponseEntity<DataApproval>(dataApproval, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/workflow/dataapproval/historyapproval", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<VwHistDataApprovalTMRequestHeader>> historyApproval(@RequestParam(value = "fromdate", required = true) String strFromDate, @RequestParam(value = "todate", required = true)  String strToDate, OAuth2Authentication authentication,
			HttpServletRequest request) {
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication.getUserAuthentication().getName());
		if(strFromDate == null || strToDate == null) {
			throw new CustomException("Parameter fromDate and toDate must be defined.");
		}
		
		Date fromDate = Utils.convertStringToDateTime(strFromDate);
		Date toDate = Utils.convertStringToDateTime(strToDate);
		List<VwHistDataApprovalTMRequestHeader> listHistoryApproval = vwHistoryApprovalRepository.findByEmployeeApprovalAndCompanyAndRangeDate(user.getEmployee(), user.getCompany(), fromDate, toDate);
		
		if(listHistoryApproval == null) {
			throw new CustomException("Approval History not found.");
		}
		
		return new ResponseEntity<List<VwHistDataApprovalTMRequestHeader>>(listHistoryApproval, HttpStatus.OK);
	}

	

	@RequestMapping(value = "/user/attachmentdataapproval/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<AttachmentDataApproval> detailAttachmentDataApproval(
			@PathVariable("id") Long id, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		AttachmentDataApproval obj = attachmentDataApprovalService.findById(id);
		obj.setImage(Utils.convertImageToBase64(obj.getPath()));
		return new ResponseEntity<AttachmentDataApproval>(obj, HttpStatus.OK);

	}

	@RequestMapping(value = "/user/workflow/actionapproval", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> actionApproval(
			@RequestBody ApprovalWorkflowDTO request,
			OAuth2Authentication authentication) {
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		String message = "Request has been approved";
		boolean isBypass = false;
	
		/*
		if ((user.getIsAdmin() != null && user.getIsAdmin()) || (user.getIsLeader() != null && user.getIsLeader()))
				isBypass = true;
			
		*/
		
		if ((user.getIsHr() != null && user.getIsHr()))
				isBypass = true;
	
		
		dataApprovalService.approval(request, user, isBypass);
		
		if (request.getStatus().equals(DataApproval.REJECTED))
			message = "Request has been Rejected";
		else if(request.getStatus().equals(DataApproval.CANCELLED))
			message = "Request has been cancelled";

		return new ResponseEntity<CustomMessage>(new CustomMessage(message,
				false), HttpStatus.OK);

	}
	
	
}
