package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_job_title")
public class JobTitle extends AbstractEntity {

	@Column(name = "company_id")
	private Long company;

	@Column(name = "job_title", length = 50)
	private String jobTitle;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "note", length = 100)
	private String note;

	@Column(name = "type", length = 50)
	private String type;

	@Column(name = "total_budget")
	private Double totalBudget;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(Double totalBudget) {
		this.totalBudget = totalBudget;
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

}
