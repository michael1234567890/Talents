package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_family")
public class Family extends AbstractEntity {

	public final static String PENDING = "pending";
	public final static String APPROVED = "approved";
	
	@Column(name = "alive_status")
	private String aliveStatus;

	/*
	 * Child Order Child_Order__c Text(20) Employee Family Company Group
	 * Employee_Family_Company_Group__c Lookup(Company)
	 */

	@Column(name = "employee_ext_id", length=100)
	private String employeeExtId;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "family_temp_id")
	private Long familyTemp;

	
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
	
	@Column(name = "status", length = 255)
	private String status;
	
	@Column(name = "need_sync")
	private Boolean needSync;

	@Column(name = "is_eligible_kacamata")
	private Boolean isEligibleKacamata;
	
	@Column(name = "is_eligible_medical")
	private Boolean isEligibleMedical;

	// @OneToOne
	// @JoinColumn(name = "employee_id")
	// private Employee employee;

	@Column(name = "employee_id")
	private Long employee;

	/*Additional */
	
	@Column(name = "nirc_no", length = 100)
	private String nircNo;
	
	@Column(name = "family_card_no", length = 100)
	private String familyCardNo;
	
	@Column(name = "district", length = 100)
	private String district;
	
	@Column(name = "sub_district", length = 100)
	private String subDistrict;
	
	@Column(name = "rt", length = 10)
	private String rt;
	
	@Column(name = "rw", length = 10)
	private String rw;
	
	@Column(name = "nationality", length = 50)
	private String nationality;
	
	@Column(name = "assurance_name", length = 50)
	private String assuranceName;
	
	@Column(name = "polis_no", length = 50)
	private String polisNo;
	
	@Column(name = "npwp_no", length = 50)
	private String npwpNo;
	
	@Column(name = "passport_no", length = 50)
	private String passportNo;
	
	@Column(name = "zip_code", length = 50)
	private String zipCode;
	
	@Column(name = "email", length = 50)
	private String email;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
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

	public Long getFamilyTemp() {
		return familyTemp;
	}

	public void setFamilyTemp(Long familyTemp) {
		this.familyTemp = familyTemp;
	}

	public String getNircNo() {
		return nircNo;
	}

	public void setNircNo(String nircNo) {
		this.nircNo = nircNo;
	}

	public String getFamilyCardNo() {
		return familyCardNo;
	}

	public void setFamilyCardNo(String familyCardNo) {
		this.familyCardNo = familyCardNo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getRw() {
		return rw;
	}

	public void setRw(String rw) {
		this.rw = rw;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAssuranceName() {
		return assuranceName;
	}

	public void setAssuranceName(String assuranceName) {
		this.assuranceName = assuranceName;
	}

	public String getPolisNo() {
		return polisNo;
	}

	public void setPolisNo(String polisNo) {
		this.polisNo = polisNo;
	}

	public String getNpwpNo() {
		return npwpNo;
	}

	public void setNpwpNo(String npwpNo) {
		this.npwpNo = npwpNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsEligibleKacamata() {
		return isEligibleKacamata;
	}

	public void setIsEligibleKacamata(Boolean isEligibleKacamata) {
		this.isEligibleKacamata = isEligibleKacamata;
	}

	public Boolean getIsEligibleMedical() {
		return isEligibleMedical;
	}

	public void setIsEligibleMedical(Boolean isEligibleMedical) {
		this.isEligibleMedical = isEligibleMedical;
	}
	
	
	
	
	
	

}
