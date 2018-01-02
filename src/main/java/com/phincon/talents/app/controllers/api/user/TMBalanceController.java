package com.phincon.talents.app.controllers.api.user;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.config.CustomException;
import com.phincon.talents.app.dao.EmploymentRepository;
import com.phincon.talents.app.dao.TMBalanceRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.TMRequestModuleCategoryDTO;
import com.phincon.talents.app.model.RecordType;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.TMBalance;

@RestController
@RequestMapping("api")
public class TMBalanceController {
	@Autowired
	TMBalanceRepository tmBalanceRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmploymentRepository employmentRepository;
	
	@RequestMapping(value = "/user/tmbalance/type", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<TMBalance>> getTypeByCategory(
			@RequestBody TMRequestModuleCategoryDTO request, OAuth2Authentication authentication) {
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<Employment> listEmployment = employmentRepository.findByEmployee(user.getEmployee());
		if(listEmployment == null && listEmployment.size() == 0)
			throw new CustomException("Your Employment ID is not Found.");
		Employment employment = listEmployment.get(0);
		
		//List<TMBalance> listBalance = tmBalanceRepository.findByCompanyModuleCategoryType(user.getCompany(), request.getModule(), request.getCategoryType(),employment.getId());
		List<TMBalance> listBalance = tmBalanceRepository.findBalanceByModuleEmployment(user.getCompany(),employment.getId(), request.getModule());
		
		return new ResponseEntity<List<TMBalance>>(listBalance, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/tmbalance/categoryname", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<RecordType>> getListCategoryName(@RequestBody TMRequestModuleCategoryDTO request, OAuth2Authentication authentication) {
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<Employment> listEmployment = employmentRepository.findByEmployee(user.getEmployee());
		if(listEmployment == null && listEmployment.size() == 0)
			throw new CustomException("Your Employment ID is not Found.");
		Employment employment = listEmployment.get(0);
		List<Object[]> listCategoryType = tmBalanceRepository.findListCategoryName(user.getCompany(), employment.getId(),request.getModule());
		List<RecordType> listRecordType = new ArrayList<RecordType>();
		if(listCategoryType != null && listCategoryType.size() > 0) {
			for (Object[] objects : listCategoryType) {
				RecordType record = new RecordType((String)objects[0],(String)objects[1]);
				listRecordType.add(record);
			}
		}
		return new ResponseEntity<List<RecordType>>(listRecordType, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/tmbalance/currenttype", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TMBalance>> getBalance(
			@RequestParam(value = "module", required = true) String module,@RequestParam(value = "category", required = true) String category,@RequestParam(value = "type", required = true) String type,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<Employment> listEmployment = employmentRepository
				.findByEmployee(user.getEmployee());
		if (listEmployment == null || listEmployment.size() == 0)
			throw new CustomException(
					"Your Employment ID is not Found.");

		Employment employment = listEmployment.get(0);
		
		List<TMBalance> listBalance = tmBalanceRepository.findBalanceTypeByEmployment(user.getCompany(), employment.getId(), module, category, type, new Date());
		if (listBalance == null || listBalance.size() == 0)
			throw new CustomException(
					"Your Balance is not Found.");
		
		//TMBalance tmBalance = listBalance.get(0);
		return new ResponseEntity<List<TMBalance>>(listBalance,
				HttpStatus.OK);
	}

}
