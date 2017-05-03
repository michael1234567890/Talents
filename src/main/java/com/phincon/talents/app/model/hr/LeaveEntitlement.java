package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;
@Entity
@Table(name="hr_leave_entitlement")
public class LeaveEntitlement extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "leave_id")
	private Leave leave;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entitlement_id")
	private Entitlement entitlement;
	
	@Column(name="length_days")
	private Double lengthDays;
	
	

	public Leave getLeave() {
		return leave;
	}

	public void setLeave(Leave leave) {
		this.leave = leave;
	}

	public Entitlement getEntitlement() {
		return entitlement;
	}

	public void setEntitlement(Entitlement entitlement) {
		this.entitlement = entitlement;
	}

	public Double getLengthDays() {
		return lengthDays;
	}

	public void setLengthDays(Double lengthDays) {
		this.lengthDays = lengthDays;
	}
	
	

}
