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

@Entity
@Table(name = "hr_tm_request_header")
public class TMRequestHeader extends AbstractEntity {

	public final static String PENDING = "pending";
	public final static String APPROVED = "approved";
	public final static String REJECTED = "rejected";
	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "request_date")
	private Date requestDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
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
	
	@Column(name = "need_report")
	private Boolean needReport;
	
	
	@Transient
	private List<TMRequest> details;
	

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

	
	
	
	
	

}
