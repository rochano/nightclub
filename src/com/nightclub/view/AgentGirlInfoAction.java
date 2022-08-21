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

import com.nightclub.controller.AgentGirlInfoManager;
import com.nightclub.controller.AgentInfoManager;
import com.nightclub.controller.CountryInfoManager;
import com.nightclub.controller.GirlSettingManager;
import com.nightclub.controller.NationalityInfoManager;
import com.nightclub.controller.ProvinceInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.AgentGirlInfo;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.CountryInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlProvince;
import com.nightclub.model.GirlService;
import com.nightclub.model.GirlServiceInfo;
import com.nightclub.model.GirlSetting;
import com.nightclub.model.NationalityInfo;
import com.nightclub.model.ProvinceInfo;
import com.nightclub.model.UserInfo;
import com.nightclub.model.ZoneInfo;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AgentGirlInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());

	private Map<String, Object> sessionMap;
	private List<GirlInfo> girlInfos;
	private AgentGirlInfo girlInfo;
	private AgentGirlInfo girlSearch;
//	private List<String> searchGirlLocations;
	private List<String> searchGirlProvinces;
	private List<ProvinceInfo> searchProvinceInfos;
	private String girlInfoId;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private List<GirlServiceInfo> girlServiceInfos;
	private List<String> girlServices;
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
	private List<CountryInfo> countryInfos;
	private List<ProvinceInfo> provinceInfos;
	private List<String> girlProvinces;

	private AgentGirlInfoManager girlInfoManager;
	private GirlSettingManager girlSettingManager;
	private ZoneInfoManager zoneInfoManager;
	private AgentInfoManager agentInfoManager;
	private UserInfoManager userInfoManager;
	private NationalityInfoManager nationalityInfoManager;
	private CountryInfoManager countryInfoManager;
	private ProvinceInfoManager provinceInfoManager;
	
    private String pic1FileName;
    private String pic2FileName;
    private String pic3FileName;
    private String pic4FileName;
    private String pic5FileName;
    
	private List<String> availablelist;

	public AgentGirlInfoAction() {
		girlInfoManager = new AgentGirlInfoManager();
		girlSettingManager = new GirlSettingManager();
		zoneInfoManager = new ZoneInfoManager();
		girlServices = new ArrayList();
		agentInfoManager = new AgentInfoManager();
		userInfoManager = new UserInfoManager();
		nationalityInfoManager = new NationalityInfoManager();
		countryInfoManager = new CountryInfoManager();
		provinceInfoManager = new ProvinceInfoManager();
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
		
		this.girlServices = new ArrayList<String>();
		this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
		setFormValue(userInfo);
		
		return SUCCESS;
	}

	public String add() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			girlInfo.setGirlInfoId(UUID.randomUUID().toString().toUpperCase());
			girlInfo.setAgentInfoId(userInfo.getAgentInfoId());
			
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
	            this.girlInfo.getGirlServices().clear();
	            GirlService girlService;
	            for(String girlServiceInfoId : this.girlServices) {
	            	GirlServiceInfo girlServiceInfo = new GirlServiceInfo();
	            	girlServiceInfo.setGirlServiceInfoId(girlServiceInfoId);

	            	girlService = new GirlService();
	            	girlService.setGirlServiceInfo(girlServiceInfo);
	            	girlService.setFreeAgentGirlInfo(this.girlInfo);
					this.girlInfo.getGirlServices().add(girlService);
				}
	            /*this.girlInfo.getGirlLocations().clear();
	            GirlLocation girlLocation;
	            for(String zoneInfoId : this.girlLocations) {
	            	ZoneInfo zoneInfo = new ZoneInfo();
	            	zoneInfo.setZoneInfoId(zoneInfoId);

	            	girlLocation = new GirlLocation();
	            	girlLocation.setZoneInfo(zoneInfo);
	            	girlLocation.setGirlInfo(this.girlInfo);
					this.girlInfo.getGirlLocations().add(girlLocation);
				}*/
	            this.girlInfo.getGirlProvinces().clear();
	            GirlProvince girlProvince;
	            for(String provinceInfoId : this.girlProvinces) {
	            	ProvinceInfo provinceInfo = new ProvinceInfo();
	            	provinceInfo.setProvinceInfoId(provinceInfoId);

	            	girlProvince = new GirlProvince();
	            	girlProvince.setProvinceInfo(provinceInfo);
	            	girlProvince.setGirlInfo(this.girlInfo);
					this.girlInfo.getGirlProvinces().add(girlProvince);
				}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            addActionError(e.getMessage());
	        }
			
			this.girlInfo.setCreatedDate(new Date());
			this.girlInfo.setCreatedBy(userInfo.getUserInfoId());
			if(userInfo.getAgentInfoId() == null) {
				AgentInfo agentInfo = new AgentInfo();
				agentInfo.setAgentInfoId(UUID.randomUUID().toString().toUpperCase());
				agentInfo = agentInfoManager.add(agentInfo);
				userInfo.setAgentInfoId(agentInfo.getAgentInfoId());
				userInfo = userInfoManager.update(userInfo);
				girlInfo.setAgentInfoId(userInfo.getAgentInfoId());
			}
			girlInfoManager.add(this.girlInfo);
			
			addActionMessage(getText("global.message_success_add"));
			this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
			setFormValue(userInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
		setFormValue(userInfo);
		
		return INPUT;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			GirlInfo currentGirlInfo = girlInfoManager.getGirlInfo(this.girlInfo.getGirlInfoId());
			
			girlInfo.setAgentInfoId(userInfo.getAgentInfoId());
			
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
	            this.girlInfo.getGirlServices().clear();
	            GirlService girlService;
	            for(String girlServiceInfoId : this.girlServices) {
	            	GirlServiceInfo girlServiceInfo = new GirlServiceInfo();
	            	girlServiceInfo.setGirlServiceInfoId(girlServiceInfoId);

	            	girlService = new GirlService();
	            	girlService.setGirlServiceInfo(girlServiceInfo);
	            	girlService.setFreeAgentGirlInfo(this.girlInfo);
	            	this.girlInfo.getGirlServices().add(girlService);
				}
	            /*this.girlInfo.getGirlLocations().clear();
	            GirlLocation girlLocation;
	            for(String zoneInfoId : this.girlLocations) {
	            	ZoneInfo zoneInfo = new ZoneInfo();
	            	zoneInfo.setZoneInfoId(zoneInfoId);

	            	girlLocation = new GirlLocation();
	            	girlLocation.setZoneInfo(zoneInfo);
	            	girlLocation.setGirlInfo(this.girlInfo);
					this.girlInfo.getGirlLocations().add(girlLocation);
				}*/
	            this.girlInfo.getGirlProvinces().clear();
	            GirlProvince girlProvince;
	            for(String provinceInfoId : this.girlProvinces) {
	            	ProvinceInfo provinceInfo = new ProvinceInfo();
	            	provinceInfo.setProvinceInfoId(provinceInfoId);

	            	girlProvince = new GirlProvince();
	            	girlProvince.setProvinceInfo(provinceInfo);
	            	girlProvince.setGirlInfo(this.girlInfo);
					this.girlInfo.getGirlProvinces().add(girlProvince);
				}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            addActionError(e.getMessage());
	        }
			
			this.girlInfo.setUpdatedDate(new Date());
			this.girlInfo.setUpdatedBy(userInfo.getUserInfoId());
			girlInfoManager.update(this.girlInfo);
			
			addActionMessage(getText("global.message_success_update"));
			this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
			setFormValue(userInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
		setFormValue(userInfo);
		
		return INPUT;
	}
	
	public String edit() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.girlInfo = (AgentGirlInfo)girlInfoManager.getGirlInfo(this.girlInfoId);
		
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
		this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
		setFormValue(userInfo);
		this.girlServices = new ArrayList<String>();
		List<GirlService> girlServices = girlInfoManager.getGirlServiceListByGirlInfoId(this.girlInfo.getGirlInfoId());
		if(girlServices != null) {
			for(GirlService girlService : girlServices) {
				this.girlServices.add(girlService.getGirlServiceInfo().getGirlServiceInfoId());
			}
		}
