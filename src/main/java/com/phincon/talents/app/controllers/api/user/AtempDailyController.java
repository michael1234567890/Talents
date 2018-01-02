package com.phincon.talents.app.controllers.api.user;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.talents.app.dao.AtempDailyRepository;
import com.phincon.talents.app.dao.EmploymentRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.AtempDaily;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.services.AtempDailyService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.LoadResult;

@RestController
@RequestMapping("api")
public class AtempDailyController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AtempDailyRepository atempDailyRepository;
	
	@Autowired
	AtempDailyService atempDailyService;
	
	@Autowired
	EmploymentRepository employmentRepository;
	
	@Autowired
	private Environment env;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/atempdaily", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<LoadResult> listAtempdaily(@RequestParam(value = "param", required = false) String param, 
			@RequestParam(value = "value", required = false) String value,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			OAuth2Authentication authentication){
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<Employment> listEmployment = employmentRepository.findByEmployee(user.getEmployee());
		Employment employment = listEmployment.get(0);
		
		System.out.println("employee id : " +user.getEmployee());
		System.out.println("employment id : " +employment.getExtId());
		System.out.println("company id : " +user.getCompany());
		
		int pageData = Integer.valueOf(env.getProperty("talents.startpage"));
		int sizeData = Integer.valueOf(env.getProperty("talents.size"));
		if (size != null)
			sizeData = size;
		if (page != null)
			pageData = page;
		Long totalRecord = 0L;
		
		LoadResult<List<AtempDaily>> loadResult = new LoadResult<List<AtempDaily>>();
		PageRequest pageRequest = new PageRequest(pageData, sizeData);
		loadResult.setPage(pageData);
		loadResult.setSize(sizeData);
		List<Long> listTotalRecord = null;
		List<AtempDaily> listAtempdaily = null;
		
		//if else by param
		System.out.println("Param : " + param);
		if(param != null){
			
			
		} else {
			listTotalRecord = atempDailyRepository.countByEmployeeNo(employment.getExtId());
			if(listTotalRecord != null && listTotalRecord.size() > 0){
				for(Long objects : listTotalRecord){
					totalRecord = objects;
				}
			}
			listAtempdaily = atempDailyRepository.findByEmployeeNo(employment.getExtId(), pageRequest);
		}
		
		
		loadResult.setData(listAtempdaily);
		loadResult.setTotalRecord(totalRecord);
		return new ResponseEntity<LoadResult>(loadResult, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/user/atempdaily/byperiod", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<AtempDaily>> getAtempDailyType(@RequestParam(value = "type", required = true) String type, 
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			OAuth2Authentication authentication){
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<Employment> listEmployment = employmentRepository.findByEmployee(user.getEmployee());
		Employment employment = listEmployment.get(0);
		
		System.out.println("employee id : " +user.getEmployee());
		System.out.println("employment id : " +employment.getExtId());
		System.out.println("company id : " +user.getCompany());
		System.out.println("month : " +month);
		
		List<AtempDaily> listAtempDaily = null;
		
		if(type.equals("monthly")){
			if(month == null)
				throw new RuntimeException("Month can not be empty.");
			
			listAtempDaily = atempDailyRepository.findByEmployeeAndCompanyAndMonthPeriod(employment.getExtId(), user.getCompany(), month);
			
		} else if(type.equals("rangedate")){
			if(startDate == null)
				throw new RuntimeException("Start Date can not be empty.");
			
			if(endDate == null)
				endDate = startDate;
			
			listAtempDaily = atempDailyRepository.findByEmployeeNoAndCompanyAndWorkdate(employment.getExtId(), user.getCompany(), startDate, endDate);
			
		} else {
			throw new RuntimeException("Invalid value of type.");
		}
			
		
		return new ResponseEntity<List<AtempDaily>>(listAtempDaily, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/user/atempdaily/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<CustomMessage> attendanceEdit(@PathVariable Long id){
		
		
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Attendance Edit Success", false), HttpStatus.OK);
	}
	
}
