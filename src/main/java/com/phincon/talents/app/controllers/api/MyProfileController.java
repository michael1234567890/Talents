package com.phincon.talents.app.controllers.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.phincon.talents.app.dao.AttachmentCertificationRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dao.VwEmpAssignmentRepository;
import com.phincon.talents.app.dto.AddressDTO;
import com.phincon.talents.app.dto.CertificationDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.dto.FamilyDTO;
import com.phincon.talents.app.dto.UserChangePasswordDTO;
import com.phincon.talents.app.model.AttachmentCertification;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.Certification;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.services.AddressService;
import com.phincon.talents.app.services.CertificationService;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.FamilyService;
import com.phincon.talents.app.services.UserService;
import com.phincon.talents.app.services.VwEmpAssignmentService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class MyProfileController {

	@Autowired
	UserService userService;


	
	@Autowired
	FamilyService familyService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	AddressService addressService;

	@Autowired
	CertificationService certificationService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private Environment env;

	@Autowired
	AttachmentCertificationRepository attachmentCertificationRepository;

	@Autowired
	DataApprovalService dataApprovalService;

	@Autowired
	VwEmpAssignmentService assignmentService;
	
	@Autowired
	VwEmpAssignmentRepository assignmentRepository;

	@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
	public ResponseEntity<User> myprofile(OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		// user.setPassword("*************");

		if (user.getEmployee() != null) {
			Employee employee = employeeService
					.findEmployee(user.getEmployee());
			VwEmpAssignment assignment = assignmentService
					.findAssignmentByEmployee(employee.getId());
			if (assignment != null)
				employee.setAssignment(assignment);

			user.setEmployeeTransient(employee);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/personal", method = RequestMethod.GET)
	public ResponseEntity<Employee> personal(OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		if (user.getEmployee() == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		Employee employee = employeeRepository.findOne(user.getEmployee());

		if (employee == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		// check report to
		VwEmpAssignment assignment = assignmentService
				.findAssignmentByEmployee(employee.getId());
		if (assignment != null) {
			employee.setAssignment(assignment);
		} else {
			employee.setAssignment(null);
		}
		
		
		Address address = addressService.findByAddressStatusAndEmployee("Yes", user.getEmployee());
		employee.setAddress(address);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/team", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> myTeam(
			OAuth2Authentication authentication,HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		if (user.getEmployee() == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		
		// cek dl di viewAssignment atasan atau bawahan berdasarkan ada enggaknya row di direct_employee_id -->user.getEmployee 
		List<VwEmpAssignment> employeeAssignment = assignmentRepository.findByDirectEmployee(
				user.getEmployee());
		
		// klo ada
		List<Employee> listEmployee = null;
		if(employeeAssignment != null && employeeAssignment.size() > 0){
			listEmployee = assignmentService.findEmployee(user
					.getEmployee());
		}else{
			List<VwEmpAssignment> myAssignmentList = assignmentRepository.findByEmployee(
					user.getEmployee());
			VwEmpAssignment myAssignment = null;
			if(myAssignmentList != null && myAssignmentList.size() > 0){
				myAssignment = myAssignmentList.get(0);
				listEmployee = assignmentService.findEmployee(myAssignment.getDirectEmployee());
			}
		}
		
		
		
		// klo gak ada , direct_emp_id siapa,
		// siapa aja yg direct_employee_id nya sama
		/*List<Employee> listEmployee = assignmentService.findEmployee(user
				.getEmployee());*/
		
		List<Employee> lisEmployeesReturn = new ArrayList<Employee>();
		if (listEmployee != null && listEmployee.size() > 0) {
			for (Employee employee : listEmployee) {
				VwEmpAssignment assignment = assignmentService
						.findAssignmentByEmployee(employee.getId());
				if (assignment != null) {
					employee.setAssignment(assignment);
				} else {
					employee.setAssignment(null);
				}
				
				// User
				User userEmployee = userRepository.findByEmployee(employee.getId());
			
				// ambil foto profile
				String image = null;
				if(userEmployee != null && userEmployee.getPhotoProfile() != null && !userEmployee.getPhotoProfile().equals("")) {
					
					 // get foto
					// image = Utils.convertImageToBase64(userEmployee.getPhotoProfile());
					String http = env.getProperty("talents.protocol");
					image = Utils.getUrlAttachment(http, request, userEmployee.getPhotoProfile());
				}
				
				employee.setPhotoProfile(image);
				
				lisEmployeesReturn.add(employee);
			}
		}

		return new ResponseEntity<List<Employee>>(lisEmployeesReturn,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/family", method = RequestMethod.GET)
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

	@RequestMapping(value = "/myprofile/family", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> addFamily(
			@RequestBody FamilyDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		Family family = new Family();
		family.setAddress(request.getAddress());
		family.setBirthPlace(request.getBirthPlace());
		family.setBirthDate(request.getBirthDate());
		family.setBloodType(request.getBloodType());
		family.setAddress(request.getAddress());
		// family.setrequest.getEmail()
		family.setName(request.getName());
		family.setPhone(request.getPhone());
		family.setRelationship(request.getRelationship());
		family.setEmployee(user.getEmployee());
		family.setGender(request.getGender());
		family.setOccupation(request.getOccupation());
		family.setMaritalStatus(request.getMaritalStatus());
		family.setCreatedDate(new Date());
		family.setModifiedDate(new Date());
		family.setCreatedBy(authentication.getUserAuthentication().getName());
		family.setModifiedBy(authentication.getUserAuthentication().getName());
		family.setCompany(user.getCompany());
		family.setEmployeeExtId(user.getEmployeeExtId());

		// check this company have regulation approval to SUBMITFAMILY
		Workflow workflow = workflowService.findByCodeAndCompanyAndActive(
				Workflow.SUBMIT_FAMILY, user.getCompany(), true);
		if (workflow != null) {
			family.setStatus(Family.PENDING);
			family.setNeedSync(false);
		} else {
			family.setNeedSync(true);
		}

		familyService.save(family);
		if (workflow != null) {
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO.setIdRef(family.getId());
			dataApprovalDTO.setTask(Workflow.SUBMIT_FAMILY);
			if (request.getAttachments() != null
					&& request.getAttachments().size() > 0) {
				System.out.println("Attachment is not empty");
				dataApprovalDTO.setAttachments(request.getAttachments());
			}

			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Family has been Added successfully", false), HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/address", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Address>> listAddress(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		if (user.getEmployee() == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		Employee employee = employeeRepository.findOne(user.getEmployee());
		Iterable<Address> listAddress = addressService.findByEmployee(employee
				.getId());
		return new ResponseEntity<Iterable<Address>>(listAddress, HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/address", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> addAddress(
			@RequestBody AddressDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		Employee employee = employeeService.findEmployee(user.getEmployee());

		Address address = new Address();
		address.setAddress(request.getAddress());
		address.setAddressStatus(request.getAddressStatus());
		address.setCity(request.getCity());
		address.setDistance(request.getDistrict());
		address.setProvince(request.getProvince());
		address.setCountry(request.getCountry());
		address.setRt(request.getRt());
		address.setRw(request.getRw());
		address.setEmployee(employee.getId());
		address.setPhone(request.getPhone());
		address.setResidence(request.getResidence());
		address.setZipCode(request.getZipCode());
		address.setStayStatus(request.getStayStatus());
		address.setCreatedDate(new Date());
		address.setModifiedDate(new Date());
		address.setCreatedBy(authentication.getUserAuthentication().getName());
		address.setModifiedBy(authentication.getUserAuthentication().getName());
		addressService.save(address);

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Address has been Added successfully", false), HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/certification", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Certification>> certification(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		if (user.getEmployee() == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		Employee employee = employeeRepository.findOne(user.getEmployee());
		if (employee == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		Iterable<Certification> certification = certificationService
				.findByEmployee(employee);

		return new ResponseEntity<Iterable<Certification>>(certification,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/certification/{id}/attachments", method = RequestMethod.GET)
	public ResponseEntity<List<AttachmentCertification>> getAttachment(
			@PathVariable("id") Long id, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		List<AttachmentCertification> listAttachment = attachmentCertificationRepository
				.findByCertification(id);
		int i = 0;
		for (AttachmentCertification attachmentCertification : listAttachment) {
			if (attachmentCertification.getPath() != null) {
				String base64String = Utils
						.convertImageToBase64(attachmentCertification.getPath());
				listAttachment.get(i).setImage(base64String);

			}

			i++;
		}

		return new ResponseEntity<List<AttachmentCertification>>(
				listAttachment, HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/certification", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> addCertification(
			@RequestBody CertificationDTO request,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		Employee employee = employeeService.findEmployee(user.getEmployee());

		Certification certification = new Certification();
		certification.setDate(request.getDate());
		certification.setDescription(request.getDescription());
		certification.setEmployee(employee);
		certification.setEmployeeExtId(user.getEmployeeExtId());
		certification.setExpired(request.getExpired());
		certification.setFull(request.getFull());
		certification.setName(request.getName());
		certification.setNo(request.getNo());
		certification.setType(request.getType());
		certification.setPrinciple(request.getPrinciple());
		certification.setYear(request.getYear());
		certification.setModifiedDate(new Date());
		certification.setCreatedDate(new Date());
		certification.setCreatedBy(authentication.getUserAuthentication()
				.getName());
		certification.setModifiedBy(authentication.getUserAuthentication()
				.getName());
		certification.setNeedSync(true);
		certificationService.save(certification);

		if (request.getAttachments() != null
				&& request.getAttachments().size() > 0) {

			// save attachment`
			for (Map<String, Object> map : request.getAttachments()) {
				String imageBase64 = (String) map.get("image");
				System.out.println("image : " + (String) map.get("image"));
				if (imageBase64 != null) {
					String pathname = "certification/"
							+ RandomStringUtils.randomAlphanumeric(10) + "."
							+ Utils.UPLOAD_IMAGE_TYPE;
					Utils.createImage(imageBase64, pathname);
					AttachmentCertification obj = new AttachmentCertification();
					obj.setPath(pathname);
					obj.setCertification(certification.getId());
					attachmentCertificationRepository.save(obj);
				}

			}

		}

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Certification has been Added successfully", false),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/changepassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> forgot(
			@RequestBody UserChangePasswordDTO request,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		if(request.getConfirmPassword()==null ||request.getNewPassword() == null || request.getOldPassword() == null || request.getConfirmPassword().trim().isEmpty() || request.getNewPassword().trim().isEmpty() ||  request.getOldPassword().trim().isEmpty()) {
			throw new RuntimeException("Required Field is not Empty.");
		}
		
		userService.changePassword(request, user,true);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Your Password has been changed successfully", false),
				HttpStatus.OK);
	}

}
