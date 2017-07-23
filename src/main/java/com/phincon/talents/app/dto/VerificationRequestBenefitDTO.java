package com.phincon.talents.app.dto;

public class VerificationRequestBenefitDTO {
	private Double totalSubmitedClaim;
	private Double totalLastClaim;
	private Double totalCurrentClaim;
	public Double getTotalSubmitedClaim() {
		return totalSubmitedClaim;
	}
	public void setTotalSubmitedClaim(Double totalSubmitedClaim) {
		this.totalSubmitedClaim = totalSubmitedClaim;
	}
	public Double getTotalLastClaim() {
		return totalLastClaim;
	}
	public void setTotalLastClaim(Double totalLastClaim) {
		this.totalLastClaim = totalLastClaim;
	}
	public Double getTotalCurrentClaim() {
		return totalCurrentClaim;
	}
	public void setTotalCurrentClaim(Double totalCurrentClaim) {
		this.totalCurrentClaim = totalCurrentClaim;
	}
	
	
	
}
