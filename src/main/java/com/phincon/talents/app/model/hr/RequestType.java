package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_request_type")
public class RequestType extends AbstractEntity {

	@Column(name = "module", length = 100)
	private String module;

	@Column(name = "category_type", length = 100)
	private String categoryType;

	@Column(name = "category_type_ext_id", length = 100)
	private String categoryTypeExtId;

	@Column(name = "type", length = 100)
	private String type;

	@Column(name = "remark", length = 255)
	private String remark;

	@Column(name = "grade")
	private Integer grade;

	@Column(name = "position", length = 2000)
	private String position;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;

	
	@Column(name = "company_id")
	private Long company;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "request_cat_type_id")
//	private RequestCategoryType requestCategoryType;

	@Column(name = "request_cat_type_id")
	private Long requestCategoryType;
	
	@Column(name = "active")
	private Boolean active;
	
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

	public String getCategoryTypeExtId() {
		return categoryTypeExtId;
	}

	public void setCategoryTypeExtId(String categoryTypeExtId) {
		this.categoryTypeExtId = categoryTypeExtId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public Long getRequestCategoryType() {
		return requestCategoryType;
	}

	public void setRequestCategoryType(Long requestCategoryType) {
		this.requestCategoryType = requestCategoryType;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	
	

}
