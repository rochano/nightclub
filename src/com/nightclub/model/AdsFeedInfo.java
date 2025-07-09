package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="ads_feed_info")
public class AdsFeedInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String adsFeedInfoId;
	private String title;
	private String meta;
	private String description;
	private String adsImg1;
	private String link;
	private String buttonLabel;
	private String active;

	@Id
	@Column(name="ads_feed_info_id")
	@Length(max=40)
	public String getAdsFeedInfoId() {
		return adsFeedInfoId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	@Column(name="meta")
	public String getMeta() {
		return meta;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	@Column(name="ads_img_1")
	public String getAdsImg1() {
		if (adsImg1 == null) {
			return "";
		}
		return adsImg1;
	}
	@Column(name="link")
	public String getLink() {
		if (link == null) {
			return "";
		}
		return link;
	}
	@Column(name="buttonLabel")
	public String getButtonLabel() {
		if (buttonLabel == null) {
			return "";
		}
		return buttonLabel;
	}
	@Column(name="active")
	public String getActive() {
		return active;
	}
	public void setAdsFeedInfoId(String adsFeedInfoId) {
		this.adsFeedInfoId = adsFeedInfoId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setAdsImg1(String adsImg1) {
		this.adsImg1 = adsImg1;
	}
	public void setButtonLabel(String buttonLabel) {
		this.buttonLabel = buttonLabel;
	}
	public void setActive(String active) {
		this.active = active;
	}
}
