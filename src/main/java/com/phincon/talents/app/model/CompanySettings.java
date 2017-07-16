package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company_settings")

public class CompanySettings extends AbstractEntity {
	
	public static final String PAYSLIP_TYPE_MONTHLY = "monthly";
	public static final String PAYSLIP_TYPE_LATEST = "latest";
	
	
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
	

	@Column(name="payslip_type", length=100)
	private String payslipType; // latest, monthly
	

	@Column(name="logo", length=255)
	private String logo;

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

	public String getPayslipType() {
		return payslipType;
	}

	public void setPayslipType(String payslipType) {
		this.payslipType = payslipType;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
	
	
	
	
	
	
}
