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

import com.phincon.talents.app.dao.DataApprovalRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.ApprovalWorkflowDTO;
import com.phincon.talents.app.dto.CountObjectDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.AttachmentDataApproval;
import com.phincon.talents.app.model.DataApproval;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.services.AttachmentDataApprovalService;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.FamilyService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class WorkflowController {
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
	AttachmentDataApprovalService attachmentDataApprovalService;

	@RequestMapping(value = "/user/workflow/dataapproval", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> submitDataApproval(
			@RequestBody DataApprovalDTO request,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		// get workflow record with task name
		String taskName = request.getTask();
		Workflow workflow = workflowService.findByCodeAndCompanyAndActive(taskName,
				user.getCompany(), true);
		if (workflow == null) {
			throw new RuntimeException("Error : Your workflow activity is Not Registered.");
		}
		dataApprovalService.save(request, user, workflow);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Submit Activity successfully. Please waiting approval", false), HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/user/workflow/myrequest", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DataApproval>> myRequest(@RequestParam(value="module",required=false) String module,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<DataApproval> listDataApproval = null;
		
		if(module == null){
			listDataApproval  = dataApprovalService.findByEmployee(user.getEmployee());
		}else {
			listDataApproval  = dataApprovalService.findByEmployeeAndModule(user.getEmployee(),module);
		}
		
		return new ResponseEntity<List<DataApproval>>(listDataApproval, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/user/workflow/needapproval", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<DataApproval>> listDataApproval(@RequestParam(value="module",required=false) String module,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		// get workflow record with task name
		String strEmployee = "#" + user.getEmployee() + "#";
		List<DataApproval> listDataApproval = null;
		if(module == null)
			listDataApproval = dataApprovalService.findNeedApproval(strEmployee, DataApproval.NOT_COMPLETED, user.getCompany(),null);
		else
			listDataApproval = dataApprovalService.findNeedApproval(strEmployee, DataApproval.NOT_COMPLETED, user.getCompany(),module);
		
		return new ResponseEntity<List<DataApproval>>(listDataApproval, HttpStatus.OK);

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
		String emp = "#"+user.getEmployee()+"#";
		List<Long> countNeedApproval = dataApprovalRepository.countNeedApproval(emp, DataApproval.NOT_COMPLETED, user.getCompany());
		if(countNeedApproval != null && countNeedApproval.size() > 0) {
			for (Long objects : countNeedApproval) {
				obj.setCount(objects);
			}
		}
		
		return new ResponseEntity<CountObjectDTO>(obj, HttpStatus.OK);

	}
	
	
	
	
	@RequestMapping(value = "/user/workflow/dataapproval/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<DataApproval> detailApproval(@PathVariable("id") Long id,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		DataApproval dataApproval = dataApprovalService.findById(id);
		List<AttachmentDataApproval> listAttachmentDataApproval = attachmentDataApprovalService
				.findByDataApproval(dataApproval.getId());
		List<AttachmentDataApproval> tempAttachmentDataApproval = new ArrayList<AttachmentDataApproval>();
		if (listAttachmentDataApproval != null
				&& listAttachmentDataApproval.size() > 0)
			for (AttachmentDataApproval attachmentDataApproval : listAttachmentDataApproval) {
				if(!attachmentDataApproval.getPath().equals(null)) {
					String base64String = Utils.convertImageToBase64(attachmentDataApproval.getPath());
					attachmentDataApproval.setImage(base64String);
				}
				tempAttachmentDataApproval.add(attachmentDataApproval);
			}
		dataApproval.setAttachments(tempAttachmentDataApproval);
		
		return new ResponseEntity<DataApproval>(dataApproval, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/user/attachmentdataapproval/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<AttachmentDataApproval> detailAttachmentDataApproval(@PathVariable("id") Long id,
			OAuth2Authentication authentication) {

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
		if(request.getStatus().equals(DataApproval.CANCELLED)) {
			dataApprovalService.cancelRequest(request,user);
			message = "Request has been cancelled";
		}else {
			dataApprovalService.approval(request, user);
		}
		
		if(request.getStatus().equals(DataApproval.REJECTED)) 
			 message = "Request has been Rejected";
		
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				message, false), HttpStatus.OK);

	}

}
