package com.nightclub.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.FreeAgentGirlInfoManager;
import com.nightclub.controller.GirlSettingManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.FreeAgentGirlInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.GirlService;
import com.nightclub.model.GirlServiceInfo;
import com.nightclub.model.GirlSetting;
import com.nightclub.model.UserInfo;
import com.nightclub.model.ZoneInfo;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class FreeAgentGirlInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private FreeAgentGirlInfo girlInfo;
	private String girlInfoId;
	private String menu;
	
	private FreeAgentGirlInfoManager girlInfoManager;
	private UserInfoManager userInfoManager;
	private GirlSettingManager girlSettingManager;
	private ZoneInfoManager zoneInfoManager;
 
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
	
    private String pic1FileName;
    private String pic2FileName;
    private String pic3FileName;
    private String pic4FileName;
    private String pic5FileName;

	public FreeAgentGirlInfoAction() {
		userInfoManager = new UserInfoManager();
		girlInfoManager = new FreeAgentGirlInfoManager();
		girlSettingManager = new GirlSettingManager();
		zoneInfoManager = new ZoneInfoManager();
		girlServices = new ArrayList();
	}

	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.girlInfo = (FreeAgentGirlInfo)girlInfoManager.getGirlInfo(userInfo.getGirlInfoId());
		if (this.girlInfo == null) {
			this.girlInfo = new FreeAgentGirlInfo();
		}
		this.girlServiceInfos = girlInfoManager.getGirlServiceList();
		setFormValue(userInfo);
		this.girlServices = new ArrayList<String>();
		List<GirlService> girlServices = girlInfoManager.getGirlServiceListByGirlInfoId(this.girlInfo.getGirlInfoId());
		if(girlServices != null) {
			for(GirlService girlService : girlServices) {
				this.girlServices.add(girlService.getGirlServiceInfo().getGirlServiceInfoId());
			}
		}

		return SUCCESS;
	}

	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
			GirlInfo currentGirlInfo = girlInfoManager.getGirlInfo(userInfo.getGirlInfoId());
			if (currentGirlInfo == null) {
				currentGirlInfo = new FreeAgentGirlInfo();
			}

			girlInfo.setGirlInfoId(userInfo.getGirlInfoId());
			
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
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            addActionError(e.getMessage());
	        }
			
			this.girlInfo.setUpdatedDate(new Date());
			this.girlInfo.setUpdatedBy(userInfo.getUsername());

			if(girlInfoManager.getGirlInfo(userInfo.getGirlInfoId()) != null) {
				girlInfoManager.update(this.girlInfo);
			} else {
				if(userInfo.getGirlInfoId() != null) {
					this.girlInfo.setGirlInfoId(userInfo.getGirlInfoId());
				} else {
					this.girlInfo.setGirlInfoId(UUID.randomUUID().toString().toUpperCase());
					userInfo.setGirlInfoId(this.girlInfo.getGirlInfoId());
					userInfo = userInfoManager.update(userInfo);
				}
				girlInfoManager.add(this.girlInfo);
			}
			
			addActionMessage("You have been successfully updated");
			setFormValue(userInfo);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return INPUT;
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

	public FreeAgentGirlInfo getGirlInfo() {
		return girlInfo;
	}

	public void setGirlInfo(FreeAgentGirlInfo girlInfo) {
		this.girlInfo = girlInfo;
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

	public List<String> getGirlServices() {
		return girlServices;
	}

	public void setGirlServices(List<String> girlServices) {
		this.girlServices = girlServices;
	}

	public UserInfoManager getUserInfoManager() {
		return userInfoManager;
	}

	public void setUserInfoManager(UserInfoManager userInfoManager) {
		this.userInfoManager = userInfoManager;
	}

	public List<GirlServiceInfo> getGirlServiceInfos() {
		return girlServiceInfos;
	}

	public void setGirlServiceInfos(List<GirlServiceInfo> girlServiceInfos) {
		this.girlServiceInfos = girlServiceInfos;
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

	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
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

	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}
	
	private ArrayList<String> makeList(Integer from, Integer to) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=from; i<=to;i++) {
			list.add(String.valueOf(i));
		}
		return list;
	}
	
	private void setFormValue(UserInfo userInfo) {
		this.girlServiceInfos = girlInfoManager.getGirlServiceList();
		this.girlSetting = girlSettingManager.getGirlSetting();
		this.ageList = makeList(girlSetting.getAgeFrom(), girlSetting.getAgeTo());
		this.bustSizeList = makeList(girlSetting.getBustSizeFrom(), girlSetting.getBustSizeTo());
		this.waistSizeList = makeList(girlSetting.getWaistSizeFrom(), girlSetting.getWaistSizeTo());
		this.hipSizeList = makeList(girlSetting.getHipSizeFrom(), girlSetting.getHipSizeTo());
		this.heightList = makeList(girlSetting.getHeightFrom(), girlSetting.getHeightTo());
		this.weightList = makeList(girlSetting.getWeightFrom(), girlSetting.getWeightTo());
		this.zoneInfos = zoneInfoManager.list();
	}
}