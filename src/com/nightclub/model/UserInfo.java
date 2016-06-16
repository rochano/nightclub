package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="user_info")
public class UserInfo implements Serializable{

	private static final long serialVersionUID = -1441957305820464269L;
	
	private String username;
	private String password;
	private String userType;
	private String shopInfoId;
	
	@Id
	@Column(name="username")
	@Length(max=20)
	public String getUsername() {
		return username;
	}
	@Column(name="password")
	@Length(max=20)
	public String getPassword() {
		return password;
	}
	@Column(name="user_type")
	@Length(max=1)
	public String getUserType() {
		return userType;
	}
	@Column(name="shop_info_id")
	@Length(max=40)
	public String getShopInfoId() {
		return shopInfoId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
}
