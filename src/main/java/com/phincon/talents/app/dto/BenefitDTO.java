package com.phincon.talents.app.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.phincon.talents.app.utils.Utils;

public class BenefitDTO {
	private Double total;
	private Double totalSubmit;
	private String module;
	private String origin;
	private String destination;
	private String categoryType;
	private String categoryTypeExtId;
	private Date startDate;
	private Date endDate;
	private Long linkRef;
	private Long linkRefHeader;
	private String workflow;
	private String remark;
	private boolean isVerified = false;
	private Boolean pulangKampung;

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


	public void setStartDate(String startDate) {
		if (startDate != null && !startDate.equals(""))
			this.startDate = Utils.convertStringToDate(startDate);
		else
			this.startDate = null;
	}

	
	public Date getStartDate() {
		return startDate;
	}
	

	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if (endDate != null && !endDate.equals(""))
			this.endDate = Utils.convertStringToDate(endDate);
		else
			this.endDate = null;
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

	public String getWorkflow() {
		return workflow;
	}

	public void setWorkflow(String workflow) {
		this.workflow = workflow;
	}
	
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	
	

	

	public Boolean getPulangKampung() {
		return pulangKampung;
	}

	public void setPulangKampung(Boolean pulangKampung) {
		this.pulangKampung = pulangKampung;
	}

	public Double getTotalSubmit() {
		return totalSubmit;
	}

	public void setTotalSubmit(Double totalSubmit) {
		this.totalSubmit = totalSubmit;
	}

	@Override
	public String toString() {
		return "BenefitDTO [total=" + total + ", module=" + module
				+ ", categoryType=" + categoryType + ", details=" + details
				+ "]";
	}

}
