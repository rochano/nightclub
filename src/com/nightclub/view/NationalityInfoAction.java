package com.nightclub.view;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.NationalityInfoManager;
import com.nightclub.model.NationalityInfo;
import com.opensymphony.xwork2.ActionSupport;

public class NationalityInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<NationalityInfo> nationalityInfos;
	private NationalityInfo nationalityInfo;
	private NationalityInfo nationalitySearch;
	private String nationalityInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private NationalityInfoManager nationalityInfoManager;

	public NationalityInfoAction() {
		nationalityInfoManager = new NationalityInfoManager();
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
		
		this.nationalityInfos = nationalityInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			nationalityInfo.setNationalityInfoId(UUID.randomUUID().toString().toUpperCase());
			nationalityInfoManager.add(this.nationalityInfo);
			
			addActionMessage(getText("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			nationalityInfoManager.update(this.nationalityInfo);
			
			addActionMessage(getText("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.nationalityInfo = nationalityInfoManager.getNationalityInfo(this.nationalityInfoId);
		this.showInfo = true;
		this.nationalityInfos = nationalityInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!nationalityInfoManager.isRelatedGirlInfo(getNationalityInfoId())) {
			nationalityInfoManager.delete(getNationalityInfoId());
			addActionMessage(getText("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getText("global.message_nationality_delete_fail"));
			result = INPUT;
		}
		this.nationalityInfos = nationalityInfoManager.list();
		return result;
	}
	
	public String search() {
		this.nationalityInfos = nationalityInfoManager.search(this.nationalitySearch);
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

	public List<NationalityInfo> getNationalityInfos() {
		return nationalityInfos;
	}

	public NationalityInfo getNationalityInfo() {
		return nationalityInfo;
	}

	public void setNationalityInfos(List<NationalityInfo> nationalityInfos) {
		this.nationalityInfos = nationalityInfos;
	}

	public void setNationalityInfo(NationalityInfo nationalityInfo) {
		this.nationalityInfo = nationalityInfo;
	}

	public String getNationalityInfoId() {
		return nationalityInfoId;
	}

	public void setNationalityInfoId(String nationalityInfoId) {
		this.nationalityInfoId = nationalityInfoId;
	}

	public NationalityInfo getNationalitySearch() {
		return nationalitySearch;
	}

	public void setNationalitySearch(NationalityInfo nationalitySearch) {
		this.nationalitySearch = nationalitySearch;
	}


}
