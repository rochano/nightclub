package com.nightclub.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.controller.ZoneInfoManager;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.GirlLocation;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.ShopLocation;
import com.nightclub.model.UserInfo;
import com.nightclub.model.ZoneInfo;
import com.nightclub.util.LineApiUtils;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class BasicInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private BasicInfo basicInfo;
	private String basicInfoId;
	private String menu;
    private String shopLogoFileName;
    private String shopImageFileName;
    private String shopImage2FileName;
    private String shopImage3FileName;
    private String shopImage4FileName;
    private String shopImage5FileName;
    private String shopLogoFileNameDelete;
    private String shopImageFileNameDelete;
    private String shopImage2FileNameDelete;
    private String shopImage3FileNameDelete;
    private String shopImage4FileNameDelete;
    private String shopImage5FileNameDelete;
    private String code;
    private String lineToken;
    private String lineOauthUrl;
    private HomeInfo homeInfo;

    private List<CategoryInfo> categoryInfos;
    private CategoryInfo categoryInfo;
	
	private BasicInfoManager basicInfoManager;
	private UserInfoManager userInfoManager;
	private CategoryInfoManager categoryInfoManager;
	private ZoneInfoManager zoneInfoManager;
	private HomeInfoManager homeInfoManager;
	
	private List<BasicInfo> basicInfos;
	private List<ZoneInfo> zoneInfos;
	private List<String> shopLocations;

	public BasicInfoAction() {
		basicInfoManager = new BasicInfoManager();
		userInfoManager = new UserInfoManager();
		categoryInfoManager = new CategoryInfoManager();
		zoneInfoManager = new ZoneInfoManager();
		homeInfoManager = new HomeInfoManager();
	}

	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.basicInfo = basicInfoManager.getBasicInfo(userInfo.getShopInfoId());
		this.categoryInfos = categoryInfoManager.list();
		
		if(this.basicInfo != null) {
			this.categoryInfo = categoryInfoManager.getCategoryInfo(basicInfo.getCategoryInfoId());
		} else {
			this.basicInfo = new BasicInfo();
			this.basicInfo.setPhone(userInfo.getPhone());
		}
		sessionMap.put("basicInfo", basicInfo);
		
		if(this.categoryInfo == null) {
			this.categoryInfo = categoryInfoManager.getCategoryInfo(this.categoryInfos.get(0).getCategoryInfoId());
		}
		this.zoneInfos = zoneInfoManager.list();
		this.shopLocations = new ArrayList<String>();
		List<ShopLocation> shopLocations = basicInfoManager.getShopLocationListByShopInfoId(userInfo.getShopInfoId());
		if(shopLocations != null) {
			for(ShopLocation shopLocation : shopLocations) {
				this.shopLocations.add(shopLocation.getZoneInfo().getZoneInfoId());
			}
		}
		
		homeInfo = homeInfoManager.getHomeInfo("0");
		this.lineOauthUrl = LineApiUtils.getUrlForRequestCode(homeInfo.getLineClientId(), homeInfo.getLineRedirectUrl());
		
		return SUCCESS;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
