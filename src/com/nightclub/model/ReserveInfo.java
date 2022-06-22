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
@Table(name="reserve_info")
public class ReserveInfo implements Serializable{

	private static final long serialVersionUID = 6848161747389312861L;
	
	private String reserveInfoId;
	private String shopInfoId;
	private String girlInfoId;
	private String clientInfoId;
	private Date reserveDate;
	private String startTime;
	private String endTime;

	private GirlInfo girlInfo; 
	private ClientInfo clientInfo;

	@Id
	@Column(name="reserve_info_id")
	@Length(max=40)
	public String getReserveInfoId() {
		return reserveInfoId;
	}
	@Column(name="shop_info_id")
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="girl_info_id")
	public String getGirlInfoId() {
		return girlInfoId;
	}
	@Column(name="client_info_id")
	public String getClientInfoId() {
		return clientInfoId;
	}
	@Column(name="reserve_date")
	public Date getReserveDate() {
		return reserveDate;
	}
	@Column(name="start_time")
	public String getStartTime() {
		return startTime;
	}
	@Column(name="end_time")
	public String getEndTime() {
		return endTime;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	public GirlInfo getGirlInfo() {
		return girlInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="client_info_id", insertable=false, updatable=false)
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
	public void setReserveInfoId(String reserveInfoId) {
		this.reserveInfoId = reserveInfoId;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
	public void setClientInfoId(String clientInfoId) {
		this.clientInfoId = clientInfoId;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}
	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
}
