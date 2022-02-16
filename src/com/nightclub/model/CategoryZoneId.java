package com.nightclub.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Embeddable
public class CategoryZoneId implements Serializable { 
	
	private static final long serialVersionUID = 6735955327727345394L;
	private CategoryInfo categoryInfo;
	private ZoneInfo zoneInfo;
	private BigInteger orderNo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public ZoneInfo getZoneInfo() {
		return zoneInfo;
	}
	@Column(name = "order_no")
	public BigInteger getOrderNo() {
		return orderNo;
	}
	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}
	public void setZoneInfo(ZoneInfo zoneInfo) {
		this.zoneInfo = zoneInfo;
	}
	public void setOrderNo(BigInteger orderNo) {
		this.orderNo = orderNo;
	}

}
