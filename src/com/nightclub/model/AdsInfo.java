package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="ads_info")
public class AdsInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String adsInfoId;
	private String title;
	private Date adsDateFrom;
	private Date adsDateTo;
	private String active;
	private String customUrl;
	private String adsImg1;
	private String adsImg2;

	@Id
	@Column(name="ads_info_id")
	@Length(max=40)
	public String getAdsInfoId() {
		return adsInfoId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	@Column(name="ads_date_from")
	public Date getAdsDateFrom() {
		return adsDateFrom;
	}
	@Column(name="ads_date_to")
	public Date getAdsDateTo() {
		return adsDateTo;
	}
	@Column(name="active")
	public String getActive() {
		return active;
	}
	@Column(name="custom_url")
	public String getCustomUrl() {
		if (customUrl == null) {
			return "";
		}
		return customUrl;
	}
	@Column(name="ads_img_1")
	public String getAdsImg1() {
		if (adsImg1 == null) {
			return "";
		}
		return adsImg1;
	}
	@Column(name="ads_img_2")
	public String getAdsImg2() {
		if (adsImg2 == null) {
			return "";
		}
		return adsImg2;
	}
	public void setAdsInfoId(String adsInfoId) {
		this.adsInfoId = adsInfoId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAdsDateFrom(Date adsDateFrom) {
		this.adsDateFrom = adsDateFrom;
	}
	public void setAdsDateTo(Date adsDateTo) {
		this.adsDateTo = adsDateTo;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}
	public void setAdsImg1(String adsImg1) {
		this.adsImg1 = adsImg1;
	}
	public void setAdsImg2(String adsImg2) {
		this.adsImg2 = adsImg2;
	}
}
