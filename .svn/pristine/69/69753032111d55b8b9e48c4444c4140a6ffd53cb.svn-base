package com.nightclub.view;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.CategoryZone;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.ZoneInfo;
import com.opensymphony.xwork2.ActionSupport;

public class AdminInfoAction extends ActionSupport  {

	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());

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


}
