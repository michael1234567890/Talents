package com.phincon.talents.app.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import com.phincon.talents.app.model.hr.RequestType;
import com.phincon.talents.app.model.hr.TMBalance;
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
	private String reason;
	private boolean isVerified = false;
	private Boolean pulangKampung;
	private String spdType;
	private String type;
	private String typeDesc;
	private Date startTimeBreak;
	private Date endTimeBreak;
	private Date attendanceInTime;
	private Date attendanceOutTime;
	private RequestType requestType;
	private Long employee;
	private Double totalBalance;
	private Integer overtimeIn;
	private Integer overtimeOut;
	private Long substituteToEmployment;
	private Date startDateInTime;
	private Date endDateInTime;
	private Long atempdaily;
	private Date startDateOutTime;
	private Date endDateOutTime;
	private Long requestForFamily;

	private List<BenefitDetailDTO> details;
	private List<Map<String, Object>> attachments;
	private List<TMBalance> listBalance;

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
	public String getSpdType() {
		return spdType;
	}

	public void setSpdType(String spdType) {
		this.spdType = spdType;
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

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}
	
	

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

	
	public Date getAttendanceInTime() {
		return attendanceInTime;
	}

	public void setAttendanceInTime(String attendanceInTime) {
		if (attendanceInTime != null && !attendanceInTime.equals(""))
			this.attendanceInTime = Utils.convertStringToDateTime(attendanceInTime);
		else
			this.attendanceInTime = null;
	}

	public Date getAttendanceOutTime() {
		return attendanceOutTime;
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
	
	
	

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Long getSubstituteToEmployment() {
		return substituteToEmployment;
	}

	public void setSubstituteToEmployment(Long substituteToEmployment) {
		this.substituteToEmployment = substituteToEmployment;
	}

	public void setAttendanceOutTime(String attendanceOutTime) {
		if (attendanceOutTime != null && !attendanceOutTime.equals(""))
			this.attendanceOutTime = Utils.convertStringToDateTime(attendanceOutTime);
		else
			this.attendanceOutTime = null;
		
	}
	
	
	
	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	
	

	public Date getStartDateInTime() {
		return startDateInTime;
	}

	public void setStartDateInTime(String startDateInTime) {
		if (startDateInTime != null && !startDateInTime.equals(""))
			this.startDateInTime = Utils.convertStringToDateTime(startDateInTime);
		else
			this.startDateInTime = null;
	}

	public Date getEndDateInTime() {
		return endDateInTime;
	}

	public void setEndDateInTime(String endDateInTime) {
		if (endDateInTime != null && !endDateInTime.equals(""))
			this.endDateInTime = Utils.convertStringToDateTime(endDateInTime);
		else
			this.endDateInTime = null;
	}
	
	

	public Date getStartDateOutTime() {
		return startDateOutTime;
	}

	public void setStartDateOutTime(String startDateOutTime) {
		if (startDateOutTime != null && !startDateOutTime.equals(""))
			this.startDateOutTime = Utils.convertStringToDateTime(startDateOutTime);
		else
			this.startDateOutTime = null;
	}

	public Date getEndDateOutTime() {
		return endDateOutTime;
	}
	
	

	public Long getAtempdaily() {
		return atempdaily;
	}

	public void setAtempdaily(Long atempdaily) {
		this.atempdaily = atempdaily;
	}

	public void setEndDateOutTime(String endDateOutTime) {
		if (endDateOutTime != null && !endDateOutTime.equals(""))
			this.endDateOutTime = Utils.convertStringToDateTime(endDateOutTime);
		else
			this.endDateOutTime = null;
	}
	
	

	public List<TMBalance> getListBalance() {
		return listBalance;
	}

	public void setListBalance(List<TMBalance> listBalance) {
		this.listBalance = listBalance;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getRequestForFamily() {
		return requestForFamily;
	}

	public void setRequestForFamily(Long requestForFamily) {
		this.requestForFamily = requestForFamily;
	}

	@Override
	public String toString() {
		return "BenefitDTO [total=" + total + ", totalSubmit=" + totalSubmit
				+ ", module=" + module + ", origin=" + origin
				+ ", destination=" + destination + ", categoryType="
				+ categoryType + ", categoryTypeExtId=" + categoryTypeExtId
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", linkRef=" + linkRef + ", linkRefHeader=" + linkRefHeader
				+ ", workflow=" + workflow + ", remark=" + remark + ", reason="
				+ reason + ", isVerified=" + isVerified + ", pulangKampung="
				+ pulangKampung + ", spdType=" + spdType + ", type=" + type
				+ ", typeDesc=" + typeDesc + ", startTimeBreak="
				+ startTimeBreak + ", endTimeBreak=" + endTimeBreak
				+ ", attendanceInTime=" + attendanceInTime
				+ ", attendanceOutTime=" + attendanceOutTime + ", requestType="
				+ requestType + ", employee=" + employee + ", totalBalance="
				+ totalBalance + ", overtimeIn=" + overtimeIn
				+ ", overtimeOut=" + overtimeOut + ", substituteToEmployment="
				+ substituteToEmployment + ", startDateInTime="
				+ startDateInTime + ", endDateInTime=" + endDateInTime
				+ ", atempdaily=" + atempdaily + ", startDateOutTime="
				+ startDateOutTime + ", endDateOutTime=" + endDateOutTime
				+ ", requestForFamily=" + requestForFamily + ", details="
				+ details + ", attachments=" + attachments + ", listBalance="
				+ listBalance + "]";
	}

	

}
