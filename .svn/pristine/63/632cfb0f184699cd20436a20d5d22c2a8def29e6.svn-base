package com.nightclub.view;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.MapInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.MapInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class MapInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private MapInfo mapInfo;
	private BasicInfo basicInfo;
	private String menu;
	
	private BasicInfoManager basicInfoManager;
	private MapInfoManager mapInfoManager;
	private UserInfoManager userInfoManager;

	public MapInfoAction() {
		basicInfoManager = new BasicInfoManager();
		mapInfoManager = new MapInfoManager();
		userInfoManager = new UserInfoManager();
	}

	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.basicInfo = basicInfoManager.getBasicInfo(userInfo.getShopInfoId());
		this.mapInfo = mapInfoManager.getMapInfo(userInfo.getShopInfoId());
		
		return SUCCESS;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.basicInfo = basicInfoManager.getBasicInfo(userInfo.getShopInfoId());
		this.mapInfo.setShopInfoId(userInfo.getShopInfoId());
		
		if(mapInfoManager.getMapInfo(userInfo.getShopInfoId()) != null) {
			this.mapInfo.setShopInfoId(userInfo.getShopInfoId());
			this.mapInfo = mapInfoManager.update(this.mapInfo);
		} else {
			if(userInfo.getShopInfoId() != null) {
				this.mapInfo.setShopInfoId(userInfo.getShopInfoId());
			} else {
				this.mapInfo.setShopInfoId(UUID.randomUUID().toString().toUpperCase());
				userInfo.setShopInfoId(this.mapInfo.getShopInfoId());
				userInfo = userInfoManager.update(userInfo);
			}
			
			this.mapInfo = mapInfoManager.add(this.mapInfo);
		}
		
		addActionMessage("You have been successfully updated");
		
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

	public MapInfo getMapInfo() {
		return mapInfo;
	}

	public void setMapInfo(MapInfo mapInfo) {
		this.mapInfo = mapInfo;
	}

	public BasicInfo getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
}
