package com.phincon.talents.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	
	@Column(name = "logo", length = 255)
	private String logo;
	
	@Column(name = "telephone", length = 100)
	private String telephone;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Transient
	private List<BranchCompany> listBranchCompany;

	public List<BranchCompany> getListBranchCompany() {
		return listBranchCompany;
	}

	public void setListBranchCompany(List<BranchCompany> listBranchCompany) {
		this.listBranchCompany = listBranchCompany;
	}

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

	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public ConnectedApp getConnectedApp() {
		return connectedApp;
	}

	public void setConnectedApp(ConnectedApp connectedApp) {
		this.connectedApp = connectedApp;
	}
	
	

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Company() {
	}

	@Override
	public String toString() {
		return "Company{" + "name='" + name + '\'' + "}";
	}

}
