package com.nightclub.view;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.AgentInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.UserInfo;
import com.nightclub.util.LineApiUtils;
import com.nightclub.util.UploadFileUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AgentInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());

	private Map<String, Object> sessionMap;
	private AgentInfo agentInfo;
	private String agentInfoId;
	private String menu;
    private String agentLogoFileName;
    private String code;
    private String lineToken;
    private String lineOauthUrl;
    private HomeInfo homeInfo;
    private String phone;
    private String email;
 
	private AgentInfoManager agentInfoManager;
	private UserInfoManager userInfoManager;
	private HomeInfoManager homeInfoManager;

	public AgentInfoAction() {
		agentInfoManager = new AgentInfoManager();
		userInfoManager = new UserInfoManager();
		homeInfoManager = new HomeInfoManager();
	}

	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.agentInfo = agentInfoManager.getAgentInfo(userInfo.getAgentInfoId());
		this.phone = userInfo.getPhone();
		this.email = userInfo.getEmail();
		sessionMap.put("agentInfo", agentInfo);
		
		homeInfo = homeInfoManager.getHomeInfo("0");
		this.lineOauthUrl = LineApiUtils.getUrlForRequestCode(homeInfo.getLineClientId(), homeInfo.getLineRedirectUrl());

		return SUCCESS;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		try {
//			String filePath = ResourceBundleUtil.getUploadPath();
			
			if (!phone.isEmpty()) {
				if (userInfoManager.checkPhoneOtherInUsed(userInfo.getUsername(), phone)) {
					addActionError(getTexts("global_th").getString("global.phone_exists")); 
	    			return INPUT;
				}
			}
			if (!email.isEmpty()) {
				if (userInfoManager.checkEmailOtherInUsed(userInfo.getUsername(), email)) {
					addActionError(getTexts("global_th").getString("global.email_exists")); 
	    			return INPUT;
				}
			}
            
            AgentInfo currentAgentInfo = agentInfoManager.getAgentInfo(userInfo.getAgentInfoId());
            if(currentAgentInfo != null) {
        		if(currentAgentInfo.getLogoImg() != null && !currentAgentInfo.getLogoImg().isEmpty()) {
        			this.agentInfo.setLogoImg(currentAgentInfo.getLogoImg());
        		}
        	}
            
            if(!getAgentLogoFileName().isEmpty()) {
            	this.agentLogoFileName = UploadFileUtils.uploadImageApi(getAgentLogoFileName(), sessionMap, userInfo);
//            	String fileName = this.agentLogoFileName;
//            	String extension = FilenameUtils.getExtension(this.agentLogoFileName);
//            	this.agentLogoFileName = UUID.randomUUID().toString().toUpperCase() + "." + extension;
//            	
//            	if(currentAgentInfo != null) {
//            		if(currentAgentInfo.getLogoImg() != null && !currentAgentInfo.getLogoImg().isEmpty()) {
//            			String oldName = currentAgentInfo.getLogoImg();
//            			this.agentLogoFileName = oldName.substring(0, oldName.lastIndexOf(".")) + "." + extension;
//            		}
//            	}
            	
//	            File fileToCreate = new File(filePath, this.agentLogoFileName);
//	            FileModel fileModel = (FileModel) sessionMap.get(fileName);
//	            FileUtils.writeByteArrayToFile(fileToCreate, fileModel.getImageInBytes());
	            this.agentInfo.setLogoImg(this.agentLogoFileName);
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
		
//		if(agentInfoManager.validateAgentCode(userInfo.getAgentInfoId(), this.agentInfo.getAgentCode()) != null) {
//			addActionError("Agent code is duplicated.");
//			this.categoryInfos = categoryInfoManager.list();
//			
//			if(this.agentInfo != null) {
//				this.categoryInfo = categoryInfoManager.getCategoryInfoByCode(agentInfo.getCategoryCode());
//			} else {
//				this.categoryInfo = categoryInfoManager.getCategoryInfoByCode(this.categoryInfos.get(0).getCategoryCode());
//			}
//			return INPUT;
//		}
		
		if(agentInfoManager.getAgentInfo(userInfo.getAgentInfoId()) != null) {
			this.agentInfo.setAgentInfoId(userInfo.getAgentInfoId());
			this.agentInfo = agentInfoManager.update(this.agentInfo);
		} else {
			if(userInfo.getAgentInfoId() != null) {
				this.agentInfo.setAgentInfoId(userInfo.getAgentInfoId());
				this.agentInfo = agentInfoManager.add(this.agentInfo);
			} else {
				this.agentInfo.setAgentInfoId(UUID.randomUUID().toString().toUpperCase());
				this.agentInfo = agentInfoManager.add(this.agentInfo);
				userInfo.setAgentInfoId(this.agentInfo.getAgentInfoId());
				userInfo = userInfoManager.update(userInfo);
			}
		}
		userInfo.setPhone(this.phone);
		userInfo.setEmail(this.email);
		userInfoManager.update(userInfo);

		addActionMessage(getTexts("global_th").getString("global.message_success_update"));
		
		this.execute();
		
		return SUCCESS;
	}
	
	public String lineTokenCallback() {
		try {
			HomeInfo homeInfo = homeInfoManager.getHomeInfo("0");
			this.lineToken = LineApiUtils.requestToken(this.code, homeInfo.getLineClientId(), homeInfo.getLineClientSecret(), homeInfo.getLineRedirectUrl());
			log_.info(this.lineToken);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public AgentInfo getAgentInfo() {
		return agentInfo;
	}

	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}
	
	public String getAgentInfoId() {
		return agentInfoId;
	}

	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
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

	public String getAgentLogoFileName() {
		return agentLogoFileName;
	}
	public void setAgentLogoFileName(String agentLogoFileName) {
		this.agentLogoFileName = agentLogoFileName;
	}

	public String getCode() {
		return code;
	}

	public String getLineToken() {
		return lineToken;
	}

	public String getLineOauthUrl() {
		return lineOauthUrl;
	}

	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLineToken(String lineToken) {
		this.lineToken = lineToken;
	}

	public void setLineOauthUrl(String lineOauthUrl) {
		this.lineOauthUrl = lineOauthUrl;
	}

	public void setHomeInfo(HomeInfo homeInfo) {
		this.homeInfo = homeInfo;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
