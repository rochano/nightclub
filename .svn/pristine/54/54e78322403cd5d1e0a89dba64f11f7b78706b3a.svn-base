package com.nightclub.view;

import com.nightclub.controller.HomeInfoManager;
import com.nightclub.model.HomeInfo;
import com.opensymphony.xwork2.ActionSupport;

public class AdminInfoAction extends ActionSupport  {

	private static final long serialVersionUID = 1L;

	private String menu;
	private HomeInfo homeInfo;
	
	private HomeInfoManager homeInfoManager;
	
	public AdminInfoAction() {
		homeInfoManager = new HomeInfoManager();
	}
	
	public String execute() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}
	
	public String update() {
		homeInfo.setHomeInfoId("0");
		if(homeInfoManager.getHomeInfo("0") != null) {
			homeInfoManager.update(homeInfo);
		} else {
			homeInfoManager.add(homeInfo);
		}
		
		addActionMessage("You have been successfully updated");
		
		this.execute();
		
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
}
