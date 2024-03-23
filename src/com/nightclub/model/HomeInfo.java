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
	private String howToInputShopService;
	private String howToInputAgent;
	private String howToInputFreeAgent;
	private String howToInputEnGirl;
	private String lineClientId;
	private String lineClientSecret;
	private String lineRedirectUrl;
	private String lineNotifyActive;
	private String lineChannelAccessToken;
	private String inform;
	private String descriptionShopService;
	private String descriptionAgent;
	private String descriptionFreeAgent;
	private String descriptionEnGirl;
	private String contact;
	
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
	@Column(name="how_to_input_shop_service")
	@Length(max=1000)
	public String getHowToInputShopService() {
		return howToInputShopService;
	}
	@Column(name="how_to_input_agent")
	@Length(max=1000)
	public String getHowToInputAgent() {
		return howToInputAgent;
	}
	@Column(name="how_to_input_free_agent")
	@Length(max=1000)
	public String getHowToInputFreeAgent() {
		return howToInputFreeAgent;
	}
	@Column(name="how_to_input_en_girl")
	@Length(max=1000)
	public String getHowToInputEnGirl() {
		return howToInputEnGirl;
	}
	@Column(name="inform")
	@Length(max=1000)
	public String getInform() {
		if (inform == null) {
			return "";
		}
		return inform;
	}
	@Column(name="description_shop_service")
	@Length(max=1000)
	public String getDescriptionShopService() {
		return descriptionShopService;
	}
	@Column(name="description_agent")
	@Length(max=1000)
	public String getDescriptionAgent() {
		return descriptionAgent;
	}
	@Column(name="description_free_agent")
	@Length(max=1000)
	public String getDescriptionFreeAgent() {
		return descriptionFreeAgent;
	}
	@Column(name="description_en_girl")
	@Length(max=1000)
	public String getDescriptionEnGirl() {
		return descriptionEnGirl;
	}
	@Column(name="line_channel_access_token")
	@Length(max=1000)
	public String getLineChannelAccessToken() {
		return lineChannelAccessToken;
	}
	@Column(name="contact")
	@Length(max=1000)
	public String getContact() {
		if (contact == null) {
			return "";
		}
		return contact;
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
	public void setHowToInputShopService(String howToInputShopService) {
		this.howToInputShopService = howToInputShopService;
	}
	public void setHowToInputAgent(String howToInputAgent) {
		this.howToInputAgent = howToInputAgent;
	}
	public void setHowToInputFreeAgent(String howToInputFreeAgent) {
		this.howToInputFreeAgent = howToInputFreeAgent;
	}
	public void setHowToInputEnGirl(String howToInputEnGirl) {
		this.howToInputEnGirl = howToInputEnGirl;
	}
	public String getLineClientId() {
		return lineClientId;
	}
	public String getLineClientSecret() {
		return lineClientSecret;
	}
	public void setLineClientId(String lineClientId) {
		this.lineClientId = lineClientId;
	}
	public void setLineClientSecret(String lineClientSecret) {
		this.lineClientSecret = lineClientSecret;
	}
	public String getLineRedirectUrl() {
		return lineRedirectUrl;
	}
	public void setLineRedirectUrl(String lineRedirectUrl) {
		this.lineRedirectUrl = lineRedirectUrl;
	}
	public String getLineNotifyActive() {
		return lineNotifyActive;
	}
	public void setLineNotifyActive(String lineNotifyActive) {
		this.lineNotifyActive = lineNotifyActive;
	}
	public void setInform(String inform) {
		this.inform = inform;
	}
	public void setDescriptionShopService(String descriptionShopService) {
		this.descriptionShopService = descriptionShopService;
	}
	public void setDescriptionAgent(String descriptionAgent) {
		this.descriptionAgent = descriptionAgent;
	}
	public void setDescriptionFreeAgent(String descriptionFreeAgent) {
		this.descriptionFreeAgent = descriptionFreeAgent;
	}
	public void setDescriptionEnGirl(String descriptionEnGirl) {
		this.descriptionEnGirl = descriptionEnGirl;
	}
	public void setLineChannelAccessToken(String lineChannelAccessToken) {
		this.lineChannelAccessToken = lineChannelAccessToken;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
