package com.phincon.talents.app.model.hr;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_payroll_element_header_yearly")
public class PayrollElementHeaderYearly extends AbstractEntity {

	
	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Column(name = "employee_ext_id", length = 100)
	private String employeeExtId;

//	@ManyToOne
//	@JoinColumn(name = "employment_id")
//	private Employment employment;
	
	@Column(name = "employment_id")
	private Long employment;

	@Column(name = "employment_ext_id", length = 100)
	private String employmentExtId;

	
	@Column(name = "company_id")
	private Long company;

	@Column(name = "current_year")
	private String currentYear;

	@Column(name = "payroll_period")
	private String payrollPeriod;
	

	@Column(name = "pension_sallary_jht")
	private Double pensionSallaryJHT;

	@Column(name = "iuran_pension")
	private Double iuranPension; 
	

	@Column(name = "total_deduction")
	private Double totalDeduction;

	@Column(name = "total_allowance_net")
	private Double totalAllowanceNet;
	

	@Column(name = "total_allowance_net_yearly")
	private Double totalAllowanceNetYearly;
	

	@Column(name = "pkp_yearly")
	private Double pkpYearly;

	@Column(name = "tunjangan_lainnya")
	private Double tunjanganLainnya;

	@Column(name = "honorarium")
	private Double honorarium;

	@Column(name = "premi_assurance")
	private Double premiAssurance;
	

	@Column(name = "pph21_dipotong_dilunasi")
	private Double pph21DipotongDilunasi;
	

	@Column(name = "pph21_terhutang")
	private Double pph21Terhutang;
	


	@Column(name = "jumlah_ph_bruto")
	private Double jumlahPhBruto;
	
	@Column(name = "tax_paid")
	private Double taxPaid;

	@Column(name = "tax_status", length=50)
	private String taxStatus;
	
	@Column(name = "tax_type", length=50)
	private String taxType;
	
	@Transient
	private Employee employeeTransient;
	
	@Transient
	private List<PayrollElementDetailYearly> detailIn;
	
	
	@Transient
	private List<PayrollElementDetailYearly> detailOut;
	

	@Column(name = "total_allowance")
	private Double totalAllowance;
	
	public Double getJumlahPhBruto() {
		return jumlahPhBruto;
	}

	public void setJumlahPhBruto(Double jumlahPhBruto) {
		this.jumlahPhBruto = jumlahPhBruto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmployeeExtId() {
		return employeeExtId;
	}

	public void setEmployeeExtId(String employeeExtId) {
		this.employeeExtId = employeeExtId;
	}

	public Long getEmployment() {
		return employment;
	}

	public void setEmployment(Long employment) {
		this.employment = employment;
	}

	public String getEmploymentExtId() {
		return employmentExtId;
	}

	public void setEmploymentExtId(String employmentExtId) {
		this.employmentExtId = employmentExtId;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public String getPayrollPeriod() {
		return payrollPeriod;
	}

	public void setPayrollPeriod(String payrollPeriod) {
		this.payrollPeriod = payrollPeriod;
	}

	public Double getPensionSallaryJHT() {
		return pensionSallaryJHT;
	}

	public void setPensionSallaryJHT(Double pensionSallaryJHT) {
		this.pensionSallaryJHT = pensionSallaryJHT;
	}

	public Double getIuranPension() {
		return iuranPension;
	}

	public void setIuranPension(Double iuranPension) {
		this.iuranPension = iuranPension;
	}

	public Double getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(Double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public Double getTotalAllowanceNet() {
		return totalAllowanceNet;
	}

	public void setTotalAllowanceNet(Double totalAllowanceNet) {
		this.totalAllowanceNet = totalAllowanceNet;
	}

	public Double getTotalAllowanceNetYearly() {
		return totalAllowanceNetYearly;
	}

	public void setTotalAllowanceNetYearly(Double totalAllowanceNetYearly) {
		this.totalAllowanceNetYearly = totalAllowanceNetYearly;
	}

	public Double getPkpYearly() {
		return pkpYearly;
	}

	public void setPkpYearly(Double pkpYearly) {
		this.pkpYearly = pkpYearly;
	}

	public Double getTunjanganLainnya() {
		return tunjanganLainnya;
	}

	public void setTunjanganLainnya(Double tunjanganLainnya) {
		this.tunjanganLainnya = tunjanganLainnya;
	}

	public Double getHonorarium() {
		return honorarium;
	}

	public void setHonorarium(Double honorarium) {
		this.honorarium = honorarium;
	}

	public Double getPremiAssurance() {
		return premiAssurance;
	}

	public void setPremiAssurance(Double premiAssurance) {
		this.premiAssurance = premiAssurance;
	}

	public Double getPph21DipotongDilunasi() {
		return pph21DipotongDilunasi;
	}

	public void setPph21DipotongDilunasi(Double pph21DipotongDilunasi) {
		this.pph21DipotongDilunasi = pph21DipotongDilunasi;
	}

	public Double getPph21Terhutang() {
		return pph21Terhutang;
	}

	public void setPph21Terhutang(Double pph21Terhutang) {
		this.pph21Terhutang = pph21Terhutang;
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

	public Double getTaxPaid() {
		return taxPaid;
	}

	public void setTaxPaid(Double taxPaid) {
		this.taxPaid = taxPaid;
	}

	public Employee getEmployeeTransient() {
		return employeeTransient;
	}

	public void setEmployeeTransient(Employee employeeTransient) {
		this.employeeTransient = employeeTransient;
	}

	public List<PayrollElementDetailYearly> getDetailIn() {
		return detailIn;
	}

	public void setDetailIn(List<PayrollElementDetailYearly> detailIn) {
		this.detailIn = detailIn;
	}

	public List<PayrollElementDetailYearly> getDetailOut() {
		return detailOut;
	}

	public void setDetailOut(List<PayrollElementDetailYearly> detailOut) {
		this.detailOut = detailOut;
	}

	public Double getTotalAllowance() {
		return totalAllowance;
	}

	public void setTotalAllowance(Double totalAllowance) {
		this.totalAllowance = totalAllowance;
	}

	
	

}
