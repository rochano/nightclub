package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="map_info")
public class MapInfo  implements Serializable {

	private static final long serialVersionUID = 2879190957451673547L;
	private String shopInfoId;
	private Double latitude;
	private Double longitude;
	private String description;
	
	@Id
	@Column(name="shop_info_id")
	@Length(max=40)
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="latitude")
	public Double getLatitude() {
		return latitude;
	}
	@Column(name="longitude")
	public Double getLongitude() {
		return longitude;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
