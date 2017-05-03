package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;
import com.phincon.talents.app.model.Company;

@Entity
@Table(name = "hr_leave")
public class Leave extends AbstractEntity {

	public final static String PENDING = "pending";
	public final static String REJECTED = "rejected";
	public final static String CANCELLED = "cancelled";
	public final static String TAKEN = "taken";
	public final static String SCHEDULED = "scheduled";

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "company_id")
	// private Company company;

	@Column(name = "company_id")
	private Long company;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "employee_id")
	// private Employee employee;

	@Column(name = "employee_id")
	private Long employee;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	@Column(name = "length_hours")
	private Double lengthHours;

	@Column(name = "length_days")
	private Double lengthDays;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "leave_status")
	private LeaveStatus leaveStatus;

	@Column(name = "comments")
	private String comments;

	@Column(name = "status")
	private String status;

	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "leave_request_id")
	 private LeaveRequest leaveRequest;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "leave_type_id")
//	private LeaveType leaveType;
	 
	@Column(name = "leave_type_id")
	private Long leaveType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "duration_type")
	private String durationType;

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getLengthHours() {
		return lengthHours;
	}

	public void setLengthHours(Double lengthHours) {
		this.lengthHours = lengthHours;
	}

	public Double getLengthDays() {
		return lengthDays;
	}

	public void setLengthDays(Double lengthDays) {
		this.lengthDays = lengthDays;
	}

	public LeaveStatus getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(LeaveStatus leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Long leaveType) {
		this.leaveType = leaveType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public LeaveRequest getLeaveRequest() {
		return leaveRequest;
	}

	public void setLeaveRequest(LeaveRequest leaveRequest) {
		this.leaveRequest = leaveRequest;
	}
	
	

}
