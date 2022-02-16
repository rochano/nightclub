package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="agent_info")
public class AgentInfo implements Serializable {
	
	private static final long serialVersionUID = 7406550732066083106L;
	
	private String agentInfoId;
	private String agentName;
	private String logoImg;
	
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
	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public AgentInfo clone() {
		return new AgentInfo(this);
	}
}
