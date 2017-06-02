package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company_settings")

public class CompanySettings extends AbstractEntity {
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="company_id")
//	private Company company;
	
	@Column(name="company_id")
	private Long company;
	
	@Column(name="payslip_disclaimer", length=350)
	private String payslipDisclaimer;
	
	@Column(name="is_regex_password_active")
	private Boolean isRegexPasswordActive;
	
	@Column(name="regex_password", length=100)
	private String regexPassword;
	

	@Column(name="msg_error_regex_password", length=255)
	private String msgErrorRegexPassword;

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getPayslipDisclaimer() {
		return payslipDisclaimer;
	}

	public void setPayslipDisclaimer(String payslipDisclaimer) {
		this.payslipDisclaimer = payslipDisclaimer;
	}

	public String getRegexPassword() {
		return regexPassword;
	}

	public void setRegexPassword(String regexPassword) {
		this.regexPassword = regexPassword;
	}

	public String getMsgErrorRegexPassword() {
		return msgErrorRegexPassword;
	}

	public void setMsgErrorRegexPassword(String msgErrorRegexPassword) {
		this.msgErrorRegexPassword = msgErrorRegexPassword;
	}

	public Boolean getIsRegexPasswordActive() {
		return isRegexPasswordActive;
	}

	public void setIsRegexPasswordActive(Boolean isRegexPasswordActive) {
		this.isRegexPasswordActive = isRegexPasswordActive;
	}
	
	
	
	
	
	
	
	
}
