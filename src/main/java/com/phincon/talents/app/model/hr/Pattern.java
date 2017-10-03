package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_at_pattern")
public class Pattern extends AbstractEntity{
	
	@Column(name = "lookup_field", length = 50)
	private String lookupField;
	
	@Column(name = "master_code", length = 50)
	private String masterCode;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "no_days", length = 10)
	private Integer noDays;
	
	@Column(name = "owner_id", length = 50)
	private String ownerId;
	
	@Column(name = "pattern_no", length = 10)
	private Integer patternNo;
	
	@Column(name = "shift_code", length = 50)
	private String shiftCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "working_on_holiday")
	private Boolean workingOnHoliday;
	
	@Column(name = "need_sync")
	private Boolean needSync;
	
	@Column(name = "company_id")
	private Long company;

	public String getLookupField() {
		return lookupField;
	}

	public void setLookupField(String lookupField) {
		this.lookupField = lookupField;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public Integer getNoDays() {
		return noDays;
	}

	public void setNoDays(Integer noDays) {
		this.noDays = noDays;
	}

	public Integer getPatternNo() {
		return patternNo;
	}

	public void setPatternNo(Integer patternNo) {
		this.patternNo = patternNo;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}

	public Boolean getWorkingOnHoliday() {
		return workingOnHoliday;
	}

	public void setWorkingOnHoliday(Boolean workingOnHoliday) {
		this.workingOnHoliday = workingOnHoliday;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}
	
	
	
}
