package com.phincon.talents.app.dto;

public class TotalCategoryDTO {
	private Double total;

	private String module;
	private String category;
	private String type;
	private Long company;
	private Long employee;
	private Long employment;
	
	private Long totalOvertimeIn;
	private Long totalOvertimeOut;
	private String month;

	public TotalCategoryDTO(Double total, String module, String category,
			Long company, Long employee) {
		super();
		this.total = total;
		this.module = module;
		this.category = category;
		this.company = company;
		this.employee = employee;
	}
	
	public TotalCategoryDTO(Long totalOvertimeIn, Long totalOvertimeOut, String month,String module, String type,
			Long company, Long employment) {
		super();
		this.totalOvertimeIn = totalOvertimeIn;
		this.totalOvertimeOut = totalOvertimeOut;
		this.type = type;
		this.module = module;
		this.month = month;
		this.company = company;
		this.employment = employment;
	}

	
	
	public Long getEmployment() {
		return employment;
	}

	public void setEmployment(Long employment) {
		this.employment = employment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Long getTotalOvertimeIn() {
		return totalOvertimeIn;
	}

	public void setTotalOvertimeIn(Long totalOvertimeIn) {
		this.totalOvertimeIn = totalOvertimeIn;
	}

	public Long getTotalOvertimeOut() {
		return totalOvertimeOut;
	}

	public void setTotalOvertimeOut(Long totalOvertimeOut) {
		this.totalOvertimeOut = totalOvertimeOut;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	

}
