package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_assignment")
public class Assignment extends AbstractEntity {
	public static String MODEDIRECT = "direct";
	public static String MODEINDIRECT = "indirect";
	public static String MODEOTHER = "other";

	@Column(name="position_id")
	private Long position;
	
	@Column(name="position_ext_id")
	private String positionExtId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "job_title")
//	private JobTitle jobTitle;
	
	@Column(name = "job_title")
	private Long jobTitle;

	@Column(name = "employee_id")
	private Long employee;

	@Column(name = "report_to_id")
	private Long reportTo;

	@Column(name = "company_id")
	private Long company;

	@Column(name = "mode", length = 20)
	private String mode;

	@Column(name = "description", length = 200)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "deleted")
	private Boolean deleted = false;

	@Column(name = "work_location", length = 100)
	private String workLocation;

	@Column(name = "home_base", length = 100)
	private String homeBase;

	@Column(name = "department", length = 100)
	private String deparment;

	@Column(name = "division", length = 100)
	private String division;

	@Column(name = "level", length = 100)
	private String level;

	@Column(name = "category", length = 100)
	private String category;
	
	@Column(name = "type", length = 50)
	private String type;
	
	@Column(name = "status", length = 50)
	private String status;
	
	@Column(name = "name", length = 50)
	private String name;
	

	@Column(name = "employee_ext_id", length=100)
	private String employeeExtId;
	

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getHomeBase() {
		return homeBase;
	}

	public void setHomeBase(String homeBase) {
		this.homeBase = homeBase;
	}

	public String getDeparment() {
		return deparment;
	}

	public void setDeparment(String deparment) {
		this.deparment = deparment;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public Long getReportTo() {
		return reportTo;
	}

	public void setReportTo(Long reportTo) {
		this.reportTo = reportTo;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(Long jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeeExtId() {
		return employeeExtId;
	}

	public void setEmployeeExtId(String employeeExtId) {
		this.employeeExtId = employeeExtId;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getPositionExtId() {
		return positionExtId;
	}

	public void setPositionExtId(String positionExtId) {
		this.positionExtId = positionExtId;
	}
	
	



}
