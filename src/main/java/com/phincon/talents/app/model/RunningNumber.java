package com.phincon.talents.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="running_number")
public class RunningNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date date = new Date();
	

	@Column(length = 10)
	private String month;
	
	
	@Column(nullable=false, length = 5)
	private String prefix;
	
	@Column(length = 20)
	private String arg;
	
	
	@Column(name="company_code", length = 5)
	private String customerCode;
	
	@Column(name="last_number")
	private Long lastNumber = Long.valueOf(1L);
	
	@Column(name="company_id")
	private Long company;

	public RunningNumber() {
	}

	public RunningNumber(String id) {
		this.id = Long.valueOf(id);
	}

	public RunningNumber(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Long getLastNumber() {
		return lastNumber;
	}

	public void setLastNumber(Long lastNumber) {
		this.lastNumber = lastNumber;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String code) {
		this.customerCode = code;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	

}
