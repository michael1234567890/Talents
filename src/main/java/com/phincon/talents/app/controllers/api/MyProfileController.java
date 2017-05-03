package com.phincon.talents.app.controllers.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.AddressDTO;
import com.phincon.talents.app.dto.CertificationDTO;
import com.phincon.talents.app.dto.FamilyDTO;
import com.phincon.talents.app.dto.UserChangePasswordDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.Certification;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Family;
import com.phincon.talents.app.model.hr.VwEmpAssignment;
import com.phincon.talents.app.services.AddressService;
import com.phincon.talents.app.services.CertificationService;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.FamilyService;
import com.phincon.talents.app.services.UserService;
import com.phincon.talents.app.services.VwEmpAssignmentService;
import com.phincon.talents.app.utils.CustomMessage;

@RestController
@RequestMapping("api")
public class MyProfileController {

	@Autowired
	UserService userService;

	@Autowired
	FamilyService familyService;

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
	VwEmpAssignmentService assignmentService;

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
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}

	@RequestMapping(value = "/myprofile/team", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> myTeam(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		if (user.getEmployee() == null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		List<Employee> listEmployee = assignmentService.findEmployee(user
				.getEmployee());
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
		familyService.save(family);

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
		Iterable<Address> listAddress = addressService.findByEmployee(employee);
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
		address.setEmployee(employee);
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
		certificationService.save(certification);

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

		userService.changePassword(request, user);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Your Password has been changed successfully", false),
				HttpStatus.OK);
	}

}
