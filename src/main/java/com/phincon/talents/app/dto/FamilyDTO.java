package com.phincon.talents.app.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

public class FamilyDTO {
	private Long id;
	private String name;
	private String relationship;
	private String birthPlace;
	private String gender;
	private Date birthDate;
	private String bloodType;
	private String phone;
	private String email;
	private String address;
	private String occupation;
	private String maritalStatus;
	private String aliveStatus;
	private List<Map<String,Object>> attachments;
	private String nircNo;
	private String familyCardNo;
	private String district;
	private String subDistrict;
	private String rt;
	private String rw;
	private String nationality;
	private String assuranceName;
	private String polisNo;
	private String npwpNo;
	private String passportNo;
	private String zipCode;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public List<Map<String, Object>> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Map<String, Object>> attachments) {
		this.attachments = attachments;
	}

	public String getAliveStatus() {
		return aliveStatus;
	}

	public void setAliveStatus(String aliveStatus) {
		this.aliveStatus = aliveStatus;
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
	
	
	
	
	

}
