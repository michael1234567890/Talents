package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wf_master")
public class Workflow extends AbstractEntity {
	
	public final static String DEFAULT = "default";
	
	public final static String OPERATION_ADD = "add";
	public final static String OPERATION_EDIT = "edit";
	
	
	// Code register workflow 
	public final static String CHANGE_MARITAL_STATUS = "CHANGEMARITALSTATUS";
	
	public final static String SUBMIT_LEAVE = "SUBMITLEAVE";
	public final static String SUBMIT_FAMILY = "SUBMITFAMILY";
	public final static String SUBMIT_ADDRESS = "SUBMITADDRESS";

	public final static String CHANGE_FAMILY = "CHANGEFAMILY";
	public final static String CHANGE_ADDRESS = "CHANGEADDRESS";
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name="company_id")
	private Long company;

	@Column(name = "code", length = 30)
	private String code;

	@Column(name = "task", length = 30)
	private String task;

	@Column(name = "name", length = 30)
	private String name;
	
	@Column(name = "days", length = 30)
	private Integer days;

	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "operation", length = 30)
	private String operation;

	@Column(name = "approval_code_level_one", length = 255)
	private String approvalCodeLevelOne;

	@Column(name = "approval_code_level_two", length = 255)
	private String approvalCodeLevelTwo;

	@Column(name = "approval_code_level_three", length = 255)
	private String approvalCodeLevelThree;
	
	@Column(name = "active")
	private Boolean active = true;
	
	

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getApprovalCodeLevelOne() {
		return approvalCodeLevelOne;
	}

	public void setApprovalCodeLevelOne(String approvalCodeLevelOne) {
		this.approvalCodeLevelOne = approvalCodeLevelOne;
	}

	public String getApprovalCodeLevelTwo() {
		return approvalCodeLevelTwo;
	}

	public void setApprovalCodeLevelTwo(String approvalCodeLevelTwo) {
		this.approvalCodeLevelTwo = approvalCodeLevelTwo;
	}

	public String getApprovalCodeLevelThree() {
		return approvalCodeLevelThree;
	}

	public void setApprovalCodeLevelThree(String approvalCodeLevelThree) {
		this.approvalCodeLevelThree = approvalCodeLevelThree;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
	
	
	
	
	
	

}
