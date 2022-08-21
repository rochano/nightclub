package com.nightclub.view;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.CountryInfoManager;
import com.nightclub.model.CountryInfo;
import com.opensymphony.xwork2.ActionSupport;

public class CountryInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<CountryInfo> countryInfos;
	private CountryInfo countryInfo;
	private CountryInfo countrySearch;
	private String countryInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private CountryInfoManager countryInfoManager;

	public CountryInfoAction() {
		countryInfoManager = new CountryInfoManager();
	}
	
	public String execute() {
		log_.info("getAction() >>> " + getAction());
		if(getAction() != null) {
			if(getAction().equals("add")) {
				add();
			} else if(getAction().equals("update")) {
				update();
			}
		}
		
		this.countryInfos = countryInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			countryInfo.setCountryInfoId(UUID.randomUUID().toString().toUpperCase());
			countryInfoManager.add(this.countryInfo);
			
			addActionMessage(getText("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			countryInfoManager.update(this.countryInfo);
			
			addActionMessage(getText("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.countryInfo = countryInfoManager.getCountryInfo(this.countryInfoId);
		this.showInfo = true;
		this.countryInfos = countryInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!countryInfoManager.isRelatedProvince(getCountryInfoId())) {
			countryInfoManager.delete(getCountryInfoId());
			addActionMessage(getText("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getText("global.message_country_delete_fail"));
			result = INPUT;
		}
		this.countryInfos = countryInfoManager.list();
		return result;
	}
	
	public String search() {
		this.countryInfos = countryInfoManager.search(this.countrySearch);
		return SUCCESS;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isShowInfo() {
		return showInfo;
	}

	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}

	public List<CountryInfo> getCountryInfos() {
		return countryInfos;
	}

	public CountryInfo getCountryInfo() {
		return countryInfo;
	}

	public void setCountryInfos(List<CountryInfo> countryInfos) {
		this.countryInfos = countryInfos;
	}

	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}

	public String getCountryInfoId() {
		return countryInfoId;
	}

	public void setCountryInfoId(String countryInfoId) {
		this.countryInfoId = countryInfoId;
	}

	public CountryInfo getCountrySearch() {
		return countrySearch;
	}

	public void setCountrySearch(CountryInfo countrySearch) {
		this.countrySearch = countrySearch;
	}


}