//		this.girlLocations = new ArrayList<String>();
//		List<GirlLocation> girlLocations = girlInfoManager.getGirlLocationListByGirlInfoId(this.girlInfo.getGirlInfoId());
//		if(girlLocations != null) {
//			for(GirlLocation girlLocation : girlLocations) {
//				this.girlLocations.add(girlLocation.getZoneInfo().getZoneInfoId());
//			}
//		}
		if(this.girlInfo.getCountryInfoId() != null && !"".equals(this.girlInfo.getCountryInfoId())) {
			this.provinceInfos = provinceInfoManager.listByCountry(this.girlInfo.getCountryInfoId());
		} else if(this.countryInfos.size() > 0){
			this.provinceInfos = provinceInfoManager.listByCountry(this.countryInfos.get(0).getCountryInfoId());
		}
		this.girlProvinces = new ArrayList<String>();
		List<GirlProvince> girlProvinces = girlInfoManager.getGirlProvinceListByGirlInfoId(this.girlInfo.getGirlInfoId());
		if(girlProvinces != null) {
			for(GirlProvince girlProvince : girlProvinces) {
				this.girlProvinces.add(girlProvince.getProvinceInfo().getProvinceInfoId());
			}
		}
		return SUCCESS;
	}

	public String delete() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		girlInfoManager.delete(getGirlInfoId());
		addActionMessage(getText("global.message_success_delete"));
		this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
		setFormValue(userInfo);
		return SUCCESS;
	}
	
	public String search() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.girlSearch.setAgentInfoId(userInfo.getAgentInfoId());
		this.girlInfos = girlInfoManager.search(this.girlSearch, this.searchGirlProvinces);
		setFormValue(userInfo);
		if(this.girlSearch.getCountryInfoId() != null && !"".equals(this.girlSearch.getCountryInfoId())) {
			this.searchProvinceInfos = provinceInfoManager.listByCountry(this.girlSearch.getCountryInfoId());
		}
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

	public AgentGirlInfo getGirlInfo() {
		return girlInfo;
	}

	public void setGirlInfo(AgentGirlInfo girlInfo) {
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

	public AgentGirlInfo getGirlSearch() {
		return girlSearch;
	}

	public void setGirlSearch(AgentGirlInfo girlSearch) {
		this.girlSearch = girlSearch;
	}

	public List<GirlServiceInfo> getGirlServiceInfos() {
		return girlServiceInfos;
	}

	public void setGirlServiceInfos(List<GirlServiceInfo> girlServiceInfos) {
		this.girlServiceInfos = girlServiceInfos;
	}

	public List<String> getGirlServices() {
		return girlServices;
	}

	public void setGirlServices(List<String> girlServices) {
		this.girlServices = girlServices;
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
		this.girlInfos = girlInfoManager.list(userInfo.getAgentInfoId());
		addActionMessage(getText("global.message_success_update"));
		setFormValue(userInfo);
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

	public ArrayList<String> getAgeList() {
		return ageList;
	}

	public ArrayList<String> getBustSizeList() {
		return bustSizeList;
	}

	public ArrayList<String> getWaistSizeList() {
		return waistSizeList;
	}

	public ArrayList<String> getHipSizeList() {
		return hipSizeList;
	}

	public ArrayList<String> getHeightList() {
		return heightList;
	}

	public ArrayList<String> getWeightList() {
		return weightList;
	}

	public void setGirlSetting(GirlSetting girlSetting) {
		this.girlSetting = girlSetting;
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
		this.girlServiceInfos = girlInfoManager.getGirlServiceInfoList();
		this.girlSetting = girlSettingManager.getGirlSetting();
		this.ageList = makeList(girlSetting.getAgeFrom(), girlSetting.getAgeTo());
		this.bustSizeList = makeList(girlSetting.getBustSizeFrom(), girlSetting.getBustSizeTo());
		this.waistSizeList = makeList(girlSetting.getWaistSizeFrom(), girlSetting.getWaistSizeTo());
		this.hipSizeList = makeList(girlSetting.getHipSizeFrom(), girlSetting.getHipSizeTo());
		this.heightList = makeList(girlSetting.getHeightFrom(), girlSetting.getHeightTo());
		this.weightList = makeList(girlSetting.getWeightFrom(), girlSetting.getWeightTo());
		this.zoneInfos = zoneInfoManager.list();
		this.nationalityInfos = nationalityInfoManager.list();
		this.countryInfos = countryInfoManager.list();
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

//	public List<String> getSearchGirlLocations() {
//		return searchGirlLocations;
//	}
//
//	public void setSearchGirlLocations(List<String> searchGirlLocations) {
//		this.searchGirlLocations = searchGirlLocations;
//	}

	public List<NationalityInfo> getNationalityInfos() {
		return nationalityInfos;
	}

	public void setNationalityInfos(List<NationalityInfo> nationalityInfos) {
		this.nationalityInfos = nationalityInfos;
	}

	public List<String> getGirlProvinces() {
		return girlProvinces;
	}

	public void setGirlProvinces(List<String> girlProvinces) {
		this.girlProvinces = girlProvinces;
	}

	public List<CountryInfo> getCountryInfos() {
		return countryInfos;
	}

	public List<ProvinceInfo> getProvinceInfos() {
		return provinceInfos;
	}

	public void setCountryInfos(List<CountryInfo> countryInfos) {
		this.countryInfos = countryInfos;
	}

	public void setProvinceInfos(List<ProvinceInfo> provinceInfos) {
		this.provinceInfos = provinceInfos;
	}

	public List<String> getSearchGirlProvinces() {
		return searchGirlProvinces;
	}

	public void setSearchGirlProvinces(List<String> searchGirlProvinces) {
		this.searchGirlProvinces = searchGirlProvinces;
	}

	public List<ProvinceInfo> getSearchProvinceInfos() {
		return searchProvinceInfos;
	}

	public void setSearchProvinceInfos(List<ProvinceInfo> searchProvinceInfos) {
		this.searchProvinceInfos = searchProvinceInfos;
	}
}
