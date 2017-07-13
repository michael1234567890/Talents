package com.phincon.talents.app.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.phincon.talents.app.utils.Utils;

public class BenefitDTO {
	private Double total;
	private String module;
	private String origin;
	private String destination;
	private String categoryType;
	private String categoryTypeExtId;
	private Date transactionDate;
	private Long linkRef;
	private Long linkRefHeader;

	private List<BenefitDetailDTO> details;
	private List<Map<String, Object>> attachments;

	public BenefitDTO() {

	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<BenefitDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<BenefitDetailDTO> details) {
		this.details = details;
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		if (transactionDate != null && !transactionDate.equals(""))
			this.transactionDate = Utils.convertStringToDate(transactionDate);
		else
			this.transactionDate = null;
	}

	public String getCategoryTypeExtId() {
		return categoryTypeExtId;
	}

	public void setCategoryTypeExtId(String categoryTypeExtId) {
		this.categoryTypeExtId = categoryTypeExtId;
	}

	public List<Map<String, Object>> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Map<String, Object>> attachments) {
		this.attachments = attachments;
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
	
	public Long getLinkRefHeader() {
		return linkRefHeader;
	}

	public void setLinkRefHeader(Long linkRefHeader) {
		this.linkRefHeader = linkRefHeader;
	}

	public Long getLinkRef() {
		return linkRef;
	}

	public void setLinkRef(Long linkRef) {
		this.linkRef = linkRef;
	}

	@Override
	public String toString() {
		return "BenefitDTO [total=" + total + ", module=" + module
				+ ", categoryType=" + categoryType + ", details=" + details
				+ "]";
	}

}
