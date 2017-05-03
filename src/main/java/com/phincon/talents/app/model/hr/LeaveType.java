package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;
import com.phincon.talents.app.model.Company;

@Entity
@Table(name="hr_leave_type")
public class LeaveType extends AbstractEntity{

	@Column(name = "name", length=30)
	private String name;

	private Boolean deleted = false;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;

	@Column(name = "company_id")
	private Long company;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}
	
	

}
