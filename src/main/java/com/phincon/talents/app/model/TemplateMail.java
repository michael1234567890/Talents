package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "template_mail")
public class TemplateMail {
	
	public static final String APPBENEFIT = "APPBENEFIT";
	
	public static final String RESULTAPPBENEFIT = "RESULTAPPBENEFIT";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 1000)
	private String content;
	
	@Column(length = 255)
	private String subject;

	@Column(length = 25)
	private String code;

	@Column(name = "company_id")
	private Long company;

	public TemplateMail() {
	}

	public TemplateMail(String id) {
		this.id = Long.valueOf(id);
	}

	public TemplateMail(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	

}
