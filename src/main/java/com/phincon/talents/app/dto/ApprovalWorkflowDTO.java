package com.phincon.talents.app.dto;

public class ApprovalWorkflowDTO {
	
	private Long id;
	private String status;
	private String reasonReject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReasonReject() {
		return reasonReject;
	}

	public void setReasonReject(String reasonReject) {
		this.reasonReject = reasonReject;
	}
	
	

}
