package com.phincon.talents.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ElementGroupEmbed implements Serializable {
	@Column(name="payroll_element_header_id")
	private Long payrollElementHeader;
	
	@Column(name="element_group", length=50)
	private String elementGroup;
	
	@Column(name="element_type", length=50)
	private String elementType;

	
	public ElementGroupEmbed() {
    }
	
	public Long getPayrollElementHeader() {
		return payrollElementHeader;
	}

	public void setPayrollElementHeader(Long payrollElementHeader) {
		this.payrollElementHeader = payrollElementHeader;
	}

	public String getElementGroup() {
		return elementGroup;
	}

	public void setElementGroup(String elementGroup) {
		this.elementGroup = elementGroup;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	
	
}
