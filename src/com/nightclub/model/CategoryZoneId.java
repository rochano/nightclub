package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class CategoryZoneId implements Serializable { 
	
	private static final long serialVersionUID = 6735955327727345394L;
	private CategoryInfo categoryInfo;
	private ZoneInfo zoneInfo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public ZoneInfo getZoneInfo() {
		return zoneInfo;
	}
	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}
	public void setZoneInfo(ZoneInfo zoneInfo) {
		this.zoneInfo = zoneInfo;
	}
	

}
