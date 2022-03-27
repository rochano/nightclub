package com.nightclub.model;

import java.io.Serializable;
import java.util.List;

public class FrontSearch implements Serializable {

	private static final long serialVersionUID = -6584035519739513456L;
	
	private String chkCategory;
	private String categoryInfoId;
	private String chkAgents;
	private String agentInfoId;
	private String chkFreeAgents;
	private String chkEnGirls;
	private String gender;
	private List<String> zoneInfos;
	private String chkIncallOutcall;
	private String incallOutcall;

	public String getChkCategory() {
		return chkCategory;
	}
	public void setChkCategory(String chkCategory) {
		this.chkCategory = chkCategory;
	}
	public String getCategoryInfoId() {
		return categoryInfoId;
	}
	public void setCategoryInfoId(String categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}
	public String getChkAgents() {
		return chkAgents;
	}
	public void setChkAgents(String chkAgents) {
		this.chkAgents = chkAgents;
	}
	public String getAgentInfoId() {
		return agentInfoId;
	}
	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
	}
	public String getChkFreeAgents() {
		return chkFreeAgents;
	}
	public void setChkFreeAgents(String chkFreeAgents) {
		this.chkFreeAgents = chkFreeAgents;
	}
	public String getChkEnGirls() {
		return chkEnGirls;
	}
	public void setChkEnGirls(String chkEnGirls) {
		this.chkEnGirls = chkEnGirls;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<String> getZoneInfos() {
		return zoneInfos;
	}
	public void setZoneInfos(List<String> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}
	public String getChkIncallOutcall() {
		return chkIncallOutcall;
	}
	public void setChkIncallOutcall(String chkIncallOutcall) {
		this.chkIncallOutcall = chkIncallOutcall;
	}
	public String getIncallOutcall() {
		return incallOutcall;
	}
	public void setIncallOutcall(String incallOutcall) {
		this.incallOutcall = incallOutcall;
	}
	
}
