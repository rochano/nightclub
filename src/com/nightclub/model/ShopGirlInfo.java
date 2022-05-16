package com.nightclub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="shop_girl_info")
public class ShopGirlInfo extends GirlInfo {
	private static final long serialVersionUID = 1L;
	private String shopInfoId;
	private String category;


	private BasicInfo basicInfo;

	@Column(name="shop_info_id")
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="category")
	public String getCategory() {
		return category;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="shop_info_id", referencedColumnName="shop_info_id", insertable=false, updatable=false)
	public BasicInfo getBasicInfo() {
		return basicInfo;
	}
	@Column(name="line_id", insertable=false, updatable=false)
	public String getLineId() {
		if (basicInfo == null) {
			return "";
		}
		return basicInfo.getLineId();
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
}
