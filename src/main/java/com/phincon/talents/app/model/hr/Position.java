package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_position")
public class Position extends AbstractEntity{
	@Column(name = "company_id")
	private Long company;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "from_grade", length = 30)
	private String fromGrade;
	
	@Column(name = "to_grade", length = 30)
	private String toGrade;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "direct_position_name", length = 50)
	private String directPositionName;
	
	@Column(name = "flag_operational")
	private String flagOperational;
	
	@Column(name = "job_title_name", length = 50)
	private String jobTitleName;
	
	@Column(name = "letter_no", length = 50)
	private String letterNo;
	
	@Column(name = "max_market_salary")
	private Integer maxMarketSalary;
	
	@Column(name = "min_market_salary")
	private Integer minMarketSalary;
	
	@Column(name = "organization_name", length = 50)
	private String organizationName;
	
	@Column(name = "job_description", length = 255)
	private String jobDescription;
	
	@Column(name = "level", length = 50)
	private String level;
	
	@Column(name = "level_detail", length = 50)
	private String levelDetail;
	
	@Column(name = "type", length = 50)
	private String type;
	
	@Column(name = "used")
	private String used;
	
	@Column(name = "remark", length = 255)
	private String remark;
	
	@Column(name = "total_budget")
	private Integer totalBudget;
	
	@Column(name = "total_number")
	private Integer totalNumber;
	
	@Column(name = "organization_id")
	private Long organization;
	
	@Column(name = "organization_ext_id", length = 50)
	private String organizationExtId;

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
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

	public String getFromGrade() {
		return fromGrade;
	}

	public void setFromGrade(String fromGrade) {
		this.fromGrade = fromGrade;
	}

	public String getToGrade() {
		return toGrade;
	}

	public void setToGrade(String toGrade) {
		this.toGrade = toGrade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlagOperational() {
		return flagOperational;
	}

	public void setFlagOperational(String flagOperational) {
		this.flagOperational = flagOperational;
	}

	public String getJobTitleName() {
		return jobTitleName;
	}

	public void setJobTitleName(String jobTitleName) {
		this.jobTitleName = jobTitleName;
	}

	public String getLetterNo() {
		return letterNo;
	}

	public void setLetterNo(String letterNo) {
		this.letterNo = letterNo;
	}

	public Integer getMaxMarketSalary() {
		return maxMarketSalary;
	}

	public void setMaxMarketSalary(Integer maxMarketSalary) {
		this.maxMarketSalary = maxMarketSalary;
	}

	public Integer getMinMarketSalary() {
		return minMarketSalary;
	}

	public void setMinMarketSalary(Integer minMarketSalary) {
		this.minMarketSalary = minMarketSalary;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevelDetail() {
		return levelDetail;
	}

	public void setLevelDetail(String levelDetail) {
		this.levelDetail = levelDetail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(Integer totalBudget) {
		this.totalBudget = totalBudget;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getDirectPositionName() {
		return directPositionName;
	}

	public void setDirectPositionName(String directPositionName) {
		this.directPositionName = directPositionName;
	}

	public Long getOrganization() {
		return organization;
	}

	public void setOrganization(Long organization) {
		this.organization = organization;
	}

	public String getOrganizationExtId() {
		return organizationExtId;
	}

	public void setOrganizationExtId(String organizationExtId) {
		this.organizationExtId = organizationExtId;
	}
	
	
	
	
}
