package com.phincon.talents.app.controllers.api.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.phincon.talents.app.dao.UserRepository;
import com.phincon.talents.app.dto.DataApprovalDTO;
import com.phincon.talents.app.dto.LeaveDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.Workflow;
import com.phincon.talents.app.model.hr.Employee;
import com.phincon.talents.app.model.hr.Entitlement;
import com.phincon.talents.app.model.hr.Leave;
import com.phincon.talents.app.model.hr.LeaveRequest;
import com.phincon.talents.app.model.hr.LeaveType;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.EmployeeService;
import com.phincon.talents.app.services.EntitlementService;
import com.phincon.talents.app.services.LeaveRequestService;
import com.phincon.talents.app.services.LeaveService;
import com.phincon.talents.app.services.LeaveTypeService;
import com.phincon.talents.app.services.UserService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class LeaveController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	LeaveService leaveService;
	
	@Autowired
	LeaveTypeService leaveTypeService;

	@Autowired
	LeaveRequestService leaveRequestService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EntitlementService entitlementService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	DataApprovalService dataApprovalService;

	@RequestMapping(value = "/user/leave", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> submitAttendance(
			@RequestBody LeaveDTO request, OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<Entitlement> listEntitlement = entitlementService.findByEmployeeAndLeaveType(user.getEmployee(), request.getLeaveType());
		if(listEntitlement == null || listEntitlement.size() == 0) {
			throw new RuntimeException("Your Leave type is not found.");
		}
		
		Entitlement objEntitlement = listEntitlement.get(0);
		if(objEntitlement.getNoOfDays() <= 0) {
			throw new RuntimeException("Your No of Of days is empty.");
		}
		
		LeaveType leaveType = leaveTypeService.findById(request.getLeaveType());
		Employee employee = employeeService.findById(user.getEmployee());
		LeaveRequest leaveRequest = new LeaveRequest();
		leaveRequest.setEmployee(user.getEmployee());
		leaveRequest.setDateApplied(new Date());
		leaveRequest.setLeaveType(request.getLeaveType());
		leaveRequest.setLeaveTypeName(leaveType.getName());
		leaveRequest.setCompany(user.getCompany());
		leaveRequest.setStatus(LeaveRequest.PENDING);
		String fullname = employee.getFirstName();
		if(employee.getMiddleName()!=null)
			fullname = fullname + " " + employee.getMiddleName();
		if(employee.getLastName()!=null)
			fullname = fullname + " " + employee.getLastName();
		
		leaveRequest.setEmpFullName(fullname);
		leaveRequest.setStartDate(Utils.convertStringToDate(request.getStartTime()));
		leaveRequest.setEndDate(Utils.convertStringToDate(request.getEndTime()));
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = formatter.parse(request.getStartTime());
			endDate = formatter.parse(request.getEndTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		
		int i=1;
		Leave leave = new Leave();
		if(start.equals(end)) {
			 leave.setComments(request.getComment());
			 leave.setCompany(user.getCompany());
			 leave.setEmployee(user.getEmployee());
			 leave.setStartTime(endDate);
			 leave.setEndTime(endDate);
			 leave.setLengthDays(1.00);
			 leave.setLengthHours(8.00);
			 leave.setDate(endDate);
			 //leave.setLeaveRequest(leaveRequest);
			 leave.setStatus(Leave.PENDING);
			 leave.setLeaveType(request.getLeaveType());
			 //leaveService.save(leave);
		}else {
			for (Date date = start.getTime(); start.before(end); start.add(
					Calendar.DATE, 1), date = start.getTime()) {
				
				 //Leave leave = new Leave();
				 leave.setComments(request.getComment());
				 leave.setCompany(user.getCompany());
				 leave.setEmployee(user.getEmployee());
				 leave.setStartTime(date);
				 leave.setEndTime(date);
				 leave.setLengthDays(1.00);
				 leave.setLengthHours(8.00);
				 leave.setDate(date);
				 
				 leave.setStatus(Leave.PENDING);
				 leave.setLeaveType(request.getLeaveType());
				// leaveService.save(leave);
				System.out.println(i + " " + date);
				i++;
			}
			
			
			 // Leave leave = new Leave();
			 leave.setComments(request.getComment());
			 leave.setCompany(user.getCompany());
			 leave.setEmployee(user.getEmployee());
			 leave.setStartTime(endDate);
			 leave.setEndTime(endDate);
			 leave.setLengthDays(1.00);
			 leave.setLengthHours(8.00);
			 leave.setDate(endDate);
			// leave.setLeaveRequest(leaveRequest);
			 leave.setStatus(Leave.PENDING);
			 leave.setLeaveType(request.getLeaveType());
			 
			 
			
				
				
		}
		Double days = Double.valueOf(""+i);
		leaveRequest.setDays(days);
		leaveRequestService.save(leaveRequest);
		leave.setLeaveRequest(leaveRequest);
		leaveService.save(leave);
		
		
		// Decrease entitlemet
		Double noOfDays = objEntitlement.getNoOfDays() - days;
		Double daysUsed = objEntitlement.getDaysUsed() + days;
		objEntitlement.setNoOfDays(noOfDays);
		objEntitlement.setDaysUsed(daysUsed);
		entitlementService.save(objEntitlement);
		
		 // add to workflow
		 String taskName = Workflow.SUBMIT_LEAVE;
		 Workflow workflow = workflowService.findByCodeAndCompanyAndActive(taskName,
					user.getCompany(),true);
		 if(workflow != null){
			 DataApprovalDTO dataApprovalDTO = new DataApprovalDTO();
			 dataApprovalDTO.setIdRef(leaveRequest.getId());
			 dataApprovalDTO.setTask(Workflow.SUBMIT_LEAVE);
			 dataApprovalService.save(dataApprovalDTO, user, workflow);
		 }
		// attendanceService.save(attendance);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Submit Leave successfully", false), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/user/leave", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<LeaveRequest>> listDataApproval(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<LeaveRequest> listLeaveRequest = leaveRequestService.findByEmployee(user.getEmployee());
		return new ResponseEntity<List<LeaveRequest>>(listLeaveRequest, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/user/leave/pending", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<LeaveRequest>> listPendingLeave(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<LeaveRequest> listLeaveRequest = leaveRequestService.findByEmployeeAndStatus(user.getEmployee(),Leave.PENDING);
		return new ResponseEntity<List<LeaveRequest>>(listLeaveRequest, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/user/leave/entitlement", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Entitlement>> listEntitlement(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());
		List<Entitlement> listLeaveRequest = entitlementService.findByEmployee(user.getEmployee());
		List<Entitlement> listLeaveRequestTemp = new ArrayList<Entitlement>();
		if(listLeaveRequest != null) {
			for (Entitlement entitlement : listLeaveRequest) {
				LeaveType  leaveType = leaveTypeService.findById(entitlement.getLeaveType());
				entitlement.setLeaveTypeObj(leaveType);
				listLeaveRequestTemp.add(entitlement);
			}
		}
		return new ResponseEntity<List<Entitlement>>(listLeaveRequestTemp, HttpStatus.OK);

	}

	

}
