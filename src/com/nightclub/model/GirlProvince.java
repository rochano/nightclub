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
@Table(name = "girl_province")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.girlInfo",
        joinColumns = @JoinColumn(name = "girl_info_id")),
    @AssociationOverride(name = "primaryKey.provinceInfo",
        joinColumns = @JoinColumn(name = "province_info_id")) })
public class GirlProvince implements Serializable {

	private static final long serialVersionUID = 1L;
	private GirlProvinceId primaryKey = new GirlProvinceId();

	@EmbeddedId
	public GirlProvinceId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public GirlInfo getGirlInfo() {
		return getPrimaryKey().getGirlInfo();
	}
	@Transient
	public ProvinceInfo getProvinceInfo() {
		return getPrimaryKey().getProvinceInfo();
	}
	public void setPrimaryKey(GirlProvinceId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		getPrimaryKey().setGirlInfo(girlInfo);
	}
	public void setProvinceInfo(ProvinceInfo provinceInfo) {
		getPrimaryKey().setProvinceInfo(provinceInfo);
	}
}
