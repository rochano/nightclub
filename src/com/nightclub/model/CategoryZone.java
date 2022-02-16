package com.nightclub.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "category_zone")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.categoryInfo",
        joinColumns = @JoinColumn(name = "category_info_id")),
    @AssociationOverride(name = "primaryKey.zoneInfo",
        joinColumns = @JoinColumn(name = "zone_info_id")) })
public class CategoryZone implements Serializable {

	private static final long serialVersionUID = 916427013034934939L;
	private CategoryZoneId primaryKey = new CategoryZoneId();

	@EmbeddedId
	public CategoryZoneId getPrimaryKey() {
		return primaryKey;
	}
	@Transient
	public CategoryInfo getCategoryInfo() {
		return getPrimaryKey().getCategoryInfo();
	}
	@Transient
	public ZoneInfo getZoneInfo() {
		return getPrimaryKey().getZoneInfo();
	}
	public void setPrimaryKey(CategoryZoneId primaryKey) {
		this.primaryKey = primaryKey;
	}
	public void setCategoryInfo(CategoryInfo categoryInfo) {
		getPrimaryKey().setCategoryInfo(categoryInfo);;
	}
	public void setZoneInfo(ZoneInfo zoneInfo) {
		getPrimaryKey().setZoneInfo(zoneInfo);
	}
	public void setOrderNo(BigInteger orderNo) {
		getPrimaryKey().setOrderNo(orderNo);
	}
}
