package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.phincon.talents.app.model.AbstractEntity;
import com.phincon.talents.app.model.Company;
@Entity
@Table(name="hr_entitlement")
public class Entitlement extends AbstractEntity {
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_id")
//	private Employee employee;
	
	@Column(name = "employee_id")
	private Long employee;
	
	@Column(name="no_of_days")
	private Double noOfDays;
	
	@Column(name="days_used")
	private Double daysUsed;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "leave_type_id")
//	private LeaveType leaveType;
	
	@Column(name = "leave_type_id")
	private Long leaveType;
	
	@Transient
	private LeaveType leaveTypeObj;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="from_date")
	private Date fromDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="to_date")
	private Date toDate;
	
	@Column(name="note", length=100)
	private String note;
	
	@Column(name="deleted")
	private Boolean deleted=false;

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

	public Double getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Double noOfDays) {
		this.noOfDays = noOfDays;
	}

	public Double getDaysUsed() {
		return daysUsed;
	}

	public void setDaysUsed(Double daysUsed) {
		this.daysUsed = daysUsed;
	}

	public Long getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(Long leaveType) {
		this.leaveType = leaveType;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public LeaveType getLeaveTypeObj() {
		return leaveTypeObj;
	}

	public void setLeaveTypeObj(LeaveType leaveTypeObj) {
		this.leaveTypeObj = leaveTypeObj;
	}
	
	
	
	
	
	
}
