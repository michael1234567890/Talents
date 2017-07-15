package com.phincon.talents.app.model.hr;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.phincon.talents.app.model.AbstractEntity;

@Entity
@Table(name = "hr_request_category_type")
public class RequestCategoryType extends AbstractEntity {

	@Column(name = "module", length = 100)
	private String module;

	@Column(name = "category_type", length = 100)
	private String categoryType;

	@Column(name = "category_type_ext_id", length = 100)
	private String categoryTypeExtId;


//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "company_id")
//	private Company company;

	
	@Column(name = "icon", length = 100)
	private String icon;
	
	@Column(name = "company_id")
	private Long company;
	
	@Column(name = "is_direct_type")
	private boolean isDirectType = false;
	
	
	@Column(name = "label", length = 100)
	private String label;

	@Column(name = "type", length = 100)
	private String type;

	
	@Transient
	private List<RequestType> listRequestType;
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryTypeExtId() {
		return categoryTypeExtId;
	}

	public void setCategoryTypeExtId(String categoryTypeExtId) {
		this.categoryTypeExtId = categoryTypeExtId;
	}

	
	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<RequestType> getListRequestType() {
		return listRequestType;
	}

	public void setListRequestType(List<RequestType> listRequestType) {
		this.listRequestType = listRequestType;
	}

	public boolean isDirectType() {
		return isDirectType;
	}

	public void setDirectType(boolean isDirectType) {
		this.isDirectType = isDirectType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
	

}
