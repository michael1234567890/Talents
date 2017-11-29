package com.phincon.talents.app.model.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.phincon.talents.app.model.AbstractEntity;


@Entity
@Table(name = "hr_work_location")
public class WorkLocation extends AbstractEntity{
	
	@Column(name = "name", length = 255)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
