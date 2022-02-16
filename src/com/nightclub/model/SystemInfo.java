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

	private String shopInfoId;
//	private String infoName;
	private String classType;
	private Double priceNormal1;
	private Double priceNormal2;
	private Double priceNormal3;
	private Double priceVIP1;
	private Double priceVIP2;
	private Double priceVIP3;
//	private String description;
	
//	@Id
//	@Column(name="system_info_id")
//	@Length(max=40)
//	public String getSystemInfoId() {
//		return systemInfoId;
//	}
	@Id
	@Column(name="shop_info_id")
	public String getShopInfoId() {
		return shopInfoId;
	}
//	@Column(name="info_name")
//	public String getInfoName() {
//		return infoName;
//	}
	@Column(name="classType")
	public String getClassType() {
		return classType;
	}
	@Column(name="priceNormal1")
	public Double getPriceNormal1() {
		if (priceNormal1 == null) {
			return 0.0;
		} else {
			return priceNormal1;
		}
	}
	@Column(name="priceNormal2")
	public Double getPriceNormal2() {
		if (priceNormal2 == null) {
			return 0.0;
		} else {
			return priceNormal2;
		}
	}
	@Column(name="priceNormal3")
	public Double getPriceNormal3() {
		if (priceNormal3 == null) {
			return 0.0;
		} else {
			return priceNormal3;
		}
	}
	@Column(name="priceVIP1")
	public Double getPriceVIP1() {
		if (priceVIP1 == null) {
			return 0.0;
		} else {
			return priceVIP1;
		}
	}
	@Column(name="priceVIP2")
	public Double getPriceVIP2() {
		if (priceVIP2 == null) {
			return 0.0;
		} else {
			return priceVIP2;
		}
	}
	@Column(name="priceVIP3")
	public Double getPriceVIP3() {
		if (priceVIP3 == null) {
			return 0.0;
		} else {
			return priceVIP3;
		}
	}
//	@Column(name="description")
//	public String getDescription() {
//		return description;
//	}
//	public void setSystemInfoId(String systemInfoId) {
//		this.systemInfoId = systemInfoId;
//	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
//	public void setInfoName(String infoName) {
//		this.infoName = infoName;
//	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public void setPriceNormal1(Double priceNormal1) {
		this.priceNormal1 = priceNormal1;
	}
	public void setPriceNormal2(Double priceNormal2) {
		this.priceNormal2 = priceNormal2;
	}
	public void setPriceNormal3(Double priceNormal3) {
		this.priceNormal3 = priceNormal3;
	}
	public void setPriceVIP1(Double priceVIP1) {
		this.priceVIP1 = priceVIP1;
	}
	public void setPriceVIP2(Double priceVIP2) {
		this.priceVIP2 = priceVIP2;
	}
	public void setPriceVIP3(Double priceVIP3) {
		this.priceVIP3 = priceVIP3;
	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
}
