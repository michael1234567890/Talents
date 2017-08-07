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
	
	@Column(name = "member_type_one", length = 30)
	private String memberTypeOne;
	
	@Column(name = "member_type_two", length = 30)
	private String memberTypeTwo;
	
	@Column(name = "member_type_three", length = 30)
	private String memberTypeThree;
	
	@Column(name = "member_type_four", length = 30)
	private String memberTypeFour;
	
	@Column(name = "active")
	private Boolean active = true;

	@Column(name = "have_type")
	private Boolean haveType = false;
	

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

	public String getMemberTypeOne() {
		return memberTypeOne;
	}

	public void setMemberTypeOne(String memberTypeOne) {
		this.memberTypeOne = memberTypeOne;
	}

	public String getMemberTypeTwo() {
		return memberTypeTwo;
	}

	public void setMemberTypeTwo(String memberTypeTwo) {
		this.memberTypeTwo = memberTypeTwo;
	}

	public String getMemberTypeThree() {
		return memberTypeThree;
	}

	public void setMemberTypeThree(String memberTypeThree) {
		this.memberTypeThree = memberTypeThree;
	}

	public String getMemberTypeFour() {
		return memberTypeFour;
	}

	public void setMemberTypeFour(String memberTypeFour) {
		this.memberTypeFour = memberTypeFour;
	}

	public Boolean getHaveType() {
		return haveType;
	}

	public void setHaveType(Boolean haveType) {
		this.haveType = haveType;
	}
	
	
	
	
	
	

}
