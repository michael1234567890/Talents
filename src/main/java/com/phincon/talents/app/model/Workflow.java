package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wf_master")
public class Workflow extends AbstractEntity {
	
	public final static String DEFAULT = "default";
	public final static String WORK_LOCATION = "worklocation";
	public final static String OPERATION_ADD = "add";
	public final static String OPERATION_EDIT = "edit";
	
	
	// Code register workflow 
	public final static String CHANGE_MARITAL_STATUS = "CHANGEMARITALSTATUS";
	public final static String SUBMIT_LEAVE = "SUBMITLEAVE";
	public final static String SUBMIT_FAMILY = "SUBMITFAMILY";
	public final static String SUBMIT_ADDRESS = "SUBMITADDRESS";
	public final static String CHANGE_FAMILY = "CHANGEFAMILY";
	public final static String CHANGE_ADDRESS = "CHANGEADDRESS";
	public final static String SUBMIT_BENEFIT = "SUBMITBENEFIT";
	public final static String SUBMIT_BENEFIT1 = "SUBMITBENEFIT1";
	public final static String SUBMIT_BENEFIT2 = "SUBMITBENEFIT2";
	public final static String SUBMIT_BENEFIT2_5 = "SUBMITBENEFIT2_5";
	public final static String SUBMITBENEFIT2_1 = "SUBMITBENEFIT2_1";
	public final static String SUBMITBENEFIT2_1_5 = "SUBMITBENEFIT2_1_5";
	public final static String SUBMIT_BENEFIT3 = "SUBMITBENEFIT3";
	public final static String SUBMIT_BENEFIT4 = "SUBMITBENEFIT4";
	public final static String SUBMIT_BENEFIT5 = "SUBMITBENEFIT5";
	public final static String SUBMIT_BENEFIT6 = "SUBMITBENEFIT6";
	public final static String SUBMIT_BENEFIT7 = "SUBMITBENEFIT7";
	public final static String SUBMIT_BENEFIT8 = "SUBMITBENEFIT8";
	public final static String SUBMIT_BENEFIT9 = "SUBMITBENEFIT9";
	public final static String SUBMIT_BENEFIT10 = "SUBMITBENEFIT10";
	public final static String SUBMIT_ATTENDANCE = "SUBMITAT";
	public final static String SUBMIT_TIMESHEET = "SUBMITTIMESHEET";
	public final static String APPROVAL_TYPE_CENTRALIZE = "centralize";
	public final static String APPROVAL_TYPE_DECENTRALIZE = "decentralize";
	public final static String DEFAULT_APPROVER = "#HRD#";
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name="company_id")
	private Long company;

	@Column(name = "code", length = 30)
	private String code;

	@Column(name = "task", length = 30)
	private String task;
	
	@Column(name = "module", length = 30)
	private String module;
	

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
	
	@Column(name = "approval_code_level_four", length = 255)
	private String approvalCodeLevelFour;
	
	@Column(name = "approval_code_level_five", length = 255)
	private String approvalCodeLevelFive;
	
	@Column(name = "approval_code_level_six", length = 255)
	private String approvalCodeLevelSix;
	
	@Column(name = "approval_code_level_seven", length = 255)
	private String approvalCodeLevelSeven;
	
	@Column(name = "active")
	private Boolean active = true;
	
	@Column(name = "approval_type", length = 50)
	private String approvalType = APPROVAL_TYPE_CENTRALIZE;
	

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

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalCodeLevelFour() {
		return approvalCodeLevelFour;
	}

	public void setApprovalCodeLevelFour(String approvalCodeLevelFour) {
		this.approvalCodeLevelFour = approvalCodeLevelFour;
	}

	public String getApprovalCodeLevelFive() {
		return approvalCodeLevelFive;
	}

	public void setApprovalCodeLevelFive(String approvalCodeLevelFive) {
		this.approvalCodeLevelFive = approvalCodeLevelFive;
	}

	public String getApprovalCodeLevelSix() {
		return approvalCodeLevelSix;
	}

	public void setApprovalCodeLevelSix(String approvalCodeLevelSix) {
		this.approvalCodeLevelSix = approvalCodeLevelSix;
	}

	public String getApprovalCodeLevelSeven() {
		return approvalCodeLevelSeven;
	}

	public void setApprovalCodeLevelSeven(String approvalCodeLevelSeven) {
		this.approvalCodeLevelSeven = approvalCodeLevelSeven;
	}

	
	
	
	
	
	
	

}
