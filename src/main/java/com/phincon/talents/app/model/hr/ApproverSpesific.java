package com.phincon.talents.app.model.hr;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "wf_approver_spesific")
public class ApproverSpesific extends AbstractEntity{
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name="task", length=30)
	private String task;
	
	@Column(name="work_location", length=100)
	private String workLocation;
	
	@Column(name="employee_no", length=20)
	private String employeeNo;
	
	@Column(name="employee")
	private Long employee;

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	
	
}
