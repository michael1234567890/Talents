package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_at_group")
public class Group extends AbstractEntity {
	
	@Column(name = "master_code", length = 50)
	private String masterCode;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "owner_id", length = 50)
	private String ownerId;
	
	@Column(name = "pattern_code", length = 50)
	private String patternCode;
	
	@Column(name = "pattern_no", length = 5)
	private Integer patternNo;
	
	@Column(name = "sequence_day_no", length = 5)
	private Integer sequenceDayNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "working_on_holiday")
	private Boolean workingOnHoliday;
	
	@Column(name = "need_sync")
	private Boolean needSync;
	
	@Column(name = "company_id")
	private Long company;

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

	public Integer getPatternNo() {
		return patternNo;
	}

	public void setPatternNo(Integer patternNo) {
		this.patternNo = patternNo;
	}

	public Integer getSequenceDayNo() {
		return sequenceDayNo;
	}

	public void setSequenceDayNo(Integer sequenceDayNo) {
		this.sequenceDayNo = sequenceDayNo;
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

	public String getPatternCode() {
		return patternCode;
	}

	public void setPatternCode(String patternCode) {
		this.patternCode = patternCode;
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
