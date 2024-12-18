package com.nightclub.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;
import org.cloudinary.json.JSONObject;

import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.GirlSettingManager;
import com.nightclub.controller.NationalityInfoManager;
import com.nightclub.controller.ShopGirlInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlLocation;
import com.nightclub.model.GirlSetting;
import com.nightclub.model.NationalityInfo;
import com.nightclub.model.ShopGirlInfo;
import com.nightclub.model.UserInfo;
import com.nightclub.model.ZoneInfo;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class ShopGirlInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private List<GirlInfo> girlInfos;
	private ShopGirlInfo girlInfo;
	private ShopGirlInfo girlSearch;
	private List<String> searchGirlLocations;
	private String girlInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private GirlSetting girlSetting;
	private ArrayList<String> ageList;
	private ArrayList<String> bustSizeList;
	private ArrayList<String> waistSizeList;
	private ArrayList<String> hipSizeList;
	private ArrayList<String> heightList;
	private ArrayList<String> weightList;
	private List<ZoneInfo> zoneInfos;
	private List<String> girlLocations;
	private List<NationalityInfo> nationalityInfos;

	private ShopGirlInfoManager girlInfoManager;
	private GirlSettingManager girlSettingManager;
	private BasicInfoManager basicInfoManager;
	private UserInfoManager userInfoManager;
	private ZoneInfoManager zoneInfoManager;
	private NationalityInfoManager nationalityInfoManager;

    private String pic1FileName;
    private String pic2FileName;
    private String pic3FileName;
    private String pic4FileName;
    private String pic5FileName;

	private List<String> availablelist;

	public ShopGirlInfoAction() {
		girlInfoManager = new ShopGirlInfoManager();
		girlSettingManager = new GirlSettingManager();
		basicInfoManager = new BasicInfoManager();
		userInfoManager = new UserInfoManager();
		zoneInfoManager = new ZoneInfoManager();
		nationalityInfoManager = new NationalityInfoManager();
	}
	
	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		if(getAction() != null) {
			if(getAction().equals("add")) {
				add();
			} else if(getAction().equals("update")) {
				update();
			}
		}
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		setFormValue(userInfo);
		
		return SUCCESS;
	}

	public String add() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			girlInfo.setGirlInfoId(UUID.randomUUID().toString().toUpperCase());
			girlInfo.setShopInfoId(userInfo.getShopInfoId());
			
			try {
	            if(!getPic1FileName().isEmpty()) {
//	            	this.pic1FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic1FileName);
	            	this.pic1FileName = UploadFileUtils.uploadImageApi(getPic1FileName(), sessionMap, userInfo);
	            	this.girlInfo.setPic1(this.pic1FileName);
	            }
	            
	            if(!getPic2FileName().isEmpty()) {
//	            	this.pic2FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic2FileName);
	            	this.pic2FileName = UploadFileUtils.uploadImageApi(getPic2FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic2(this.pic2FileName);
	            }
	            
	            if(!getPic3FileName().isEmpty()) {
//	            	this.pic3FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic3FileName);
	            	this.pic3FileName = UploadFileUtils.uploadImageApi(getPic3FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic3(this.pic3FileName);
	            }
	            
	            if(!getPic4FileName().isEmpty()) {
//	            	this.pic4FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic4FileName);
	            	this.pic4FileName = UploadFileUtils.uploadImageApi(getPic4FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic4(this.pic4FileName);
	            }
	            
	            if(!getPic5FileName().isEmpty()) {
//	            	this.pic5FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic5FileName);
	            	this.pic5FileName = UploadFileUtils.uploadImageApi(getPic5FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic5(this.pic5FileName);
	            }
	            
	            this.girlInfo.setDescription(UploadFileUtils.uploadImageinDescription(this.girlInfo.getDescription(), sessionMap, userInfo));
	            this.girlInfo.getGirlLocations().clear();
	            GirlLocation girlLocation;
	            for(String zoneInfoId : this.girlLocations) {
	            	ZoneInfo zoneInfo = new ZoneInfo();
	            	zoneInfo.setZoneInfoId(zoneInfoId);

	            	girlLocation = new GirlLocation();
	            	girlLocation.setZoneInfo(zoneInfo);
	            	girlLocation.setGirlInfo(this.girlInfo);
					this.girlInfo.getGirlLocations().add(girlLocation);
				}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            addActionError(e.getMessage());
	        }
			
			this.girlInfo.setCreatedDate(new Date());
			this.girlInfo.setCreatedBy(userInfo.getUserInfoId());
			if(userInfo.getShopInfoId() == null) {
				BasicInfo basicInfo = new BasicInfo();
				basicInfo.setShopInfoId(UUID.randomUUID().toString().toUpperCase());
				basicInfo = basicInfoManager.add(basicInfo);
				userInfo.setShopInfoId(basicInfo.getShopInfoId());
				userInfo = userInfoManager.update(userInfo);
				girlInfo.setShopInfoId(userInfo.getShopInfoId());
			}
			girlInfoManager.add(this.girlInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_add"));
			this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
			setFormValue(userInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		setFormValue(userInfo);
		
		return INPUT;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			GirlInfo currentGirlInfo = girlInfoManager.getGirlInfo(this.girlInfo.getGirlInfoId());
			
			girlInfo.setShopInfoId(userInfo.getShopInfoId());
			
			try {
				if(!getPic1FileName().isEmpty()) {
					
//					if(currentGirlInfo.getPic1() != null && !currentGirlInfo.getPic1().isEmpty()) {
//						this.pic1FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic1FileName, currentGirlInfo.getPic1());
//            		} else {					
//            			this.pic1FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic1FileName);
//            		}
					this.pic1FileName = UploadFileUtils.uploadImageApi(getPic1FileName(), sessionMap, userInfo);
					this.girlInfo.setPic1(this.pic1FileName);
	            } 
				else if(currentGirlInfo.getPic1() != null && !currentGirlInfo.getPic1().isEmpty()) {
	            	this.girlInfo.setPic1(currentGirlInfo.getPic1());
	            }
	            
	            if(!getPic2FileName().isEmpty()) {
//	            	if(currentGirlInfo.getPic2() != null && !currentGirlInfo.getPic2().isEmpty()) {
//	            		this.pic2FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic2FileName, currentGirlInfo.getPic2());
//            		} else {					
//            			this.pic2FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic2FileName);
//            		}
	            	this.pic2FileName = UploadFileUtils.uploadImageApi(getPic2FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic2(this.pic2FileName);
	            }
	            else if(currentGirlInfo.getPic2() != null && !currentGirlInfo.getPic2().isEmpty()) {
	            	this.girlInfo.setPic2(currentGirlInfo.getPic2());
	            }
	            
	            if(!getPic3FileName().isEmpty()) {
//	            	if(currentGirlInfo.getPic3() != null && !currentGirlInfo.getPic3().isEmpty()) {
//	            		this.pic3FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic3FileName, currentGirlInfo.getPic3());
//            		} else {					
//            			this.pic3FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic3FileName);
//            		}
	            	this.pic3FileName = UploadFileUtils.uploadImageApi(getPic3FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic3(this.pic3FileName);
	            }
	            else if(currentGirlInfo.getPic3() != null && !currentGirlInfo.getPic3().isEmpty()) {
	            	this.girlInfo.setPic3(currentGirlInfo.getPic3());
	            }
	            
	            if(!getPic4FileName().isEmpty()) {
//	            	if(currentGirlInfo.getPic4() != null && !currentGirlInfo.getPic4().isEmpty()) {
//	            		this.pic4FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic4FileName, currentGirlInfo.getPic4());
//            		} else {					
//            			this.pic4FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic4FileName);
//            		}
	            	this.pic4FileName = UploadFileUtils.uploadImageApi(getPic4FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic4(this.pic4FileName);
	            }
	            else if(currentGirlInfo.getPic4() != null && !currentGirlInfo.getPic4().isEmpty()) {
	            	this.girlInfo.setPic4(currentGirlInfo.getPic4());
	            }
	            
	            if(!getPic5FileName().isEmpty()) {
//	            	if(currentGirlInfo.getPic5() != null && !currentGirlInfo.getPic5().isEmpty()) {
//	            		this.pic5FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic5FileName, currentGirlInfo.getPic5());
//            		} else {					
//            			this.pic5FileName = UploadFileUtils.writeByteArrayToFile(sessionMap, this.pic5FileName);
//            		}
	            	this.pic5FileName = UploadFileUtils.uploadImageApi(getPic5FileName(), sessionMap, userInfo);
		            this.girlInfo.setPic5(this.pic5FileName);
	            }
	            else if(currentGirlInfo.getPic5() != null && !currentGirlInfo.getPic5().isEmpty()) {
	            	this.girlInfo.setPic5(currentGirlInfo.getPic5());
	            }
	            
	            this.girlInfo.setDescription(UploadFileUtils.uploadImageinDescription(this.girlInfo.getDescription(), sessionMap, userInfo));
	            this.girlInfo.getGirlLocations().clear();
	            GirlLocation girlLocation;
	            for(String zoneInfoId : this.girlLocations) {
	            	ZoneInfo zoneInfo = new ZoneInfo();
	            	zoneInfo.setZoneInfoId(zoneInfoId);

	            	girlLocation = new GirlLocation();
	            	girlLocation.setZoneInfo(zoneInfo);
	            	girlLocation.setGirlInfo(this.girlInfo);
					this.girlInfo.getGirlLocations().add(girlLocation);
				}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            addActionError(e.getMessage());
	        }
			
			this.girlInfo.setUpdatedDate(new Date());
			this.girlInfo.setUpdatedBy(userInfo.getUserInfoId());
			girlInfoManager.update(this.girlInfo);
			
			addActionMessage(getTexts("global_th").getString("global.message_success_update"));
			this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
			setFormValue(userInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		setFormValue(userInfo);
		
		return INPUT;
	}
	
	public String edit() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.girlInfo = (ShopGirlInfo)girlInfoManager.getGirlInfo(this.girlInfoId);
		
//		String filePath = ResourceBundleUtil.getUploadPath();
        
		if(this.girlInfo.getPic1() == null){// || !new File(filePath, this.girlInfo.getPic1()).exists()) {
        	this.girlInfo.setPic1("");
        }
		if(this.girlInfo.getPic2() == null){// || !new File(filePath, this.girlInfo.getPic2()).exists()) {
        	this.girlInfo.setPic2("");
        }
		if(this.girlInfo.getPic3() == null){// || !new File(filePath, this.girlInfo.getPic3()).exists()) {
        	this.girlInfo.setPic3("");
        }
		if(this.girlInfo.getPic4() == null){// || !new File(filePath, this.girlInfo.getPic4()).exists()) {
        	this.girlInfo.setPic4("");
        }
		if(this.girlInfo.getPic5() == null){// || !new File(filePath, this.girlInfo.getPic5()).exists()) {
        	this.girlInfo.setPic5("");
        }
        if(this.girlInfo.getPic5() == null){// || !new File(filePath, this.girlInfo.getPic5()).exists()) {
        	this.girlInfo.setPic5("");
        }
		this.showInfo = true;
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		this.girlLocations = new ArrayList<String>();
		List<GirlLocation> girlLocations = girlInfoManager.getGirlLocationListByGirlInfoId(this.girlInfo.getGirlInfoId());
		if(girlLocations != null) {
			for(GirlLocation girlLocation : girlLocations) {
				this.girlLocations.add(girlLocation.getZoneInfo().getZoneInfoId());
			}
		}
		setFormValue(userInfo);
		return SUCCESS;
	}

	public String delete() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		girlInfoManager.delete(getGirlInfoId());
		addActionMessage(getTexts("global_th").getString("global.message_success_delete"));
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		setFormValue(userInfo);
		return SUCCESS;
	}
	
	public String search() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.girlSearch.setShopInfoId(userInfo.getShopInfoId());
		this.girlInfos = girlInfoManager.search(this.girlSearch, this.searchGirlLocations);
		setFormValue(userInfo);
		return SUCCESS;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getGirlInfoId() {
		return girlInfoId;
	}

	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}

	public List<GirlInfo> getGirlInfos() {
		return girlInfos;
	}

	public void setGirlInfos(List<GirlInfo> girlInfos) {
		this.girlInfos = girlInfos;
	}

	public ShopGirlInfo getGirlInfo() {
		return girlInfo;
	}

	public void setGirlInfo(ShopGirlInfo girlInfo) {
		this.girlInfo = girlInfo;
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

	public String getPic1FileName() {
		return pic1FileName;
	}

	public String getPic2FileName() {
		return pic2FileName;
	}

	public String getPic3FileName() {
		return pic3FileName;
	}

	public String getPic4FileName() {
		return pic4FileName;
	}

	public String getPic5FileName() {
		return pic5FileName;
	}

	public void setPic1FileName(String pic1FileName) {
		this.pic1FileName = pic1FileName;
	}

	public void setPic2FileName(String pic2FileName) {
		this.pic2FileName = pic2FileName;
	}

	public void setPic3FileName(String pic3FileName) {
		this.pic3FileName = pic3FileName;
	}

	public void setPic4FileName(String pic4FileName) {
		this.pic4FileName = pic4FileName;
	}

	public void setPic5FileName(String pic5FileName) {
		this.pic5FileName = pic5FileName;
	}

	public ShopGirlInfo getGirlSearch() {
		return girlSearch;
	}

	public void setGirlSearch(ShopGirlInfo girlSearch) {
		this.girlSearch = girlSearch;
	}

	public String girlsupdate() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		if(getAvailablelist() == null) {
			setAvailablelist(new ArrayList<String>());
		}

		Iterator<String> it = getAvailablelist().iterator();
		List<String> allGirlInfoIdList = new ArrayList<String>();
		List<String> availableGirlInfoIdList = new ArrayList<String>();
		while(it.hasNext()) {
			JSONObject jsonData = new JSONObject(it.next());
			allGirlInfoIdList.add(jsonData.getString("id"));
			if (jsonData.getBoolean("checked")) {
				availableGirlInfoIdList.add(jsonData.getString("id"));
			}
		}
		girlInfoManager.avaiableByGirlInfoId(allGirlInfoIdList, availableGirlInfoIdList);
		this.girlInfos = girlInfoManager.list(userInfo.getShopInfoId());
		setFormValue(userInfo);
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		return SUCCESS;
	}

	public List<String> getAvailablelist() {
		return availablelist;
	}

	public void setAvailablelist(List<String> availablelist) {
		this.availablelist = availablelist;
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

	public List<String> getAgeList() {
		return ageList;
	}

	public List<String> getBustSizeList() {
		return bustSizeList;
	}

	public List<String> getWaistSizeList() {
		return waistSizeList;
	}

	public List<String> getHipSizeList() {
		return hipSizeList;
	}

	public List<String> getHeightList() {
		return heightList;
	}

	public List<String> getWeightList() {
		return weightList;
	}

	public void setAgeList(ArrayList<String> ageList) {
		this.ageList = ageList;
	}

	public void setBustSizeList(ArrayList<String> bustSizeList) {
		this.bustSizeList = bustSizeList;
	}

	public void setWaistSizeList(ArrayList<String> waistSizeList) {
		this.waistSizeList = waistSizeList;
	}

	public void setHipSizeList(ArrayList<String> hipSizeList) {
		this.hipSizeList = hipSizeList;
	}

	public void setHeightList(ArrayList<String> heightList) {
		this.heightList = heightList;
	}

	public void setWeightList(ArrayList<String> weightList) {
		this.weightList = weightList;
	}

	private ArrayList<String> makeList(Integer from, Integer to) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=from; i<=to;i++) {
			list.add(String.valueOf(i));
		}
		return list;
	}
	
	private void setFormValue(UserInfo userInfo) {
		this.girlSetting = girlSettingManager.getGirlSetting();
		this.ageList = makeList(girlSetting.getAgeFrom(), girlSetting.getAgeTo());
		this.bustSizeList = makeList(girlSetting.getBustSizeFrom(), girlSetting.getBustSizeTo());
		this.waistSizeList = makeList(girlSetting.getWaistSizeFrom(), girlSetting.getWaistSizeTo());
		this.hipSizeList = makeList(girlSetting.getHipSizeFrom(), girlSetting.getHipSizeTo());
		this.heightList = makeList(girlSetting.getHeightFrom(), girlSetting.getHeightTo());
		this.weightList = makeList(girlSetting.getWeightFrom(), girlSetting.getWeightTo());
		this.zoneInfos = zoneInfoManager.list();
		this.nationalityInfos = nationalityInfoManager.list();
	}

	public List<String> getSearchGirlLocations() {
		return searchGirlLocations;
	}

	public void setSearchGirlLocations(List<String> searchGirlLocations) {
		this.searchGirlLocations = searchGirlLocations;
	}

	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
	}

	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}

	public List<String> getGirlLocations() {
		return girlLocations;
	}

	public void setGirlLocations(List<String> girlLocations) {
		this.girlLocations = girlLocations;
	}

	public List<NationalityInfo> getNationalityInfos() {
		return nationalityInfos;
	}

	public void setNationalityInfos(List<NationalityInfo> nationalityInfos) {
		this.nationalityInfos = nationalityInfos;
	}
}