//			String filePath = ResourceBundleUtil.getUploadPath();
            
            BasicInfo currentBasicInfo = basicInfoManager.getBasicInfo(userInfo.getShopInfoId());
            if(currentBasicInfo != null) {
        		if(currentBasicInfo.getLogoImg() != null && !currentBasicInfo.getLogoImg().isEmpty()) {
        			this.basicInfo.setLogoImg(currentBasicInfo.getLogoImg());
        		}
        		if(currentBasicInfo.getShopImg() != null && !currentBasicInfo.getShopImg().isEmpty()) {
        			this.basicInfo.setShopImg(currentBasicInfo.getShopImg());
        		}
        		if(currentBasicInfo.getShopImg2() != null && !currentBasicInfo.getShopImg2().isEmpty()) {
        			this.basicInfo.setShopImg2(currentBasicInfo.getShopImg2());
        		}
        		if(currentBasicInfo.getShopImg3() != null && !currentBasicInfo.getShopImg3().isEmpty()) {
        			this.basicInfo.setShopImg3(currentBasicInfo.getShopImg3());
        		}
        		if(currentBasicInfo.getShopImg4() != null && !currentBasicInfo.getShopImg4().isEmpty()) {
        			this.basicInfo.setShopImg4(currentBasicInfo.getShopImg4());
        		}
        		if(currentBasicInfo.getShopImg5() != null && !currentBasicInfo.getShopImg5().isEmpty()) {
        			this.basicInfo.setShopImg5(currentBasicInfo.getShopImg5());
        		}
        	}
            
            if(!getShopLogoFileName().isEmpty()) {
            	this.shopLogoFileName = UploadFileUtils.uploadImageApi(getShopLogoFileName(), sessionMap, userInfo);
//            	String fileName = this.shopLogoFileName;
//            	String extension = FilenameUtils.getExtension(this.shopLogoFileName);
//            	this.shopLogoFileName = UUID.randomUUID().toString().toUpperCase() + "." + extension;
//            	
//            	if(currentBasicInfo != null) {
//            		if(currentBasicInfo.getLogoImg() != null && !currentBasicInfo.getLogoImg().isEmpty()) {
//            			String oldName = currentBasicInfo.getLogoImg();
//            			this.shopLogoFileName = oldName.substring(0, oldName.lastIndexOf(".")) + "." + extension;
//            		}
//            	}
            	
//	            File fileToCreate = new File(filePath, this.shopLogoFileName);
//	            FileModel fileModel = (FileModel) sessionMap.get(fileName);
//	            FileUtils.writeByteArrayToFile(fileToCreate, fileModel.getImageInBytes());
	            this.basicInfo.setLogoImg(this.shopLogoFileName);
            }
            
            if(!getShopLogoFileNameDelete().isEmpty()) {
            	if(this.basicInfo.getLogoImg() != null && !this.basicInfo.getLogoImg().isEmpty()) {
            		UploadFileUtils.deleteImageApi(this.basicInfo.getLogoImg());
            		this.basicInfo.setLogoImg("");
            	}
            }
            
            if(!getShopImageFileName().isEmpty()) {
            	this.shopImageFileName = UploadFileUtils.uploadImageApi(getShopImageFileName(), sessionMap, userInfo);
//            	String fileName = this.shopImageFileName;
//            	String extension = FilenameUtils.getExtension(this.shopImageFileName);
//            	this.shopImageFileName = UUID.randomUUID().toString().toUpperCase() + "." + extension;
//            	
//            	if(currentBasicInfo != null) {
//            		if(currentBasicInfo.getShopImg() != null && !currentBasicInfo.getShopImg().isEmpty()) {
//            			String oldName = currentBasicInfo.getShopImg();
//            			this.shopImageFileName = oldName.substring(0, oldName.lastIndexOf(".")) + "." + extension;
//            		}
//            	}
            	
//            	File fileToCreate = new File(filePath, this.shopImageFileName);
//            	FileModel fileModel = (FileModel) sessionMap.get(fileName);
//            	FileUtils.writeByteArrayToFile(fileToCreate, fileModel.getImageInBytes());
	            this.basicInfo.setShopImg(this.shopImageFileName);
            }
            
            if(!getShopImageFileNameDelete().isEmpty()) {
            	if(this.basicInfo.getShopImg() != null && !this.basicInfo.getShopImg().isEmpty()) {
            		UploadFileUtils.deleteImageApi(this.basicInfo.getShopImg());
            		this.basicInfo.setShopImg("");
            	}
            }
            
            if(!getShopImage2FileName().isEmpty()) {
            	this.shopImage2FileName = UploadFileUtils.uploadImageApi(getShopImage2FileName(), sessionMap, userInfo);
	            this.basicInfo.setShopImg2(this.shopImage2FileName);
            }
            
            if(!getShopImage2FileNameDelete().isEmpty()) {
            	if(this.basicInfo.getShopImg2() != null && !this.basicInfo.getShopImg2().isEmpty()) {
            		UploadFileUtils.deleteImageApi(this.basicInfo.getShopImg2());
            		this.basicInfo.setShopImg2("");
            	}
            }
            
            if(!getShopImage3FileName().isEmpty()) {
            	this.shopImage3FileName = UploadFileUtils.uploadImageApi(getShopImage3FileName(), sessionMap, userInfo);
	            this.basicInfo.setShopImg3(this.shopImage3FileName);
            }
            
            if(!getShopImage3FileNameDelete().isEmpty()) {
            	if(this.basicInfo.getShopImg3() != null && !this.basicInfo.getShopImg3().isEmpty()) {
            		UploadFileUtils.deleteImageApi(this.basicInfo.getShopImg3());
            		this.basicInfo.setShopImg3("");
            	}
            }
            
            if(!getShopImage4FileName().isEmpty()) {
            	this.shopImage4FileName = UploadFileUtils.uploadImageApi(getShopImage4FileName(), sessionMap, userInfo);
	            this.basicInfo.setShopImg4(this.shopImage4FileName);
            }
            
            if(!getShopImage4FileNameDelete().isEmpty()) {
            	if(this.basicInfo.getShopImg4() != null && !this.basicInfo.getShopImg4().isEmpty()) {
            		UploadFileUtils.deleteImageApi(this.basicInfo.getShopImg4());
            		this.basicInfo.setShopImg4("");
            	}
            }
            
            if(!getShopImage5FileName().isEmpty()) {
            	this.shopImage5FileName = UploadFileUtils.uploadImageApi(getShopImage5FileName(), sessionMap, userInfo);
	            this.basicInfo.setShopImg5(this.shopImage5FileName);
            }
            
            if(!getShopImage5FileNameDelete().isEmpty()) {
            	if(this.basicInfo.getShopImg5() != null && !this.basicInfo.getShopImg5().isEmpty()) {
            		UploadFileUtils.deleteImageApi(this.basicInfo.getShopImg5());
            		this.basicInfo.setShopImg5("");
            	}
            }
            
            this.basicInfo.setDescription(UploadFileUtils.uploadImageinDescription(this.basicInfo.getDescription(), sessionMap, userInfo));

            this.basicInfo.getShopLocations().clear();
            ShopLocation shopLocation;
            for(String zoneInfoId : this.shopLocations) {
            	ZoneInfo zoneInfo = new ZoneInfo();
            	zoneInfo.setZoneInfoId(zoneInfoId);

            	shopLocation = new ShopLocation();
            	shopLocation.setZoneInfo(zoneInfo);
            	shopLocation.setBasicInfo(this.basicInfo);
				this.basicInfo.getShopLocations().add(shopLocation);
			}
            
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
		
