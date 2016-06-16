package com.nightclub.view;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.ScheduleInfoManager;
import com.nightclub.controller.SystemInfoManager;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.ScheduleInfo;
import com.nightclub.model.SystemInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class SystemInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private List<SystemInfo> systemInfos;
	private SystemInfo systemInfo;
	private SystemInfo systemSearch;
	private String systemInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private SystemInfoManager systemInfoManager;

	public SystemInfoAction() {
		systemInfoManager = new SystemInfoManager();
	}
	
	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		if(getAction() != null) {
			if(getAction().equals("add")) {
				add();
			} else if(getAction().equals("update")) {
				update();
			}
		}
		
		this.systemInfos = systemInfoManager.list(userInfo.getShopInfoId());
		
		return SUCCESS;
	}

	public String add() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			systemInfo.setSystemInfoId(UUID.randomUUID().toString().toUpperCase());
			systemInfo.setShopInfoId(userInfo.getShopInfoId());
			systemInfoManager.add(this.systemInfo);
			
			addActionMessage("You have been successfully inserted");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			systemInfo.setShopInfoId(userInfo.getShopInfoId());
			systemInfoManager.update(this.systemInfo);
			
			addActionMessage("You have been successfully updated");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.systemInfo = systemInfoManager.getSystemInfo(this.systemInfoId);
		this.showInfo = true;
		this.systemInfos = systemInfoManager.list(userInfo.getShopInfoId());
		return SUCCESS;
	}

	public String delete() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		systemInfoManager.delete(getSystemInfoId());
		addActionMessage("You have been successfully deleted");
		this.systemInfos = systemInfoManager.list(userInfo.getShopInfoId());
		return SUCCESS;
	}
	
	public String search() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.systemSearch.setShopInfoId(userInfo.getShopInfoId());
		this.systemInfos = systemInfoManager.search(this.systemSearch);
		return SUCCESS;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
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

	public List<SystemInfo> getSystemInfos() {
		return systemInfos;
	}

	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfos(List<SystemInfo> systemInfos) {
		this.systemInfos = systemInfos;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}

	public String getSystemInfoId() {
		return systemInfoId;
	}

	public void setSystemInfoId(String systemInfoId) {
		this.systemInfoId = systemInfoId;
	}

	public SystemInfo getSystemSearch() {
		return systemSearch;
	}

	public void setSystemSearch(SystemInfo systemSearch) {
		this.systemSearch = systemSearch;
	}


}
