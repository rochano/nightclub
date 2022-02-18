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
	private String categoryInfoId;
    private List<CategoryInfo> categoryInfos;
	private CategoryInfoManager categoryInfoManager;
	
	public CategoryZoneJSonAction() {
		categoryInfoManager = new CategoryInfoManager();
	}
	
	public String execute() {
		CategoryInfoManager categoryInfoManager = new CategoryInfoManager();
		if ("".equals(getCategoryInfoId())) {
			this.categoryInfos = categoryInfoManager.list();
			setCategoryInfoId(this.categoryInfos.get(0).getCategoryInfoId());
		}
		CategoryInfo categoryInfo = categoryInfoManager.getCategoryInfo(getCategoryInfoId());
		List<CategoryZone> list = categoryInfo.getCategoryZones();
		ZoneInfo zoneInfo = new ZoneInfo();
		CategoryZone categoryZone;
		for(CategoryZone cz : list) {
			zoneInfo = new ZoneInfo();
			categoryZone = new CategoryZone();
			categoryZone.setZoneInfo(zoneInfo);
			categoryZones.add(categoryZone);
			
			zoneInfo.setZoneInfoId(cz.getZoneInfo().getZoneInfoId());
			zoneInfo.setZoneNameJp(cz.getZoneInfo().getZoneNameJp());
			zoneInfo.setZoneNameEn(cz.getZoneInfo().getZoneNameEn());
		}
		
        return Action.SUCCESS;
	}
	
	public String getCategoryInfoId() {
		return categoryInfoId;
	}

	public void setCategoryInfoId(String categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}

	public List<CategoryZone> getCategoryZones() {
		return categoryZones;
	}

	public void setCategoryZones(List<CategoryZone> categoryZones) {
		this.categoryZones = categoryZones;
	}
}
