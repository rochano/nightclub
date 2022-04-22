package com.nightclub.view;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.GirlSettingManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.controller.HomeSlideImageManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.model.CategoryZone;
import com.nightclub.model.GirlServiceInfo;
import com.nightclub.model.GirlSetting;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.HomeSlideImage;
import com.nightclub.model.UserInfo;
import com.nightclub.model.ZoneInfo;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AdminInfoAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());

	private Map<String, Object> sessionMap;
	private String menu;
	private HomeInfo homeInfo;
	private List<HomeSlideImage> homeSlideImages;
	private List<String> homeSlideImagesFileNameList;
	private List<UserInfo> userInfos;
	private List<String> activelist;
	private List<GirlServiceInfo> girlServicInfoList;
	private List<String> girlservicenamelist;
	private GirlSetting girlSetting;
	private boolean showInfo = false;
	private String userInfoId;
	private UserInfo userInfo;
	private String userType;
	
	private HomeInfoManager homeInfoManager;
	private HomeSlideImageManager homeSlideImageManager;
	private GirlInfoManager girlInfoManager;
	private GirlSettingManager girlSettingManager;
	private UserInfoManager userInfoManager;

	public AdminInfoAction() {
		homeInfoManager = new HomeInfoManager();
		girlInfoManager = new GirlInfoManager();
		girlSettingManager = new GirlSettingManager();
		userInfoManager = new UserInfoManager();
		homeSlideImageManager = new HomeSlideImageManager();
	}
	
	public String execute() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}
	
	public String update() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			HomeInfo homeInfo_ = homeInfoManager.getHomeInfo("0");
			homeInfo_.setHomeInfoId("0");
			homeInfo_.setDescription(UploadFileUtils.uploadImageinDescription(homeInfo.getDescription(), sessionMap, userInfo));
			homeInfo_.setDescription2(UploadFileUtils.uploadImageinDescription(homeInfo.getDescription2(), sessionMap, userInfo));
			homeInfo_.setDescriptionEn(UploadFileUtils.uploadImageinDescription(homeInfo.getDescriptionEn(), sessionMap, userInfo));
			homeInfo_.setDescriptionEn2(UploadFileUtils.uploadImageinDescription(homeInfo.getDescriptionEn2(), sessionMap, userInfo));
			
			if(homeInfoManager.getHomeInfo("0") != null) {
				homeInfoManager.update(homeInfo_);
			} else {
				homeInfoManager.add(homeInfo_);
			}
			
			addActionMessage("You have been successfully updated");
			
			this.execute();
			
			return SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String homeslideimage() {
		this.homeSlideImages = homeSlideImageManager.list();
		
		return SUCCESS;
	}
	
	public String homeslideimageupdate() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			Integer homeSlideImageId = 1;
			HomeSlideImage homeSlideImage;
			this.homeSlideImages = new ArrayList<HomeSlideImage>();
			if(this.homeSlideImagesFileNameList != null) {
				for(String fileName : this.homeSlideImagesFileNameList) {
					if(!fileName.isEmpty()) {
						fileName = UploadFileUtils.uploadImageApi(fileName, sessionMap, userInfo);
					}
					homeSlideImage = new HomeSlideImage();
					homeSlideImage.setHomeSlideImageId(homeSlideImageId);
					homeSlideImage.setSlideImg(fileName);
					this.homeSlideImages.add(homeSlideImage);
					homeSlideImageId++;
				}
			}
			homeSlideImageManager.add(this.homeSlideImages);
			addActionMessage("You have been successfully updated");
			
			this.homeslideimage();
			
			return SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String userlist() {
		this.userInfos = userInfoManager.list(getUserType());
		
		return SUCCESS;
	}
	
	public String useractive() {
		
		if(getActivelist() == null) {
			setActivelist(new ArrayList<String>());
		}

		userInfoManager.activeByUserInfoId(getActivelist(), getUserType());
		this.userInfos = userInfoManager.list(getUserType());
		addActionMessage("You have been successfully updated");
		return SUCCESS;
	}
	
	public String useredit() {
		this.userInfos = userInfoManager.list(getUserType());
		this.userInfo = userInfoManager.getUserByUserInfoId(getUserInfoId());
		this.showInfo = true;
		
		return SUCCESS;
	}
	
	public String userupdate() {
		UserInfo userInfo_ = userInfoManager.getUserByUserInfoId(getUserInfoId());
		userInfo_.setValidDateFrom(userInfo.getValidDateFrom());
		userInfo_.setValidDateTo(userInfo.getValidDateTo());
		userInfoManager.update(userInfo_);
		addActionMessage("You have been successfully updated");
		this.userInfos = userInfoManager.list(getUserType());
		
		return SUCCESS;
	}
	
	public String girlservice() {
		this.girlServicInfoList = girlInfoManager.getGirlServiceList();
		
		return SUCCESS;
	}

	public String girlserviceupdate() {
		
		if(getGirlservicenamelist() == null) {
			setGirlservicenamelist(new ArrayList<String>());
		}
		BigInteger orderNo = BigInteger.ONE;
		setGirlServicInfoList(new ArrayList<GirlServiceInfo>());
		for(String girlServiceName : getGirlservicenamelist()) {
			GirlServiceInfo girlServiceInfo = new GirlServiceInfo();
			girlServiceInfo.setGirlServiceInfoId(UUID.randomUUID().toString().toUpperCase());
			girlServiceInfo.setGirlServiceName(girlServiceName);
			girlServiceInfo.setOrderNo(orderNo);
			orderNo = orderNo.add(BigInteger.ONE);
			getGirlServicInfoList().add(girlServiceInfo);
		}

		girlInfoManager.updateGirlServiceInfo(getGirlServicInfoList());
		addActionMessage("You have been successfully updated");
		return SUCCESS;
	}

	public String girlsetting() {
		this.girlSetting = girlSettingManager.getGirlSetting();

		return SUCCESS;
	}
	
	public String girlsettingupdate() {
		if(girlSettingManager.getGirlSetting() != null) {
			this.girlSetting = girlSettingManager.getGirlSetting();
		} else {
			this.girlSetting.setGirlSettingId("common");
			this.girlSetting = girlSettingManager.add(this.girlSetting);
		}
		
		addActionMessage("You have been successfully updated");
		
		this.execute();
		
		return SUCCESS;
	}

	public String howToUse() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}

	public String howToUseupdate() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			HomeInfo homeInfo_ = homeInfoManager.getHomeInfo("0");
			homeInfo_.setHomeInfoId("0");
			homeInfo_.setHowToUse(UploadFileUtils.uploadImageinDescription(homeInfo.getHowToUse(), sessionMap, userInfo));
			
			if(homeInfoManager.getHomeInfo("0") != null) {
				homeInfoManager.update(homeInfo_);
			} else {
				homeInfoManager.add(homeInfo_);
			}
			
			addActionMessage("You have been successfully updated");
			
			this.execute();
			
			return SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}

	public String contact() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}

	public String contactupdate() {
		HomeInfo homeInfo_ = homeInfoManager.getHomeInfo("0");
		homeInfo_.setHomeInfoId("0");
		homeInfo_.setLineContactUrl(homeInfo.getLineContactUrl());
		
		if(homeInfoManager.getHomeInfo("0") != null) {
			homeInfoManager.update(homeInfo_);
		} else {
			homeInfoManager.add(homeInfo_);
		}
		
		addActionMessage("You have been successfully updated");
		
		this.execute();
		
		return SUCCESS;
	}
	
	public String howToInput() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}

	public String howToInputupdate() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			HomeInfo homeInfo_ = homeInfoManager.getHomeInfo("0");
			homeInfo_.setHomeInfoId("0");
			homeInfo_.setHowToInputShopService(UploadFileUtils.uploadImageinDescription(homeInfo.getHowToInputShopService(), sessionMap, userInfo));
			homeInfo_.setHowToInputAgent(UploadFileUtils.uploadImageinDescription(homeInfo.getHowToInputAgent(), sessionMap, userInfo));
			homeInfo_.setHowToInputFreeAgent(UploadFileUtils.uploadImageinDescription(homeInfo.getHowToInputFreeAgent(), sessionMap, userInfo));
			homeInfo_.setHowToInputEnGirl(UploadFileUtils.uploadImageinDescription(homeInfo.getHowToInputEnGirl(), sessionMap, userInfo));
			
			if(homeInfoManager.getHomeInfo("0") != null) {
				homeInfoManager.update(homeInfo_);
			} else {
				homeInfoManager.add(homeInfo_);
			}
			
			addActionMessage("You have been successfully updated");
			
			this.execute();
			
			return SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String getMenu() {
		return menu;
	}

	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public void setHomeInfo(HomeInfo homeInfo) {
		this.homeInfo = homeInfo;
	}

	public List<String> getActivelist() {
		return activelist;
	}

	public void setActivelist(List<String> activelist) {
		this.activelist = activelist;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<GirlServiceInfo> getGirlServicInfoList() {
		return girlServicInfoList;
	}

	public void setGirlServicInfoList(List<GirlServiceInfo> girlServicInfoList) {
		this.girlServicInfoList = girlServicInfoList;
	}

	public List<String> getGirlservicenamelist() {
		return girlservicenamelist;
	}

	public void setGirlservicenamelist(List<String> girlservicenamelist) {
		this.girlservicenamelist = girlservicenamelist;
	}

	public GirlSetting getGirlSetting() {
		return girlSetting;
	}

	public GirlSettingManager getGirlSettingManager() {
		return girlSettingManager;
	}

	public void setGirlSetting(GirlSetting girlSetting) {
		this.girlSetting = girlSetting;
	}

	public void setGirlSettingManager(GirlSettingManager girlSettingManager) {
		this.girlSettingManager = girlSettingManager;
	}

	public boolean isShowInfo() {
		return showInfo;
	}

	public void setShowInfo(boolean showInfo) {
		this.showInfo = showInfo;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public String getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(String userInfoId) {
		this.userInfoId = userInfoId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<HomeSlideImage> getHomeSlideImages() {
		return homeSlideImages;
	}

	public void setHomeSlideImages(List<HomeSlideImage> homeSlideImages) {
		this.homeSlideImages = homeSlideImages;
	}

	public List<String> getHomeSlideImagesFileNameList() {
		return homeSlideImagesFileNameList;
	}

	public void setHomeSlideImagesFileNameList(List<String> homeSlideImagesFileNameList) {
		this.homeSlideImagesFileNameList = homeSlideImagesFileNameList;
	}


}
