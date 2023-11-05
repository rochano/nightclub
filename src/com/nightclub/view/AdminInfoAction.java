package com.nightclub.view;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;
import org.cloudinary.json.JSONObject;

import com.nightclub.common.IConstants;
import com.nightclub.controller.AgentInfoManager;
import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.controller.CountryInfoManager;
import com.nightclub.controller.GenderInfoManager;
import com.nightclub.controller.GirlCommentManager;
import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.GirlSettingManager;
import com.nightclub.controller.GirlTagInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.controller.HomeSlideImageManager;
import com.nightclub.controller.NationalityInfoManager;
import com.nightclub.controller.ProvinceInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.AdminSearch;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.CountryInfo;
import com.nightclub.model.FrontSearch;
import com.nightclub.model.GenderInfo;
import com.nightclub.model.GirlComment;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlProvince;
import com.nightclub.model.GirlService;
import com.nightclub.model.GirlServiceInfo;
import com.nightclub.model.GirlSetting;
import com.nightclub.model.GirlTag;
import com.nightclub.model.GirlTagId;
import com.nightclub.model.GirlTagInfo;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.HomeSlideImage;
import com.nightclub.model.NationalityInfo;
import com.nightclub.model.ProvinceInfo;
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
	private AdminSearch search;
	private List<GirlInfo> girlInfos;
	private FrontSearch girlSearch;
	private List<String> allsamelist;
	private List<ZoneInfo> zoneInfos;
	private List<CategoryInfo> categoryInfos;
	private List<AgentInfo> agentInfos;
	private List<CountryInfo> countryInfos;
	private List<ProvinceInfo> provinceInfos;
	private List<GirlProvince> girlProvinces;
	private List<String> checklist;
	private List<GenderInfo> genderInfos;
	private List<NationalityInfo> nationalityInfos;
	private List<GirlComment> girlComments;
	private String girlInfoId;
	private GirlInfo girlInfo;
	private List<GirlTagInfo> girlTagInfos;
	private List<String> girlTagList;

	private HomeInfoManager homeInfoManager;
	private HomeSlideImageManager homeSlideImageManager;
	private GirlInfoManager girlInfoManager;
	private GirlSettingManager girlSettingManager;
	private UserInfoManager userInfoManager;
	private ZoneInfoManager zoneInfoManager;
	private CategoryInfoManager categoryInfoManager;
	private AgentInfoManager agentInfoManager;
	private BasicInfoManager basicInfoManager;
	private CountryInfoManager countryInfoManager;
	private ProvinceInfoManager provinceInfoManager;
	private GenderInfoManager genderInfoManager;
	private NationalityInfoManager nationalityInfoManager;
	private GirlCommentManager girlCommentManager;
	private GirlTagInfoManager girlTagInfoManager;

	public AdminInfoAction() {
		homeInfoManager = new HomeInfoManager();
		girlInfoManager = new GirlInfoManager();
		girlSettingManager = new GirlSettingManager();
		userInfoManager = new UserInfoManager();
		homeSlideImageManager = new HomeSlideImageManager();
		zoneInfoManager = new ZoneInfoManager();
		categoryInfoManager = new CategoryInfoManager();
		agentInfoManager = new AgentInfoManager();
		basicInfoManager = new BasicInfoManager();
		countryInfoManager = new CountryInfoManager();
		provinceInfoManager = new ProvinceInfoManager();
		genderInfoManager = new GenderInfoManager();
		nationalityInfoManager = new NationalityInfoManager();
		girlCommentManager = new GirlCommentManager();
		girlTagInfoManager = new GirlTagInfoManager();
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
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
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
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
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
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		}
		
		return SUCCESS;
	}
	
	public String useractive() {
		
		if(getActivelist() == null) {
			setActivelist(new ArrayList<String>());
		}

		Iterator<String> it = getActivelist().iterator();
		List<String> allUserInfoIdList = new ArrayList<String>();
		List<String> availableUserInfoIdList = new ArrayList<String>();
		while(it.hasNext()) {
			JSONObject jsonData = new JSONObject(it.next());
			allUserInfoIdList.add(jsonData.getString("id"));
			if (jsonData.getBoolean("checked")) {
				availableUserInfoIdList.add(jsonData.getString("id"));
			}
		}
		userInfoManager.activeByUserInfoId(allUserInfoIdList, availableUserInfoIdList, getUserType());
		this.userInfos = userInfoManager.list(getUserType());
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		}
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		return SUCCESS;
	}
	
	public String usermultipleupdate() {
		if(getChecklist() == null) {
			setChecklist(new ArrayList<String>());
		}

		Iterator<String> it = getChecklist().iterator();
		List<String> updateUserInfoIdList = new ArrayList<String>();
		while(it.hasNext()) {
			JSONObject jsonData = new JSONObject(it.next());
			if (jsonData.getBoolean("checked")) {
				updateUserInfoIdList.add(jsonData.getString("id"));
			}
		}
		userInfoManager.updateValidDateByUserInfoId(updateUserInfoIdList, userInfo.getValidDateFrom(), userInfo.getValidDateTo(), getUserType());
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		this.userInfos = userInfoManager.list(getUserType());
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		}
		
		return SUCCESS;
	}
	
	public String useredit() {
		this.userInfos = userInfoManager.list(getUserType());
		this.userInfo = userInfoManager.getUserByUserInfoId(getUserInfoId());
		this.showInfo = true;
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		}
		
		return SUCCESS;
	}
	
	public String userupdate() {
		UserInfo userInfo_ = userInfoManager.getUserByUserInfoId(getUserInfoId());
		userInfo_.setValidDateFrom(userInfo.getValidDateFrom());
		userInfo_.setValidDateTo(userInfo.getValidDateTo());
		userInfoManager.update(userInfo_);
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		} else if(IConstants.USER_TYPE_AGENT.equals(userType)) {
			AgentInfo agentInfo = agentInfoManager.getAgentInfo(userInfo_.getAgentInfoId());
			if(agentInfo != null) {
				agentInfo.setLineId(userInfo.getAgentInfo().getLineId());
				agentInfoManager.update(agentInfo);
			}
		} else if(IConstants.USER_TYPE_FREE_AGENT.equals(userType)) {
			GirlInfo girlInfo = girlInfoManager.getGirlInfo(userInfo_.getGirlInfoId());
			girlInfo.setLineId(userInfo.getFreeAgentGirlInfo().getLineId());
			girlInfoManager.update(girlInfo);
		} else if(IConstants.USER_TYPE_EN_GIRL.equals(userType)) {
			GirlInfo girlInfo = girlInfoManager.getGirlInfo(userInfo_.getGirlInfoId());
			girlInfo.setLineId(userInfo.getEnGirlInfo().getLineId());
			girlInfoManager.update(girlInfo);
		}
		this.userInfos = userInfoManager.list(getUserType());
		
		return SUCCESS;
	}
	
	public String userdelete() {
		userInfoManager.deleteByUserInfoId(getUserInfoId());
		addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
		this.userInfos = userInfoManager.list(getUserType());
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		}
		
		return SUCCESS;
	}

	public String usermultipledelete() {
		if(getChecklist() == null) {
			setChecklist(new ArrayList<String>());
		}

		Iterator<String> it = getChecklist().iterator();
		List<String> updateUserInfoIdList = new ArrayList<String>();
		while(it.hasNext()) {
			JSONObject jsonData = new JSONObject(it.next());
			if (jsonData.getBoolean("checked")) {
				updateUserInfoIdList.add(jsonData.getString("id"));
			}
		}
		userInfoManager.deleteByUserInfoId(updateUserInfoIdList);
		addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
		this.userInfos = userInfoManager.list(getUserType());
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		}
		
		return SUCCESS;
	}
	
	public String usersearch() {
		// shop
		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
			this.userInfos = userInfoManager.searchShop(getUserType(), getSearch());
			for(UserInfo userInfo : userInfos) {
				if (userInfo.getShopInfoId() == null) continue;
				userInfo.getShopInfo().setShopLocations(basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId()));
			}
		// agent
		} else if(IConstants.USER_TYPE_AGENT.equals(userType)) {
			this.userInfos = userInfoManager.searchAgent(getUserType(), getSearch());
		// free agent
		} else if(IConstants.USER_TYPE_FREE_AGENT.equals(userType)) {
			this.userInfos = userInfoManager.searchFreeAgent(getUserType(), getSearch());
		// client
		} else if(IConstants.USER_TYPE_CLIENT.equals(userType)) {
			this.userInfos = userInfoManager.searchClient(getUserType(), getSearch());
		// entertain girl
		} else if(IConstants.USER_TYPE_EN_GIRL.equals(userType)) {
			this.userInfos = userInfoManager.searchEnGirl(getUserType(), getSearch());
		}
		
		return SUCCESS;
	}
	
	public String girlservice() {
		this.girlServicInfoList = girlInfoManager.getGirlServiceInfoList();
		
		return SUCCESS;
	}

	public String girlserviceupdate() {
		if(getGirlservicenamelist() == null) {
			setGirlservicenamelist(new ArrayList<String>());
		}
		BigInteger orderNo = BigInteger.ONE;
		setGirlServicInfoList(new ArrayList<GirlServiceInfo>());
		List<String> girlServiceInfoIdList = new ArrayList<String>();
		Iterator<String> it = getGirlservicenamelist().iterator();
		while(it.hasNext()) {
			JSONObject jsonData = new JSONObject(it.next());
			GirlServiceInfo girlServiceInfo = new GirlServiceInfo();
			String girlServiceInfoId = jsonData.getString("girlServiceInfoId");
			if (girlServiceInfoId.isEmpty()) {
				girlServiceInfo.setGirlServiceInfoId(UUID.randomUUID().toString().toUpperCase());
			} else {
				girlServiceInfo.setGirlServiceInfoId(girlServiceInfoId);
			}
			girlServiceInfo.setGirlServiceName(jsonData.getString("girlServiceName"));
			girlServiceInfo.setGirlServiceNameJp(jsonData.getString("girlServiceNameJp"));
			girlServiceInfo.setOrderNo(orderNo);
			orderNo = orderNo.add(BigInteger.ONE);
			getGirlServicInfoList().add(girlServiceInfo);
			girlServiceInfoIdList.add(girlServiceInfo.getGirlServiceInfoId());
		}

		List<GirlService> girlServices = girlInfoManager.getGirlServiceList();
		if(girlServices.size() > 0) {
			Iterator<GirlService> itInUseed = girlServices.iterator();
			boolean bIsDeleteInUsed = false;
			// check if delete in used girl service
			while(itInUseed.hasNext()) {
				GirlService girlService = itInUseed.next();
				String girlServiceInfoId = girlService.getPrimaryKey().getGirlServiceInfo().getGirlServiceInfoId();
				if (!girlServiceInfoIdList.contains(girlServiceInfoId)) {
					bIsDeleteInUsed = true;
					break;
				}
			}
			if (bIsDeleteInUsed) {
				addActionError(getTexts("global_th").getString("global.message_girl_service_update_fail"));
				this.girlServicInfoList = girlInfoManager.getGirlServiceInfoList();
				return INPUT;
			}
		}
		girlInfoManager.updateGirlServiceInfo(getGirlServicInfoList(), girlServiceInfoIdList);
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		return girlservice();
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
		
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		
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
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
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
		
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		
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
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			this.execute();
			
			return SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String girllist() {
		this.girlInfos = girlInfoManager.search(new FrontSearch(), -1, 0);
		this.zoneInfos = zoneInfoManager.list();
		this.categoryInfos = categoryInfoManager.list();
		this.agentInfos = agentInfoManager.list();
		this.countryInfos = countryInfoManager.list();
		this.genderInfos = genderInfoManager.list();
		this.nationalityInfos = nationalityInfoManager.list();
		this.girlTagInfos = girlTagInfoManager.list();
		
		return SUCCESS;
	}
	
	public String girlupdate() {
		
		if(getAllsamelist() == null) {
			setAllsamelist(new ArrayList<String>());
		}
		if(getGirlTagList() == null) {
			setGirlTagList(new ArrayList<String>());
		}

		Iterator<String> it = getAllsamelist().iterator();
		List<String> allGirlInfoIdList = new ArrayList<String>();
		List<String> allSameGirlInfoIdList = new ArrayList<String>();
		while(it.hasNext()) {
			JSONObject jsonData = new JSONObject(it.next());
			allGirlInfoIdList.add(jsonData.getString("id"));
			if (jsonData.getBoolean("checked")) {
				allSameGirlInfoIdList.add(jsonData.getString("id"));
			}
		}
		girlInfoManager.allSameByGirlInfoId(allGirlInfoIdList, allSameGirlInfoIdList);

		Iterator<String> itAllGirlTag = getGirlTagList().iterator();
		List<String> allGirlInfoIdTagList = new ArrayList<String>();
		List<GirlTag> allTagGirlInfoIdTagList = new ArrayList<GirlTag>();
		while(itAllGirlTag.hasNext()) {
			JSONObject jsonData = new JSONObject(itAllGirlTag.next());
			allGirlInfoIdTagList.add(jsonData.getString("id"));
			if (!jsonData.getString("value").equals("")) {
				GirlInfo girlInfo = new GirlInfo();
				girlInfo.setGirlInfoId(jsonData.getString("id"));
				List girlTags = Arrays.asList(jsonData.getString("value").split(","));
				Iterator itGirlTag = girlTags.iterator();
				while(itGirlTag.hasNext()) {
					String girlTagInfoId = (String) itGirlTag.next();
					GirlTagId girlTagId = new GirlTagId();
					girlTagId.setGirlInfo(girlInfo);
					GirlTagInfo girlTagInfo = new GirlTagInfo();
					girlTagInfo.setGirlTagInfoId(girlTagInfoId);
					girlTagId.setGirlTagInfo(girlTagInfo);
					GirlTag girlTag = new GirlTag();
					girlTag.setPrimaryKey(girlTagId);
					allTagGirlInfoIdTagList.add(girlTag);
				}
			}
		}
		girlInfoManager.allTagByGirlInfoId(allGirlInfoIdTagList, allTagGirlInfoIdTagList);

		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		return girllist();
	}
	
	public String girlsearch() {
		this.girlInfos = girlInfoManager.search(getGirlSearch(), -1, 0);
		this.zoneInfos = zoneInfoManager.list();
		this.categoryInfos = categoryInfoManager.list();
		this.agentInfos = agentInfoManager.list();
		this.countryInfos = countryInfoManager.list();
		if(getGirlSearch().getCountryInfoId() != null && !"".equals(getGirlSearch().getCountryInfoId())) {
			this.provinceInfos = provinceInfoManager.listByCountry(getGirlSearch().getCountryInfoId());
		}
		this.genderInfos = genderInfoManager.list();
		this.nationalityInfos = nationalityInfoManager.list();
		this.girlTagInfos = girlTagInfoManager.list();
		
		return SUCCESS;
	}
	
	public String girlInfo() {
		this.girlInfo = girlInfoManager.getGirlInfo(girlInfoId);
		this.girlComments = girlCommentManager.list(girlInfoId);
		
		return SUCCESS;
	}
	
	public String lineNotify() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}

	public String lineNotifyupdate() {
		HomeInfo homeInfo_ = homeInfoManager.getHomeInfo("0");
		homeInfo_.setHomeInfoId("0");
		homeInfo_.setLineClientId(homeInfo.getLineClientId());
		homeInfo_.setLineClientSecret(homeInfo.getLineClientSecret());
		homeInfo_.setLineRedirectUrl(homeInfo.getLineRedirectUrl());
		homeInfo_.setLineNotifyActive(homeInfo.getLineNotifyActive());
		
		if(homeInfoManager.getHomeInfo("0") != null) {
			homeInfoManager.update(homeInfo_);
		} else {
			homeInfoManager.add(homeInfo_);
		}
		
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		
		this.execute();
		
		return SUCCESS;
	}
	
	public String inform() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}

	public String informupdate() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			HomeInfo homeInfo_ = homeInfoManager.getHomeInfo("0");
			homeInfo_.setHomeInfoId("0");
			homeInfo_.setInform(UploadFileUtils.uploadImageinDescription(homeInfo.getInform(), sessionMap, userInfo));
			
			if(homeInfoManager.getHomeInfo("0") != null) {
				homeInfoManager.update(homeInfo_);
			} else {
				homeInfoManager.add(homeInfo_);
			}
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
			this.execute();
			
			return SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String description() {
		this.homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}

	public String descriptionupdate() {
		try {
			UserInfo userInfo = (UserInfo)sessionMap.get("adminInfo");
			HomeInfo homeInfo_ = homeInfoManager.getHomeInfo("0");
			homeInfo_.setHomeInfoId("0");
			homeInfo_.setDescriptionShopService(UploadFileUtils.uploadImageinDescription(homeInfo.getDescriptionShopService(), sessionMap, userInfo));
			homeInfo_.setDescriptionAgent(UploadFileUtils.uploadImageinDescription(homeInfo.getDescriptionAgent(), sessionMap, userInfo));
			homeInfo_.setDescriptionFreeAgent(UploadFileUtils.uploadImageinDescription(homeInfo.getDescriptionFreeAgent(), sessionMap, userInfo));
			homeInfo_.setDescriptionEnGirl(UploadFileUtils.uploadImageinDescription(homeInfo.getDescriptionEnGirl(), sessionMap, userInfo));
			
			if(homeInfoManager.getHomeInfo("0") != null) {
				homeInfoManager.update(homeInfo_);
			} else {
				homeInfoManager.add(homeInfo_);
			}
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			
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

	public AdminSearch getSearch() {
		return search;
	}

	public void setSearch(AdminSearch search) {
		this.search = search;
	}

	public List<GirlInfo> getGirlInfos() {
		return girlInfos;
	}

	public FrontSearch getGirlSearch() {
		return girlSearch;
	}

	public List<String> getAllsamelist() {
		return allsamelist;
	}

	public void setGirlInfos(List<GirlInfo> girlInfos) {
		this.girlInfos = girlInfos;
	}

	public void setGirlSearch(FrontSearch girlSearch) {
		this.girlSearch = girlSearch;
	}

	public void setAllsamelist(List<String> allsamelist) {
		this.allsamelist = allsamelist;
	}

	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
	}

	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}

	public List<CategoryInfo> getCategoryInfos() {
		return categoryInfos;
	}

	public List<AgentInfo> getAgentInfos() {
		return agentInfos;
	}

	public void setCategoryInfos(List<CategoryInfo> categoryInfos) {
		this.categoryInfos = categoryInfos;
	}

	public void setAgentInfos(List<AgentInfo> agentInfos) {
		this.agentInfos = agentInfos;
	}

	public List<CountryInfo> getCountryInfos() {
		return countryInfos;
	}

	public List<ProvinceInfo> getProvinceInfos() {
		return provinceInfos;
	}

	public List<GirlProvince> getGirlProvinces() {
		return girlProvinces;
	}

	public void setCountryInfos(List<CountryInfo> countryInfos) {
		this.countryInfos = countryInfos;
	}

	public void setProvinceInfos(List<ProvinceInfo> provinceInfos) {
		this.provinceInfos = provinceInfos;
	}

	public void setGirlProvinces(List<GirlProvince> girlProvinces) {
		this.girlProvinces = girlProvinces;
	}

	public List<String> getChecklist() {
		return checklist;
	}

	public void setChecklist(List<String> checklist) {
		this.checklist = checklist;
	}

	public List<GenderInfo> getGenderInfos() {
		return genderInfos;
	}

	public void setGenderInfos(List<GenderInfo> genderInfos) {
		this.genderInfos = genderInfos;
	}

	public List<NationalityInfo> getNationalityInfos() {
		return nationalityInfos;
	}

	public void setNationalityInfos(List<NationalityInfo> nationalityInfos) {
		this.nationalityInfos = nationalityInfos;
	}

	public List<GirlComment> getGirlComments() {
		return girlComments;
	}

	public void setGirlComments(List<GirlComment> girlComments) {
		this.girlComments = girlComments;
	}

	public String getGirlInfoId() {
		return girlInfoId;
	}

	public GirlInfo getGirlInfo() {
		return girlInfo;
	}

	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}

	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}

	public List<GirlTagInfo> getGirlTagInfos() {
		return girlTagInfos;
	}

	public void setGirlTagInfos(List<GirlTagInfo> girlTagInfos) {
		this.girlTagInfos = girlTagInfos;
	}

	public List<String> getGirlTagList() {
		return girlTagList;
	}

	public void setGirlTagList(List<String> girlTagList) {
		this.girlTagList = girlTagList;
	}


}
