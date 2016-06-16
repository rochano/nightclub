package com.nightclub.view;

import java.util.List;

import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.CategoryZone;
import com.nightclub.model.ZoneInfo;
import com.opensymphony.xwork2.Action;

public class CategoryZoneJSonAction {

	private List<CategoryZone> categoryZones;
	private String categoryCode;
	
	public CategoryZoneJSonAction() {
	}
	
	public String execute() {
		CategoryInfoManager categoryInfoManager = new CategoryInfoManager();
		CategoryInfo categoryInfo = categoryInfoManager.getCategoryInfoByCode(getCategoryCode());
		setCategoryZones(categoryInfo.getCategoryZones());
		
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
