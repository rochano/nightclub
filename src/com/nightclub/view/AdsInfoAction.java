package com.nightclub.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
    private String adsImageFileName;
    private String adsImageMobileFileName;
    private String autoSubscribe;;
    private String active;
    private String currentRangeFrom = "";
    private String currentRangeTo = "";
    private String nextRangeFrom = "";
    private String nextRangeTo = "";
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

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
			
			if(!getAdsImageFileName().isEmpty()) {
				this.adsImageFileName = UploadFileUtils.uploadImageApi(getAdsImageFileName(), sessionMap, userInfo);
				this.adsInfo.setAdsImg(this.adsImageFileName);
			}
			
			if(getAutoSubscribe() != null) {
				this.adsInfo.setAutoSubscribe(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsInfo.setAutoSubscribe(Boolean.FALSE.toString().toLowerCase());
			}
			
			if(getActive() != null) {
				this.adsInfo.setActive(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsInfo.setActive(Boolean.FALSE.toString().toLowerCase());
			}
			
			adsInfoManager.add(this.adsInfo);
			
			addActionMessage("You have been successfully inserted");
			
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
			
			if(!getAdsImageFileName().isEmpty()) {
				this.adsImageFileName = UploadFileUtils.uploadImageApi(getAdsImageFileName(), sessionMap, userInfo);
				this.adsInfo.setAdsImg(this.adsImageFileName);
			}
			else if(currentAdsInfo.getAdsImg() != null && !currentAdsInfo.getAdsImg().isEmpty()) {
            	this.adsInfo.setAdsImg(currentAdsInfo.getAdsImg());
            }
			
			if(getAutoSubscribe() != null) {
				this.adsInfo.setAutoSubscribe(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsInfo.setAutoSubscribe(Boolean.FALSE.toString().toLowerCase());
			}
			
			if(getActive() != null) {
				this.adsInfo.setActive(Boolean.TRUE.toString().toLowerCase());
			} else {
				this.adsInfo.setActive(Boolean.FALSE.toString().toLowerCase());
			}
			
			adsInfoManager.update(this.adsInfo);
			
			addActionMessage("You have been successfully updated");
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INPUT;
	}
	
	public String edit() {
		this.adsInfo = adsInfoManager.getAdsInfo(this.adsInfoId);
		
		if(this.adsInfo.getAdsImg() == null){// || !new File(filePath, this.girlInfo.getPic1()).exists()) {
        	this.adsInfo.setAdsImg("");
        }

		if(this.adsInfo.getAdsDateFrom() != null) {
			Calendar calCurrentRangeFrom = Calendar.getInstance();
			calCurrentRangeFrom.add(Calendar.MONTH, -1);
			calCurrentRangeFrom = findDateRange(calCurrentRangeFrom);
			calCurrentRangeFrom.add(Calendar.DATE, 1);
			Calendar calCurrentRangeTo = findDateRange(calCurrentRangeFrom);
			Calendar calNextRangeFrom = (Calendar) calCurrentRangeTo.clone();
			calNextRangeFrom.add(Calendar.DATE, 1);
			Calendar calNextRangeTo = findDateRange(calNextRangeFrom);
			this.currentRangeFrom = df.format(calCurrentRangeFrom.getTime());
			this.currentRangeTo = df.format(calCurrentRangeTo.getTime());
			this.nextRangeFrom = df.format(calNextRangeFrom.getTime());
			this.nextRangeTo = df.format(calNextRangeTo.getTime());
		}

		this.showInfo = true;
		this.adsInfos = adsInfoManager.list();
		return SUCCESS;
	}

	private Calendar findDateRange(Calendar calRangeFrom) {
		Calendar calRangeTo = (Calendar) calRangeFrom.clone();
		calRangeTo.set(Calendar.DATE, 1);
		calRangeTo.add(Calendar.MONTH, 1);
		if (calRangeTo.getMaximum(Calendar.DATE) > this.adsInfo.getAdsDateFrom().getDate()) {
			calRangeTo.set(Calendar.DATE, this.adsInfo.getAdsDateFrom().getDate());
		} else {
			calRangeTo.set(Calendar.DATE, calRangeTo.getMaximum(Calendar.DATE));
		}
		calRangeTo.add(Calendar.DATE, -1);
		return calRangeTo;
	}

	public String delete() {
		adsInfoManager.delete(getAdsInfoId());
		addActionMessage("You have been successfully deleted");
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

	public String getAdsImageFileName() {
		return adsImageFileName;
	}
	
	public String getAdsImageMobileFileName() {
		return adsImageMobileFileName;
	}
	
	public void setAdsImageFileName(String adsImageFileName) {
		this.adsImageFileName = adsImageFileName;
	}
	
	public void setAdsImageMobileFileName(String adsImageMobileFileName) {
		this.adsImageMobileFileName = adsImageMobileFileName;
	}

	public String getAutoSubscribe() {
		return autoSubscribe;
	}

	public void setAutoSubscribe(String autoSubscribe) {
		this.autoSubscribe = autoSubscribe;
	}
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCurrentRangeFrom() {
		return currentRangeFrom;
	}

	public String getCurrentRangeTo() {
		return currentRangeTo;
	}

	public String getNextRangeFrom() {
		return nextRangeFrom;
	}

	public String getNextRangeTo() {
		return nextRangeTo;
	}

	public void setCurrentRangeFrom(String currentRangeFrom) {
		this.currentRangeFrom = currentRangeFrom;
	}

	public void setCurrentRangeTo(String currentRangeTo) {
		this.currentRangeTo = currentRangeTo;
	}

	public void setNextRangeFrom(String nextRangeFrom) {
		this.nextRangeFrom = nextRangeFrom;
	}

	public void setNextRangeTo(String nextRangeTo) {
		this.nextRangeTo = nextRangeTo;
	}
}
