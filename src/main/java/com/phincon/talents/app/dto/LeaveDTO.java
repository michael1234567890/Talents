package com.phincon.talents.app.dto;

import java.util.Date;


public class LeaveDTO {
	
	private Long leaveType;
	private String startTime;
	private String endTime;
	private String comment;
	private Long employee;
	public Long getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(Long leaveType) {
		this.leaveType = leaveType;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getEmployee() {
		return employee;
	}
	public void setEmployee(Long employee) {
		this.employee = employee;
	}
	


}
