package net.viralpatel.contact.view;

import java.util.logging.Logger;

import com.nightclub.controller.HomeInfoManager;
import com.nightclub.model.HomeInfo;
import com.opensymphony.xwork2.ActionSupport;


public class CommonAction extends ActionSupport {
	
	Logger log_ = Logger.getLogger(this.getClass().getName());
	private String menu;
	private HomeInfo homeInfo;
	
	private HomeInfoManager homeInfoManager;
	
	public CommonAction() {
		homeInfoManager = new HomeInfoManager();
	}

	public String execute() {
		log_.info("menu >> " + menu);
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		return SUCCESS;
	}
	
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	public void setHomeInfo(HomeInfo homeInfo) {
		this.homeInfo = homeInfo;
	}
}
