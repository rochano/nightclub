package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="skin_info")
public class SkinInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String skinInfoId;
	private String skinNameEn;
	private String skinNameJp;

	@Id
	@Column(name="skin_info_id")
	@Length(max=40)
	public String getSkinInfoId() {
		return skinInfoId;
	}

	@Column(name="skin_name_en")
	public String getSkinNameEn() {
		return skinNameEn;
	}
	@Column(name="skin_name_jp")
	public String getSkinNameJp() {
		return skinNameJp;
	}
	public void setSkinInfoId(String skinInfoId) {
		this.skinInfoId = skinInfoId;
	}
	public void setSkinNameEn(String skinNameEn) {
		this.skinNameEn = skinNameEn;
	}
	public void setSkinNameJp(String skinNameJp) {
		this.skinNameJp = skinNameJp;
	}
}
