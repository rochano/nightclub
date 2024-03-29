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
	private String genderInfoId;
	private List<String> zoneInfos;
	private String chkIncallOutcall;
	private String incallOutcall;
	private String nickName;
	private List<String> nationalityInfos;
	private String countryInfoId;
	private List<String> provinceInfos;
	private int searchRandom;
	private String countryClassification;
	private String countryInfoIdThai;

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
	public String getGenderInfoId() {
		return genderInfoId;
	}
	public void setGenderInfoId(String genderInfoId) {
		this.genderInfoId = genderInfoId;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public List<String> getNationalityInfos() {
		return nationalityInfos;
	}
	public void setNationalityInfos(List<String> nationalityInfos) {
		this.nationalityInfos = nationalityInfos;
	}
	public String getCountryInfoId() {
		return countryInfoId;
	}
	public List<String> getProvinceInfos() {
		return provinceInfos;
	}
	public void setCountryInfoId(String countryInfoId) {
		this.countryInfoId = countryInfoId;
	}
	public void setProvinceInfos(List<String> provinceInfos) {
		this.provinceInfos = provinceInfos;
	}
	public int getSearchRandom() {
		return searchRandom;
	}
	public void setSearchRandom(int searchRandom) {
		this.searchRandom = searchRandom;
	}
	public String getCountryClassification() {
		return countryClassification;
	}
	public void setCountryClassification(String countryClassification) {
		this.countryClassification = countryClassification;
	}
	public String getCountryInfoIdThai() {
		return countryInfoIdThai;
	}
	public void setCountryInfoIdThai(String countryInfoIdThai) {
		this.countryInfoIdThai = countryInfoIdThai;
	}
	
}
