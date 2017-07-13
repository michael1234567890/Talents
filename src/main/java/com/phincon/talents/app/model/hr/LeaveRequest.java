package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name="hr_leave_request")
public class LeaveRequest extends AbstractEntity {
	public final static String APPROVED = "approved";
	public final static String REJECTED = "rejected";
	public final static String PENDING = "pending";
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "leave_type_id")
//	private LeaveType leaveType;
	
	@Column(name = "leave_type_id")
	private Long leaveType;
	
	@Column(name="leave_type_name", length=30)
	private String leaveTypeName;
	
	@Column(name="emp_full_name", length=100)
	private String empFullName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_applied")
	private Date dateApplied;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_id")
//	private Employee employee;
	
	@Column(name = "employee_id")
	private Long employee;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name="status")
	private String status;
	
	@Column(name="comment")
	private String comment;
	
	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	public Long getLeaveType() {
		return leaveType;
	}

	@Column(name = "days")
	private Double days;
	
	public void setLeaveType(Long leaveType) {
		this.leaveType = leaveType;
	}
	
	

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getEmpFullName() {
		return empFullName;
	}

	public void setEmpFullName(String empFullName) {
		this.empFullName = empFullName;
	}

	public Date getDateApplied() {
		return dateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public Double getDays() {
		return days;
	}



	public void setDays(Double days) {
		this.days = days;
	}
	
	
	
	
	
	
	
	
}
