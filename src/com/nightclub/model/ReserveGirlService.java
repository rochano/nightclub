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
@Table(name = "reserve_girl_service")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.reserveInfo",
        joinColumns = @JoinColumn(name = "reserve_info_id")),
    @AssociationOverride(name = "primaryKey.girlServiceInfo",
        joinColumns = @JoinColumn(name = "girl_service_info_id")) })
public class ReserveGirlService implements Serializable {
	private static final long serialVersionUID = 1L;
	private ReserveGirlServiceId primaryKey = new ReserveGirlServiceId();

	@EmbeddedId
	public ReserveGirlServiceId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public ReserveInfo getReserveInfo() {
		return getPrimaryKey().getReserveInfo();
	}
	@Transient
	public GirlServiceInfo getGirlServiceInfo() {
		return getPrimaryKey().getGirlServiceInfo();
	}
	public void setPrimaryKey(ReserveGirlServiceId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setReserveInfo(ReserveInfo reserveInfo) {
		getPrimaryKey().setReserveInfo(reserveInfo);;
	}
	public void setGirlServiceInfo(GirlServiceInfo girlServiceInfo) {
		getPrimaryKey().setGirlServiceInfo(girlServiceInfo);
	}
}
