package com.nightclub.view;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.CountryInfoManager;
import com.nightclub.controller.ProvinceInfoManager;
import com.nightclub.model.CountryInfo;
import com.nightclub.model.ProvinceInfo;
import com.opensymphony.xwork2.ActionSupport;

public class ProvinceInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());

	private ProvinceInfo provinceInfo;
	private ProvinceInfo provinceSearch;
	private String provinceInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private List<CountryInfo> countryInfos;
	private List<ProvinceInfo> provinceInfos;
	private String countryInfoId;
	
	private ProvinceInfoManager provinceInfoManager;
	private CountryInfoManager countryInfoManager;

	public ProvinceInfoAction() {
		provinceInfoManager = new ProvinceInfoManager();
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
		
		this.provinceInfos = provinceInfoManager.list();
		this.countryInfos = countryInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			provinceInfo.setProvinceInfoId(UUID.randomUUID().toString().toUpperCase());
			provinceInfoManager.add(this.provinceInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			provinceInfoManager.update(this.provinceInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.provinceInfo = provinceInfoManager.getProvinceInfo(this.provinceInfoId);
		this.showInfo = true;
		this.provinceInfos = provinceInfoManager.list();
		this.countryInfos = countryInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!provinceInfoManager.isRelatedGirlInfo(getProvinceInfoId())) {
			provinceInfoManager.delete(getProvinceInfoId());
			addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getTexts("global_th").getString("global.message_province_delete_fail"));
			result = INPUT;
		}
		this.provinceInfos = provinceInfoManager.list();
		this.countryInfos = countryInfoManager.list();
		return result;
	}
	
	public String search() {
		this.provinceInfos = provinceInfoManager.search(this.provinceSearch);
		this.countryInfos = countryInfoManager.list();
		return SUCCESS;
	}
	
	public String loadProvinceByCountry() {
		this.provinceInfos = provinceInfoManager.listByCountry(this.countryInfoId);
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

	public List<ProvinceInfo> getProvinceInfos() {
		return provinceInfos;
	}

	public ProvinceInfo getProvinceInfo() {
		return provinceInfo;
	}

	public void setProvinceInfos(List<ProvinceInfo> provinceInfos) {
		this.provinceInfos = provinceInfos;
	}

	public void setProvinceInfo(ProvinceInfo provinceInfo) {
		this.provinceInfo = provinceInfo;
	}

	public String getProvinceInfoId() {
		return provinceInfoId;
	}

	public void setProvinceInfoId(String provinceInfoId) {
		this.provinceInfoId = provinceInfoId;
	}

	public ProvinceInfo getProvinceSearch() {
		return provinceSearch;
	}

	public void setProvinceSearch(ProvinceInfo provinceSearch) {
		this.provinceSearch = provinceSearch;
	}

	public List<CountryInfo> getCountryInfos() {
		return countryInfos;
	}

	public void setCountryInfos(List<CountryInfo> countryInfos) {
		this.countryInfos = countryInfos;
	}

	public String getCountryInfoId() {
		return countryInfoId;
	}

	public void setCountryInfoId(String countryInfoId) {
		this.countryInfoId = countryInfoId;
	}

}