//		if(basicInfoManager.validateShopCode(userInfo.getShopInfoId(), this.basicInfo.getShopCode()) != null) {
//			addActionError("Shop code is duplicated.");
//			this.categoryInfos = categoryInfoManager.list();
//			
//			if(this.basicInfo != null) {
//				this.categoryInfo = categoryInfoManager.getCategoryInfoByCode(basicInfo.getCategoryCode());
//			} else {
//				this.categoryInfo = categoryInfoManager.getCategoryInfoByCode(this.categoryInfos.get(0).getCategoryCode());
//			}
//			return INPUT;
//		}
		
		if(basicInfoManager.getBasicInfo(userInfo.getShopInfoId()) != null) {
			this.basicInfo.setShopInfoId(userInfo.getShopInfoId());
			this.basicInfo = basicInfoManager.update(this.basicInfo);
		} else {
			if(userInfo.getShopInfoId() != null) {
				this.basicInfo.setShopInfoId(userInfo.getShopInfoId());
				this.basicInfo = basicInfoManager.add(this.basicInfo);
			} else {
				this.basicInfo.setShopInfoId(UUID.randomUUID().toString().toUpperCase());
				this.basicInfo = basicInfoManager.add(this.basicInfo);
				userInfo.setShopInfoId(this.basicInfo.getShopInfoId());
				userInfo = userInfoManager.update(userInfo);
			}
		}
		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		
		this.execute();
		
		return SUCCESS;
	}
	
	public String lineTokenCallback() {
		try {
			HomeInfo homeInfo = homeInfoManager.getHomeInfo("0");
			this.lineToken = LineApiUtils.requestToken(this.code, homeInfo.getLineClientId(), homeInfo.getLineClientSecret(), homeInfo.getLineRedirectUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String list() {
		
		this.basicInfos = basicInfoManager.list();
		
		return SUCCESS;
	}

	public BasicInfo getBasicInfo() {
		return basicInfo;
	}

	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	
	public String getBasicInfoId() {
		return basicInfoId;
	}

	public void setBasicInfoId(String basicInfoId) {
		this.basicInfoId = basicInfoId;
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

	public String getShopLogoFileName() {
		return shopLogoFileName;
	}
	public String getShopImageFileName() {
		return shopImageFileName;
	}
	
	public void setShopLogoFileName(String shopLogoFileName) {
		this.shopLogoFileName = shopLogoFileName;
	}

	public void setShopImageFileName(String shopImageFileName) {
		this.shopImageFileName = shopImageFileName;
	}

	public List<CategoryInfo> getCategoryInfos() {
		return categoryInfos;
	}

	public void setCategoryInfos(List<CategoryInfo> categoryInfos) {
		this.categoryInfos = categoryInfos;
	}

	public CategoryInfo getCategoryInfo() {
		return categoryInfo;
	}

	public void setCategoryInfo(CategoryInfo categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	public List<BasicInfo> getBasicInfos() {
		return basicInfos;
	}

	public void setBasicInfos(List<BasicInfo> basicInfos) {
		this.basicInfos = basicInfos;
	}

	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
	}

	public List<String> getShopLocations() {
		return shopLocations;
	}

	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}

	public void setShopLocations(List<String> shopLocations) {
		this.shopLocations = shopLocations;
	}

	public String getShopLogoFileNameDelete() {
		return shopLogoFileNameDelete;
	}

	public String getShopImageFileNameDelete() {
		return shopImageFileNameDelete;
	}

	public void setShopLogoFileNameDelete(String shopLogoFileNameDelete) {
		this.shopLogoFileNameDelete = shopLogoFileNameDelete;
	}

	public void setShopImageFileNameDelete(String shopImageFileNameDelete) {
		this.shopImageFileNameDelete = shopImageFileNameDelete;
	}

	public String getShopImage2FileName() {
		return shopImage2FileName;
	}

	public String getShopImage3FileName() {
		return shopImage3FileName;
	}

	public String getShopImage4FileName() {
		return shopImage4FileName;
	}

	public String getShopImage5FileName() {
		return shopImage5FileName;
	}

	public String getShopImage2FileNameDelete() {
		return shopImage2FileNameDelete;
	}

	public String getShopImage3FileNameDelete() {
		return shopImage3FileNameDelete;
	}

	public String getShopImage4FileNameDelete() {
		return shopImage4FileNameDelete;
	}

	public String getShopImage5FileNameDelete() {
		return shopImage5FileNameDelete;
	}

	public void setShopImage2FileName(String shopImage2FileName) {
		this.shopImage2FileName = shopImage2FileName;
	}

	public void setShopImage3FileName(String shopImage3FileName) {
		this.shopImage3FileName = shopImage3FileName;
	}

	public void setShopImage4FileName(String shopImage4FileName) {
		this.shopImage4FileName = shopImage4FileName;
	}

	public void setShopImage5FileName(String shopImage5FileName) {
		this.shopImage5FileName = shopImage5FileName;
	}

	public void setShopImage2FileNameDelete(String shopImage2FileNameDelete) {
		this.shopImage2FileNameDelete = shopImage2FileNameDelete;
	}

	public void setShopImage3FileNameDelete(String shopImage3FileNameDelete) {
		this.shopImage3FileNameDelete = shopImage3FileNameDelete;
	}

	public void setShopImage4FileNameDelete(String shopImage4FileNameDelete) {
		this.shopImage4FileNameDelete = shopImage4FileNameDelete;
	}

	public void setShopImage5FileNameDelete(String shopImage5FileNameDelete) {
		this.shopImage5FileNameDelete = shopImage5FileNameDelete;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLineToken() {
		return lineToken;
	}

	public void setLineToken(String lineToken) {
		this.lineToken = lineToken;
	}

	public String getLineOauthUrl() {
		return lineOauthUrl;
	}

	public void setLineOauthUrl(String lineOauthUrl) {
		this.lineOauthUrl = lineOauthUrl;
	}

	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	public void setHomeInfo(HomeInfo homeInfo) {
		this.homeInfo = homeInfo;
	}

}
