package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class GirlTagId implements Serializable { 

	private GirlInfo girlInfo;
	private GirlTagInfo girlTagInfo;

	@ManyToOne(cascade = CascadeType.ALL)
	public GirlInfo getGirlInfo() {
		return girlInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public GirlTagInfo getGirlTagInfo() {
		return girlTagInfo;
	}

	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}
	public void setGirlTagInfo(GirlTagInfo girlTagInfo) {
		this.girlTagInfo = girlTagInfo;
	}

}
