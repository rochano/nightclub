package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class GirlServiceId implements Serializable { 

	private FreeAgentGirlInfo freeAgentGirlInfo;
	private GirlServiceInfo girlServiceInfo;

	@ManyToOne(cascade = CascadeType.ALL)
	public FreeAgentGirlInfo getFreeAgentGirlInfo() {
		return freeAgentGirlInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public GirlServiceInfo getGirlServiceInfo() {
		return girlServiceInfo;
	}

	public void setFreeAgentGirlInfo(FreeAgentGirlInfo freeAgentGirlInfo) {
		this.freeAgentGirlInfo = freeAgentGirlInfo;
	}
	public void setGirlServiceInfo(GirlServiceInfo girlServiceInfo) {
		this.girlServiceInfo = girlServiceInfo;
	}

}
