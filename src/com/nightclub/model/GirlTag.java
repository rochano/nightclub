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
@Table(name = "girl_tag")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.girlInfo",
        joinColumns = @JoinColumn(name = "girl_info_id")),
    @AssociationOverride(name = "primaryKey.girlTagInfo",
        joinColumns = @JoinColumn(name = "girl_tag_info_id")) })
public class GirlTag implements Serializable {

	private static final long serialVersionUID = 1L;
	private GirlTagId primaryKey = new GirlTagId();

	@EmbeddedId
	public GirlTagId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public GirlInfo getGirlInfo() {
		return getPrimaryKey().getGirlInfo();
	}
	@Transient
	public GirlTagInfo getGirlTagInfo() {
		return getPrimaryKey().getGirlTagInfo();
	}
	public void setPrimaryKey(GirlTagId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		getPrimaryKey().setGirlInfo(girlInfo);;
	}
	public void setGirlTagInfo(GirlTagInfo girlTagInfo) {
		getPrimaryKey().setGirlTagInfo(girlTagInfo);
	}
}
