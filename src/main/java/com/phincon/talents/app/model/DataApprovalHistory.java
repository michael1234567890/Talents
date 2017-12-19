package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wf_data_approval_history")
public class DataApprovalHistory extends AbstractEntity {

	@Column(name = "data_approval_id")
	private Long dataApproval;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "employee_approval")
	private Long employeeApproval;
	
	@Column(name = "current_approval")
	private int currentApproval;
	

	@Column(name = "status", length=20)
	private String status;
	
	@Column(name = "description", length=100)
	private String description;

	public Long getDataApproval() {
		return dataApproval;
	}

	public void setDataApproval(Long dataApproval) {
		this.dataApproval = dataApproval;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getEmployeeApproval() {
		return employeeApproval;
	}

	public void setEmployeeApproval(Long employeeApproval) {
		this.employeeApproval = employeeApproval;
	}

	public int getCurrentApproval() {
		return currentApproval;
	}

	public void setCurrentApproval(int currentApproval) {
		this.currentApproval = currentApproval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
