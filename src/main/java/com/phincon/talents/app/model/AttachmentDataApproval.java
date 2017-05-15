package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "wf_data_approval_attachment")
public class AttachmentDataApproval extends AbstractEntity {

//	@ManyToOne
//	@JoinColumn(name = "data_approval_id")
//	private DataApproval dataApproval;
	
	@Column(name = "data_approval_id")
	private Long dataApproval;

	@Column(name = "path", length = 255)
	private String path;
	
	@Transient
	private String image;

	public Long getDataApproval() {
		return dataApproval;
	}

	public void setDataApproval(Long dataApproval) {
		this.dataApproval = dataApproval;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
	
	
	
	

}
