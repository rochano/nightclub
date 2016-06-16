package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="system_info")
public class SystemInfo implements Serializable {

	private static final long serialVersionUID = 5108861147966720107L;

	private String systemInfoId;
	private String shopInfoId;
	private String infoName;
	private String duration;
	private Double price;
	private String description;
	
	@Id
	@Column(name="system_info_id")
	@Length(max=40)
	public String getSystemInfoId() {
		return systemInfoId;
	}
	@Column(name="shop_info_id")
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="info_name")
	public String getInfoName() {
		return infoName;
	}
	@Column(name="duration")
	public String getDuration() {
		return duration;
	}
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setSystemInfoId(String systemInfoId) {
		this.systemInfoId = systemInfoId;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
