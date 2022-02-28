package com.nightclub.view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.CategoryInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.CategoryInfo;
import com.nightclub.model.FileModel;
import com.nightclub.model.UserInfo;
import com.nightclub.util.ResourceBundleUtil;
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
    
    private List<CategoryInfo> categoryInfos;
    private CategoryInfo categoryInfo;
	
	private BasicInfoManager basicInfoManager;
	private UserInfoManager userInfoManager;
	private CategoryInfoManager categoryInfoManager;
	
	private List<BasicInfo> basicInfos;

	public BasicInfoAction() {
		basicInfoManager = new BasicInfoManager();
		userInfoManager = new UserInfoManager();
		categoryInfoManager = new CategoryInfoManager();
	}

	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.basicInfo = basicInfoManager.getBasicInfo(userInfo.getShopInfoId());
		this.categoryInfos = categoryInfoManager.list();
		
		if(this.basicInfo != null) {
			this.categoryInfo = categoryInfoManager.getCategoryInfo(basicInfo.getCategoryInfoId());
		} 
		
		if(this.categoryInfo == null) {
			this.categoryInfo = categoryInfoManager.getCategoryInfo(this.categoryInfos.get(0).getCategoryInfoId());
		}
		
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
            
            this.basicInfo.setDescription(UploadFileUtils.uploadImageinDescription(this.basicInfo.getDescription(), sessionMap, userInfo));
                        
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
		
		addActionMessage("You have been successfully updated");
		
		this.execute();
		
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

}
