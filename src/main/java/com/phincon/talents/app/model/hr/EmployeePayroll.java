package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_payroll_employee_payroll")
public class EmployeePayroll extends AbstractEntity {
	
	@Column(name = "account_name", length = 100)
	private String accountName;
	
	@Column(name = "attendance_group_code", length = 100)
	private String attendanceGroupCode;
	
	@Column(name = "bank_account", length = 50)
	private String bankAccount;
	
	@Column(name = "bank_branch", length = 100)
	private String bankBranch;
	
	@Column(name = "bank_location", length = 100)
	private String bankLocation;
	
	@Column(name = "bank_name", length = 50)
	private String bankName;
	
	@Column(name = "bank_transfer_type", length = 100)
	private String bankTransferType;
	
	@Column(name = "company_name", length = 100)
	private String companyName;
	
	@Column(name = "employee_no", length = 100)
	private String employeeNo;
	
	@Column(name = "employment_id")
	private Long employment;
	
	@Column(name = "flag_cop")
	private Boolean flagCOP;
	
	@Column(name = "flag_incentive")
	private Boolean flagIncentive;
	
	@Column(name = "flag_kost")
	private Boolean flagKost;
	
	@Column(name = "flag_lembur")
	private Boolean flagLembur;
	
	@Column(name = "flag_operasional")
	private Boolean flagOperasional;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	
	@Column(name = "is_processed_previous")
	private Boolean isProcessedPrevious;
	
	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name = "owner_id", length = 100)
	private String ownerId;
	
	@Column(name = "pay_type", length = 50)
	private String payType;
	
	@Column(name = "payment_method", length = 50)
	private String paymentMethod;
	
	@Column(name = "previous_bjbt", length = 30)
	private Double previousBJBT;
	
	@Column(name = "previous_net_income", length = 30)
	private Double previousNetIncome;
	
	@Column(name = "previous_other_ptkp", length = 30)
	private Double previousOtherPTKP;
	
	@Column(name = "previous_ptkp", length = 30)
	private Double previousPTKP;
	
	@Column(name = "previous_period", length = 30)
	private Integer previousPeriod;
	
	@Column(name = "previous_tax_penalty", length = 30)
	private Double previousTaxPenalty;
	
	@Column(name = "previous_tax", length = 30)
	private Double previousTax;
	
	@Column(name = "tax_file_no_name", length = 100)
	private String taxFileNoName;
	
	@Column(name = "tax_file_no", length = 100)
	private String taxFileNo;
	
	@Column(name = "tax_location_name", length = 100)
	private String taxLocationName;
	
	@Column(name = "tax_status", length = 50)
	private String taxStatus;
	
	@Column(name = "tax_type", length = 50)
	private String taxType;
	
	@Column(name = "is_reguler")
	private Boolean isReguler;
	
	@Column(name = "need_sync")
	private Boolean needSync;
	
	@Column(name = "company_id")
	private Long company;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAttendanceGroupCode() {
		return attendanceGroupCode;
	}

	public void setAttendanceGroupCode(String attendanceGroupCode) {
		this.attendanceGroupCode = attendanceGroupCode;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getBankLocation() {
		return bankLocation;
	}

	public void setBankLocation(String bankLocation) {
		this.bankLocation = bankLocation;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankTransferType() {
		return bankTransferType;
	}

	public void setBankTransferType(String bankTransferType) {
		this.bankTransferType = bankTransferType;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Boolean getFlagCOP() {
		return flagCOP;
	}

	public void setFlagCOP(Boolean flagCOP) {
		this.flagCOP = flagCOP;
	}

	public Boolean getFlagIncentive() {
		return flagIncentive;
	}

	public void setFlagIncentive(Boolean flagIncentive) {
		this.flagIncentive = flagIncentive;
	}

	public Boolean getFlagKost() {
		return flagKost;
	}

	public void setFlagKost(Boolean flagKost) {
		this.flagKost = flagKost;
	}

	public Boolean getFlagLembur() {
		return flagLembur;
	}

	public void setFlagLembur(Boolean flagLembur) {
		this.flagLembur = flagLembur;
	}

	public Boolean getFlagOperasional() {
		return flagOperasional;
	}

	public void setFlagOperasional(Boolean flagOperasional) {
		this.flagOperasional = flagOperasional;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsProcessedPrevious() {
		return isProcessedPrevious;
	}

	public void setIsProcessedPrevious(Boolean isProcessedPrevious) {
		this.isProcessedPrevious = isProcessedPrevious;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Double getPreviousBJBT() {
		return previousBJBT;
	}

	public void setPreviousBJBT(Double previousBJBT) {
		this.previousBJBT = previousBJBT;
	}

	public Double getPreviousNetIncome() {
		return previousNetIncome;
	}

	public void setPreviousNetIncome(Double previousNetIncome) {
		this.previousNetIncome = previousNetIncome;
	}

	public Double getPreviousOtherPTKP() {
		return previousOtherPTKP;
	}

	public void setPreviousOtherPTKP(Double previousOtherPTKP) {
		this.previousOtherPTKP = previousOtherPTKP;
	}

	public Double getPreviousPTKP() {
		return previousPTKP;
	}

	public void setPreviousPTKP(Double previousPTKP) {
		this.previousPTKP = previousPTKP;
	}

	public Integer getPreviousPeriod() {
		return previousPeriod;
	}

	public void setPreviousPeriod(Integer previousPeriod) {
		this.previousPeriod = previousPeriod;
	}

	public Double getPreviousTaxPenalty() {
		return previousTaxPenalty;
	}

	public void setPreviousTaxPenalty(Double previousTaxPenalty) {
		this.previousTaxPenalty = previousTaxPenalty;
	}

	public Double getPreviousTax() {
		return previousTax;
	}

	public void setPreviousTax(Double previousTax) {
		this.previousTax = previousTax;
	}

	public String getTaxFileNoName() {
		return taxFileNoName;
	}

	public void setTaxFileNoName(String taxFileNoName) {
		this.taxFileNoName = taxFileNoName;
	}

	public String getTaxFileNo() {
		return taxFileNo;
	}

	public void setTaxFileNo(String taxFileNo) {
		this.taxFileNo = taxFileNo;
	}

	public String getTaxLocationName() {
		return taxLocationName;
	}

	public void setTaxLocationName(String taxLocationName) {
		this.taxLocationName = taxLocationName;
	}

	public String getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(String taxStatus) {
		this.taxStatus = taxStatus;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public Boolean getIsReguler() {
		return isReguler;
	}

	public void setIsReguler(Boolean isReguler) {
		this.isReguler = isReguler;
	}

	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
	}

	public Long getEmployment() {
		return employment;
	}

	public void setEmployment(Long employment) {
		this.employment = employment;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}
	
	
	
}
