package com.phincon.talents.app.dto;

public class PayrollRequestDTO {
	public final static String PY_TYPE_MONTH = "monthly";
	public final static String PY_TYPE_LATEST = "latest";
	private String payrollType;
	private String month;
	private String year;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPayrollType() {
		return payrollType;
	}

	public void setPayrollType(String payrollType) {
		this.payrollType = payrollType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "PayrollRequestDTO [payrollType=" + payrollType + ", month="
				+ month + ", year=" + year + "]";
	}
	
	
	
	

}
