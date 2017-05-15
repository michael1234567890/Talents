package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_organization")
public class Organization extends AbstractEntity{
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name="name", length=100)
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	

	@Column(name="organization_level")
	private Integer organizationLevel;
	

	@Column(name="organization_sub_level")
	private Integer organizationSubLevel;
	


	@Column(name="organization_tracking", length=100)
	private String organizationTracking;
	
	@Column(name="organization_structure_name", length=100)
	private String organizationStructureName;
	
	@Column(name="organization_type", length=100)
	private String oragnizationType;
	
	@Column(name="remark", length=100)
	private String remark;

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getOrganizationLevel() {
		return organizationLevel;
	}

	public void setOrganizationLevel(Integer oragnizationLevel) {
		this.organizationLevel = oragnizationLevel;
	}

	public Integer getOrganizationSubLevel() {
		return organizationSubLevel;
	}

	public void setOrganizationSubLevel(Integer oragnizationSubLevel) {
		this.organizationSubLevel = oragnizationSubLevel;
	}

	public String getOrganizationTracking() {
		return organizationTracking;
	}

	public void setOrganizationTracking(String oragnizationTracking) {
		this.organizationTracking = oragnizationTracking;
	}

	public String getOragnizationType() {
		return oragnizationType;
	}

	public void setOragnizationType(String oragnizationType) {
		this.oragnizationType = oragnizationType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrganizationStructureName() {
		return organizationStructureName;
	}

	public void setOrganizationStructureName(String organizationStructureName) {
		this.organizationStructureName = organizationStructureName;
	}
	
	
	
	
	
	
	
	
}
