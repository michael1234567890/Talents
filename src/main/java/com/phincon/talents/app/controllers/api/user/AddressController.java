package com.phincon.talents.app.controllers.api.user;

import java.util.Date;
import java.util.List;

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

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.AddressRepository;
import com.phincon.talents.app.dao.AddressTempRepository;
import com.phincon.talents.app.dao.CompanySettingsRepository;
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.AddressDTO;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Address;
import com.phincon.talents.app.model.hr.AddressTemp;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.services.AddressService;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.FamilyService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;

@RestController
@RequestMapping("api")
public class AddressController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressTempRepository addressTempRepository;

	@Autowired
	FamilyService familyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	DataApprovalService dataApprovalService;
	
	@Autowired
	CompanySettingsRepository companySettingsRepository;


	@RequestMapping(value = "/user/address", method = RequestMethod.GET)
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
		Iterable<Address> listAddress = addressService.findByEmployee(employee.getId());
		return new ResponseEntity<Iterable<Address>>(listAddress, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/address", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> addAddress(
			@RequestBody AddressDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		List<CompanySettings> listCompany = companySettingsRepository.findByCompany(user.getCompany());
		CompanySettings companySettings = null;
		if(listCompany != null && listCompany.size() > 0)
			companySettings = listCompany.get(0);
		
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
		address.setDistrict(request.getDistrict());
		address.setSubdistrict(request.getSubdistrict());
		address.setCreatedDate(new Date());
		address.setEmployeeExtId(user.getEmployeeExtId());
		address.setModifiedDate(new Date());
		address.setCreatedBy(authentication.getUserAuthentication().getName());
		address.setModifiedBy(authentication.getUserAuthentication().getName());
		Workflow workflow = workflowService.findByCodeAndCompanyAndActive(
				Workflow.SUBMIT_ADDRESS, user.getCompany(), true);
		address.setNeedSync(true);
		if(workflow != null){
			address.setStatus(Address.STATUS_PENDING);
			address.setNeedSync(false);
		}
			
		addressService.save(address);
		
		if (workflow != null) {
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO.setObjectName(Address.class.getSimpleName());
			dataApprovalDTO.setDescription(workflow.getDescription());
			dataApprovalDTO.setIdRef(address.getId());
			dataApprovalDTO.setTask(Workflow.SUBMIT_ADDRESS);
			dataApprovalDTO.setModule(workflow.getModule());
			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}
		
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Address has been Added successfully", false), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/user/address/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> updateAddress(@PathVariable("id") Long id,
			@RequestBody AddressDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		boolean bypass = false;
		if((user.getIsAdmin() != null && user.getIsAdmin()) || (user.getIsLeader()!=null&& user.getIsLeader()) || (user.getIsHr() != null && user.getIsHr())) {
			bypass = true;
		}
		
		Address address = null;
		if(bypass) {
			address = addressService.findById(id);
		}else {
			address = addressRepository.findByIdAndEmployee(id, user.getEmployee());
		}
		
		 
		if(address == null) {
			throw new CustomException("Your Detail Address is not found.");
		}
		
		
		AddressTemp addressTemp = copyFromAddress(address);
		address.setAddress(request.getAddress());
		address.setAddressStatus(request.getAddressStatus());
		address.setCity(request.getCity());
		address.setDistance(request.getDistrict());
		address.setProvince(request.getProvince());
		address.setCountry(request.getCountry());
		address.setRt(request.getRt());
		address.setRw(request.getRw());
		address.setPhone(request.getPhone());
		address.setResidence(request.getResidence());
		address.setZipCode(request.getZipCode());
		address.setStayStatus(request.getStayStatus());
		address.setModifiedDate(new Date());
		address.setDistrict(request.getDistrict());
		address.setSubdistrict(request.getSubdistrict());
		
		address.setModifiedBy(authentication.getUserAuthentication().getName());
		
		Workflow  workflow = null;
		
		//if(address.getStatus() != null && !address.getStatus().equals(Address.STATUS_PENDING)) {
			String taskName = Workflow.CHANGE_ADDRESS;
			workflow = workflowService.findByCodeAndCompanyAndActive(
					taskName, user.getCompany(), true);
			if (workflow != null) {
				addressTempRepository.save(addressTemp);
				address.setAddressTemp(addressTemp.getId());
				address.setStatus(Address.STATUS_PENDING);
				address.setNeedSync(false);
			}else {
				address.setNeedSync(true);
			}
		//}
		
		
		// cek activity need approval

		addressService.save(address);

		if (workflow != null) {
			DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			dataApprovalDTO.setObjectName(Address.class.getSimpleName());
			dataApprovalDTO.setDescription(workflow.getDescription());
			dataApprovalDTO.setIdRef(address.getId());
			dataApprovalDTO.setTask(Workflow.CHANGE_ADDRESS);
			dataApprovalDTO.setModule(workflow.getModule());
			dataApprovalService.save(dataApprovalDTO, user, workflow);
		}

		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Address has been Updated", false), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/address/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> getDetailAddress(@PathVariable("id") Long id,
			OAuth2Authentication authentication) {
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		if (user == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}		
		Address address = null;
		boolean bypass = false;
		if((user.getIsAdmin() != null && user.getIsAdmin()) || (user.getIsLeader()!=null&& user.getIsLeader()) || (user.getIsHr() != null && user.getIsHr())) {
			bypass = true;
		}
		
		if(bypass) {
			address = addressService.findById(id);
		}else {
			address = addressRepository.findByIdAndEmployee(id, user.getEmployee());
		}
		
		 
		if(address == null) {
			throw new CustomException("Your Detail Address is not found.");
		}
		
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	
	
	private AddressTemp copyFromAddress(Address address) {
		AddressTemp addressTemp = new AddressTemp();
		addressTemp.setAddress(address.getAddress());
		addressTemp.setAddressStatus(address.getAddressStatus());
		addressTemp.setCity(address.getCity());
		addressTemp.setDistance(address.getDistance());
		addressTemp.setProvince(address.getProvince());
		addressTemp.setCountry(address.getCountry());
		addressTemp.setRt(address.getRt());
		addressTemp.setRw(address.getRw());
		addressTemp.setPhone(address.getPhone());
		addressTemp.setResidence(address.getResidence());
		addressTemp.setZipCode(address.getZipCode());
		addressTemp.setStayStatus(address.getStayStatus());
		return addressTemp;
	}

}
