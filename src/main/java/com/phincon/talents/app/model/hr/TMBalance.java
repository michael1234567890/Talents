package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_tm_balance")
public class TMBalance extends AbstractEntity {

	public final static String MEDICAL = "Medical";
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date_final")
	private Date endDateFinal;
	

	@Column(name = "module", length=100)
	private String module;
	
	@Column(name = "category_type", length=100)
	private String categoryType;
	
	@Column(name = "type", length=100)
	private String type;
	
	@Column(name = "balance_type", length=100)
	private String balanceType;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_claim_date")
	private Date lastClaimDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_claim_date_before")
	private Date lastClaimDateBefore;
	
	
	@Column(name = "balance_limit")
	private Double balanceLimit;
	
	@Column(name = "adjustment_medical")
	private Double adjustmentMedical;
	
	@Column(name = "balance_valid")
	private Double balanceValid;
	
	@Column(name = "balance_used")
	private Double balanceUsed;
	
	@Column(name = "balance_end")
	private Double balanceEnd;
	
	@Column(name = "period", length=10)
	private String period;
	
	
	@Column(name = "remark", length=255)
	private String remark;
	

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "employment_id")
//	private Employment employment;

	@Column(name = "employment_id")
	private Long employment;
	
	
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;

	
	@Column(name = "category_type_id", length=100)
	private String categoryTypeId;
	
	@Column(name = "employment_ext_id")
	private String employmentExtId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "marital_status")
	private String maritalStatus;
	
	@Column(name = "need_sync")
	private Boolean needSync;
	
	@Column(name = "last_claim_date_family_id")
	private Long lastClaimDateFamilyId;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDateFinal() {
		return endDateFinal;
	}

	public void setEndDateFinal(Date endDateFinal) {
		this.endDateFinal = endDateFinal;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBalanceLimit() {
		return balanceLimit;
	}

	public void setBalanceLimit(Double balanceLimit) {
		this.balanceLimit = balanceLimit;
	}

	public Double getAdjustmentMedical() {
		return adjustmentMedical;
	}

	public void setAdjustmentMedical(Double adjustmentMedical) {
		this.adjustmentMedical = adjustmentMedical;
	}

	public Double getBalanceValid() {
		return balanceValid;
	}

	public void setBalanceValid(Double balanceValid) {
		this.balanceValid = balanceValid;
	}

	public Double getBalanceUsed() {
		return balanceUsed;
	}

	public void setBalanceUsed(Double balanceUsed) {
		this.balanceUsed = balanceUsed;
	}

	public Double getBalanceEnd() {
		return balanceEnd;
	}

	public void setBalanceEnd(Double balanceEnd) {
		this.balanceEnd = balanceEnd;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getCategoryTypeId() {
		return categoryTypeId;
	}

	public void setCategoryTypeId(String categoryTypeId) {
		this.categoryTypeId = categoryTypeId;
	}

	
	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	
	

	public Date getLastClaimDate() {
		return lastClaimDate;
	}

	public void setLastClaimDate(Date lastClaimDate) {
		this.lastClaimDate = lastClaimDate;
	}
	
	

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	
	public Boolean getNeedSync() {
		return needSync;
	}

	public void setNeedSync(Boolean needSync) {
		this.needSync = needSync;
	}
	
	

	public Date getLastClaimDateBefore() {
		return lastClaimDateBefore;
	}

	public void setLastClaimDateBefore(Date lastClaimDateBefore) {
		this.lastClaimDateBefore = lastClaimDateBefore;
	}

	public Long getLastClaimDateFamilyId() {
		return lastClaimDateFamilyId;
	}

	public void setLastClaimDateFamilyId(Long lastClaimDateFamilyId) {
		this.lastClaimDateFamilyId = lastClaimDateFamilyId;
	}

	@Override
	public String toString() {
		return "TMBalance [startDate=" + startDate + ", endDate=" + endDate
				+ ", endDateFinal=" + endDateFinal + ", module=" + module
				+ ", categoryType=" + categoryType + ", type=" + type
				+ ", balanceLimit=" + balanceLimit + ", adjustmentMedical="
				+ adjustmentMedical + ", balanceValid=" + balanceValid
				+ ", balanceUsed=" + balanceUsed + ", balanceEnd=" + balanceEnd
				+ ", period=" + period + ", remark=" + remark + ", employment="
				+ employment + ", company=" + company + "]";
	}

	
	
	
	
	

}
