package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_employee_assignment")
public class VwEmpAssignment  {
	@Id
	@Column(name = "employment_id")
	private Long employment;

	@Column(name = "employee_id")
	private Long employee;

	@Column(name = "employee_no", length = 100)
	private String employeeNo;
	
	@Column(name = "first_name", length = 255)
	private String firstName;
	
	@Column(name = "middle_name", length = 255)
	private String middleName;
	
	@Column(name = "full_name", length = 255)
	private String fullName;
	
	
	@Column(name = "position_name", length = 255)
	private String positionName;
	
	@Column(name = "organization_name", length = 255)
	private String organizationName;
	
	@Column(name = "organization_id", length = 255)
	private Long organization;
	
	@Column(name = "work_location_ext_id", length = 100)
	private String workLocationExtId;
	
	@Column(name = "work_location_name", length = 100)
	private String workLocationName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(name = "job_title_id")
	private Long jobTitle;

	@Column(name = "job_title_name", length=100)
	private String jobTitleName;

	@Column(name = "assignment_id")
	private Long assignment;

	@Column(name = "assignment_category", length=100)
	private String assignmentCategory;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "department", length=100)
	private String department;
	
	@Column(name = "division", length=100)
	private String division;
	
	@Column(name = "employee_status", length=100)
	private String employeeStatus;
	
	@Column(name = "employee_type", length=100)
	private String employeeType;
	
	@Column(name = "employment_direct_to_id")
	private Long employmentDirectTo;
	
	@Column(name = "direct_employee_id")
	private Long directEmployee;
	
	@Column(name = "direct_first_name")
	private String directFirstName;
	
	@Column(name = "direct_middle_name")
	private String directMiddleName;
	
	@Column(name = "direct_last_name")
	private String directLastName;
	
	@Column(name = "grade_name", length = 100)
	private String gradeName;

	@Column(name = "grade_id")
	private Long grade;
	
	@Column(name = "grade_nominal")
	private Integer gradeNominal;

	public Long getEmployment() {
		return employment;
	}

	public void setEmployment(Long employment) {
		this.employment = employment;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(Long jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

	public Long getAssignment() {
		return assignment;
	}

	public void setAssignment(Long assignment) {
		this.assignment = assignment;
	}

	public String getAssignmentCategory() {
		return assignmentCategory;
	}

	public void setAssignmentCategory(String assignmentCategory) {
		this.assignmentCategory = assignmentCategory;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	

	public Long getEmploymentDirectTo() {
		return employmentDirectTo;
	}

	public void setEmploymentDirectTo(Long employmentDirectTo) {
		this.employmentDirectTo = employmentDirectTo;
	}

	public Long getDirectEmployee() {
		return directEmployee;
	}

	public void setDirectEmployee(Long directEmployee) {
		this.directEmployee = directEmployee;
	}

	public String getDirectFirstName() {
		return directFirstName;
	}

	public void setDirectFirstName(String directFirstName) {
		this.directFirstName = directFirstName;
	}

	public String getDirectMiddleName() {
		return directMiddleName;
	}

	public void setDirectMiddleName(String directMiddleName) {
		this.directMiddleName = directMiddleName;
	}

	public String getDirectLastName() {
		return directLastName;
	}

	public void setDirectLastName(String directLastName) {
		this.directLastName = directLastName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public Long getGrade() {
		return grade;
	}

	public void setGrade(Long grade) {
		this.grade = grade;
	}

	public Integer getGradeNominal() {
		return gradeNominal;
	}

	public void setGradeNominal(Integer gradeNominal) {
		this.gradeNominal = gradeNominal;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public String getWorkLocationExtId() {
		return workLocationExtId;
	}

	public void setWorkLocationExtId(String workLocationExtId) {
		this.workLocationExtId = workLocationExtId;
	}

	public String getWorkLocationName() {
		return workLocationName;
	}

	public void setWorkLocationName(String workLocationName) {
		this.workLocationName = workLocationName;
	}
	
	
	
	
	

}
