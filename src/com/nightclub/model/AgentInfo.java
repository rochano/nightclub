package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.Length;

@Entity
@Table(name="agent_info")
public class AgentInfo implements Serializable {
	
	private static final long serialVersionUID = 7406550732066083106L;
	
	private String agentInfoId;
	private String agentName;
	private String logoImg;
	private UserInfo userInfo;
	private String lineId;
	private String lineToken;
	private String telegramId;
	private String skypeId;
	private String twitterId;
	private String wechatId;
	private String whatsAppId;

	public AgentInfo() {}
	
	protected AgentInfo(AgentInfo agentInfo) {
		this.agentInfoId = agentInfo.agentInfoId;
		this.agentName = agentInfo.agentName;
		this.logoImg = agentInfo.logoImg;
	}
	
	@Id
	@Column(name="agent_info_id")
	@Length(max=40)
	public String getAgentInfoId() {
		return agentInfoId;
	}
	@Column(name="agent_name")
	public String getAgentName() {
		return agentName;
	}
	@Column(name="logo_img")
	public String getLogoImg() {
		return logoImg;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="agent_info_id", insertable=false, updatable=false)
	public UserInfo getUserInfo() {
		return userInfo;
	}
	@Column(name="line_id")
	public String getLineId() {
		return lineId;
	}
	@Column(name="line_token")
	public String getLineToken() {
		return lineToken;
	}
	@Column(name="telegram_id")
	public String getTelegramId() {
		if (telegramId == null) {
			return "";
		}
		return telegramId;
	}
	@Column(name="skype_id")
	public String getSkypeId() {
		if (skypeId == null) {
			return "";
		}
		return skypeId;
	}
	@Column(name="twitter_id")
	public String getTwitterId() {
		if (twitterId == null) {
			return "";
		}
		return twitterId;
	}
	@Column(name="wechat_id")
	public String getWechatId() {
		if (wechatId == null) {
			return "";
		}
		return wechatId;
	}
	@Column(name="whatsapp_id")
	public String getWhatsAppId() {
		if (whatsAppId == null) {
			return "";
		}
		return whatsAppId;
	}
	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public AgentInfo clone() {
		return new AgentInfo(this);
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public void setLineToken(String lineToken) {
		this.lineToken = lineToken;
	}
	public void setTelegramId(String telegramId) {
		this.telegramId = telegramId;
	}
	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public void setWhatsAppId(String whatsAppId) {
		this.whatsAppId = whatsAppId;
	}
}
