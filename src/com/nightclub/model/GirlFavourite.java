package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "girl_favourite")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.freeAgentGirlInfo",
        joinColumns = @JoinColumn(name = "client_info_id")),
    @AssociationOverride(name = "primaryKey.girlServiceInfo",
        joinColumns = @JoinColumn(name = "girl_info_id")) })
public class GirlFavourite implements Serializable {

	private static final long serialVersionUID = 1L;
	private GirlFavouriteId primaryKey = new GirlFavouriteId();

	@EmbeddedId
	public GirlFavouriteId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public ClientInfo getClientInfo() {
		return getPrimaryKey().getClientInfo();
	}
	@Transient
	public GirlInfo getGirlInfo() {
		return getPrimaryKey().getGirlInfo();
	}
	public void setPrimaryKey(GirlFavouriteId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setClientInfo(ClientInfo clientInfo) {
		getPrimaryKey().setClientInfo(clientInfo);;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		getPrimaryKey().setGirlInfo(girlInfo);
	}
}
