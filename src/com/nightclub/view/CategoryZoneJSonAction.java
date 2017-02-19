package com.nightclub.view;

import java.util.ArrayList;
import java.util.List;

import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.CategoryZone;
import com.nightclub.model.ZoneInfo;
import com.opensymphony.xwork2.Action;

public class CategoryZoneJSonAction {

	private List<CategoryZone> categoryZones = new ArrayList<CategoryZone>();
	private String categoryCode;
    private List<CategoryInfo> categoryInfos;
	private CategoryInfoManager categoryInfoManager;
	
	public CategoryZoneJSonAction() {
		categoryInfoManager = new CategoryInfoManager();
	}
	
	public String execute() {
		CategoryInfoManager categoryInfoManager = new CategoryInfoManager();
		if ("".equals(getCategoryCode())) {
			this.categoryInfos = categoryInfoManager.list();
			setCategoryCode(this.categoryInfos.get(0).getCategoryCode());
		}
		CategoryInfo categoryInfo = categoryInfoManager.getCategoryInfoByCode(getCategoryCode());
		List<CategoryZone> list = categoryInfo.getCategoryZones();
		ZoneInfo zoneInfo = new ZoneInfo();
		CategoryZone categoryZone;
		for(CategoryZone cz : list) {
			zoneInfo = new ZoneInfo();
			categoryZone = new CategoryZone();
			categoryZone.setZoneInfo(zoneInfo);
			categoryZones.add(categoryZone);
			
			zoneInfo.setZoneCode(cz.getZoneInfo().getZoneCode());
			zoneInfo.setZoneNameJp(cz.getZoneInfo().getZoneNameJp());
		}
		
        return Action.SUCCESS;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public List<CategoryZone> getCategoryZones() {
		return categoryZones;
	}

	public void setCategoryZones(List<CategoryZone> categoryZones) {
		this.categoryZones = categoryZones;
	}
}
