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
@Table(name = "hr_employment")
public class Employment extends AbstractEntity {

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;

	@Column(name = "company_id")
	private Long company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Column(name = "employee_ext_id", length = 50)
	private String employeeExtId;

	@Column(name = "account_name", length = 50)
	private String accountName;

	@Column(name = "description", length = 255)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "effective_date")
	private Date effectiveDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date_contract")
	private Date endDateContract;

	@Temporal(TemporalType.DATE)
	@Column(name = "eop_date")
	private Date eopDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "letter_date")
	private Date letterDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "termination_date")
	private Date terminationDate;

	@Column(name = "termination_notes", length = 100)
	private String terminationNotes;

	@Column(name = "letter_no", length = 20)
	private String letterNo;
	
	@Column(name = "resignation", length = 100)
	private String resignation;
	
	@Column(name = "reason", length = 100)
	private String reason;
	
	
	@Column(name = "name", length = 100)
	private String name;
	

	@Column(name = "employee_type", length = 100)
	private String employeeType;
	
	

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmployeeExtId() {
		return employeeExtId;
	}

	public void setEmployeeExtId(String employeeExtId) {
		this.employeeExtId = employeeExtId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getEndDateContract() {
		return endDateContract;
	}

	public void setEndDateContract(Date endDateContract) {
		this.endDateContract = endDateContract;
	}

	public Date getEopDate() {
		return eopDate;
	}

	public void setEopDate(Date eopDate) {
		this.eopDate = eopDate;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTerminationNotes() {
		return terminationNotes;
	}

	public void setTerminationNotes(String terminationNotes) {
		this.terminationNotes = terminationNotes;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public String getResignation() {
		return resignation;
	}

	public void setResignation(String resignation) {
		this.resignation = resignation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	@Override
	public String toString() {
		return "Employment [company=" + company + ", employee=" + employee
				+ ", employeeExtId=" + employeeExtId + ", accountName="
				+ accountName + ", description=" + description
				+ ", effectiveDate=" + effectiveDate + ", endDateContract="
				+ endDateContract + ", eopDate=" + eopDate + ", letterDate="
				+ letterDate + ", terminationDate=" + terminationDate
				+ ", terminationNotes=" + terminationNotes + ", letterNo="
				+ letterNo + ", resignation=" + resignation + ", reason="
				+ reason + "]";
	}
	
	

}
