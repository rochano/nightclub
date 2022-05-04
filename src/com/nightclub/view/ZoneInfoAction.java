package com.nightclub.view;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.ZoneInfo;
import com.opensymphony.xwork2.ActionSupport;

public class ZoneInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<ZoneInfo> zoneInfos;
	private ZoneInfo zoneInfo;
	private ZoneInfo zoneSearch;
	private String zoneInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private ZoneInfoManager zoneInfoManager;

	public ZoneInfoAction() {
		zoneInfoManager = new ZoneInfoManager();
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
		
		this.zoneInfos = zoneInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			zoneInfo.setZoneInfoId(UUID.randomUUID().toString().toUpperCase());
			zoneInfoManager.add(this.zoneInfo);
			
			addActionMessage(getText("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			zoneInfoManager.update(this.zoneInfo);
			
			addActionMessage(getText("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.zoneInfo = zoneInfoManager.getZoneInfo(this.zoneInfoId);
		this.showInfo = true;
		this.zoneInfos = zoneInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!zoneInfoManager.isRelatedCategory(getZoneInfoId())) {
			zoneInfoManager.delete(getZoneInfoId());
			addActionMessage(getText("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getText("global.message_location_delete_fail"));
			result = INPUT;
		}
		this.zoneInfos = zoneInfoManager.list();
		return result;
	}
	
	public String search() {
		this.zoneInfos = zoneInfoManager.search(this.zoneSearch);
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

	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
	}

	public ZoneInfo getZoneInfo() {
		return zoneInfo;
	}

	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}

	public void setZoneInfo(ZoneInfo zoneInfo) {
		this.zoneInfo = zoneInfo;
	}

	public String getZoneInfoId() {
		return zoneInfoId;
	}

	public void setZoneInfoId(String zoneInfoId) {
		this.zoneInfoId = zoneInfoId;
	}

	public ZoneInfo getZoneSearch() {
		return zoneSearch;
	}

	public void setZoneSearch(ZoneInfo zoneSearch) {
		this.zoneSearch = zoneSearch;
	}


}
