package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="girl_tag_info")
public class GirlTagInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String girlTagInfoId;
	private String girlTagNameEn;
	private String girlTagNameJp;
	private String color;

	@Id
	@Column(name="girl_tag_info_id")
	@Length(max=40)
	public String getGirlTagInfoId() {
		return girlTagInfoId;
	}
	@Column(name="girl_tag_name_en")
	public String getGirlTagNameEn() {
		return girlTagNameEn;
	}
	@Column(name="girl_tag_name_jp")
	public String getGirlTagNameJp() {
		return girlTagNameJp;
	}
	@Column(name="color")
	public String getColor() {
		return color;
	}
	public void setGirlTagInfoId(String girlTagInfoId) {
		this.girlTagInfoId = girlTagInfoId;
	}
	public void setGirlTagNameEn(String girlTagNameEn) {
		this.girlTagNameEn = girlTagNameEn;
	}
	public void setGirlTagNameJp(String girlTagNameJp) {
		this.girlTagNameJp = girlTagNameJp;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
