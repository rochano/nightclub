package com.nightclub.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.GirlTagInfoManager;
import com.nightclub.model.GirlTagInfo;
import com.opensymphony.xwork2.ActionSupport;

public class GirlTagInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<GirlTagInfo> girlTagInfos;
	private GirlTagInfo girlTagInfo;
	private GirlTagInfo girlTagSearch;
	private String girlTagInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private List<String> colorList;
	
	private GirlTagInfoManager girlTagInfoManager;

	public GirlTagInfoAction() {
		girlTagInfoManager = new GirlTagInfoManager();
	}
	
	public String execute() {
		log_.info("getAction() >>> " + getAction());
		if(getAction() != null) {
			if(getAction().equals("add")) {
				add();
			} else if(getAction().equals("update")) {
				update();
			}
		}
		
		this.girlTagInfos = girlTagInfoManager.list();
		this.colorList = createColorList();

		return SUCCESS;
	}

	public String add() {
		try {
			girlTagInfo.setGirlTagInfoId(UUID.randomUUID().toString().toUpperCase());
			girlTagInfoManager.add(this.girlTagInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			girlTagInfoManager.update(this.girlTagInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.girlTagInfo = girlTagInfoManager.getGirlTagInfo(this.girlTagInfoId);
		this.showInfo = true;
		this.girlTagInfos = girlTagInfoManager.list();
		this.colorList = createColorList();
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!girlTagInfoManager.isRelatedProvince(getGirlTagInfoId())) {
			girlTagInfoManager.delete(getGirlTagInfoId());
			addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getTexts("global_th").getString("global.message_girlTag_delete_fail"));
			result = INPUT;
		}
		this.girlTagInfos = girlTagInfoManager.list();
		return result;
	}
	
	public String search() {
		this.girlTagInfos = girlTagInfoManager.search(this.girlTagSearch);
		return SUCCESS;
	}

	public List<String> createColorList() {
		List<String> colorList_ = new ArrayList<String>();
		colorList_.add("red");
		colorList_.add("orange");
		colorList_.add("yellow");
		colorList_.add("olive");
		colorList_.add("green");
		colorList_.add("teal");
		colorList_.add("blue");
		colorList_.add("violet");
		colorList_.add("purple");
		colorList_.add("pink");
		colorList_.add("brown");
		return colorList_;
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

	public List<GirlTagInfo> getGirlTagInfos() {
		return girlTagInfos;
	}

	public GirlTagInfo getGirlTagInfo() {
		return girlTagInfo;
	}

	public void setGirlTagInfos(List<GirlTagInfo> girlTagInfos) {
		this.girlTagInfos = girlTagInfos;
	}

	public void setGirlTagInfo(GirlTagInfo girlTagInfo) {
		this.girlTagInfo = girlTagInfo;
	}

	public String getGirlTagInfoId() {
		return girlTagInfoId;
	}

	public void setGirlTagInfoId(String girlTagInfoId) {
		this.girlTagInfoId = girlTagInfoId;
	}

	public GirlTagInfo getGirlTagSearch() {
		return girlTagSearch;
	}

	public void setGirlTagSearch(GirlTagInfo girlTagSearch) {
		this.girlTagSearch = girlTagSearch;
	}

	public List<String> getColorList() {
		return colorList;
	}

	public void setColorList(ArrayList<String> colorList) {
		this.colorList = colorList;
	}
}
