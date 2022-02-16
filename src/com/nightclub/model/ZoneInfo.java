package com.nightclub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="zone_info")
public class ZoneInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String zoneInfoId;
//	private String zoneCode;
	private String zoneNameEn;
	private String zoneNameJp;
	private String chkCustomUrl;
	private String customUrl;
	
	private List<CategoryZone> catgoryZones = new ArrayList<CategoryZone>();

	@Id
	@Column(name="zone_info_id")
	@Length(max=40)
	public String getZoneInfoId() {
		return zoneInfoId;
	}
	
//	@Column(name="zone_code", unique=true)
//	public String getZoneCode() {
//		return zoneCode;
//	}
	
	@Column(name="zone_name_en")
	public String getZoneNameEn() {
		return zoneNameEn;
	}
	@Column(name="zone_name_jp")
	public String getZoneNameJp() {
		return zoneNameJp;
	}
	@OneToMany(mappedBy = "primaryKey.zoneInfo",
            cascade = CascadeType.ALL)
	public List<CategoryZone> getCategoryZones() {
		return catgoryZones;
	}
	@Column(name="chk_custom_url")
	public String getChkCustomUrl() {
		return chkCustomUrl;
	}
	@Column(name="custom_url")
	public String getCustomUrl() {
		return customUrl;
	}
	public void setZoneInfoId(String zoneInfoId) {
		this.zoneInfoId = zoneInfoId;
	}
//	public void setZoneCode(String zoneCode) {
//		this.zoneCode = zoneCode;
//	}
	public void setZoneNameEn(String zoneNameEn) {
		this.zoneNameEn = zoneNameEn;
	}
	public void setZoneNameJp(String zoneNameJp) {
		this.zoneNameJp = zoneNameJp;
	}
	public void setCategoryZones(List<CategoryZone> catgoryZones) {
		this.catgoryZones = catgoryZones;
	}
	public void setChkCustomUrl(String chkCustomUrl) {
		this.chkCustomUrl = chkCustomUrl;
	}
	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}
}
