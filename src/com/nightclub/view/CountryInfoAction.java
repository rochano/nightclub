package com.nightclub.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.cloudinary.json.JSONObject;

import com.nightclub.controller.CountryInfoManager;
import com.nightclub.model.CountryInfo;
import com.nightclub.model.ProvinceInfo;
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
	private List<String> activelist;
	private String countryInfoIdThai;
	private String countryClassification;

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
		
		if ("overseas".equals(getMenu())) {
			this.countryInfos = countryInfoManager.listOverseas(getCountryInfoIdThai());
		} else {
			this.countryInfos = countryInfoManager.list();
		}
		
		return SUCCESS;
	}

	public String add() {
		try {
			countryInfo.setCountryInfoId(UUID.randomUUID().toString().toUpperCase());
			countryInfoManager.add(this.countryInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			countryInfoManager.update(this.countryInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.countryInfo = countryInfoManager.getCountryInfo(this.countryInfoId);
		this.showInfo = true;
		if ("overseas".equals(getMenu())) {
			this.countryInfos = countryInfoManager.listOverseas(getCountryInfoIdThai());
		} else {
			this.countryInfos = countryInfoManager.list();
		}
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!countryInfoManager.isRelatedProvince(getCountryInfoId())) {
			countryInfoManager.delete(getCountryInfoId());
			addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getTexts("global_th").getString("global.message_country_delete_fail"));
			result = INPUT;
		}
		if ("overseas".equals(getMenu())) {
			this.countryInfos = countryInfoManager.listOverseas(getCountryInfoIdThai());
		} else {
			this.countryInfos = countryInfoManager.list();
		}
		return result;
	}
	
	public String search() {
		if ("overseas".equals(getMenu())) {
			this.countryInfos = countryInfoManager.searchOverseas(this.countrySearch, getCountryInfoIdThai());
		} else {
			this.countryInfos = countryInfoManager.search(this.countrySearch);
		}
		return SUCCESS;
	}

	public String active() {
		
		if(getActivelist() == null) {
			setActivelist(new ArrayList<String>());
		}

		Iterator<String> it = getActivelist().iterator();
		List<String> allCountryInfoIdList = new ArrayList<String>();
		List<String> availableCountryInfoIdList = new ArrayList<String>();
		while(it.hasNext()) {
			JSONObject jsonData = new JSONObject(it.next());
			allCountryInfoIdList.add(jsonData.getString("id"));
			if (jsonData.getBoolean("checked")) {
				availableCountryInfoIdList.add(jsonData.getString("id"));
			}
		}
		countryInfoManager.activeByCountryInfoId(allCountryInfoIdList, availableCountryInfoIdList);
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		return SUCCESS;
	}
	
	public String loadCountryByClassification() {
		if ("1".equals(getCountryClassification())) {
			this.countryInfos = new ArrayList();
			CountryInfo countryInfoThai = countryInfoManager.getCountryInfo(getCountryInfoIdThai());
			this.countryInfos.add(countryInfoThai);
		} else if ("2".equals(getCountryClassification())) {
			this.countryInfos = countryInfoManager.searchOverseas(new CountryInfo(), getCountryInfoIdThai());
		} else {
			this.countryInfos = new ArrayList();
		}
		Iterator it = this.countryInfos.iterator();
		CountryInfo countryInfo;
		while(it.hasNext()) {
			countryInfo = (CountryInfo) it.next();
			countryInfo.setProvinceInfos(new ArrayList());
		}
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

	public List<String> getActivelist() {
		return activelist;
	}

	public void setActivelist(List<String> activelist) {
		this.activelist = activelist;
	}
	
	public String getCountryInfoIdThai() {
		return countryInfoIdThai;
	}

	public void setCountryInfoIdThai(String countryInfoIdThai) {
		this.countryInfoIdThai = countryInfoIdThai;
	}

	public String getCountryClassification() {
		return countryClassification;
	}

	public void setCountryClassification(String countryClassification) {
		this.countryClassification = countryClassification;
	}

}
