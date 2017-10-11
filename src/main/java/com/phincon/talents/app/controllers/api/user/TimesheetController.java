package com.phincon.talents.app.controllers.api.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.neo4j.cypher.internal.compiler.v2_1.functions.Substring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.phincon.talents.app.dao.EmployeeRepository;
import com.phincon.talents.app.dao.EmploymentRepository;
import com.phincon.talents.app.dao.TimesheetRepository;
import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.TimesheetDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Employment;
import com.phincon.talents.app.model.hr.Timesheet;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.TimesheetService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.LoadResult;

@RestController
@RequestMapping("api")
public class TimesheetController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TimesheetRepository timesheetRepository;

	@Autowired
	TimesheetService timesheetService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	DataApprovalService dataApprovalService;
	
	@Autowired
	private Environment env;
	
	@Autowired
	EmploymentRepository employmentRepository;
	
	private static final SimpleDateFormat dateFormat 
    = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final Date invalidDate = new Date(0);
	
	private static final Date fromString(String date){
		try{
			return dateFormat.parse(date);
		} catch(ParseException e){
			return invalidDate;
		}
	}

	
	@RequestMapping(value = "/user/timesheet", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> addTimesheet(
			@RequestBody TimesheetDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		timesheetService.save(request, user,Workflow.SUBMIT_TIMESHEET);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Timesheet has been Added successfully", false), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/timesheet/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Timesheet> detailTimesheet(
			@PathVariable("id") Long id, OAuth2Authentication authentication, HttpServletRequest request) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<Timesheet> listTimesheet = timesheetRepository.findByIdAndEmployee(id,user.getEmployee());
		if(listTimesheet == null) {
			throw new RuntimeException("Timesheet with ID "+id+" is not found.");
		}
		
		Timesheet timesheet = null;
		if(listTimesheet.size() > 0)
			timesheet = listTimesheet.get(0);
		return new ResponseEntity<Timesheet>(timesheet, HttpStatus.OK);
	}
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/user/timesheet", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<LoadResult> listTimesheet(@RequestParam(value = "param", required = false) String param, 
			@RequestParam(value = "value", required = false) String value,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			OAuth2Authentication authentication) {
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		int pageData = Integer.valueOf(env.getProperty("talents.startpage"));
		int sizeData = Integer.valueOf(env.getProperty("talents.size"));
		if (size != null)
			sizeData = size;
		if (page != null)
			pageData = page;
		Long totalRecord = 0L;
		LoadResult<List<Timesheet>> loadResult = new LoadResult<List<Timesheet>>();
		PageRequest pageRequest = new PageRequest(pageData, sizeData);
		
		loadResult.setPage(pageData);
		loadResult.setSize(sizeData);
		List<Long> listTotalRecord = null;
		List<Timesheet> listTimesheet = null;
		
		//if else by param
		if(param != null) {
			
			if(param.toLowerCase().equals("status")){
				listTotalRecord = timesheetRepository.countByStatus(value);
				if(listTotalRecord != null && listTotalRecord.size() > 0){
					for(Long objects : listTotalRecord){
						totalRecord = objects;
					}
				}
				listTimesheet = timesheetRepository.findByStatus(value, pageRequest);
			}
			
		}else {
			listTotalRecord = timesheetRepository.countByEmployee(user.getEmployee());
			if (listTotalRecord != null && listTotalRecord.size() > 0) {
				for (Long objects : listTotalRecord) {
					totalRecord = objects;
				}
			}
			 listTimesheet = timesheetRepository.findByEmployee(user.getEmployee(), pageRequest);
			 
		}
		
//		List<Timesheet> listTimesheetPeriod = timesheetRepository.findByEmployeeAndToday(user.getEmployee(), startDate, endDate)
		loadResult.setData(listTimesheet);
		loadResult.setTotalRecord(totalRecord);
		return new ResponseEntity<LoadResult>(loadResult,
				HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/user/timesheet/byperiod", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Timesheet>> getTimesheetType(@RequestParam(value = "type", required = true) String type, 
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
			OAuth2Authentication authentication){
		
		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		
		List<Timesheet> listTimesheet = null;
		
		if(type.equals("monthly")){
			if(month == null)
				throw new RuntimeException("Month can not be empty.");
			
			listTimesheet = timesheetRepository.findByEmployeeAndCompanyAndMonthPeriod(user.getEmployee(), user.getCompany(), month);
			
		} else if(type.equals("rangedate")){
			if(startDate == null)
				throw new RuntimeException("Start Date can not be empty.");
			
			if(endDate == null)
				endDate = startDate;
			
			listTimesheet = timesheetRepository.findByEmployeeAndCompanyAndToday(user.getEmployee(), user.getCompany(), startDate, endDate);
			
		} else {
			throw new RuntimeException("Invalid value of type.");
		}
		
		return new ResponseEntity<List<Timesheet>>(listTimesheet, HttpStatus.OK);
	}

}
