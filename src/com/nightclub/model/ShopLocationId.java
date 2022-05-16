package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class ShopLocationId implements Serializable { 

	private BasicInfo basicInfo;
	private ZoneInfo zoneInfo;

	@ManyToOne(cascade = CascadeType.ALL)
	public BasicInfo getBasicInfo() {
		return basicInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public ZoneInfo getZoneInfo() {
		return zoneInfo;
	}

	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	public void setZoneInfo(ZoneInfo zoneInfo) {
		this.zoneInfo = zoneInfo;
	}

}
