package com.phincon.talents.app.dto;

import java.util.List;

public class TMRequestTypeDTO {
	private String categoryName;
	private String categoryExtId;
	private List<String> type;

	public TMRequestTypeDTO() {

	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryExtId() {
		return categoryExtId;
	}

	public void setCategoryExtId(String categoryExtId) {
		this.categoryExtId = categoryExtId;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	
	

}
