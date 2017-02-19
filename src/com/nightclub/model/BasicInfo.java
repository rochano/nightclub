package com.nightclub.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.hibernate.validator.Length;

@Entity
@Table(name="basic_info")
public class BasicInfo implements Serializable{
	
	private static final long serialVersionUID = 7406550732066083106L;
	
	private String shopInfoId;
	private String shopCode;
	private String categoryCode;
	private String shopNameJp;
	private String shopNameEn;
	private String zoneCode;
	private String logoImg;
	private String shopImg;
	private String address;
	private String province;
	private String postcode;
	private String phone;
	private String mobile;
	private String email;
	private String description;
	private String workMon;
	private String workTue;
	private String workWed;
	private String workThu;
	private String workFri;
	private String workSat;
	private String workSun;
	private String startTime;
	private String endTime;
	private String chkCustomUrl;
	private String customUrl;
	private String active;
	
	private CategoryInfo categoryInfo;
	private ZoneInfo zoneInfo;
	private MapInfo mapInfo;
	private SystemInfo systemInfo;
	
	@Id
	@Column(name="shop_info_id")
	@Length(max=40)
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="shop_code")
	public String getShopCode() {
		return shopCode;
	}
	@Column(name="category_code")
	public String getCategoryCode() {
		return categoryCode;
	}
	@Column(name="shop_name_jp")
	public String getShopNameJp() {
		return shopNameJp;
	}
	@Column(name="shop_name_en")
	public String getShopNameEn() {
		return shopNameEn;
	}
	@Column(name="zone_code")
	public String getZoneCode() {
		return zoneCode;
	}
	@Column(name="logo_img")
	public String getLogoImg() {
		return logoImg;
	}
	@Column(name="shop_img")
	public String getShopImg() {
		return shopImg;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	@Column(name="province")
	public String getProvince() {
		return province;
	}
	@Column(name="postcode")
	public String getPostcode() {
		return postcode;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	@Column(name="mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="category_code", referencedColumnName="category_code", insertable=false, updatable=false)
	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="zone_code", referencedColumnName="zone_code", insertable=false, updatable=false)
	public ZoneInfo getZoneInfo() {
		return zoneInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="shop_info_id", referencedColumnName="shop_info_id", insertable=false, updatable=false)
	public MapInfo getMapInfo() {
		return mapInfo;
	}
	@Column(name="work_mon")
	public String getWorkMon() {
		return workMon;
	}
	@Column(name="work_tue")
	public String getWorkTue() {
		return workTue;
	}
	@Column(name="work_wed")
	public String getWorkWed() {
		return workWed;
	}
	@Column(name="work_thu")
	public String getWorkThu() {
		return workThu;
	}
	@Column(name="work_fri")
	public String getWorkFri() {
		return workFri;
	}
	@Column(name="work_sat")
	public String getWorkSat() {
		return workSat;
	}
	@Column(name="work_sun")
	public String getWorkSun() {
		return workSun;
	}
	@Column(name="start_time")
	public String getStartTime() {
		return startTime;
	}
	@Column(name="end_time")
	public String getEndTime() {
		return endTime;
	}
	@Column(name="chk_custom_url")
	public String getChkCustomUrl() {
		return chkCustomUrl;
	}
	@Column(name="custom_url")
	public String getCustomUrl() {
		return customUrl;
	}
	@Column(name="active")
	public String getActive() {
		return active;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="shop_info_id", insertable=false, updatable=false)
	public SystemInfo getSystemInfo() {
		return systemInfo;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public void setShopNameJp(String shopNameJp) {
		this.shopNameJp = shopNameJp;
	}
	public void setShopNameEn(String shopNameEn) {
		this.shopNameEn = shopNameEn;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}
	public void setZoneInfo(ZoneInfo zoneInfo) {
		this.zoneInfo = zoneInfo;
	}
	public void setMapInfo(MapInfo mapInfo) {
		this.mapInfo = mapInfo;
	}
	public void setWorkMon(String workMon) {
		this.workMon = workMon;
	}
	public void setWorkTue(String workTue) {
		this.workTue = workTue;
	}
	public void setWorkWed(String workWed) {
		this.workWed = workWed;
	}
	public void setWorkThu(String workThu) {
		this.workThu = workThu;
	}
	public void setWorkFri(String workFri) {
		this.workFri = workFri;
	}
	public void setWorkSat(String workSat) {
		this.workSat = workSat;
	}
	public void setWorkSun(String workSun) {
		this.workSun = workSun;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setChkCustomUrl(String chkCustomUrl) {
		this.chkCustomUrl = chkCustomUrl;
	}
	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}
	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
