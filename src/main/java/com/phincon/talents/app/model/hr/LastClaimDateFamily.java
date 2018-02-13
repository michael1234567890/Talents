package com.phincon.talents.app.model.hr;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "last_claim_date_family")
public class LastClaimDateFamily extends AbstractEntity {
	
	@Column(name = "balance_id")
	private Long balance;
	
	@Column(name = "family_id")
	private Long family;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "last_claim_date")
	private Date lastClaimDate;
	
	@Column(name = "employment_id")
	private Long employment;

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getFamily() {
		return family;
	}

	public void setFamily(Long family) {
		this.family = family;
	}

	public Date getLastClaimDate() {
		return lastClaimDate;
	}

	public void setLastClaimDate(Date lastClaimDate) {
		this.lastClaimDate = lastClaimDate;
	}

	public Long getEmployment() {
		return employment;
	}

	public void setEmployment(Long employment) {
		this.employment = employment;
	}
	
	
}
