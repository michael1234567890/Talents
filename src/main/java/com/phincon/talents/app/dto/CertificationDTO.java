package com.phincon.talents.app.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class CertificationDTO {
	
	private String name;
	private Date date;
	private String description;
	private Date expired;
	private String no;
	private String type;
	private String principle;
	private String year;
	private String full;
	private List<Map<String, Object>> attachments;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrinciple() {
		return principle;
	}
	public void setPrinciple(String principle) {
		this.principle = principle;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFull() {
		return full;
	}
	public void setFull(String full) {
		this.full = full;
	}
	public List<Map<String, Object>> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Map<String, Object>> attachments) {
		this.attachments = attachments;
	} 
	
	
	
	
	

}
