package com.nightclub.view;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.CategoryZone;
import com.nightclub.model.ZoneInfo;
import com.opensymphony.xwork2.ActionSupport;

public class CategoryInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<CategoryInfo> categoryInfos;
	private List<ZoneInfo> zoneInfos;
	private CategoryInfo categoryInfo;
	private String categoryInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private List<String> zonelist;
	
	private CategoryInfoManager categoryInfoManager;
	private ZoneInfoManager zoneInfoManager;

	public CategoryInfoAction() {
		categoryInfoManager = new CategoryInfoManager();
		zoneInfoManager = new ZoneInfoManager();
	}
	
	public String execute() {
		if(getAction() != null) {
			if(getAction().equals("update")) {
				update();
			}
		}
		
		this.categoryInfos = categoryInfoManager.list();
		this.zoneInfos = zoneInfoManager.list();
		
		return SUCCESS;
	}

	public String update() {
		try {
			ZoneInfo zoneInfo;
			CategoryZone categoryZone;
			Integer orderNo = 1;
			for(String zoneInfoId : this.zonelist) {
				zoneInfo = new ZoneInfo();
				zoneInfo.setZoneInfoId(zoneInfoId);
				
				categoryZone = new CategoryZone();
				categoryZone.setZoneInfo(zoneInfo);
				categoryZone.setCategoryInfo(this.categoryInfo);
				categoryZone.setOrderNo(new BigInteger("" + orderNo));
				orderNo++;
				this.categoryInfo.getCategoryZones().add(categoryZone);
			}
			categoryInfoManager.update(this.categoryInfo);
			
			
			addActionMessage("You have been successfully updated");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.categoryInfo = categoryInfoManager.getCategoryInfo(this.categoryInfoId);
		this.zonelist = new ArrayList<String>();
		if(this.categoryInfo.getCategoryZones() != null) {
			for(CategoryZone catgoryZone : this.categoryInfo.getCategoryZones()) {
				this.zonelist.add(catgoryZone.getZoneInfo().getZoneInfoId());
			}
		}
		this.showInfo = true;
		this.categoryInfos = categoryInfoManager.list();
		this.zoneInfos = zoneInfoManager.list();
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

	public List<CategoryInfo> getCategoryInfos() {
		return categoryInfos;
	}

	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}

	public void setCategoryInfos(List<CategoryInfo> categoryInfos) {
		this.categoryInfos = categoryInfos;
	}

	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	public String getCategoryInfoId() {
		return categoryInfoId;
	}

	public void setCategoryInfoId(String categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}

	public List<String> getZonelist() {
		return zonelist;
	}

	public void setZonelist(List<String> zonelist) {
		this.zonelist = zonelist;
	}

	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
	}

	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}

}
