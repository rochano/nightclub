package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

public class EventSearch implements Serializable {

	private static final long serialVersionUID = -6584035519739513456L;
	
	private String shopInfoId;
	private String title;
	private Date eventDateFrom;
	private Date eventDateTo;
	
	public String getShopInfoId() {
		return shopInfoId;
	}
	public String getTitle() {
		return title;
	}
	public Date getEventDateFrom() {
		return eventDateFrom;
	}
	public Date getEventDateTo() {
		return eventDateTo;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setEventDateFrom(Date eventDateFrom) {
		this.eventDateFrom = eventDateFrom;
	}
	public void setEventDateTo(Date eventDateTo) {
		this.eventDateTo = eventDateTo;
	}
	
}
