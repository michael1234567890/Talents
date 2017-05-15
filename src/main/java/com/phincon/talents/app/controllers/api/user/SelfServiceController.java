package com.phincon.talents.app.controllers.api.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.phincon.talents.app.dto.AttendanceDTO;
import com.phincon.talents.app.model.User;
import com.phincon.talents.app.model.hr.Attendance;
import com.phincon.talents.app.services.AttendanceService;
import com.phincon.talents.app.services.DataApprovalService;
import com.phincon.talents.app.services.UserService;
import com.phincon.talents.app.services.VwEmpAssignmentService;
import com.phincon.talents.app.services.WorkflowService;
import com.phincon.talents.app.utils.CustomMessage;
import com.phincon.talents.app.utils.Utils;

@RestController
@RequestMapping("api")
public class SelfServiceController {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	VwEmpAssignmentService assignmentService;

	@Autowired
	WorkflowService workflowService;

	@Autowired
	DataApprovalService dataApprovalService;
	
	

	@RequestMapping(value = "/user/self/attendance", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CustomMessage> submitAttendance(
			@RequestBody AttendanceDTO request,
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		// cek user have attendance record for today
		Attendance attendance = attendanceService.findByTodayAndEmployee(
				new Date(), user.getEmployee());
		if (attendance == null) {
			attendance = new Attendance();
		}

		if (request.getMode().equals("out")) {
			attendance.setLatitudePunchOut(request.getLatitudePunchOut());
			attendance.setLongitudePunchOut(request.getLongitudePunchOut());
			attendance.setPunchOutUtcTime(new Date());
			attendance.setPunchOutNote(request.getPunchOutNote());
			if (request.getPunchOutUserTime() != null)
				attendance.setPunchOutUserTime(Utils
						.convertStringToDate(request.getPunchOutUserTime()));
			attendance.setModifiedDate(new Date());
			attendance.setModifiedBy(user.getUsername());
		} else {
			attendance.setLatitudePunchIn(request.getLatitudePunchIn());
			attendance.setLongitudePunchIn(request.getLongitudePunchIn());
			attendance.setPunchInUtcTime(new Date());
			attendance.setPunchInNote(request.getPunchInNote());
			if (request.getPunchInUserTime() != null)
				attendance.setPunchInUserTime(Utils.convertStringToDate(request
						.getPunchInUserTime()));
			attendance.setCreatedDate(new Date());
			attendance.setModifiedDate(new Date());
			attendance.setModifiedBy(user.getUsername());
			attendance.setCreatedBy(user.getUsername());
		}
		attendance.setToday(new Date());
		attendance.setCompany(user.getCompany());
		attendance.setCreatedDate(new Date());
		attendance.setEmployee(user.getEmployee());

		attendanceService.save(attendance);
		return new ResponseEntity<CustomMessage>(new CustomMessage(
				"Submit Attendance successfully", false), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/self/attendance/prepare", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> prepareAttendance(
			OAuth2Authentication authentication) {

		User user = userRepository.findByUsernameCaseInsensitive(authentication
				.getUserAuthentication().getName());

		// cek user have attendance record for today
		Attendance attendance = attendanceService.findByTodayAndEmployee(
				new Date(), user.getEmployee());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serverTime", new Date());
		if (attendance != null) {
			map.put("punchInUtcTime", attendance.getPunchInUtcTime());
			map.put("punchOutUtcTime", attendance.getPunchOutUtcTime());
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

}
