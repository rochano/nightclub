package com.nightclub.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.UserInfo;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AdminInfoAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());

	private Map<String, Object> sessionMap;
	private String menu;
	private HomeInfo homeInfo;
	private List<BasicInfo> basicInfos;
	private List<String> activelist;
	
	private HomeInfoManager homeInfoManager;
	private BasicInfoManager basicInfoManager;
	
	public AdminInfoAction() {
		homeInfoManager = new HomeInfoManager();
		basicInfoManager = new BasicInfoManager();
	}
	
	public String execute() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}
	
	public String update() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			homeInfo.setHomeInfoId("0");
			homeInfo.setDescription(UploadFileUtils.uploadImageinDescription(homeInfo.getDescription(), sessionMap, userInfo));
			homeInfo.setDescription2(UploadFileUtils.uploadImageinDescription(homeInfo.getDescription2(), sessionMap, userInfo));
			
			if(homeInfoManager.getHomeInfo("0") != null) {
				homeInfoManager.update(homeInfo);
			} else {
				homeInfoManager.add(homeInfo);
			}
			
			addActionMessage("You have been successfully updated");
			
			this.execute();
			
			return SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String shoplist() {

		
		this.basicInfos = basicInfoManager.list();
		
		return SUCCESS;
	}
	
	public String shopupdate() {
		
		if(getActivelist() == null) {
			setActivelist(new ArrayList<String>());
		}

		basicInfoManager.activeByShopInfoId(getActivelist());
		this.basicInfos = basicInfoManager.list();
		
		return SUCCESS;
	}
	
	public String getMenu() {
		return menu;
	}

	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public void setHomeInfo(HomeInfo homeInfo) {
		this.homeInfo = homeInfo;
	}

	public List<BasicInfo> getBasicInfos() {
		return basicInfos;
	}

	public void setBasicInfos(List<BasicInfo> basicInfos) {
		this.basicInfos = basicInfos;
	}

	public List<String> getActivelist() {
		return activelist;
	}

	public void setActivelist(List<String> activelist) {
		this.activelist = activelist;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}


}
