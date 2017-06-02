package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "attachment_certification")
public class AttachmentCertification extends AbstractEntity {

//	@ManyToOne
//	@JoinColumn(name = "data_approval_id")
//	private DataApproval dataApproval;
	
	@Column(name = "certification_id")
	private Long certification;

	@Column(name = "path", length = 255)
	private String path;
	
	@Transient
	private String image;

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

	public Long getCertification() {
		return certification;
	}

	public void setCertification(Long certification) {
		this.certification = certification;
	}
	
	
	
	
	
	
	
	
	

}
