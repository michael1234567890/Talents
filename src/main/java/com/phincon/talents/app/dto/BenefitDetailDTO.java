package com.phincon.talents.app.dto;



public class BenefitDetailDTO {
	private String type;
	private String data;
	private Double amount;
	private String origin;
	private String destination;
	private Long qty;
	private Double totalClaim; // isi kwitansi
	private Double totalSubmitedClaim; // total isi yang sudah di klaim ( balance used)
	private Double totalCurrentClaim; // total klaim yang di approve (min balance or total claim)
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public Double getTotalClaim() {
		return totalClaim;
	}

	public void setTotalClaim(Double totalClaim) {
		this.totalClaim = totalClaim;
	}

	public Double getTotalSubmitedClaim() {
		return totalSubmitedClaim;
	}

	public void setTotalSubmitedClaim(Double totalSubmitedClaim) {
		this.totalSubmitedClaim = totalSubmitedClaim;
	}

	public Double getTotalCurrentClaim() {
		return totalCurrentClaim;
	}

	public void setTotalCurrentClaim(Double totalCurrentClaim) {
		this.totalCurrentClaim = totalCurrentClaim;
	}

	@Override
	public String toString() {
		return "BenefitDetailDTO [type=" + type + ", data=" + data
				+ ", amount=" + amount + ", origin=" + origin
				+ ", destination=" + destination + ", qty=" + qty
				+ ", totalClaim=" + totalClaim + ", totalSubmitedClaim="
				+ totalSubmitedClaim + ", totalCurrentClaim="
				+ totalCurrentClaim + "]";
	}
	
	
	
	
	
	
	
	

}
