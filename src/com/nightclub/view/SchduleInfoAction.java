package com.nightclub.view;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.ScheduleInfoManager;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.ScheduleInfo;
import com.nightclub.model.ScheduleSearch;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class SchduleInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private List<ScheduleInfo> schduleInfos;
	private List<GirlInfo> girlInfos;
	private ScheduleInfo scheduleInfo;
	private ScheduleSearch scheduleSearch;
	private String scheduleInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private ScheduleInfoManager scheduleInfoManager;
	private GirlInfoManager girlInfoManager;

	public SchduleInfoAction() {
		scheduleInfoManager = new ScheduleInfoManager();
		girlInfoManager = new GirlInfoManager();
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
		
		this.schduleInfos = scheduleInfoManager.list(userInfo.getShopInfoId());
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		
		return SUCCESS;
	}

	public String add() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			scheduleInfo.setScheduleInfoId(UUID.randomUUID().toString().toUpperCase());
			scheduleInfo.setShopInfoId(userInfo.getShopInfoId());
			scheduleInfoManager.add(this.scheduleInfo);
			
			addActionMessage("You have been successfully inserted");
			this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			scheduleInfo.setShopInfoId(userInfo.getShopInfoId());
			scheduleInfoManager.update(this.scheduleInfo);
			
			addActionMessage("You have been successfully updated");
			this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.scheduleInfo = scheduleInfoManager.getSchduleInfo(this.scheduleInfoId);
		this.showInfo = true;
		this.schduleInfos = scheduleInfoManager.list(userInfo.getShopInfoId());
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		return SUCCESS;
	}

	public String delete() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		scheduleInfoManager.delete(getScheduleInfoId());
		addActionMessage("You have been successfully deleted");
		this.schduleInfos = scheduleInfoManager.list(userInfo.getShopInfoId());
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		return SUCCESS;
	}
	
	public String search() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.scheduleSearch.setShopInfoId(userInfo.getShopInfoId());
		this.schduleInfos = scheduleInfoManager.search(this.scheduleSearch);
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
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

	public List<ScheduleInfo> getSchduleInfos() {
		return schduleInfos;
	}

	public ScheduleInfo getScheduleInfo() {
		return scheduleInfo;
	}

	public String getScheduleInfoId() {
		return scheduleInfoId;
	}

	public void setSchduleInfos(List<ScheduleInfo> schduleInfos) {
		this.schduleInfos = schduleInfos;
	}

	public void setScheduleInfo(ScheduleInfo scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}

	public void setScheduleInfoId(String scheduleInfoId) {
		this.scheduleInfoId = scheduleInfoId;
	}

	public List<GirlInfo> getGirlInfos() {
		return girlInfos;
	}

	public void setGirlInfos(List<GirlInfo> girlInfos) {
		this.girlInfos = girlInfos;
	}

	public ScheduleSearch getScheduleSearch() {
		return scheduleSearch;
	}

	public void setScheduleSearch(ScheduleSearch scheduleSearch) {
		this.scheduleSearch = scheduleSearch;
	}

}
