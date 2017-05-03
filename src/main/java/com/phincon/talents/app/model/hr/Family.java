package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_family")
public class Family extends AbstractEntity {

	@Column(name = "alive_status")
	private String aliveStatus;

	/*
	 * Child Order Child_Order__c Text(20) Employee Family Company Group
	 * Employee_Family_Company_Group__c Lookup(Company)
	 */

	@Column(name = "address", length = 1000)
	private String address;

	@Column(name = "birth_place", length = 100)
	private String birthPlace;

	@Column(name = "blood_type", length = 10)
	private String bloodType;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "decease_date")
	private Date deceaseDate;

	@Column(name = "dependent", length = 20)
	private String dependent;

	@Column(name = "last_education", length = 255)
	private String lastEducation;

	@Column(name = "medical_status", length = 255)
	private String medicalStatus;

	@Column(name = "occupation", length = 255)
	private String occupation;

	@Column(name = "phone", length = 30)
	private String phone;

	@Column(name = "relationship", length = 30)
	private String relationship;

	@Column(name = "gender", length = 30)
	private String gender;

	@Column(name = "letter_no", length = 255)
	private String letterNo;

	@Column(name = "marital_status", length = 255)
	private String maritalStatus;

	@Column(name = "name", length = 255)
	private String name;

	// @OneToOne
	// @JoinColumn(name = "employee_id")
	// private Employee employee;

	@Column(name = "employee_id")
	private Long employee;

	public String getAliveStatus() {
		return aliveStatus;
	}

	public void setAliveStatus(String aliveStatus) {
		this.aliveStatus = aliveStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeceaseDate() {
		return deceaseDate;
	}

	public void setDeceaseDate(Date deceaseDate) {
		this.deceaseDate = deceaseDate;
	}

	public String getDependent() {
		return dependent;
	}

	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	public String getLastEducation() {
		return lastEducation;
	}

	public void setLastEducation(String lastEducation) {
		this.lastEducation = lastEducation;
	}

	public String getMedicalStatus() {
		return medicalStatus;
	}

	public void setMedicalStatus(String medicalStatus) {
		this.medicalStatus = medicalStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Long getEmployee() {
		return employee;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
