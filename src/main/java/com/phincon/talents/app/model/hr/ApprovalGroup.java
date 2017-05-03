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
@Table(name = "wf_approval_group")
public class ApprovalGroup extends AbstractEntity {
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;

	@Column(name = "code", length = 30)
	private String code;
	
	@Column(name = "name", length = 30)
	private String name;

	@Column(name = "logic", length = 30)
	private String logic;

	@Column(name = "member", length = 30)
	private String member;
	
	@Column(name = "active")
	private Boolean active = true;

	

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

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	

}
