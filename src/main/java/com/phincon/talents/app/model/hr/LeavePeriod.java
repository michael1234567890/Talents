package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;
import com.phincon.talents.app.model.Company;

@Entity
@Table(name="hr_leave_period")
public class LeavePeriod extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company leaveType;
	
	
	@Column(name="period_start_month")
	private int periodStartMonth;
	

	@Column(name="period_start_day")
	private int periodStartDay;


	public Company getLeaveType() {
		return leaveType;
	}


	public void setLeaveType(Company leaveType) {
		this.leaveType = leaveType;
	}


	public int getPeriodStartMonth() {
		return periodStartMonth;
	}


	public void setPeriodStartMonth(int periodStartMonth) {
		this.periodStartMonth = periodStartMonth;
	}


	public int getPeriodStartDay() {
		return periodStartDay;
	}


	public void setPeriodStartDay(int periodStartDay) {
		this.periodStartDay = periodStartDay;
	}


	@Override
	public String toString() {
		return "LeavePeriod [leaveType=" + leaveType + ", periodStartMonth="
				+ periodStartMonth + ", periodStartDay=" + periodStartDay + "]";
	}
	
	
	
	
	
	
	
	
	
}
