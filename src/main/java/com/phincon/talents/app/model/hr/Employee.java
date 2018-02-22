package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name="hr_employee")
public class Employee extends AbstractEntity {
	
	public final static String STATUS_PENDING = "pending"; 
	public final static String APPROVAL_1 = "approval 1"; 
	public final static String APPROVAL_2 = "approval 2"; 
	public final static String APPROVAL_3 = "approval 3"; 
	public final static String APPROVAL_4 = "approval 4"; 
	
	@Column(name="name", length=255)
	private String name;
	
	@Column(name="age", length=50)
	private String age;
	
	@Temporal(TemporalType.DATE)
	@Column(name="anniversary_date")
	private Date anniversaryDate;
	
	@Column(name="applicant_no", length=50)
	private String applicantNo;
	
	@Column(name="attendance_code", length=30)
	private String attendanceCode;
	
	@Column(name="authorized", length=30)
	private String authorized;
	 
	@Column(name="birth_place", length=100)
	private String birthPlace;
	
	@Column(name="blood_type", length=10)
	private String bloodType;
	
	@Column(name="bpjs_kesehatan_no", length=100)
	private String bpjsKesehatanNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="bpjs_kesehatan_date")
	private Date bpjsKesehatanDate;
	
	@Column(name="bpjs_tk_no", length=50)
	private String bpjsTKNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="bpjs_tk_date")
	private Date bpjsTKDate;
	
	
	@Column(name="cloth_size",length=5)
	private String clothSize;
	
	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	private Date birthDate;
	

	@Column(name="dialect", length=50)
	private String dialect;

	@Column(name="disability", length=30)
	private String disablity;
	
	@Temporal(TemporalType.DATE)
	@Column(name="license_driving_date_1")
	private Date licenseDrivingDate1;
	
	@Temporal(TemporalType.DATE)
	private Date licenseExpiredDriving1;
	
	@Column(name="license_driving_no_1", length=50)
	private String licenseDrivingNo1;
	
	@Temporal(TemporalType.DATE)
	@Column(name="license_driving_date_2")
	private Date licenseDrivingDate2;
	
	@Temporal(TemporalType.DATE)
	@Column(name="license_expired_driving_2")
	private Date licenseExpiredDriving2;
	
	@Column(name="license_driving_no_2", length=50)
	private String licenseDrivingNo2;

	
	@Column(name="education_title_1", length=30)
	private String educationTitle1;
	
	@Column(name="education_title_2", length=30)
	private String educationTitle2;
	
	@Column(name="effective_date")
	private Date effectiveDate;
	
	@Column(name="eligible_for_rehire")
	private Boolean eligibleForRehire;
	
	
	@Transient
	private Address address;
	
	
	@Column(name="emergency_contact_city")
	private String emergencyContactCity;
	

	@Column(name="emergency_contact_address")
	private String emergencyContactAddress;
	
	@Column(name="emergency_contact_country")
	private String emergencyContactCountry;
	
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	
	@Column(name="emergency_contact_name")
	private String emergencyContactName;
	
	@Column(name="emergency_contact_other_phone", length=20)
	private String emergencyContactOtherPhone;
	
	@Column(name="emergency_contact_phone", length=20)
	private String emergencyContactPhone;
	
	@Column(name="emergency_contact_relationship", length=30)
	private String emergencyContactRelationship;
	
	@Column(name="emergency_contact_rt", length=5)
	private String emergencyContactRt;
	
	@Column(name="emergency_contact_rw", length=5)
	private String emergencyContactRw;
	
	@Column(name="emergency_contact_state_province", length=50)
	private String emergencyContactStateProvince;
	
	@Column(name="emergency_contact_subdistrict", length=50)
	private String emergencyContactSubdistrict;
	
	@Column(name="emergency_contact_zip_code",length=20)
	private String emergencyContactZipCode;
	
	/*
	Employee_No__c	Lookup(Employment)
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	*/
	
	@Column(name="employee_status",length=20)
	private String employeeStatus;
	
	@Column(name="end_date_contract")
	private Date endDateContract;
	
	@Column(name="family_card_no", length=50)
	private String familyCardNo;
	
	@Column(name="first_name", length=50)
	private String firstName;
	
	@Column(name="ktp_name",length=200)
	private String ktpName;
	
	/*Foreign Language Proficiency	Foreign_Language_Proficiency__c
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;*/
	
	@Column(name="pension_reminder_day")
	private Integer pensionReminderDay;
	
	@Temporal(TemporalType.DATE)
	@Column(name="pension_reminder_months")
	private Date pensionReminderMonths;
	
	@Column(name="full_name_title", length=255)
	private String fullNameTitle;
	
	@Column(name="gender", length=15)
	private String gender;
	
	@Column(name="head_size", length=10)
	private String headSize;
	
	@Column(name="height",length=5)
	private String height;

	@Temporal(TemporalType.DATE)
	@Column(name="hire_date")
	private Date hireDate;
	
	@Column(name="hoby", length=255)
	private String hoby;
	
	@Column(name="initial_name", length=5)
	private String initialName;
	
	@Column(name="interest", length=4000)
	private String interest;
	
	@Column(name="job_title_name", length=50)
	private String jobTitleName;
	


	
	/*
	 * Last_Education__c	Lookup(Education)
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	*/
	
	@Column(name="last_name", length=50)
	private String lastName;
	
	/*
	Length_of_Service__c	Formula (Text)
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	*/
	
	@Temporal(TemporalType.DATE)
	@Column(name="letter_date")
	private Date letterDate;
	
	@Column(name="letter_no",length=50)
	private String letterNo;
	
	/*
	LOS	LOS__c
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	
	LOSF	LOSF__c
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	*/
	
	@Column(name="marital_status", length=30)
	private String maritalStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="married_date")
	private Date marriedDate;
	
	@Column(name="married_place", length=50)
	private String marriedPlace;
	
	/*
	Medical_Aware__c	Rich Text Area(4000)
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	*/
	
	@Column(name="middle_name",length=50)
	private String middleName;
	
	@Column(name="mobile_phone", length=20)
	private String mobilePhone;
	
	
	@Column(name="mobile_phone_1",length=20)
	private String mobilePhone1;
	
	
	@Column(name="mobile_phone_2", length=20)
	private String mobilePhone2;
	
	@Column(name="mobile_phone_3", length=20)
	private String mobilePhone3;
	
	@Column(name="nationality", length=30)
	private String nationality;
	
	@Temporal(TemporalType.DATE)
	@Column(name="next_birth_date")
	private Date nextBirthDate;
	
	@Column(name="nickname",length=30)
	private String nickName;
	
	@Column(name="npwp_no", length=50)
	private String npwpNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="npwp_date")
	private Date npwpDate;

	@Temporal(TemporalType.DATE)
	@Column(name="nirc_date")
	private Date nircDate;
	

	@Temporal(TemporalType.DATE)
	@Column(name="nirc_expiration_date")
	private Date nircExpirationDate;
	
	
	@Column(name="nirc_no", length=255)
	private String nircNo;
	
	@Column(name="office_mail", length=100)
	private String officeMail;
	
	@Temporal(TemporalType.DATE)
	@Column(name="official_end_date")
	private Date officialEndDate;
	
	@Column(name="pant_size", length=10)
	private String pantSize;
	
	@Temporal(TemporalType.DATE)
	@Column(name="passport_date")
	private Date passportDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="passport_expiry_date")
	private Date passportExpiryDate;
	
	
	@Column(name="passport_id", length=200)
	private String passpordId;
	
	@Column(name="passport_no", length=200)
	private String passportNo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="pension_date")
	private Date pensionData;
	
	/*
	Perform Value	Perform_Value__c
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	*/
	
	@Temporal(TemporalType.DATE)
	@Column(name="permanent_date")
	private Date permanentDate;
	
	
	@Column(name="email", length=100)
	private String email;
	
	
	@Column(name="phone", length=20)
	private String phone;
	
	
	@Column(name="phone_ext", length=10)
	private String phoneExt;
	
	/*
	Photo__c	Formula (Text)
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	
	
	Photo Id	Photo_Id__c
	@Column(name="emergency_contact_district")
	private String emergencyContactDistrict;
	*/
	
	@Temporal(TemporalType.DATE)
	@Column(name="pre_pension_date")
	private Date prePensionDate;
	
	
	@Column(name="religion", length=50)
	private String religion;
	
	@Column(name="resignation", length=50)
	private String resignation;
	
	@Column(name="salution",length=10)
	private String salution;
	
	@Column(name="shoe_size",length=5)
	private String shoeSize;
	
	
	@Column(name="skill", length=4000)
	private String skill;
	
	@Column(name="skill_level", length=5)
	private String skillLevel;
	
	@Column(name="strong_point", length=400)
	private String strongPoint;

	@Temporal(TemporalType.DATE)
	@Column(name="termination_date")
	private Date terminationDate;
	
	@Column(name="termination_notes", length=4000)
	private String terminatioNotes;
	
	@Column(name="tools", length=4000)
	private String tools;
	
	
	@Column(name="weak_point",length=4000)
	private String weakPoint;
	
	@Column(name="wear_glasses")
	private String wearGlasses;
	
	
	@Column(name="weight", length=5)
	private String weight;
	
	
	@Column(name="company_id")
	private Long company;
	
	@Column(name="employee_no", length=50)
	private String employeeNo;
	
	@Column(name="work_interest")
	private String workInterest;
	
	@Column(name="change_marital_status", length=50)
	private String changeMaritalStatus;
	
	@Column(name="change_npwp", length=50)
	private String changeNPWP;
	
	
	@Column(name="change_nircno", length=50)
	private String changeNIRCNO;
	
	@Column(name="change_ktpname", length=50)
	private String changeKTPNAME;

	
	public String getChangeKTPNAME() {
		return changeKTPNAME;
	}

	public void setChangeKTPNAME(String changeKTPNAME) {
		this.changeKTPNAME = changeKTPNAME;
	}

	@Column(name="marital_status_dataapproval_id")
	private Long maritalStatusDataApproval;
	
	@Column(name="npwp_status_dataapproval_id")
	private Long npwpStatusDataApproval;
	
	
	@Column(name="nirc_status_dataapproval_id")
	private Long nircStatusDataApproval;
	
	
	@Column(name="ktpname_status_dataapproval_id")
	private Long ktpnameStatusDataApproval;
	
	
	@Column(name="need_sync")
	private Boolean needSync;
	
	public Long getKtpnameStatusDataApproval() {
		return ktpnameStatusDataApproval;
	}

	public void setKtpnameStatusDataApproval(Long ktpnameStatusDataApproval) {
		this.ktpnameStatusDataApproval = ktpnameStatusDataApproval;
	}

	// transient
	@Transient
	private VwEmpAssignment assignment;
	
	@Transient
	private String jobTitle;
	
	@Transient
	private String photoProfile;
	
	@Column(name="benefit_type", length=20)
	private String benefitType;
	
	@Column(name="last_education", length=100)
	private String lastEducation;
	

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getAnniversaryDate() {
		return anniversaryDate;
	}

	public void setAnniversaryDate(Date anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}

	public String getApplicantNo() {
		return applicantNo;
	}

	public void setApplicantNo(String applicantNo) {
		this.applicantNo = applicantNo;
	}

	public String getAttendanceCode() {
		return attendanceCode;
	}

	public void setAttendanceCode(String attendanceCode) {
		this.attendanceCode = attendanceCode;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
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

	public String getBpjsKesehatanNo() {
		return bpjsKesehatanNo;
	}

	public void setBpjsKesehatanNo(String bpjsKesehatanNo) {
		this.bpjsKesehatanNo = bpjsKesehatanNo;
	}

	public Date getBpjsKesehatanDate() {
		return bpjsKesehatanDate;
	}

	public void setBpjsKesehatanDate(Date bpjsKesehatanDate) {
		this.bpjsKesehatanDate = bpjsKesehatanDate;
	}

	public String getBpjsTKNo() {
		return bpjsTKNo;
	}

	public void setBpjsTKNo(String bpjsTKNo) {
		this.bpjsTKNo = bpjsTKNo;
	}

	public Date getBpjsTKDate() {
		return bpjsTKDate;
	}

	public void setBpjsTKDate(Date bpjsTKDate) {
		this.bpjsTKDate = bpjsTKDate;
	}

	public String getClothSize() {
		return clothSize;
	}

	public void setClothSize(String clothSize) {
		this.clothSize = clothSize;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public String getDisablity() {
		return disablity;
	}

	public void setDisablity(String disablity) {
		this.disablity = disablity;
	}

	public Date getLicenseDrivingDate1() {
		return licenseDrivingDate1;
	}

	public void setLicenseDrivingDate1(Date licenseDrivingDate1) {
		this.licenseDrivingDate1 = licenseDrivingDate1;
	}

	public Date getLicenseExpiredDriving1() {
		return licenseExpiredDriving1;
	}

	public void setLicenseExpiredDriving1(Date licenseExpiredDriving1) {
		this.licenseExpiredDriving1 = licenseExpiredDriving1;
	}

	public String getLicenseDrivingNo1() {
		return licenseDrivingNo1;
	}

	public void setLicenseDrivingNo1(String licenseDrivingNo1) {
		this.licenseDrivingNo1 = licenseDrivingNo1;
	}

	public Date getLicenseDrivingDate2() {
		return licenseDrivingDate2;
	}

	public void setLicenseDrivingDate2(Date licenseDrivingDate2) {
		this.licenseDrivingDate2 = licenseDrivingDate2;
	}

	public Date getLicenseExpiredDriving2() {
		return licenseExpiredDriving2;
	}

	public void setLicenseExpiredDriving2(Date licenseExpiredDriving2) {
		this.licenseExpiredDriving2 = licenseExpiredDriving2;
	}

	public String getLicenseDrivingNo2() {
		return licenseDrivingNo2;
	}

	public void setLicenseDrivingNo2(String licenseDrivingNo2) {
		this.licenseDrivingNo2 = licenseDrivingNo2;
	}

	public String getEducationTitle1() {
		return educationTitle1;
	}

	public void setEducationTitle1(String educationTitle1) {
		this.educationTitle1 = educationTitle1;
	}

	public String getEducationTitle2() {
		return educationTitle2;
	}

	public void setEducationTitle2(String educationTitle2) {
		this.educationTitle2 = educationTitle2;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Boolean getEligibleForRehire() {
		return eligibleForRehire;
	}

	public void setEligibleForRehire(Boolean eligibleForRehire) {
		this.eligibleForRehire = eligibleForRehire;
	}

	public String getEmergencyContactCity() {
		return emergencyContactCity;
	}

	public void setEmergencyContactCity(String emergencyContactCity) {
		this.emergencyContactCity = emergencyContactCity;
	}

	public String getEmergencyContactAddress() {
		return emergencyContactAddress;
	}

	public void setEmergencyContactAddress(String emergencyContactAddress) {
		this.emergencyContactAddress = emergencyContactAddress;
	}

	public String getEmergencyContactCountry() {
		return emergencyContactCountry;
	}

	public void setEmergencyContactCountry(String emergencyContactCountry) {
		this.emergencyContactCountry = emergencyContactCountry;
	}

	public String getEmergencyContactDistrict() {
		return emergencyContactDistrict;
	}

	public void setEmergencyContactDistrict(String emergencyContactDistrict) {
		this.emergencyContactDistrict = emergencyContactDistrict;
	}

	public String getEmergencyContactName() {
		return emergencyContactName;
	}

	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactOtherPhone() {
		return emergencyContactOtherPhone;
	}

	public void setEmergencyContactOtherPhone(String emergencyContactOtherPhone) {
		this.emergencyContactOtherPhone = emergencyContactOtherPhone;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public String getEmergencyContactRelationship() {
		return emergencyContactRelationship;
	}

	public void setEmergencyContactRelationship(String emergencyContactRelationship) {
		this.emergencyContactRelationship = emergencyContactRelationship;
	}

	public String getEmergencyContactRt() {
		return emergencyContactRt;
	}

	public void setEmergencyContactRt(String emergencyContactRt) {
		this.emergencyContactRt = emergencyContactRt;
	}

	public String getEmergencyContactRw() {
		return emergencyContactRw;
	}

	public void setEmergencyContactRw(String emergencyContactRw) {
		this.emergencyContactRw = emergencyContactRw;
	}

	public String getEmergencyContactStateProvince() {
		return emergencyContactStateProvince;
	}

	public void setEmergencyContactStateProvince(
			String emergencyContactStateProvince) {
		this.emergencyContactStateProvince = emergencyContactStateProvince;
	}

	public String getEmergencyContactSubdistrict() {
		return emergencyContactSubdistrict;
	}

	public void setEmergencyContactSubdistrict(String emergencyContactSubdistrict) {
		this.emergencyContactSubdistrict = emergencyContactSubdistrict;
	}

	public String getEmergencyContactZipCode() {
		return emergencyContactZipCode;
	}

	public void setEmergencyContactZipCode(String emergencyContactZipCode) {
		this.emergencyContactZipCode = emergencyContactZipCode;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public Date getEndDateContract() {
		return endDateContract;
	}

	public void setEndDateContract(Date endDateContract) {
		this.endDateContract = endDateContract;
	}

	public String getFamilyCardNo() {
		return familyCardNo;
	}

	public void setFamilyCardNo(String familyCardNo) {
		this.familyCardNo = familyCardNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getKtpName() {
		return ktpName;
	}

	public void setKtpName(String ktpName) {
		this.ktpName = ktpName;
	}

	public Integer getPensionReminderDay() {
		return pensionReminderDay;
	}

	public void setPensionReminderDay(Integer pensionReminderDay) {
		this.pensionReminderDay = pensionReminderDay;
	}

	public Date getPensionReminderMonths() {
		return pensionReminderMonths;
	}

	public void setPensionReminderMonths(Date pensionReminderMonths) {
		this.pensionReminderMonths = pensionReminderMonths;
	}

	public String getFullNameTitle() {
		return fullNameTitle;
	}

	public void setFullNameTitle(String fullNameTitle) {
		this.fullNameTitle = fullNameTitle;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadSize() {
		return headSize;
	}

	public void setHeadSize(String headSize) {
		this.headSize = headSize;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getHoby() {
		return hoby;
	}

	public void setHoby(String hoby) {
		this.hoby = hoby;
	}

	public String getInitialName() {
		return initialName;
	}

	public void setInitialName(String initialName) {
		this.initialName = initialName;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getLetterDate() {
		return letterDate;
	}

	public void setLetterDate(Date letterDate) {
		this.letterDate = letterDate;
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

	public Date getMarriedDate() {
		return marriedDate;
	}

	public void setMarriedDate(Date marriedDate) {
		this.marriedDate = marriedDate;
	}

	public String getMarriedPlace() {
		return marriedPlace;
	}

	public void setMarriedPlace(String marriedPlace) {
		this.marriedPlace = marriedPlace;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getMobilePhone1() {
		return mobilePhone1;
	}

	public void setMobilePhone1(String mobilePhone1) {
		this.mobilePhone1 = mobilePhone1;
	}

	public String getMobilePhone2() {
		return mobilePhone2;
	}

	public void setMobilePhone2(String mobilePhone2) {
		this.mobilePhone2 = mobilePhone2;
	}

	public String getMobilePhone3() {
		return mobilePhone3;
	}

	public void setMobilePhone3(String mobilePhone3) {
		this.mobilePhone3 = mobilePhone3;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Date getNextBirthDate() {
		return nextBirthDate;
	}

	public void setNextBirthDate(Date nextBirthDate) {
		this.nextBirthDate = nextBirthDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNpwpNo() {
		return npwpNo;
	}

	public void setNpwpNo(String npwpNo) {
		this.npwpNo = npwpNo;
	}

	public Date getNpwpDate() {
		return npwpDate;
	}

	public void setNpwpDate(Date npwpDate) {
		this.npwpDate = npwpDate;
	}

	public Date getNircDate() {
		return nircDate;
	}

	public void setNircDate(Date nircDate) {
		this.nircDate = nircDate;
	}

	public Date getNircExpirationDate() {
		return nircExpirationDate;
	}

	public void setNircExpirationDate(Date nircExpirationDate) {
		this.nircExpirationDate = nircExpirationDate;
	}

	public String getNircNo() {
		return nircNo;
	}

	public void setNircNo(String nircNo) {
		this.nircNo = nircNo;
	}

	public String getOfficeMail() {
		return officeMail;
	}

	public void setOfficeMail(String officeMail) {
		this.officeMail = officeMail;
	}

	public Date getOfficialEndDate() {
		return officialEndDate;
	}

	public void setOfficialEndDate(Date officialEndDate) {
		this.officialEndDate = officialEndDate;
	}

	public String getPantSize() {
		return pantSize;
	}

	public void setPantSize(String pantSize) {
		this.pantSize = pantSize;
	}

	public Date getPassportDate() {
		return passportDate;
	}

	public void setPassportDate(Date passportDate) {
		this.passportDate = passportDate;
	}

	public Date getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(Date passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public String getPasspordId() {
		return passpordId;
	}

	public void setPasspordId(String passpordId) {
		this.passpordId = passpordId;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public Date getPensionData() {
		return pensionData;
	}

	public void setPensionData(Date pensionData) {
		this.pensionData = pensionData;
	}

	public Date getPermanentDate() {
		return permanentDate;
	}

	public void setPermanentDate(Date permanentDate) {
		this.permanentDate = permanentDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneExt() {
		return phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	public Date getPrePensionDate() {
		return prePensionDate;
	}

	public void setPrePensionDate(Date prePensionDate) {
		this.prePensionDate = prePensionDate;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getResignation() {
		return resignation;
	}

	public void setResignation(String resignation) {
		this.resignation = resignation;
	}

	public String getSalution() {
		return salution;
	}

	public void setSalution(String salution) {
		this.salution = salution;
	}

	public String getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(String shoeSize) {
		this.shoeSize = shoeSize;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getStrongPoint() {
		return strongPoint;
	}

	public void setStrongPoint(String strongPoint) {
		this.strongPoint = strongPoint;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTerminatioNotes() {
		return terminatioNotes;
	}

	public void setTerminatioNotes(String terminatioNotes) {
		this.terminatioNotes = terminatioNotes;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getWeakPoint() {
		return weakPoint;
	}

	public void setWeakPoint(String weakPoint) {
		this.weakPoint = weakPoint;
	}

	public String getWearGlasses() {
		return wearGlasses;
	}

	public void setWearGlasses(String wearGlasses) {
		this.wearGlasses = wearGlasses;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWorkInterest() {
		return workInterest;
	}

	public void setWorkInterest(String workInterest) {
		this.workInterest = workInterest;
	}

//	public Company getCompany() {
//		return company;
//	}
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}
	
	

	public String getEmployeeNo() {
		return employeeNo;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public VwEmpAssignment getAssignment() {
		return assignment;
	}

	public void setAssignment(VwEmpAssignment assignment) {
		this.assignment = assignment;
	}
	
	

	public String getChangeMaritalStatus() {
		return changeMaritalStatus;
	}

	public void setChangeMaritalStatus(String changeMaritalStatus) {
		this.changeMaritalStatus = changeMaritalStatus;
	}

	
	

	public String getChangeNPWP() {
		return changeNPWP;
	}

	public void setChangeNPWP(String changeNPWP) {
		this.changeNPWP = changeNPWP;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
	}

	public Long getMaritalStatusDataApproval() {
		return maritalStatusDataApproval;
	}

	public void setMaritalStatusDataApproval(Long maritalStatusDataApproval) {
		this.maritalStatusDataApproval = maritalStatusDataApproval;
	}
	

	public Long getNpwpStatusDataApproval() {
		return npwpStatusDataApproval;
	}

	public void setNpwpStatusDataApproval(Long npwpStatusDataApproval) {
		this.npwpStatusDataApproval = npwpStatusDataApproval;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getPhotoProfile() {
		return photoProfile;
	}

	public void setPhotoProfile(String photoProfile) {
		this.photoProfile = photoProfile;
	}
	
	

	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public String getLastEducation() {
		return lastEducation;
	}

	public String getChangeNIRCNO() {
		return changeNIRCNO;
	}

	public void setChangeNIRCNO(String changeNIRCNO) {
		this.changeNIRCNO = changeNIRCNO;
	}

	public Long getNircStatusDataApproval() {
		return nircStatusDataApproval;
	}

	public void setNircStatusDataApproval(Long nircStatusDataApproval) {
		this.nircStatusDataApproval = nircStatusDataApproval;
	}

	public void setLastEducation(String lastEducation) {
		this.lastEducation = lastEducation;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", gender=" + gender
				+ ", maritalStatus=" + maritalStatus + "]";
	}

	
	
 
	
	
	
	
}
