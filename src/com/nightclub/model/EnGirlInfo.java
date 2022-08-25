package com.nightclub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="en_girl_info")
public class EnGirlInfo extends GirlInfo {
	private static final long serialVersionUID = -1930927867933854705L;
	private Double price;
	private String skinInfoId;
	private String type;
	
	private SkinInfo skinInfo;
	private UserInfo userInfo;

	@Column(name="price")
	public Double getPrice() {
		if (price == null) {
			return 0.0;
		} else {
			return price;
		}
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	@Column(name="skin_info_id")
	public String getSkinInfoId() {
		return skinInfoId;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="skin_info_id", referencedColumnName="skin_info_id", insertable=false, updatable=false)
	public SkinInfo getSkinInfo() {
		return skinInfo;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setPrice(Double price) {
		this.price= price;
	}
	public void setSkinInfoId(String skinInfoId) {
		this.skinInfoId = skinInfoId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setSkinInfo(SkinInfo skinInfo) {
		this.skinInfo = skinInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
