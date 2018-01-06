package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;
/*
 * relation between TMRequestHeader and Balance about balance used
 * 
 */
@Entity
@Table(name="hr_request_header_balance")
public class TMRequestHeaderBalance extends AbstractEntity{

	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "tm_request_header_id")
	private Long tmRequestHeader;
	
	@Column(name = "tm_balance_id")
	private Long tmBalance;
	
	@Column(name = "balance_used")
	private Double balanceUsed;

	
	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getTmRequestHeader() {
		return tmRequestHeader;
	}

	public void setTmRequestHeader(Long tmRequestHeader) {
		this.tmRequestHeader = tmRequestHeader;
	}

	public Long getTmBalance() {
		return tmBalance;
	}

	public void setTmBalance(Long tmBalance) {
		this.tmBalance = tmBalance;
	}

	public Double getBalanceUsed() {
		return balanceUsed;
	}

	public void setBalanceUsed(Double balanceUsed) {
		this.balanceUsed = balanceUsed;
	}
	
	
	
	

}
