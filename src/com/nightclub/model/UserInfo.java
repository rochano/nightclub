package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.Length;

@Entity
@Table(name="user_info")
public class UserInfo implements Serializable{

	private static final long serialVersionUID = -1441957305820464269L;
	
	private String userInfoId;
	private String username;
	private String password;
	private String userType;
	private String shopInfoId;
	private String agentInfoId;
	private String girlInfoId;
	private String clientInfoId;
	private String active;
	private Date validDateFrom;
	private Date validDateTo;
	private String phone;
	private String deleteFlg;
	private String email;

	private BasicInfo shopInfo;
	private AgentInfo agentInfo;
	private FreeAgentGirlInfo freeAgentGirlInfo;
	private ClientInfo clientInfo;
	private EnGirlInfo enGirlInfo; 

	@Id
	@Column(name="user_info_id")
	public String getUserInfoId() {
		return userInfoId;
	}
	@Column(name="username", unique=true)
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
	@JoinColumn(name="shop_info_id", insertable=false, updatable=false)
	@Length(max=40)
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="agent_info_id")
	@JoinColumn(name="agent_info_id", insertable=false, updatable=false)
	@Length(max=40)
	public String getAgentInfoId() {
		return agentInfoId;
	}
	@Column(name="girl_info_id")
	@JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	@Length(max=40)
	public String getGirlInfoId() {
		return girlInfoId;
	}
	@Column(name="client_info_id")
	@Length(max=40)
	public String getClientInfoId() {
		return clientInfoId;
	}
	@Column(name="active")
	public String getActive() {
		if (active == null || "".equals(active)) {
			return Boolean.FALSE.toString();
		}
		return active;
	}
	@Column(name="valid_date_from")
	public Date getValidDateTo() {
		return validDateTo;
	}
	@Column(name="valid_date_to")
	public Date getValidDateFrom() {
		return validDateFrom;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="shop_info_id", insertable=false, updatable=false)
	public BasicInfo getShopInfo() {
		return shopInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="agent_info_id", insertable=false, updatable=false)
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	public FreeAgentGirlInfo getFreeAgentGirlInfo() {
		return freeAgentGirlInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="client_info_id", insertable=false, updatable=false)
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	public EnGirlInfo getEnGirlInfo() {
		return enGirlInfo;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	@Column(name="delete_flg")
	public String getDeleteFlg() {
		return deleteFlg;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
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
	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
	public void setClientInfoId(String clientInfoId) {
		this.clientInfoId = clientInfoId;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public void setValidDateFrom(Date validDateFrom) {
		this.validDateFrom = validDateFrom;
	}
	public void setValidDateTo(Date validDateTo) {
		this.validDateTo = validDateTo;
	}
	public void setShopInfo(BasicInfo shopInfo) {
		this.shopInfo = shopInfo;
	}
	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}
	public void setFreeAgentGirlInfo(FreeAgentGirlInfo freeAgentGirlInfo) {
		this.freeAgentGirlInfo = freeAgentGirlInfo;
	}
	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	public void setEnGirlInfo(EnGirlInfo enGirlInfo) {
		this.enGirlInfo = enGirlInfo;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
