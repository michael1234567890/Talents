package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
@NamedQueries({ @NamedQuery(name = Role.FIND_BY_NAME, query = "select u from Role u where name = :name") })
public class Role extends AbstractEntity {
	public static final String FIND_BY_NAME = "role.findByUserName";
	
	@Column(name = "name", length = 100)
	private String name;
	public Role() {
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role" + "role='" + name + '\'' + "}";
	}

}
