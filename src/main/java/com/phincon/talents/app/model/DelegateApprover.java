package com.phincon.talents.app.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wf_delegate_approver")
public class DelegateApprover extends AbstractEntity{
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name="employee_id")
	private Long employee;
	
	@Column(name="delegate_to_employee_id")
	private Long delegateToEmployee;

	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	private Date fromDate;

	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	private Date toDate;
	
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

	public Long getDelegateToEmployee() {
		return delegateToEmployee;
	}

	public void setDelegateToEmployee(Long delegateToEmployee) {
		this.delegateToEmployee = delegateToEmployee;
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

	
	
	
}
