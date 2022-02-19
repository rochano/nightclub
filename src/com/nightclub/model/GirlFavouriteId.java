package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class GirlFavouriteId implements Serializable { 

	private ClientInfo clientInfo;
	private GirlInfo girlInfo;

	@ManyToOne(cascade = CascadeType.ALL)
	public ClientInfo getClientInfo() {
		return clientInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public GirlInfo getGirlInfo() {
		return girlInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}

}
