package com.phincon.talents.app.model.hr;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.phincon.talents.app.model.AbstractEntity;
import com.phincon.talents.app.model.DataApproval;

@Entity
@Table(name = "hr_tm_request_header")
public class TMRequestHeader extends AbstractEntity {

	public final static String PENDING = "pending";
	public final static String APPROVED = "approved";
	public final static String REJECTED = "rejected";
	public final static String CANCELLED = "cancelled";
	public final static String PREFIX_ATTENDANCE="AT";
	public final static String PREFIX_BENEFIT="BN";
	public final static String MOD_TIME_MANAGEMENT="Time Management";
	public final static String MOD_BENEFIT="Benefit";
	
	@Temporal(TemporalType.DATE)
	@Column(name = "request_date")
	private Date requestDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "request_no", length=100)
	private String reqNo;
	
	@Column(name = "origin", length=100)
	private String origin;
	
	@Column(name = "destination", length=100)
	private String destination;
	
	
	@Column(name = "remark", length=255)
	private String remark;
	
	@Column(name = "module", length=50)
	private String module;
	
	@Column(name = "category_type", length=50)
	private String categoryType;
	
	@Column(name = "category_type_ext_id", length=50)
	private String categoryTypeExtId;
	
	/*
	 * '1' Request, '2'  Waiting, '3' Reject, '5' Approved, '6' Cancel
	 */
	@Column(name = "status")
	private String status;
	
	

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employee_id")
//	private Employee employee;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "requester_employee_id")
//	private Employee requester;
	
	@Column(name = "employee_id")
	private Long employee;
	
	@Column(name = "requester_employee_id")
	private Long requester;
	
	/*
	 * Amount of benefit claim
	 */
	
	@Column(name = "total_amount")
	private Double totalAmount;
	
	@Column(name = "total_amount_submit")
	private Double totalAmountSubmit;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "ref_request_header")
	private Long refRequestHeader;
	
	@Column(name = "need_report")
	private Boolean needReport;
	
	@Column(name = "pulang_kampung")
	private Boolean pulangKampung = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "attendance_in_time")
	private Date attendanceInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "attendance_out_time")
	private Date attendanceOutTime;
	
	

	/*
	 * Overtime in and Out in minutes
	 */
	@Column(name = "overtime_in")
	private Integer overtimeIn;
	
	@Column(name = "overtime_out")
	private Integer overtimeOut;
	
	@Column(name = "subtitute_to_employment_id")
	private Long substituteToEmployment;
	
	
	@Transient
	private List<TMRequest> details;
	
	@Transient
	private TMRequestHeader refRequestHeaderObj;
	
	@Transient
	private DataApproval dataApproval;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date_in_time")
	private Date startDateInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date_in_time")
	private Date endDateInTime;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date_out_time")
	private Date startDateOutTime;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date_out_time")
	private Date endDateOutTime;
	
	
	@Column(name = "atempdaily_id")
	private Long atempdaily;
	

	public DataApproval getDataApproval() {
		return dataApproval;
	}

	public void setDataApproval(DataApproval dataApproval) {
		this.dataApproval = dataApproval;
	}

	@Column(name = "spd_type",length=100)
	private String spdType;
	
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getRequester() {
		return requester;
	}

	public void setRequester(Long requester) {
		this.requester = requester;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public List<TMRequest> getDetails() {
		return details;
	}

	public void setDetails(List<TMRequest> details) {
		this.details = details;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getCategoryTypeExtId() {
		return categoryTypeExtId;
	}

	public void setCategoryTypeExtId(String categoryTypeExtId) {
		this.categoryTypeExtId = categoryTypeExtId;
	}

	public Boolean getNeedReport() {
		return needReport;
	}

	public void setNeedReport(Boolean needReport) {
		this.needReport = needReport;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getTotalAmountSubmit() {
		return totalAmountSubmit;
	}

	public void setTotalAmountSubmit(Double totalAmountSubmit) {
		this.totalAmountSubmit = totalAmountSubmit;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getRefRequestHeader() {
		return refRequestHeader;
	}

	public void setRefRequestHeader(Long refRequestHeader) {
		this.refRequestHeader = refRequestHeader;
	}

	public TMRequestHeader getRefRequestHeaderObj() {
		return refRequestHeaderObj;
	}

	public void setRefRequestHeaderObj(TMRequestHeader refRequestHeaderObj) {
		this.refRequestHeaderObj = refRequestHeaderObj;
	}

	public Boolean getPulangKampung() {
		return pulangKampung;
	}

	public void setPulangKampung(Boolean pulangKampung) {
		this.pulangKampung = pulangKampung;
	}

	public String getSpdType() {
		return spdType;
	}

	public void setSpdType(String spdType) {
		this.spdType = spdType;
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

	public Long getSubstituteToEmployment() {
		return substituteToEmployment;
	}

	public void setSubstituteToEmployment(Long substituteToEmployment) {
		this.substituteToEmployment = substituteToEmployment;
	}

	public Date getStartDateInTime() {
		return startDateInTime;
	}

	public void setStartDateInTime(Date startDateInTime) {
		this.startDateInTime = startDateInTime;
	}

	public Date getEndDateInTime() {
		return endDateInTime;
	}

	public void setEndDateInTime(Date endDateInTime) {
		this.endDateInTime = endDateInTime;
	}

	public Date getStartDateOutTime() {
		return startDateOutTime;
	}

	public void setStartDateOutTime(Date startDateOutTime) {
		this.startDateOutTime = startDateOutTime;
	}

	public Date getEndDateOutTime() {
		return endDateOutTime;
	}

	public void setEndDateOutTime(Date endDateOutTime) {
		this.endDateOutTime = endDateOutTime;
	}

	public Long getAtempdaily() {
		return atempdaily;
	}

	public void setAtempdaily(Long atempdaily) {
		this.atempdaily = atempdaily;
	}

	
	
	
	
}
