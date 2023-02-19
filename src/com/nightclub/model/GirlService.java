package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "girl_service")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.freeAgentGirlInfo",
        joinColumns = @JoinColumn(name = "girl_info_id")),
    @AssociationOverride(name = "primaryKey.girlServiceInfo",
        joinColumns = @JoinColumn(name = "girl_service_info_id")) })
public class GirlService implements Serializable {

	private static final long serialVersionUID = 1L;
	private GirlServiceId primaryKey = new GirlServiceId();
	private String chkInclude;
	private String chkExtra;
	private Double priceExtra;
	private String crcy;

	@EmbeddedId
	public GirlServiceId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public FreeAgentGirlInfo getFreeAgentGirlInfo() {
		return getPrimaryKey().getFreeAgentGirlInfo();
	}
	@Transient
	public GirlServiceInfo getGirlServiceInfo() {
		return getPrimaryKey().getGirlServiceInfo();
	}
	@Column(name="chk_include")
	public String getChkInclude() {
		return chkInclude;
	}
	@Column(name="chk_extra")
	public String getChkExtra() {
		return chkExtra;
	}
	@Column(name="price_extra")
	public Double getPriceExtra() {
		if(priceExtra == null) {
			return 0.00;
		}
		return priceExtra;
	}
	@Column(name="crcy")
	public String getCrcy() {
		return crcy;
	}
	public void setPrimaryKey(GirlServiceId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setFreeAgentGirlInfo(FreeAgentGirlInfo freeAgentGirlInfo) {
		getPrimaryKey().setFreeAgentGirlInfo(freeAgentGirlInfo);;
	}
	public void setGirlServiceInfo(GirlServiceInfo girlServiceInfo) {
		getPrimaryKey().setGirlServiceInfo(girlServiceInfo);
	}
	public void setChkInclude(String chkInclude) {
		this.chkInclude = chkInclude;
	}
	public void setChkExtra(String chkExtra) {
		this.chkExtra = chkExtra;
	}
	public void setPriceExtra(Double priceExtra) {
		this.priceExtra = priceExtra;
	}
	public void setCrcy(String crcy) {
		this.crcy = crcy;
	}
}
