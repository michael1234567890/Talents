package com.phincon.talents.app.dto;

public class TotalCategoryDTO {
private Double total;
private String module;
private String category;
private Long company;
private Long employee;



public TotalCategoryDTO(Double total, String module, String category, Long company,
		Long employee) {
	super();
	this.total = total;
	this.module = module;
	this.category = category;
	this.company = company;
	this.employee = employee;
}


public Double getTotal() {
	return total;
}
public void setTotal(Double total) {
	this.total = total;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public Long getCompany() {
	return company;
}
public void setCompany(Long company) {
	this.company = company;
}
public Long getEmployee() {
	return employee;
}
public void setEmployee(Long employee) {
	this.employee = employee;
}

}
