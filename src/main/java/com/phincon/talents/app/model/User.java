package com.phincon.talents.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.phincon.talents.app.model.hr.Employee;

@Entity
@Table(name = "USERS")
@NamedQueries({
		@NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where username = :username"),
		@NamedQuery(name = User.FIND_ALL, query = "select u from User u ") })
public class User extends AbstractEntity {
	public static final String FIND_BY_USERNAME = "user.findByUserName";
	public static final String FIND_ALL = "user.findAll";

	@Column(name = "username", length = 100)
	private String username;

	@Column(name = "password", length = 100)
	private String password;

	private boolean activated;

	@Size(min = 0, max = 100)
	@Column(name = "activation_key")
	private String activationKey;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Size(min = 0, max = 100)
	@Column(name = "reset_password_key")
	private String resetPasswordKey;

	@Transient
	private String confirmPassword;

	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "full_name", length = 200)
	private String fullName;

	@Column(name = "apikey", length = 200)
	private String apikey;

	@Column(name = "employee_id")
	private Long employee;
	
	@Column(name = "employee_ext_id")
	private String employeeExtId;
	
	@Column(name = "company_id")
	private Long company;
	

	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
	@Column(name = "photo_profile", length = 255)
	private String photoProfile;
	
	@Column(name = "user_agent", length = 255)
	private String userAgent;
	
	@Column(name = "last_login_address", length = 100)
	private String lastLoginAddress;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_time")
	private Date lastLoginTime;
	

	@Transient
	private Employee employeeTransient;

	public User() {
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public User(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getResetPasswordKey() {
		return resetPasswordKey;
	}

	public void setResetPasswordKey(String resetPasswordKey) {
		this.resetPasswordKey = resetPasswordKey;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Employee getEmployeeTransient() {
		return employeeTransient;
	}

	public void setEmployeeTransient(Employee employeeTransient) {
		this.employeeTransient = employeeTransient;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getEmployeeExtId() {
		return employeeExtId;
	}

	public void setEmployeeExtId(String employeeExtId) {
		this.employeeExtId = employeeExtId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhotoProfile() {
		return photoProfile;
	}

	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
	}
	
	

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getLastLoginAddress() {
		return lastLoginAddress;
	}

	public void setLastLoginAddress(String lastLoginAddress) {
		this.lastLoginAddress = lastLoginAddress;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", activated=" + activated + ", activationKey="
				+ activationKey + ", firstName=" + firstName + ", lastName="
				+ lastName + ", resetPasswordKey=" + resetPasswordKey
				+ ", confirmPassword=" + confirmPassword + ", email=" + email
				+ ", fullName=" + fullName + ", apikey=" + apikey
				+ ", employee=" + employee + ", employeeExtId=" + employeeExtId
				+ ", company=" + company + ", roles=" + roles
				+ ", photoProfile=" + photoProfile + ", employeeTransient="
				+ employeeTransient + "]";
	}
	
	
	

	
}
