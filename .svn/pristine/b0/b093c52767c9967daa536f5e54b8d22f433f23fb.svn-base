package com.nightclub.view;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.EventInfoManager;
import com.nightclub.model.EventInfo;
import com.nightclub.model.EventSearch;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class EventInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private List<EventInfo> eventInfos;
	private EventInfo eventInfo;
	private EventSearch eventSearch;
	private String eventInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private EventInfoManager eventInfoManager;

	public EventInfoAction() {
		eventInfoManager = new EventInfoManager();
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
		
		this.eventInfos = eventInfoManager.list(userInfo.getShopInfoId());
		
		return SUCCESS;
	}

	public String add() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			eventInfo.setEventInfoId(UUID.randomUUID().toString().toUpperCase());
			eventInfo.setShopInfoId(userInfo.getShopInfoId());
			
			eventInfoManager.add(this.eventInfo);
			
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
			eventInfo.setShopInfoId(userInfo.getShopInfoId());
			
			eventInfoManager.update(this.eventInfo);
			
			addActionMessage("You have been successfully updated");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.eventInfo = eventInfoManager.getEventInfo(this.eventInfoId);
		this.showInfo = true;
		this.eventInfos = eventInfoManager.list(userInfo.getShopInfoId());
		return SUCCESS;
	}

	public String delete() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		eventInfoManager.delete(getEventInfoId());
		addActionMessage("You have been successfully deleted");
		this.eventInfos = eventInfoManager.list(userInfo.getShopInfoId());
		return SUCCESS;
	}
	
	public String search() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.eventSearch.setShopInfoId(userInfo.getShopInfoId());
		this.eventInfos = eventInfoManager.search(this.eventSearch);
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

	public List<EventInfo> getEventInfos() {
		return eventInfos;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public String getEventInfoId() {
		return eventInfoId;
	}

	public void setEventInfos(List<EventInfo> eventInfos) {
		this.eventInfos = eventInfos;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public void setEventInfoId(String eventInfoId) {
		this.eventInfoId = eventInfoId;
	}

	public EventSearch getEventSearch() {
		return eventSearch;
	}

	public void setEventSearch(EventSearch eventSearch) {
		this.eventSearch = eventSearch;
	}
}
