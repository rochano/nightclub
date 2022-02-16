package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="client_info")
public class ClientInfo implements Serializable {
	
	private static final long serialVersionUID = 7406550732066083106L;
	
	private String clientInfoId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private Integer age;
	private String active;

	public ClientInfo() {}
	
	protected ClientInfo(ClientInfo clientInfo) {
		this.clientInfoId = clientInfo.clientInfoId;
		this.firstName = clientInfo.firstName;
		this.lastName = clientInfo.lastName;
		this.email = clientInfo.email;
		this.mobile = clientInfo.mobile;
		this.age = clientInfo.age;
		this.active = clientInfo.active;
	}
	
	@Id
	@Column(name="client_info_id")
	@Length(max=40)
	public String getClientInfoId() {
		return clientInfoId;
	}
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name="age")
	public Integer getAge() {
		return age;
	}
	@Column(name="active")
	public String getActive() {
		return active;
	}
	public void setClientInfoId(String clientInfoId) {
		this.clientInfoId = clientInfoId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public ClientInfo clone() {
		return new ClientInfo(this);
	}
}
