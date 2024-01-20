package com.nightclub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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
	private String provinceInfoId;
	private ProvinceInfo provinceInfo;
	
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
		if (zoneNameEn == null) {
			return "";
		}
		return zoneNameEn;
	}
	@Column(name="zone_name_jp")
	public String getZoneNameJp() {
		if (zoneNameJp == null) {
			return "";
		}
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
	@Column(name="province_info_id")
	public String getProvinceInfoId() {
		return provinceInfoId;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="province_info_id", referencedColumnName="province_info_id", insertable=false, updatable=false)
	public ProvinceInfo getProvinceInfo() {
		return provinceInfo;
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
	public void setProvinceInfoId(String provinceInfoId) {
		this.provinceInfoId = provinceInfoId;
	}
	public void setProvinceInfo(ProvinceInfo provinceInfo) {
		this.provinceInfo = provinceInfo;
	}
}
