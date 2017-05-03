package com.phincon.talents.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
@NamedQueries({ @NamedQuery(name = Company.FIND_BY_CODE, query = "select bean from Company bean where code = :code") })
public class Company extends AbstractEntity {
	public static final String FIND_BY_CODE = "company.findByCode";
	public static final String FIND_ALL = "company.findAll";

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "code", length = 100)
	private String code;

	@Column(name = "address", length = 255)
	private String address;

	@JoinColumn(name = "connected_app_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private ConnectedApp connectedApp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ConnectedApp getConnectedApp() {
		return connectedApp;
	}

	public void setConnectedApp(ConnectedApp connectedApp) {
		this.connectedApp = connectedApp;
	}

	public Company() {
	}

	@Override
	public String toString() {
		return "Company{" + "name='" + name + '\'' + "}";
	}

}
