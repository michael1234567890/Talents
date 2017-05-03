package com.phincon.talents.app.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * This is a base class for all entities. It provides an equals and hashcode
 * that will always work correctly in all circumstances. This avoids frequent
 * errors related to the implementation of those same methods.
 *
 */
@MappedSuperclass
public class AbstractEntity {

	@Id
	@GeneratedValue
	private Long id;

//	@Version
//	private Long version;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Transient
	private UUID uuid;

	@Column(name = "UUID")
	private String uuidStr;
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "ext_id", length=100)
	private String extId;

	@PrePersist
	protected void prePersist() {
		syncUuidString();
	}

	protected void syncUuidString() {
		if (null == uuidStr) {
			// initial method call fills the uuid
			getUuid();
		}
	}

	public UUID getUuid() {
		if (uuidStr == null) {
			if (uuid == null) {
				uuid = UUID.randomUUID();
			}
			uuidStr = uuid.toString();
		}
		if (uuid == null && uuidStr != null) {
			uuid = UUID.fromString(uuidStr);
		}
		return uuid;
	}

	public Long getId() {
		return id;
	}

	/*
	 * 
	 * This method is mean for testing purposes only (create mock data), as we
	 * should not set Ids manually, Hibernate will do it automatically
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		AbstractEntity that = (AbstractEntity) o;

		if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that
				.getUuid() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return getUuid() != null ? getUuid().hashCode() : 0;
	}

//	public Long getVersion() {
//		return version;
//	}

	public String getUuidStr() {
		return uuidStr;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
	
	
	
}
