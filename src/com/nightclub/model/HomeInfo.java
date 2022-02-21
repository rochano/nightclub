package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="home_info")
public class HomeInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String homeInfoId;
	private String description;
	private String description2;
	private String descriptionEn;
	private String descriptionEn2;
	private String howToUse;
	private String lineContactUrl;
	
	@Id
	@Column(name="home_info_id")
	@Length(max=40)
	public String getHomeInfoId() {
		return homeInfoId;
	}
	@Column(name="description")
	@Length(max=1000)
	public String getDescription() {
		return description;
	}
	@Column(name="description2")
	@Length(max=1000)
	public String getDescription2() {
		return description2;
	}
	@Column(name="how_to_use")
	@Length(max=1000)
	public String getHowToUse() {
		return howToUse;
	}
	@Column(name="line_contact_url")
	@Length(max=40)
	public String getLineContactUrl() {
		return lineContactUrl;
	}
	@Column(name="description_en")
	@Length(max=1000)
	public String getDescriptionEn() {
		return descriptionEn;
	}
	@Column(name="description_en_2")
	@Length(max=1000)
	public String getDescriptionEn2() {
		return descriptionEn2;
	}
	public void setHomeInfoId(String homeInfoId) {
		this.homeInfoId = homeInfoId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public void setHowToUse(String howToUse) {
		this.howToUse = howToUse;
	}
	public void setLineContactUrl(String lineContactUrl) {
		this.lineContactUrl = lineContactUrl;
	}
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}
	public void setDescriptionEn2(String descriptionEn2) {
		this.descriptionEn2 = descriptionEn2;
	}
	
}
