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
@Table(name = "girl_location")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.girlInfo",
        joinColumns = @JoinColumn(name = "girl_info_id")),
    @AssociationOverride(name = "primaryKey.zoneInfo",
        joinColumns = @JoinColumn(name = "zone_info_id")) })
public class GirlLocation implements Serializable {

	private static final long serialVersionUID = 1L;
	private GirlLocationId primaryKey = new GirlLocationId();

	@EmbeddedId
	public GirlLocationId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public GirlInfo getGirlInfo() {
		return getPrimaryKey().getGirlInfo();
	}
	@Transient
	public ZoneInfo getZoneInfo() {
		return getPrimaryKey().getZoneInfo();
	}
	public void setPrimaryKey(GirlLocationId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		getPrimaryKey().setGirlInfo(girlInfo);
	}
	public void setZoneInfo(ZoneInfo zoneInfo) {
		getPrimaryKey().setZoneInfo(zoneInfo);
	}
}
