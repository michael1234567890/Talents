package com.phincon.talents.app.dto;

import java.util.List;
import java.util.Map;

public class DataApprovalDTO {
	private String task;
	private String module;
	private String data;
	private String objectName;
	private String description;
	private Long requestForFamily;
	private Long idRef;
	private List<Map<String, Object>> attachments;
	
	

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getIdRef() {
		return idRef;
	}

	public void setIdRef(Long idRef) {
		this.idRef = idRef;
	}

	public List<Map<String, Object>> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Map<String, Object>> attachments) {
		this.attachments = attachments;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Long getRequestForFamily() {
		return requestForFamily;
	}

	public void setRequestForFamily(Long requestForFamily) {
		this.requestForFamily = requestForFamily;
	}

	
	
	

}
