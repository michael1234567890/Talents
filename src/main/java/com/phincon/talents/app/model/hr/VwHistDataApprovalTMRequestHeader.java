package com.phincon.talents.app.model.hr;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vw_history_data_approval_tmrequest_header")
public class VwHistDataApprovalTMRequestHeader  {
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "request_no", length = 20)
	private String requestNo;
	
	
	@Column(name = "employee_requester_name", length = 100)
	private String employeeRequesterName;
	
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "request_id")
	private Long request;
	
	@Column(name = "requester_employee_id")
	private Long requesterEmployee;
	
	
	@Column(name = "subtitute_to_employment_id")
	private Long subtituteToEmployment;
	
	
	@Column(name = "data_approval_id")
	private Long dataApproval;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "request_date")
	private Date requestDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "attendance_in_time")
	private Date attendanceInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "attendance_out_time")
	private Date attendanceOutTime;
	
	
	@Column(name = "pulang_kampung")
	private Boolean pulangKampung;
	
	@Column(name = "overtime_in")
	private Integer overtimeIn;
	

	@Column(name = "overtime_out")
	private Integer overtimeOut;
	
	

	@Column(name = "spd_type", length = 100)
	private String spdType;
	
	
	@Column(name = "remark", length = 100)
	private String remark;
	

	@Column(name = "origin", length = 100)
	private String origin;
	

	@Column(name = "destination", length = 100)
	private String destination;
	
	@Column(name = "total_amount_submit")
	private Double totalAmountSubmit;
	

	@Column(name = "category_type", length = 100)
	private String categoryType;
	
	@Column(name = "module", length = 100)
	private String module;
	
	@Column(name = "workflow_status", length = 100)
	private String workflowStatus;
	

	@Column(name = "created_by", length = 100)
	private String createdBy;
	
	@Column(name = "description_history", length = 255)
	private String descriptionHistory;
	
	
	@Column(name = "current_approval")
	private Integer currentApproval;
	
	
	@Column(name = "approval_level")
	private Integer approvalLevel;

	@Column(name = "employee_approval")
	private Long employeeApproval;
	
	@Column(name = "status", length = 50)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	
	public String getEmployeeRequesterName() {
		return employeeRequesterName;
	}

	public void setEmployeeRequesterName(String employeeRequesterName) {
		this.employeeRequesterName = employeeRequesterName;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getRequest() {
		return request;
	}

	public void setRequest(Long request) {
		this.request = request;
	}

	public Long getRequesterEmployee() {
		return requesterEmployee;
	}

	public void setRequesterEmployee(Long requesterEmployee) {
		this.requesterEmployee = requesterEmployee;
	}

	public Long getSubtituteToEmployment() {
		return subtituteToEmployment;
	}

	public void setSubtituteToEmployment(Long subtituteToEmployment) {
		this.subtituteToEmployment = subtituteToEmployment;
	}

	public Long getDataApproval() {
		return dataApproval;
	}

	public void setDataApproval(Long dataApproval) {
		this.dataApproval = dataApproval;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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

	public Date getAttendanceInTime() {
		return attendanceInTime;
	}

	public void setAttendanceInTime(Date attendanceInTime) {
		this.attendanceInTime = attendanceInTime;
	}

	public Date getAttendanceOutTime() {
		return attendanceOutTime;
	}

	public void setAttendanceOutTime(Date attendanceOutTime) {
		this.attendanceOutTime = attendanceOutTime;
	}

	public Boolean getPulangKampung() {
		return pulangKampung;
	}

	public void setPulangKampung(Boolean pulangKampung) {
		this.pulangKampung = pulangKampung;
	}

	public Integer getOvertimeIn() {
		return overtimeIn;
	}

	public void setOvertimeIn(Integer overtimeIn) {
		this.overtimeIn = overtimeIn;
	}

	public Integer getOvertimeOut() {
		return overtimeOut;
	}

	public void setOvertimeOut(Integer overtimeOut) {
		this.overtimeOut = overtimeOut;
	}

	public String getSpdType() {
		return spdType;
	}

	public void setSpdType(String spdType) {
		this.spdType = spdType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getTotalAmountSubmit() {
		return totalAmountSubmit;
	}

	public void setTotalAmountSubmit(Double totalAmountSubmit) {
		this.totalAmountSubmit = totalAmountSubmit;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDescriptionHistory() {
		return descriptionHistory;
	}

	public void setDescriptionHistory(String descriptionHistory) {
		this.descriptionHistory = descriptionHistory;
	}

	public Integer getCurrentApproval() {
		return currentApproval;
	}

	public void setCurrentApproval(Integer currentApproval) {
		this.currentApproval = currentApproval;
	}

	public Integer getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(Integer approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public Long getEmployeeApproval() {
		return employeeApproval;
	}

	public void setEmployeeApproval(Long employeeApproval) {
		this.employeeApproval = employeeApproval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
