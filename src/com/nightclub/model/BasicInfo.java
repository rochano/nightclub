package com.nightclub.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

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
public class BasicInfo implements Serializable {
	
	private static final long serialVersionUID = 7406550732066083106L;
	
	private String shopInfoId;
//	private String shopCode;
	private String categoryInfoId;
	private String shopNameJp;
	private String shopNameEn;
	private String zoneInfoId;
	private String logoImg;
	private String shopImg;
//	private String address;
	private String province;
//	private String postcode;
	private String phone;
//	private String mobile;
//	private String email;
	private String description;
//	private String workMon;
//	private String workTue;
//	private String workWed;
//	private String workThu;
//	private String workFri;
//	private String workSat;
//	private String workSun;
	private String startTime;
	private String endTime;
//	private String chkCustomUrl;
//	private String customUrl;
	private String lineId;
	
	private CategoryInfo categoryInfo;
	private ZoneInfo zoneInfo;
	private MapInfo mapInfo;
	private SystemInfo systemInfo;
	
	public BasicInfo() {}
	
	protected BasicInfo(BasicInfo basicInfo) {
		this.shopInfoId = basicInfo.shopInfoId;
//		this.shopCode = basicInfo.shopCode;
		this.categoryInfoId = basicInfo.categoryInfoId;
		this.shopNameJp = basicInfo.shopNameJp;
		this.shopNameEn = basicInfo.shopNameEn;
		this.zoneInfoId = basicInfo.zoneInfoId;
		this.logoImg = basicInfo.logoImg;
		this.shopImg = basicInfo.shopImg;
//		this.address = basicInfo.address;
		this.province = basicInfo.province;
//		this.postcode = basicInfo.postcode;
		this.phone = basicInfo.phone;
//		this.mobile = basicInfo.mobile;
//		this.email = basicInfo.email;
		this.description = basicInfo.description;
//		this.workMon = basicInfo.workMon;
//		this.workTue = basicInfo.workTue;
//		this.workWed = basicInfo.workWed;
//		this.workThu = basicInfo.workThu;
//		this.workFri = basicInfo.workFri;
//		this.workSat = basicInfo.workSat;
//		this.workSun = basicInfo.workSun;
		this.startTime = basicInfo.startTime;
		this.endTime = basicInfo.endTime;
//		this.chkCustomUrl = basicInfo.chkCustomUrl;
//		this.customUrl = basicInfo.customUrl;
		this.lineId = basicInfo.lineId;
	}
	
	@Id
	@Column(name="shop_info_id")
	@Length(max=40)
	public String getShopInfoId() {
		return shopInfoId;
	}
//	@Column(name="shop_code", unique=true)
//	public String getShopCode() {
//		return shopCode;
//	}
	@Column(name="category_info_id")
	public String getCategoryInfoId() {
		return categoryInfoId;
	}
	@Column(name="shop_name_jp")
	public String getShopNameJp() {
		return shopNameJp;
	}
	@Column(name="shop_name_en")
	public String getShopNameEn() {
		return shopNameEn;
	}
	@Column(name="zone_info_id")
	public String getZoneInfoId() {
		return zoneInfoId;
	}
	@Column(name="logo_img")
	public String getLogoImg() {
		return logoImg;
	}
	@Column(name="shop_img")
	public String getShopImg() {
		return shopImg;
	}
//	@Column(name="address")
//	public String getAddress() {
//		return address;
//	}
	@Column(name="province")
	public String getProvince() {
		return province;
	}
//	@Column(name="postcode")
//	public String getPostcode() {
//		return postcode;
//	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
//	@Column(name="mobile")
//	public String getMobile() {
//		return mobile;
//	}
//	@Column(name="email")
//	public String getEmail() {
//		return email;
//	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="category_info_id", referencedColumnName="category_info_id", insertable=false, updatable=false)
	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="zone_info_id", referencedColumnName="zone_info_id", insertable=false, updatable=false)
	public ZoneInfo getZoneInfo() {
		return zoneInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="shop_info_id", referencedColumnName="shop_info_id", insertable=false, updatable=false)
	public MapInfo getMapInfo() {
		return mapInfo;
	}
//	@Column(name="work_mon")
//	public String getWorkMon() {
//		return workMon;
//	}
//	@Column(name="work_tue")
//	public String getWorkTue() {
//		return workTue;
//	}
//	@Column(name="work_wed")
//	public String getWorkWed() {
//		return workWed;
//	}
//	@Column(name="work_thu")
//	public String getWorkThu() {
//		return workThu;
//	}
//	@Column(name="work_fri")
//	public String getWorkFri() {
//		return workFri;
//	}
//	@Column(name="work_sat")
//	public String getWorkSat() {
//		return workSat;
//	}
//	@Column(name="work_sun")
//	public String getWorkSun() {
//		return workSun;
//	}
	@Column(name="start_time")
	public String getStartTime() {
		return startTime;
	}
	@Column(name="end_time")
	public String getEndTime() {
		return endTime;
	}
//	@Column(name="chk_custom_url")
//	public String getChkCustomUrl() {
//		return chkCustomUrl;
//	}
//	@Column(name="custom_url")
//	public String getCustomUrl() {
//		return customUrl;
//	}
	@Column(name="line_id")
	public String getLineId() {
		return lineId;
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
//	public void setShopCode(String shopCode) {
//		this.shopCode = shopCode;
//	}
	public void setCategoryInfoId(String categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}
	public void setShopNameJp(String shopNameJp) {
		this.shopNameJp = shopNameJp;
	}
	public void setShopNameEn(String shopNameEn) {
		this.shopNameEn = shopNameEn;
	}
	public void setZoneInfoId(String zoneInfoId) {
		this.zoneInfoId = zoneInfoId;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
	public void setProvince(String province) {
		this.province = province;
	}
//	public void setPostcode(String postcode) {
//		this.postcode = postcode;
//	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
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
//	public void setWorkMon(String workMon) {
//		this.workMon = workMon;
//	}
//	public void setWorkTue(String workTue) {
//		this.workTue = workTue;
//	}
//	public void setWorkWed(String workWed) {
//		this.workWed = workWed;
//	}
//	public void setWorkThu(String workThu) {
//		this.workThu = workThu;
//	}
//	public void setWorkFri(String workFri) {
//		this.workFri = workFri;
//	}
//	public void setWorkSat(String workSat) {
//		this.workSat = workSat;
//	}
//	public void setWorkSun(String workSun) {
//		this.workSun = workSun;
//	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
//	public void setChkCustomUrl(String chkCustomUrl) {
//		this.chkCustomUrl = chkCustomUrl;
//	}
//	public void setCustomUrl(String customUrl) {
//		this.customUrl = customUrl;
//	}
	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public BasicInfo clone() {
		return new BasicInfo(this);
	}
}
