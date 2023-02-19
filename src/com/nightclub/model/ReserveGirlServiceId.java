package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class ReserveGirlServiceId implements Serializable { 

	private ReserveInfo reserveInfo;
	private GirlServiceInfo girlServiceInfo;

	@ManyToOne(cascade = CascadeType.ALL)
	public ReserveInfo getReserveInfo() {
		return reserveInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public GirlServiceInfo getGirlServiceInfo() {
		return girlServiceInfo;
	}
	public void setReserveInfo(ReserveInfo reserveInfo) {
		this.reserveInfo = reserveInfo;
	}
	public void setGirlServiceInfo(GirlServiceInfo girlServiceInfo) {
		this.girlServiceInfo = girlServiceInfo;
	}

}
