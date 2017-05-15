package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name="hr_certification")
public class Certification extends AbstractEntity {

	@Column(name = "name", length = 100)
	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 100)
	private Date date;

	@Column(name = "description", length = 255)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "expired")
	private Date expired;

	@Column(name = "full", length = 500)
	private String full;

	@Column(name = "no", length = 100)
	private String no;

	@Column(name = "type", length = 100)
	private String type;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Column(name = "principle")
	private String principle;

	@Column(name = "year", length = 15)
	private String year;
	
	@Column(name = "need_sync")
	private Boolean needSync;
	

	@Column(name = "employee_ext_id", length=100)
	private String employeeExtId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}

	public String getFull() {
		return full;
	}

	public void setFull(String full) {
		this.full = full;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
	}

	public String getEmployeeExtId() {
		return employeeExtId;
	}

	public void setEmployeeExtId(String employeeExtId) {
		this.employeeExtId = employeeExtId;
	}

	
	
	
	
}
