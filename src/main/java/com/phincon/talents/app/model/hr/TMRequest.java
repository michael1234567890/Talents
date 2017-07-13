package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_tm_request")
public class TMRequest extends AbstractEntity {

	public final static String REQUEST = "Request";
	public final static String WAITING = "Waiting";
	public final static String REJECT = "Reject";
	public final static String APPROVED = "Approved";
	public final static String CANCEL = "Cancel";
	
	public final static String CAT_MEDICAL = "Medical";
	public final static String CAT_MUTASI = "mutasi";
	
	@Temporal(TemporalType.DATE)
	@Column(name = "request_date")
	private Date requestDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	/*
	 * start date, end date for leave
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "total_day")
	private Double totalDay;
	
	@Column(name = "total_work_day")
	private Double totalWorkDay;
	
	
	/*
	 * start time break , end time break for overtime
	 */

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time_break")
	private Date startTimeBreak;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time_break")
	private Date endTimeBreak;
	
	@Column(name = "request_no", length=100)
	private String reqNo;
	
	@Column(name = "module", length=100)
	private String module;
	
	@Column(name = "category_type", length=100)
	private String categoryType;
	
	@Column(name = "category_type_ext_id", length=100)
	private String categoryTypeExtId;
	
	@Column(name = "type", length=100)
	private String type;
	
	@Column(name = "remark", length=255)
	private String remark;
	
	/*
	 * '1' Request, '2'  Waiting, '3' Reject, '5' Approved, '6' Cancel
	 */
	@Column(name = "status", length=30)
	private String status;
	
	/*
	 * ref no if request cancellation
	 */
	
	@Column(name = "link_ref_id")
	private Long linkRef;
	
	@Column(name = "link_ref_no", length=50)
	private String linkRefNo;


	

	@Column(name = "employment_id")
	private Long employment;

	@Column(name = "employment_ext_id")
	private String employmentExtId;
	
	@Column(name = "requester_employment_ext_id")
	private String requesterEmploymentExtId;
	
	@Column(name = "requester_employment_id")
	private Long requesterEmployment;
	
	@Column(name = "subtitute_to_employment_id")
	private Long substituteToEmployment;
		
	@Column(name = "substitute_employment_ext_id")
	private String substituteToEmploymentExtId;
	
	
	@Column(name = "company_id")
	private Long company;
	

	/*
	 * subtituteToEmp, subtitute Reason for leave
	 */
	
	@Column(name = "subtitute_to_reason", length=100)
	private String subtituteToReason;
	
	/*
	 * Amount of benefit claim
	 */
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "need_sync")
	private Boolean needSync;
	
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "tm_request_header_id")
//	private TMRequestHeader tmRequestHeader;
	
	@Column(name = "tm_request_header_id")
	private Long tmRequestHeader;
	
	@Column(name = "data",length=450)
	private String data;
	
	@Column(name = "origin",length=100)
	private String origin;
	
	@Column(name = "destination",length=100)
	private String destination;
	

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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

	public Double getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(Double totalDay) {
		this.totalDay = totalDay;
	}

	public Double getTotalWorkDay() {
		return totalWorkDay;
	}

	public void setTotalWorkDay(Double totalWorkDay) {
		this.totalWorkDay = totalWorkDay;
	}

	public Date getStartTimeBreak() {
		return startTimeBreak;
	}

	public void setStartTimeBreak(Date startTimeBreak) {
		this.startTimeBreak = startTimeBreak;
	}

	public Date getEndTimeBreak() {
		return endTimeBreak;
	}

	public void setEndTimeBreak(Date endTimeBreak) {
		this.endTimeBreak = endTimeBreak;
	}

	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getLinkRefNo() {
		return linkRefNo;
	}

	public void setLinkRefNo(String linkRefNo) {
		this.linkRefNo = linkRefNo;
	}

	

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

	public String getSubstituteToEmploymentExtId() {
		return substituteToEmploymentExtId;
	}

	public void setSubstituteToEmploymentExtId(String substituteToEmploymentExtId) {
		this.substituteToEmploymentExtId = substituteToEmploymentExtId;
	}

	public String getSubtituteToReason() {
		return subtituteToReason;
	}

	public void setSubtituteToReason(String subtituteToReason) {
		this.subtituteToReason = subtituteToReason;
	}

	public Long getTmRequestHeader() {
		return tmRequestHeader;
	}

	public void setTmRequestHeader(Long tmRequestHeader) {
		this.tmRequestHeader = tmRequestHeader;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
	}

	
	public String getCategoryTypeExtId() {
		return categoryTypeExtId;
	}

	public void setCategoryTypeExtId(String categoryTypeExtId) {
		this.categoryTypeExtId = categoryTypeExtId;
	}

	public Long getEmployment() {
		return employment;
	}

	public void setEmployment(Long employment) {
		this.employment = employment;
	}

	public String getEmploymentExtId() {
		return employmentExtId;
	}

	public void setEmploymentExtId(String employmentExtId) {
		this.employmentExtId = employmentExtId;
	}

	public String getRequesterEmploymentExtId() {
		return requesterEmploymentExtId;
	}

	public void setRequesterEmploymentExtId(String requesterEmploymentExtId) {
		this.requesterEmploymentExtId = requesterEmploymentExtId;
	}

	public Long getRequesterEmployment() {
		return requesterEmployment;
	}

	public void setRequesterEmployment(Long requesterEmployment) {
		this.requesterEmployment = requesterEmployment;
	}

	public Long getSubstituteToEmployment() {
		return substituteToEmployment;
	}

	public void setSubstituteToEmployment(Long substituteToEmployment) {
		this.substituteToEmployment = substituteToEmployment;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public Long getLinkRef() {
		return linkRef;
	}

	public void setLinkRef(Long linkRef) {
		this.linkRef = linkRef;
	}

	
	
	
	
	
	
	

}
