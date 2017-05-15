package com.phincon.talents.app.dto;

public class UserInfoDTO {
	private String username;
	private String password;
	private String repassword;
	private String companyCode;
	private Long company;
	private String email;
	private String firstName;
	private String lastName;
	private String employeeExtId;
	private Long employeeId;

	public UserInfoDTO() {

	}

	
	public String getEmployeeExtId() {
		return employeeExtId;
	}


	public void setEmployeeExtId(String employeeExtId) {
		this.employeeExtId = employeeExtId;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInfoDTO(String username) {
		this.username = username;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}
	
	
	
	
	
	

}
