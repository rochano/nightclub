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
@Table(name = "shop_location")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.basicInfo",
        joinColumns = @JoinColumn(name = "shop_info_id")),
    @AssociationOverride(name = "primaryKey.zoneInfo",
        joinColumns = @JoinColumn(name = "zone_info_id")) })
public class ShopLocation implements Serializable {

	private static final long serialVersionUID = 1L;
	private ShopLocationId primaryKey = new ShopLocationId();

	@EmbeddedId
	public ShopLocationId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public BasicInfo getBasicInfo() {
		return getPrimaryKey().getBasicInfo();
	}
	@Transient
	public ZoneInfo getZoneInfo() {
		return getPrimaryKey().getZoneInfo();
	}
	public void setPrimaryKey(ShopLocationId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setBasicInfo(BasicInfo basicInfo) {
		getPrimaryKey().setBasicInfo(basicInfo);
	}
	public void setZoneInfo(ZoneInfo zoneInfo) {
		getPrimaryKey().setZoneInfo(zoneInfo);
	}
}
