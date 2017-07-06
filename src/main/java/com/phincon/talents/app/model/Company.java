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
	
	@Column(name = "branch_1_name")
	private String branch1Name;
	
	@Column(name = "branch_1_address", length = 255)
	private String branch1Address;
	
	@Column(name = "branch_2_name")
	private String branch2Name;
	
	@Column(name = "branch_2_address", length = 255)
	private String branch2Address;
	
	@Column(name = "logo", length = 255)
	private String logo;
	
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

	public String getBranch1Name() {
		return branch1Name;
	}

	public void setBranch1Name(String branch1Name) {
		this.branch1Name = branch1Name;
	}

	public String getBranch1Address() {
		return branch1Address;
	}

	public void setBranch1Address(String branch1Address) {
		this.branch1Address = branch1Address;
	}

	public String getBranch2Name() {
		return branch2Name;
	}

	public void setBranch2Name(String branch2Name) {
		this.branch2Name = branch2Name;
	}

	public String getBranch2Address() {
		return branch2Address;
	}

	public void setBranch2Address(String branch2Address) {
		this.branch2Address = branch2Address;
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

	public Company() {
	}

	@Override
	public String toString() {
		return "Company{" + "name='" + name + '\'' + "}";
	}

}
