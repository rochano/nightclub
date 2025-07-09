package com.nightclub.view;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.AdsFeedInfoManager;
import com.nightclub.model.AdsFeedInfo;
import com.nightclub.model.AdsFeedSearch;
import com.nightclub.model.UserInfo;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AdsFeedInfoAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private List<AdsFeedInfo> adsFeedInfos;
	private AdsFeedInfo adsFeedInfo;
	private AdsFeedSearch adsFeedSearch;
	private String adsFeedInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private String adminMode = "true";
	
	private AdsFeedInfoManager adsFeedInfoManager;
	
    private String adsImageFileName1;
    private String active;

	public AdsFeedInfoAction() {
		adsFeedInfoManager = new AdsFeedInfoManager();
	}
	
	public String execute() {
		
		if(getAction() != null) {
			if(getAction().equals("add")) {
				add();
			} else if(getAction().equals("update")) {
				update();
			}
		}
		
		this.adsFeedInfos = adsFeedInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			adsFeedInfo.setAdsFeedInfoId(UUID.randomUUID().toString().toUpperCase());
			
			if(!getAdsImageFileName1().isEmpty()) {
				this.adsImageFileName1 = UploadFileUtils.uploadImageApi(getAdsImageFileName1(), sessionMap, userInfo);
				this.adsFeedInfo.setAdsImg1(this.adsImageFileName1);
			}

			if(getActive() != null) {
				this.adsFeedInfo.setActive(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsFeedInfo.setActive(Boolean.FALSE.toString().toLowerCase());
			}
			
			adsFeedInfoManager.add(this.adsFeedInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_add"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String update() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			AdsFeedInfo currentAdsFeedInfo = adsFeedInfoManager.getAdsFeedInfo(this.adsFeedInfo.getAdsFeedInfoId());
			
			if(!getAdsImageFileName1().isEmpty()) {
				this.adsImageFileName1 = UploadFileUtils.uploadImageApi(getAdsImageFileName1(), sessionMap, userInfo);
				this.adsFeedInfo.setAdsImg1(this.adsImageFileName1);
			}
			else if(currentAdsFeedInfo.getAdsImg1() != null && !currentAdsFeedInfo.getAdsImg1().isEmpty()) {
				if(this.adsFeedInfo.getAdsImg1().isEmpty()) {
					UploadFileUtils.deleteImageApi(currentAdsFeedInfo.getAdsImg1());
					this.adsFeedInfo.setAdsImg1("");
				} else {
					this.adsFeedInfo.setAdsImg1(currentAdsFeedInfo.getAdsImg1());
				}
            }

			if(getActive() != null) {
				this.adsFeedInfo.setActive(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsFeedInfo.setActive(Boolean.FALSE.toString().toLowerCase());
			}
			
			adsFeedInfoManager.update(this.adsFeedInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.adsFeedInfo = adsFeedInfoManager.getAdsFeedInfo(this.adsFeedInfoId);
		
		if(this.adsFeedInfo.getAdsImg1() == null){// || !new File(filePath, this.girlInfo.getPic1()).exists()) {
        	this.adsFeedInfo.setAdsImg1("");
        }

		this.showInfo = true;
		this.adsFeedInfos = adsFeedInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		adsFeedInfoManager.delete(getAdsFeedInfoId());
		addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
		this.adsFeedInfos = adsFeedInfoManager.list();
		return SUCCESS;
	}
	
	public String search() {
		this.adsFeedInfos = adsFeedInfoManager.search(this.adsFeedSearch);
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

	public List<AdsFeedInfo> getAdsFeedInfos() {
		return adsFeedInfos;
	}

	public AdsFeedInfo getAdsFeedInfo() {
		return adsFeedInfo;
	}

	public String getAdsFeedInfoId() {
		return adsFeedInfoId;
	}

	public void setAdsFeedInfos(List<AdsFeedInfo> adsFeedInfos) {
		this.adsFeedInfos = adsFeedInfos;
	}

	public void setAdsFeedInfo(AdsFeedInfo adsFeedInfo) {
		this.adsFeedInfo = adsFeedInfo;
	}

	public void setAdsFeedInfoId(String adsFeedInfoId) {
		this.adsFeedInfoId = adsFeedInfoId;
	}

	public AdsFeedSearch getAdsFeedSearch() {
		return adsFeedSearch;
	}

	public void setAdsFeedSearch(AdsFeedSearch adsFeedSearch) {
		this.adsFeedSearch = adsFeedSearch;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getAdsImageFileName1() {
		return adsImageFileName1;
	}

	public void setAdsImageFileName1(String adsImageFileName1) {
		this.adsImageFileName1 = adsImageFileName1;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAdminMode() {
		return adminMode;
	}
	
	public void setAdminMode(String adminMode) {
		this.adminMode = adminMode;
	}
}
