package com.phincon.talents.app.model.hr;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_payroll_element_header")
public class PayrollElementHeader extends AbstractEntity {


	@Column(name = "ptkp")
	private Double ptkp;
	
	@Column(name = "other_ptkp")
	private Double otherPtkp;
	
	@Column(name = "other_ptkp_yearly")
	private Double otherPtkpYearly;
	
	@Column(name = "pkp")
	private Double pkp;
	
	@Column(name = "name", length = 100)
	private String name;

//	@ManyToOne
//	@JoinColumn(name = "employee_id")
//	private Employee employee;
	
	@Column(name = "employee_id")
	private Long employee;

	@Column(name = "employee_ext_id", length = 100)
	private String employeeExtId;

//	@ManyToOne
//	@JoinColumn(name = "assignment_id")
//	private Assignment assignment;
	
	@Column(name = "assignment_id")
	private Long assignment;

	@Column(name = "assignment_ext_id", length = 100)
	private String assignmentExtId;

//	@ManyToOne
//	@JoinColumn(name = "employment_id")
//	private Employment employment;
	
	@Column(name = "employment_id")
	private Long employment;

	@Column(name = "employment_ext_id", length = 100)
	private String employmentExtId;

//	@ManyToOne
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;

//	@Temporal(TemporalType.DATE)
//	@Column(name = "period_date")
//	private Date periodDate;
	
	@Column(name = "period_date")
	private String periodDate;

	@Column(name = "gross_bjbpt")
	private Double grossBJBT;

	@Column(name = "is_severence_pay")
	private Boolean isSeverencePay;

	@Column(name = "maximal_bjbpt")
	private Double maximalBJBT;

	@Column(name = "irreg_gross_income_yearly")
	private Double irregGrossIncomeYearly;

	@Column(name = "irreg_net_income_yearly")
	private Double irregNetIncomeYearly;

	// jobTitle

	@Column(name = "net_income_yearly")
	private Double netIncomeYearly;

	@Column(name = "reg_gross_income")
	private Double regGrossIncome;

	@Column(name = "reg_gross_income_yearly")
	private Double regGrossIncomeYearly;

	@Column(name = "net_bjbt")
	private Double netBjbt;

	@Column(name = "prev_irreg_gross_income")
	private Double prevIrregGrossIncome;

	@Column(name = "prev_irreg_net_income")
	private Double prevIrregNetIncome;

	@Column(name = "reg_net_income_yearly")
	private Double regNetIncomeYearly;

	@Column(name = "prev_reg_gross_income")
	private Double prevRegGrossIncome;

	@Column(name = "prev_reg_net_income")
	private Double prevRegNetIncome;

	@Column(name = "reg_net_income")
	private Double regNetIncome;

	@Column(name = "remark", length = 255)
	private String remark;

	@Column(name = "sum_non_tax_income")
	private Double sumNonTaxIncome;

	@Column(name = "take_home_pay")
	private Double takeHomePay;

	@Column(name = "tax_allowance")
	private Double taxAllowance;

	@Column(name = "tax_gross")
	private Double taxGross;

	@Column(name = "tax_penalty")
	private Double taxPenalty;

	@Column(name = "tax_penalty_percentage")
	private Double taxPenaltyPercentage;

	@Column(name = "tax_status", length = 50)
	private String taxStatus;

	@Column(name = "tax_type", length = 50)
	private String taxType;

	@Column(name = "terminate_process")
	private Integer terminateProcess;

	@Column(name = "total_all_income_monthly")
	private Double totalAllIncomeMonthly;

	@Column(name = "total_allowance")
	private Double totalAllowance;

	@Column(name = "total_deduction")
	private Double totalDeduction;

	@Column(name = "total_income")
	private Double totalIncome;

	@Column(name = "val_result_type_tax")
	private Double valueResultTypeTax;

	@Column(name = "bank_account", length = 50)
	private String bankAccount;

	@Column(name = "bank_name", length = 50)
	private String bankName;

	@Column(name = "account_name", length = 50)
	private String accountName;
	
	@Transient
	private List<PayrollElementDetail> detailIn;
	
	
	@Transient
	private List<PayrollElementDetail> detailOut;
	


	public Long getEmployee() {
		return employee;
	}

	public Double getRegGrossIncomeYearly() {
		return regGrossIncomeYearly;
	}

	public void setRegGrossIncomeYearly(Double regGrossIncomeYearly) {
		this.regGrossIncomeYearly = regGrossIncomeYearly;
	}

