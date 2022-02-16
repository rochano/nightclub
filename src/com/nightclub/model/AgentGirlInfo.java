package com.nightclub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="agent_girl_info")
public class AgentGirlInfo extends FreeAgentGirlInfo {
	private static final long serialVersionUID = -1930927867933854705L;
	private String agentInfoId;
	private AgentInfo agentInfo;

	@Column(name="agent_info_id")
	public String getAgentInfoId() {
		return agentInfoId;
	}
	@OneToOne
    @JoinColumn(name="agent_info_id", insertable=false, updatable=false)
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}
	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
	}
	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}
}
