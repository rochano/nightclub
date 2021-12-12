package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

public class AdsSearch implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private Date adsDateFrom;
	private Date adsDateTo;
	
	public String getTitle() {
		return title;
	}
	public Date getAdsDateFrom() {
		return adsDateFrom;
	}
	public Date getAdsDateTo() {
		return adsDateTo;
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
	
}
