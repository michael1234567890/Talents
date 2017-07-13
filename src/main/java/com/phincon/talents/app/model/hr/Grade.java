package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_grade")
public class Grade extends AbstractEntity{
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@Column(name = "company_id")
	private Long company;
//	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="grade_nominal")
	private Integer gradeNominal;
	
	
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

	public Integer getGradeNominal() {
		return gradeNominal;
	}

	public void setGradeNominal(Integer gradeNominal) {
		this.gradeNominal = gradeNominal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

	
	
	
	
	
}
