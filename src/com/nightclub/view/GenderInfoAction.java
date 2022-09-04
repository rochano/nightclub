package com.nightclub.view;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.GenderInfoManager;
import com.nightclub.model.GenderInfo;
import com.opensymphony.xwork2.ActionSupport;

public class GenderInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<GenderInfo> genderInfos;
	private GenderInfo genderInfo;
	private GenderInfo genderSearch;
	private String genderInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private GenderInfoManager genderInfoManager;

	public GenderInfoAction() {
		genderInfoManager = new GenderInfoManager();
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
		
		this.genderInfos = genderInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			genderInfo.setGenderInfoId(UUID.randomUUID().toString().toUpperCase());
			genderInfoManager.add(this.genderInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			genderInfoManager.update(this.genderInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.genderInfo = genderInfoManager.getGenderInfo(this.genderInfoId);
		this.showInfo = true;
		this.genderInfos = genderInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!genderInfoManager.isRelatedGirlInfo(getGenderInfoId())) {
			genderInfoManager.delete(getGenderInfoId());
			addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getTexts("global_th").getString("global.message_gender_delete_fail"));
			result = INPUT;
		}
		this.genderInfos = genderInfoManager.list();
		return result;
	}
	
	public String search() {
		this.genderInfos = genderInfoManager.search(this.genderSearch);
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

	public List<GenderInfo> getGenderInfos() {
		return genderInfos;
	}

	public GenderInfo getGenderInfo() {
		return genderInfo;
	}

	public void setGenderInfos(List<GenderInfo> genderInfos) {
		this.genderInfos = genderInfos;
	}

	public void setGenderInfo(GenderInfo genderInfo) {
		this.genderInfo = genderInfo;
	}

	public String getGenderInfoId() {
		return genderInfoId;
	}

	public void setGenderInfoId(String genderInfoId) {
		this.genderInfoId = genderInfoId;
	}

	public GenderInfo getGenderSearch() {
		return genderSearch;
	}

	public void setGenderSearch(GenderInfo genderSearch) {
		this.genderSearch = genderSearch;
	}


}
