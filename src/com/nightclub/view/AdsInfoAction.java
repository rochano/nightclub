package com.nightclub.view;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.AdsInfoManager;
import com.nightclub.model.AdsInfo;
import com.nightclub.model.AdsSearch;
import com.nightclub.model.UserInfo;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AdsInfoAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private List<AdsInfo> adsInfos;
	private AdsInfo adsInfo;
	private AdsSearch adsSearch;
	private String adsInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	
	private AdsInfoManager adsInfoManager;
	
    private String adsImageFileName1;
    private String adsImageFileName2;
    private String active;

	public AdsInfoAction() {
		adsInfoManager = new AdsInfoManager();
	}
	
	public String execute() {
		
		if(getAction() != null) {
			if(getAction().equals("add")) {
				add();
			} else if(getAction().equals("update")) {
				update();
			}
		}
		
		this.adsInfos = adsInfoManager.list();
		
		return SUCCESS;
	}

	public String add() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			adsInfo.setAdsInfoId(UUID.randomUUID().toString().toUpperCase());
			
			if(!getAdsImageFileName1().isEmpty()) {
				this.adsImageFileName1 = UploadFileUtils.uploadImageApi(getAdsImageFileName1(), sessionMap, userInfo);
				this.adsInfo.setAdsImg1(this.adsImageFileName1);
			}
			if(!getAdsImageFileName2().isEmpty()) {
				this.adsImageFileName2 = UploadFileUtils.uploadImageApi(getAdsImageFileName2(), sessionMap, userInfo);
				this.adsInfo.setAdsImg2(this.adsImageFileName2);
			}

			if(getActive() != null) {
				this.adsInfo.setActive(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsInfo.setActive(Boolean.FALSE.toString().toLowerCase());
			}
			
			adsInfoManager.add(this.adsInfo);
			
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
			AdsInfo currentAdsInfo = adsInfoManager.getAdsInfo(this.adsInfo.getAdsInfoId());
			
			if(!getAdsImageFileName1().isEmpty()) {
				this.adsImageFileName1 = UploadFileUtils.uploadImageApi(getAdsImageFileName1(), sessionMap, userInfo);
				this.adsInfo.setAdsImg1(this.adsImageFileName1);
			}
			else if(currentAdsInfo.getAdsImg1() != null && !currentAdsInfo.getAdsImg1().isEmpty()) {
				if(this.adsInfo.getAdsImg1().isEmpty()) {
					UploadFileUtils.deleteImageApi(currentAdsInfo.getAdsImg1());
					this.adsInfo.setAdsImg1("");
				} else {
					this.adsInfo.setAdsImg1(currentAdsInfo.getAdsImg1());
				}
            }
			
			if(!getAdsImageFileName2().isEmpty()) {
				this.adsImageFileName2 = UploadFileUtils.uploadImageApi(getAdsImageFileName2(), sessionMap, userInfo);
				this.adsInfo.setAdsImg2(this.adsImageFileName2);
			}
			else if(currentAdsInfo.getAdsImg2() != null && !currentAdsInfo.getAdsImg2().isEmpty()) {
				if(this.adsInfo.getAdsImg2().isEmpty()) {
					UploadFileUtils.deleteImageApi(currentAdsInfo.getAdsImg2());
					this.adsInfo.setAdsImg2("");
				} else {
					this.adsInfo.setAdsImg2(currentAdsInfo.getAdsImg2());
				}
            }

			if(getActive() != null) {
				this.adsInfo.setActive(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsInfo.setActive(Boolean.FALSE.toString().toLowerCase());
			}
			
			adsInfoManager.update(this.adsInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.adsInfo = adsInfoManager.getAdsInfo(this.adsInfoId);
		
		if(this.adsInfo.getAdsImg1() == null){// || !new File(filePath, this.girlInfo.getPic1()).exists()) {
        	this.adsInfo.setAdsImg1("");
        }
		if(this.adsInfo.getAdsImg2() == null){// || !new File(filePath, this.girlInfo.getPic1()).exists()) {
        	this.adsInfo.setAdsImg2("");
        }

		this.showInfo = true;
		this.adsInfos = adsInfoManager.list();
		return SUCCESS;
	}

	public String delete() {
		adsInfoManager.delete(getAdsInfoId());
		addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
		this.adsInfos = adsInfoManager.list();
		return SUCCESS;
	}
	
	public String search() {
		this.adsInfos = adsInfoManager.search(this.adsSearch);
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

	public List<AdsInfo> getAdsInfos() {
		return adsInfos;
	}

	public AdsInfo getAdsInfo() {
		return adsInfo;
	}

	public String getAdsInfoId() {
		return adsInfoId;
	}

	public void setAdsInfos(List<AdsInfo> adsInfos) {
		this.adsInfos = adsInfos;
	}

	public void setAdsInfo(AdsInfo adsInfo) {
		this.adsInfo = adsInfo;
	}

	public void setAdsInfoId(String adsInfoId) {
		this.adsInfoId = adsInfoId;
	}

	public AdsSearch getAdsSearch() {
		return adsSearch;
	}

	public void setAdsSearch(AdsSearch adsSearch) {
		this.adsSearch = adsSearch;
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
	
	public String getAdsImageFileName2() {
		return adsImageFileName2;
	}

	public void setAdsImageFileName2(String adsImageFileName2) {
		this.adsImageFileName2 = adsImageFileName2;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
}
