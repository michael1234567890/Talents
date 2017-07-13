package com.phincon.talents.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.phincon.talents.app.model.hr.Employee;

@Entity
@Table(name = "wf_data_approval")
public class DataApproval extends AbstractEntity {

	public final static String DIRECT = "direct";
	public final static String APPROVED = "approved";
	public final static String REJECTED = "rejected";
	public final static String CANCELLED = "cancelled";
	
	public final static String PROC_STATUS_REQUEST = "Request";
	public final static String PROC_STATUS_WAITING = "Waiting";
	public final static String PROC_STATUS_REJECT = "Reject";
	public final static String PROC_STATUS_APPROVE = "Approve";
	public final static String PROC_STATUS_CANCEL = "Cancel";
	// task
	
	
	
	// Status
	public final static String NOT_COMPLETED = "not_completed";
	public final static String COMPLETED = "completed";
	
	@Column(name = "company_id")
	private Long company;

	@Column(name = "emp_request")
	private Long empRequest;

	@Column(name = "module", length = 50)
	private String module;
	
	@Column(name = "task", length = 50)
	private String task;

	@Column(name = "object_ref_id")
	private Long objectRef;

	@Column(name = "object_name", length = 100)
	private String objectName;

	@Column(name = "data", length = 500)
	private String data;

	
	@Column(name = "status", length = 30)
	private String status;
	
	@Column(name = "processing_status", length = 30)
	private String processingStatus;

	@Column(name = "description", length = 255)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "active")
	private Boolean active = true;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "approval_level_one", length = 255)
	private String approvalLevelOne;
	
	@Column(name = "approval_level_two", length = 255)
	private String approvalLevelTwo;
	
	@Column(name = "approval_level_three", length = 255)
	private String approvalLevelThree;
	
	
	@Column(name = "current_assign_approval", length = 255)
	private String currentAssignApproval;
	
	@Column(name = "rejected_by", length = 100)
	private String rejectedBy;
	
	@Column(name = "reason_reject", length = 100)
	private String reasonReject;
	

	@Column(name = "current_approval_level")
	private Integer currentApprovalLevel;

	@Column(name = "approval_level")
	private Integer approvalLevel;
	
	@Transient
	private Employee employeeRequest;
	

	@Transient
	private List<AttachmentDataApproval> attachments;
	
	
	
	
	@Transient
	private Object ref;

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getEmpRequest() {
		return empRequest;
	}

	public void setEmpRequest(Long empRequest) {
		this.empRequest = empRequest;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static String getDirect() {
		return DIRECT;
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

	public Long getObjectRef() {
		return objectRef;
	}

	public void setObjectRef(Long objectRef) {
		this.objectRef = objectRef;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}

	public void setCurrentApprovalLevel(Integer currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}

	public Integer getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(Integer approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public String getCurrentAssignApproval() {
		return currentAssignApproval;
	}

	public void setCurrentAssignApproval(String currentAssignApproval) {
		this.currentAssignApproval = currentAssignApproval;
	}

	public String getApprovalLevelOne() {
		return approvalLevelOne;
	}

	public void setApprovalLevelOne(String approvalLevelOne) {
		this.approvalLevelOne = approvalLevelOne;
	}

	public String getApprovalLevelTwo() {
		return approvalLevelTwo;
	}

	public void setApprovalLevelTwo(String approvalLevelTwo) {
		this.approvalLevelTwo = approvalLevelTwo;
	}

	public String getApprovalLevelThree() {
		return approvalLevelThree;
	}

	public void setApprovalLevelThree(String approvalLevelThree) {
		this.approvalLevelThree = approvalLevelThree;
	}

	public Employee getEmployeeRequest() {
		return employeeRequest;
	}

	public void setEmployeeRequest(Employee employeeRequest) {
		this.employeeRequest = employeeRequest;
	}
	
	

	public Object getRef() {
		return ref;
	}

	public void setRef(Object ref) {
		this.ref = ref;
	}
	
	


	public String getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}

	public List<AttachmentDataApproval> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentDataApproval> attachments) {
		this.attachments = attachments;
	}

	
	
	public String getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public String getReasonReject() {
		return reasonReject;
	}

	public void setReasonReject(String reasonReject) {
		this.reasonReject = reasonReject;
	}

	@Override
	public String toString() {
		return "DataApproval [task=" + task + ", objectRef=" + objectRef
				+ ", objectName=" + objectName + ", data=" + data + ", status="
				+ status + ", description=" + description + ", startDate="
				+ startDate + ", active=" + active + ", endDate=" + endDate
				+ ", approvalLevelOne=" + approvalLevelOne
				+ ", approvalLevelTwo=" + approvalLevelTwo
				+ ", approvalLevelThree=" + approvalLevelThree
				+ ", currentAssignApproval=" + currentAssignApproval
				+ ", currentApprovalLevel=" + currentApprovalLevel
				+ ", approvalLevel=" + approvalLevel + "]";
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	
	
	
	
	
	

}
