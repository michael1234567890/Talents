package com.phincon.talents.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sending_email")
public class SendingEmail {
	 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column(length = 255)
	private String subject;

	@Column(length = 500)
	private String dataContent;

	@Column(length = 25)
	private String codeTemplate;

	@Column(length = 255)
	private String emailTo;
	
	@Column(length = 100)
	private String listEmpId;

	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();

	public SendingEmail() {
	}

	public SendingEmail(String id) {
		this.id = Long.valueOf(id);
	}

	public SendingEmail(Long id) {
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

	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}

	public String getCodeTemplate() {
		return codeTemplate;
	}

	public void setCodeTemplate(String codeTemplate) {
		this.codeTemplate = codeTemplate;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getListEmpId() {
		return listEmpId;
	}

	public void setListEmpId(String listEmpId) {
		this.listEmpId = listEmpId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	



}
