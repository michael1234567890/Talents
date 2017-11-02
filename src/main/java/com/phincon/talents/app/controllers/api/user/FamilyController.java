package com.phincon.talents.app.controllers.api.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.dto.FamilyDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.FamilyTemp;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.FamilyService;
import com.phincon.talents.app.services.FamilyTempService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;

@RestController
@RequestMapping("api")
public class FamilyController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	FamilyService familyService;
	
	@Autowired
	FamilyTempService familyTempService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	DataApprovalService dataApprovalService;

	@RequestMapping(value = "/user/family", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> addFamily(
			@RequestBody FamilyDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		Family family = new Family();
		family = copyFromFamilyDTO(family, request);
		family.setCreatedBy(authentication.getUserAuthentication().getName());
		family.setModifiedBy(authentication.getUserAuthentication().getName());
		family.setEmployee(user.getEmployee());
		family.setCompany(user.getCompany());
		family.setEmployeeExtId(user.getEmployeeExtId());
		
		String taskName = Workflow.SUBMIT_FAMILY;
		Workflow workflow = workflowService.findByCodeAndCompanyAndActive(
				taskName, user.getCompany(), true);
		if (workflow != null) {
			family.setStatus(Family.PENDING);
			family.setNeedSync(false);
		}
		// cek activity need approval

		familyService.save(family);

		if (workflow != null) {
			System.out.println("Workflow Description " + workflow.getDescription());
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO.setObjectName(Family.class.getSimpleName());
			dataApprovalDTO.setDescription(workflow.getDescription());
			dataApprovalDTO.setIdRef(family.getId());
			dataApprovalDTO.setTask(Workflow.SUBMIT_FAMILY);
			dataApprovalDTO.setModule(workflow.getModule());
			if(request.getAttachments() != null && request.getAttachments().size() > 0){
				System.out.println("Attachment is not empty");
				dataApprovalDTO.setAttachments(request.getAttachments());	
			}
			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Family has been Added", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/family/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> updateFamily(@PathVariable("id") Long id,
			@RequestBody FamilyDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		Family family = familyService.findById(id);
		if(family == null) {
			throw new RuntimeException("Your ID family is not found.");
		}
		FamilyTemp familyTemp = copyFromFamily(family);
		family = copyFromFamilyDTO(family, request);
		
		family.setModifiedBy(authentication.getUserAuthentication().getName());
		family.setModifiedDate(new Date());
		
		Workflow  workflow = null;
		String taskName = Workflow.CHANGE_FAMILY;
		workflow = workflowService.findByCodeAndCompanyAndActive(
					taskName, user.getCompany(), true);
		if (workflow != null) {
				familyTempService.save(familyTemp);
				family.setFamilyTemp(familyTemp.getId());
				family.setStatus(Family.PENDING);
				family.setNeedSync(false);
		}else {
			family.setNeedSync(true);
		}
		
		
		// cek activity need approval

		familyService.save(family);

		if (workflow != null) {
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO.setObjectName(Family.class.getSimpleName());
			dataApprovalDTO.setDescription(workflow.getDescription());
			dataApprovalDTO.setIdRef(family.getId());
			dataApprovalDTO.setTask(Workflow.CHANGE_FAMILY);
			dataApprovalDTO.setModule(workflow.getModule());
			if(request.getAttachments() != null && request.getAttachments().size() > 0){
				System.out.println("Attachment is not empty");
				dataApprovalDTO.setAttachments(request.getAttachments());	
			}
			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Family has been Updated", false), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/family", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Family>> listFamily(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		if (user.getEmployee() == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		Iterable<Family> listFamily = familyService.findByEmployee(user
				.getEmployee());

		return new ResponseEntity<Iterable<Family>>(listFamily, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/family/{id}", method = RequestMethod.GET)
	public ResponseEntity<Family> getDetailFamily(@PathVariable("id") Long id,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}		
		Family family = familyService.findById(id);
		return new ResponseEntity<Family>(family, HttpStatus.OK);
	}
	
	
	private FamilyTemp copyFromFamily(Family family) {
		FamilyTemp familyTemp = new FamilyTemp();
		familyTemp.setAddress(family.getAddress());
		familyTemp.setBirthPlace(family.getBirthPlace());
		familyTemp.setBirthDate(family.getBirthDate());
		familyTemp.setBloodType(family.getBloodType());
		// familyTemp.setfamily.getEmail()
		familyTemp.setName(family.getName());
		familyTemp.setPhone(family.getPhone());
		familyTemp.setRelationship(family.getRelationship());
		familyTemp.setEmployee(family.getEmployee());
		familyTemp.setGender(family.getGender());
		familyTemp.setOccupation(family.getOccupation());
		familyTemp.setMaritalStatus(family.getMaritalStatus());
		return familyTemp;
	}
	
	private Family copyFromFamilyDTO(Family family, FamilyDTO request) {
		family.setAddress(request.getAddress());
		family.setBirthPlace(request.getBirthPlace());
		family.setBirthDate(request.getBirthDate());
		family.setBloodType(request.getBloodType());
		family.setAddress(request.getAddress());
		// family.setrequest.getEmail()
		family.setName(request.getName());
		family.setPhone(request.getPhone());
		family.setRelationship(request.getRelationship());
		family.setGender(request.getGender());
		family.setOccupation(request.getOccupation());
		family.setMaritalStatus(request.getMaritalStatus());
		family.setCreatedDate(new Date());
		family.setModifiedDate(new Date());
		family.setAliveStatus(request.getAliveStatus());
		family.setNircNo(request.getNircNo());
		family.setFamilyCardNo(request.getFamilyCardNo());
		family.setDistrict(request.getDistrict());
		family.setSubDistrict(request.getSubDistrict());
		family.setRt(request.getRt());
		family.setRw(request.getRw());
		family.setEmail(request.getEmail());
		family.setNationality(request.getNationality());
		//family.setAssuranceName(request.getAssuranceName());
		//family.setPolisNo(request.getPolisNo());
		family.setNpwpNo(request.getNpwpNo());
		//family.setPassportNo(request.getPassportNo());
		family.setZipCode(request.getZipCode());
		return family;
	}

}
