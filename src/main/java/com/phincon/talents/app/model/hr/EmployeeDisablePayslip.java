package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_emp_disable_payslip")
public class EmployeeDisablePayslip extends AbstractEntity{
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;
//	
	@Column(name="employee_no", length=50, nullable=false)
	private String employeeNo;
	
	@Column(name="email", length=100)
	private String email;
	
	
	@Column(name="remark", length=100)
	private String remark;

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

		
}
