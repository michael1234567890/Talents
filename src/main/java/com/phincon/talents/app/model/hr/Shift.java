package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_at_shift")
public class Shift extends AbstractEntity{
	
	@Column(name = "master_code", length = 50)
	private String masterCode;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "owner_id", length = 50)
	private String ownerId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "start_time_hour", length = 5)
	private Integer startHour;
	
	@Column(name = "start_time_minute", length = 5)
	private Integer startMinute;
	
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

	public Integer getStartHour() {
		return startHour;
	}

	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
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

	public Boolean getWorkingOnHoliday() {
		return workingOnHoliday;
	}

	public void setWorkingOnHoliday(Boolean workingOnHoliday) {
		this.workingOnHoliday = workingOnHoliday;
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
