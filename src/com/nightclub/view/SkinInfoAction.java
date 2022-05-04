package com.nightclub.view;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.nightclub.controller.SkinInfoManager;
import com.nightclub.model.SkinInfo;
import com.opensymphony.xwork2.ActionSupport;

public class SkinInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private List<SkinInfo> skinInfos;
	private SkinInfo skinInfo;
	private SkinInfo skinSearch;
	private String skinInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private SkinInfoManager skinInfoManager;

	public SkinInfoAction() {
		skinInfoManager = new SkinInfoManager();
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
		
		this.skinInfos = skinInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			skinInfo.setSkinInfoId(UUID.randomUUID().toString().toUpperCase());
			skinInfoManager.add(this.skinInfo);
			
			addActionMessage(getText("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			skinInfoManager.update(this.skinInfo);
			
			addActionMessage(getText("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.skinInfo = skinInfoManager.getSkinInfo(this.skinInfoId);
		this.showInfo = true;
		this.skinInfos = skinInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		String result = "";
		if(!skinInfoManager.isRelatedGirlInfo(getSkinInfoId())) {
			skinInfoManager.delete(getSkinInfoId());
			addActionMessage(getText("global.message_success_delete"));
			result = SUCCESS;
		} else{
			addActionError(getText("global.message_skin_delete_fail"));
			result = INPUT;
		}
		this.skinInfos = skinInfoManager.list();
		return result;
	}
	
	public String search() {
		this.skinInfos = skinInfoManager.search(this.skinSearch);
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

	public List<SkinInfo> getSkinInfos() {
		return skinInfos;
	}

	public SkinInfo getSkinInfo() {
		return skinInfo;
	}

	public void setSkinInfos(List<SkinInfo> skinInfos) {
		this.skinInfos = skinInfos;
	}

	public void setSkinInfo(SkinInfo skinInfo) {
		this.skinInfo = skinInfo;
	}

	public String getSkinInfoId() {
		return skinInfoId;
	}

	public void setSkinInfoId(String skinInfoId) {
		this.skinInfoId = skinInfoId;
	}

	public SkinInfo getSkinSearch() {
		return skinSearch;
	}

	public void setSkinSearch(SkinInfo skinSearch) {
		this.skinSearch = skinSearch;
	}


}
