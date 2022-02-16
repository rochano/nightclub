package com.nightclub.view;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.SystemInfoManager;
import com.nightclub.model.SystemInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class SystemInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private SystemInfo systemInfo;
	private SystemInfo systemSearch;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private String classType;
	private Double price1;
	private Double price2;
	private Double price3;
	
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
		
		this.systemInfo = systemInfoManager.getSystemInfo(userInfo.getShopInfoId());
		if (this.systemInfo == null) {
			this.systemInfo = new SystemInfo();
		}
		
		return SUCCESS;
	}

	public String add() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			systemInfo.setShopInfoId(userInfo.getShopInfoId());
//			systemInfo.setDescription(UploadFileUtils.uploadImageinDescription(systemInfo.getDescription(), sessionMap, userInfo));
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
			this.systemInfo = systemInfoManager.getSystemInfo(userInfo.getShopInfoId());
			if (this.systemInfo == null) {
				this.systemInfo = new SystemInfo();
			}
			systemInfo.setShopInfoId(userInfo.getShopInfoId());
//			systemInfo.setDescription(UploadFileUtils.uploadImageinDescription(systemInfo.getDescription(), sessionMap, userInfo));systemInfo.setDescription(UploadFileUtils.uploadImageinDescription(systemInfo.getDescription(), sessionMap, userInfo));
			if (getClassType().equals("Normal")) {
				systemInfo.setPriceNormal1(getPrice1());
				systemInfo.setPriceNormal2(getPrice2());
				systemInfo.setPriceNormal3(getPrice3());
			} else {
				systemInfo.setPriceVIP1(getPrice1());
				systemInfo.setPriceVIP2(getPrice3());
				systemInfo.setPriceVIP3(getPrice3());
			}
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
		this.systemInfo = systemInfoManager.getSystemInfo(userInfo.getShopInfoId());
		if( this.systemInfo == null) {
			this.systemInfo = new SystemInfo();
			this.systemInfo.setShopInfoId(userInfo.getShopInfoId());
		}
		this.showInfo = true;
		if (getClassType().equals("Normal")) {
			this.price1 = systemInfo.getPriceNormal1();
			this.price2 = systemInfo.getPriceNormal2();
			this.price3 = systemInfo.getPriceNormal3();
		} else {
			this.price1 = systemInfo.getPriceVIP1();
			this.price2 = systemInfo.getPriceVIP2();
			this.price3 = systemInfo.getPriceVIP3();
		}
		return SUCCESS;
	}

	public String delete() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		systemInfoManager.delete(userInfo.getShopInfoId());
		addActionMessage("You have been successfully deleted");
		this.systemInfo = systemInfoManager.getSystemInfo(userInfo.getShopInfoId());
		return SUCCESS;
	}
	
//	public String search() {
//		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
//		this.systemSearch.setShopInfoId(userInfo.getShopInfoId());
//		this.systemInfos = systemInfoManager.search(this.systemSearch);
//		return SUCCESS;
//	}

	public String systemsupdate() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");

		systemInfoManager.activeByClassType(userInfo.getShopInfoId(), getClassType());
		this.systemInfo = systemInfoManager.getSystemInfo(userInfo.getShopInfoId());
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

	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}

	public SystemInfo getSystemSearch() {
		return systemSearch;
	}

	public void setSystemSearch(SystemInfo systemSearch) {
		this.systemSearch = systemSearch;
	}

	public String getClassType() {
		if (classType == null) {
			return "Normal";
		} else {
			return classType;
		}
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Double getPrice1() {
		if (price1 == null) {
			return 0.0;
		} else {
			return price1;
		}
	}

	public Double getPrice2() {
		if (price2 == null) {
			return 0.0;
		} else {
			return price2;
		}
	}

	public Double getPrice3() {
		if (price3 == null) {
			return 0.0;
		} else {
			return price3;
		}
	}

	public void setPrice1(Double price1) {
		this.price1 = price1;
	}

	public void setPrice2(Double price2) {
		this.price2 = price2;
	}

	public void setPrice3(Double price3) {
		this.price3 = price3;
	}

}
