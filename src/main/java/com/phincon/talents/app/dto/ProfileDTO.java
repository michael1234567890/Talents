package com.phincon.talents.app.dto;

import java.util.List;

import com.phincon.talents.app.model.Company;
import com.phincon.talents.app.model.CompanyReference;
import com.phincon.talents.app.model.CompanySettings;
import com.phincon.talents.app.model.hr.Employee;

public class ProfileDTO {
	private String image;
	private String firstName;
	private String lastName;
	private String companyName;
	private String name;
	private String email;
	private String mobilePhone;
	private Boolean isAdmin;
	private Boolean isLeader;
	private Long needApproval;
	private Employee employeeTransient;
	private Company company;
	private CompanySettings companySettings;
	private List<CompanyReference> companyReference;
	

	public ProfileDTO() {

	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee getEmployeeTransient() {
		return employeeTransient;
	}

	public void setEmployeeTransient(Employee employeeTransient) {
		this.employeeTransient = employeeTransient;
	}

	public List<CompanyReference> getCompanyReference() {
		return companyReference;
	}

	public void setCompanyReference(List<CompanyReference> companyReference) {
		this.companyReference = companyReference;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanySettings getCompanySettings() {
		return companySettings;
	}

	public void setCompanySettings(CompanySettings companySettings) {
		this.companySettings = companySettings;
	}

	public Long getNeedApproval() {
		return needApproval;
	}

	public void setNeedApproval(Long needApproval) {
		this.needApproval = needApproval;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Boolean isLeader) {
		this.isLeader = isLeader;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	

	

}
