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
	private String autoSubscribe;
	private String active;
	private String customUrl;
	private String adsImg;
	private Date currentRangeFrom;
	private Date currentRangeTo;

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
	@Column(name="auto_subscribe")
	public String getAutoSubscribe() {
		return autoSubscribe;
	}
	@Column(name="active")
	public String getActive() {
		return active;
	}
	@Column(name="custom_url")
	public String getCustomUrl() {
		return customUrl;
	}
	@Column(name="ads_img")
	public String getAdsImg() {
		return adsImg;
	}
	@javax.persistence.Transient
	public Date getCurrentRangeFrom() {
		return currentRangeFrom;
	}
	@javax.persistence.Transient
	public Date getCurrentRangeTo() {
		return currentRangeTo;
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
	public void setAutoSubscribe(String autoSubscribe) {
		this.autoSubscribe = autoSubscribe;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public void setCustomUrl(String customUrl) {
		this.customUrl = customUrl;
	}
	public void setAdsImg(String adsImg) {
		this.adsImg = adsImg;
	}
	public void setCurrentRangeFrom(Date currentRangeFrom) {
		this.currentRangeFrom = currentRangeFrom;
	}
	public void setCurrentRangeTo(Date currentRangeTo) {
		this.currentRangeTo = currentRangeTo;
	}
}
