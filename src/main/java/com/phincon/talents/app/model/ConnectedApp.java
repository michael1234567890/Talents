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
@Table(name = "CONNECTED_APP")
@NamedQueries({
    @NamedQuery(
            name = ConnectedApp.FIND_BY_NAME,
            query = "select bean from ConnectedApp bean where name = :name"
    )
})
public class ConnectedApp extends AbstractEntity {
	public static final String FIND_BY_NAME = "ConnectedApp.findByName";
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="company_id")
	private Company company;
	
	@Column(name="name")
	private String name;
	
	@Column(name="instance_url")
	private String instanceUrl;
	
	@Column(name="login_url")
	private String loginUrl;

	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="security_token")
	private String securityToken;
	
	@Column(name="consumer_key")
	private String consumerKey;
	
	@Column(name="consumer_secret")
	private String consumerSecret;
	
	
	

	public String getInstanceUrl() {
		return instanceUrl;
	}



	public void setInstanceUrl(String instanceUrl) {
		this.instanceUrl = instanceUrl;
	}



	public String getLoginUrl() {
		return loginUrl;
	}



	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getSecurityToken() {
		return securityToken;
	}


	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}

	public String getConsumerKey() {
		return consumerKey;
	}


	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public ConnectedApp(){}
	

	
	 @Override
	    public String toString() {
	        return "CompanySetting{" +
	                "username='" + username + '\'' + "}";
	    }
	
}
