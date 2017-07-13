package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_py_element_detail_group")
public class PayrollElementDetailGroup  {
	public final static String ELEMENT_TYPE_DEDUCTION = "Deduction";
	public final static String ELEMENT_TYPE_ALLOWANCE = "Allowance";

	@Id
	@Column(name="id")
	private Long id;

	
	@Column(name="payroll_element_header_id")
	private Long payrollElementHeader;
	

	@Column(name="total")
	private Double total;
	
	
	@Column(name="element_group", length=50)
	private String elementGroup;
	
	@Column(name="element_type", length=50)
	private String elementType;

	public Long getPayrollElementHeader() {
		return payrollElementHeader;
	}

	public void setPayrollElementHeader(Long payrollElementHeader) {
		this.payrollElementHeader = payrollElementHeader;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PayrollElementDetailGroup [payrollElementHeader="
				+ payrollElementHeader + ", total=" + total + ", elementGroup="
				+ elementGroup + ", elementType=" + elementType + "]";
	}
	
	
	
	
	


	
	
	
}