	public Double getRegNetIncomeYearly() {
		return regNetIncomeYearly;
	}

	public void setRegNetIncomeYearly(Double regNetIncomeYearly) {
		this.regNetIncomeYearly = regNetIncomeYearly;
	}

	public Double getRegGrossIncome() {
		return regGrossIncome;
	}

	public void setRegGrossIncome(Double regGrossIncome) {
		this.regGrossIncome = regGrossIncome;
	}

	public void setEmployee(Long employee) {
		this.employee = employee;
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

	public String getPeriodDate() {
		return periodDate;
	}

	public void setPeriodDate(String periodDate) {
		this.periodDate = periodDate;
	}

	public Double getGrossBJBT() {
		return grossBJBT;
	}

	public void setGrossBJBT(Double grossBJBT) {
		this.grossBJBT = grossBJBT;
	}

	public Boolean getIsSeverencePay() {
		return isSeverencePay;
	}

	public void setIsSeverencePay(Boolean isSeverencePay) {
		this.isSeverencePay = isSeverencePay;
	}

	public Double getMaximalBJBT() {
		return maximalBJBT;
	}

	public void setMaximalBJBT(Double maximalBJBT) {
		this.maximalBJBT = maximalBJBT;
	}

	public Double getIrregGrossIncomeYearly() {
		return irregGrossIncomeYearly;
	}

	public void setIrregGrossIncomeYearly(Double irregGrossIncomeYearly) {
		this.irregGrossIncomeYearly = irregGrossIncomeYearly;
	}

	public Double getIrregNetIncomeYearly() {
		return irregNetIncomeYearly;
	}

	public void setIrregNetIncomeYearly(Double irregNetIncomeYearly) {
		this.irregNetIncomeYearly = irregNetIncomeYearly;
	}

	public Double getNetIncomeYearly() {
		return netIncomeYearly;
	}

	public void setNetIncomeYearly(Double netIncomeYearly) {
		this.netIncomeYearly = netIncomeYearly;
	}

	public Double getNetBjbt() {
		return netBjbt;
	}

	public void setNetBjbt(Double netBjbt) {
		this.netBjbt = netBjbt;
	}

	public Double getPrevIrregGrossIncome() {
		return prevIrregGrossIncome;
	}

	public void setPrevIrregGrossIncome(Double prevIrregGrossIncome) {
		this.prevIrregGrossIncome = prevIrregGrossIncome;
	}

	public Double getPrevIrregNetIncome() {
		return prevIrregNetIncome;
	}

	public void setPrevIrregNetIncome(Double prevIrregNetIncome) {
		this.prevIrregNetIncome = prevIrregNetIncome;
	}

	public Double getPrevRegGrossIncome() {
		return prevRegGrossIncome;
	}

	public void setPrevRegGrossIncome(Double prevRegGrossIncome) {
		this.prevRegGrossIncome = prevRegGrossIncome;
	}

	public Double getPrevRegNetIncome() {
		return prevRegNetIncome;
	}

	public void setPrevRegNetIncome(Double prevRegNetIncome) {
		this.prevRegNetIncome = prevRegNetIncome;
	}

	public Double getRegNetIncome() {
		return regNetIncome;
	}

	public void setRegNetIncome(Double regNetIncome) {
		this.regNetIncome = regNetIncome;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getSumNonTaxIncome() {
		return sumNonTaxIncome;
	}

	public void setSumNonTaxIncome(Double sumNonTaxIncome) {
		this.sumNonTaxIncome = sumNonTaxIncome;
	}

	public Double getTakeHomePay() {
		return takeHomePay;
	}

	public void setTakeHomePay(Double takeHomePay) {
		this.takeHomePay = takeHomePay;
	}

	public Double getTaxAllowance() {
		return taxAllowance;
	}

	public void setTaxAllowance(Double taxAllowance) {
		this.taxAllowance = taxAllowance;
	}

	public Double getTaxGross() {
		return taxGross;
	}

	public void setTaxGross(Double taxGross) {
		this.taxGross = taxGross;
	}

	public Double getTaxPenalty() {
		return taxPenalty;
	}

	public void setTaxPenalty(Double taxPenalty) {
		this.taxPenalty = taxPenalty;
	}

	public Double getTaxPenaltyPercentage() {
		return taxPenaltyPercentage;
	}

	public void setTaxPenaltyPercentage(Double taxPenaltyPercentage) {
		this.taxPenaltyPercentage = taxPenaltyPercentage;
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

	public Integer getTerminateProcess() {
		return terminateProcess;
	}

	public void setTerminateProcess(Integer terminateProcess) {
		this.terminateProcess = terminateProcess;
	}

	public Double getTotalAllIncomeMonthly() {
		return totalAllIncomeMonthly;
	}

	public void setTotalAllIncomeMonthly(Double totalAllIncomeMonthly) {
		this.totalAllIncomeMonthly = totalAllIncomeMonthly;
	}

	public Double getTotalAllowance() {
		return totalAllowance;
	}

	public void setTotalAllowance(Double totalAllowance) {
		this.totalAllowance = totalAllowance;
	}

	public Double getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(Double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Double getValueResultTypeTax() {
		return valueResultTypeTax;
	}

	public void setValueResultTypeTax(Double valueResultTypeTax) {
		this.valueResultTypeTax = valueResultTypeTax;
	}

	public String getEmployeeExtId() {
		return employeeExtId;
	}

	public void setEmployeeExtId(String employeeExtId) {
		this.employeeExtId = employeeExtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmploymentExtId() {
		return employmentExtId;
	}

	public void setEmploymentExtId(String employmentExtId) {
		this.employmentExtId = employmentExtId;
	}

	public Long getAssignment() {
		return assignment;
	}

	public void setAssignment(Long assignment) {
		this.assignment = assignment;
	}

	public String getAssignmentExtId() {
		return assignmentExtId;
	}

	public void setAssignmentExtId(String assignmentExtId) {
		this.assignmentExtId = assignmentExtId;
	}
	
	

	public List<PayrollElementDetail> getDetailIn() {
		return detailIn;
	}

	public void setDetailIn(List<PayrollElementDetail> detailIn) {
		this.detailIn = detailIn;
	}

	public List<PayrollElementDetail> getDetailOut() {
		return detailOut;
	}

	public void setDetailOut(List<PayrollElementDetail> detailOut) {
		this.detailOut = detailOut;
	}
	
	

	public Double getPtkp() {
		return ptkp;
	}

	public void setPtkp(Double ptkp) {
		this.ptkp = ptkp;
	}

	public Double getOtherPtkp() {
		return otherPtkp;
	}

	public void setOtherPtkp(Double otherPtkp) {
		this.otherPtkp = otherPtkp;
	}

	public Double getOtherPtkpYearly() {
		return otherPtkpYearly;
	}

	public void setOtherPtkpYearly(Double otherPtkpYearly) {
		this.otherPtkpYearly = otherPtkpYearly;
	}

	public Double getPkp() {
		return pkp;
	}

	public void setPkp(Double pkp) {
		this.pkp = pkp;
	}

	@Override
	public String toString() {
		return "PayrollElementHeader [employee=" + employee + ", employment="
				+ employment + ", company=" + company + ", periodDate="
				+ periodDate + ", grossBJBT=" + grossBJBT + ", isSeverencePay="
				+ isSeverencePay + ", maximalBJBT=" + maximalBJBT
				+ ", irregGrossIncomeYearly=" + irregGrossIncomeYearly
				+ ", irregNetIncomeYearly=" + irregNetIncomeYearly
				+ ", netIncomeYearly=" + netIncomeYearly + ", netBjbt="
				+ netBjbt + ", prevIrregGrossIncome=" + prevIrregGrossIncome
				+ ", prevIrregNetIncome=" + prevIrregNetIncome
				+ ", prevRegGrossIncome=" + prevRegGrossIncome
				+ ", prevRegNetIncome=" + prevRegNetIncome + ", regNetIncome="
				+ regNetIncome + ", remark=" + remark + ", sumNonTaxIncome="
				+ sumNonTaxIncome + ", takeHomePay=" + takeHomePay
				+ ", taxAllowance=" + taxAllowance + ", taxGross=" + taxGross
				+ ", taxPenalty=" + taxPenalty + ", taxPenaltyPercentage="
				+ taxPenaltyPercentage + ", taxStatus=" + taxStatus
				+ ", taxType=" + taxType + ", terminateProcess="
				+ terminateProcess + ", totalAllIncomeMonthly="
				+ totalAllIncomeMonthly + ", totalAllowance=" + totalAllowance
				+ ", totalDeduction=" + totalDeduction + ", totalIncome="
				+ totalIncome + ", valueResultTypeTax=" + valueResultTypeTax
				+ "]";
	}

}
