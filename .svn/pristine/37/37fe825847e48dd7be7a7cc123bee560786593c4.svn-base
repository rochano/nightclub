package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="event_info")
public class EventInfo implements Serializable {

	private static final long serialVersionUID = -1280202620579748203L;
	
	private String eventInfoId;
	private String shopInfoId;
	private String title;
	private Date eventDate;
	private String eventTime;
	private String description;
	
	@Id
	@Column(name="event_info_id")
	@Length(max=40)
	public String getEventInfoId() {
		return eventInfoId;
	}
	@Column(name="shop_info_id")
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	@Column(name="event_date")
	public Date getEventDate() {
		return eventDate;
	}
	@Column(name="event_time")
	public String getEventTime() {
		return eventTime;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setEventInfoId(String eventInfoId) {
		this.eventInfoId = eventInfoId;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
