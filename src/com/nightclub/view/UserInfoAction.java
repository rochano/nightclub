package com.nightclub.view;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.common.IConstants;
import com.nightclub.controller.AgentInfoManager;
import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.ClientInfoManager;
import com.nightclub.controller.GirlInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.model.AgentInfo;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.ClientInfo;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -3950995967069630711L;
	
	private Map<String, Object> sessionMap;
	private UserInfo userInfo;
	private String username;
	private String email;
	private String password;
	private String chkUserType;
	private String userType;
	private String menu;
	
	private String oldPassword;
	private String confirmPassword;
	
	private UserInfoManager linkController;
	private BasicInfoManager basicInfoManager;
	private AgentInfoManager agentInfoManager;
	private GirlInfoManager girlInfoManager;
	private ClientInfoManager clientInfoManager;
	
	public static final String SHOP_SERVICE = "shopService";
	public static final String AGENT = "agent";
	public static final String FREE_AGENT = "freeAgent";
	public static final String CLIENT = "client";
	public static final String EN_GIRL = "enGirl";
	public static final String INDEPENDENT = "independent";

	public UserInfoAction() {
		linkController = new UserInfoManager();
		basicInfoManager = new BasicInfoManager();
		agentInfoManager = new AgentInfoManager();
		girlInfoManager = new GirlInfoManager();
		clientInfoManager = new ClientInfoManager();
	}

	public String login() {
        if (username != null && password != null) {
        	if(IConstants.USER_TYPE_CLIENT.equals(chkUserType)) {
        		userType = IConstants.USER_TYPE_CLIENT;
        	}
        	this.userInfo = linkController.authenticate(username, password);
        	
        	if(this.userInfo != null) {
        		if ((IConstants.USER_TYPE_AGENT.equals(userType)
        				&& !this.userInfo.getUserType().equals(userType))
        				|| (IConstants.USER_TYPE_INDEPENDENT.equals(userType)
        						&& !(IConstants.USER_TYPE_FREE_AGENT.equals(this.userInfo.getUserType())
        								|| IConstants.USER_TYPE_EN_GIRL.equals(this.userInfo.getUserType())
        								|| IConstants.USER_TYPE_INDEPENDENT.equals(this.userInfo.getUserType())))) {
        			addActionError(getText("global.user_type_not_correct"));
        			return LOGIN;
        		}
        		// add userName to the session
        		
        		if(IConstants.USER_TYPE_ADMIN.equals(userType)) {
        			sessionMap.put("adminInfo", userInfo);
        			return SUCCESS;
        		}
        		
//        		if(!Boolean.TRUE.toString().toLowerCase().equals(userInfo.getActive())) {
//        			addActionError("Your account is not active !"); 
//    				return LOGIN;
//        		}
//        		
//        		if(userInfo.getValidDateFrom() != null && userInfo.getValidDateTo() != null) {
//        			Date now = new Date();
//        			if (now.compareTo(userInfo.getValidDateFrom()) < 0
//        					|| now.compareTo(userInfo.getValidDateTo()) > 0) {
//	        			addActionError("Your account is not active !"); 
//	    				return LOGIN;
//        			}
//        		}
        		
        		// user
        		if(IConstants.USER_TYPE_SHOP.equals(userType)) {
        			sessionMap.put("userInfo", userInfo);
        			BasicInfo basicInfo = basicInfoManager.getBasicInfo(userInfo.getShopInfoId());
        			if (basicInfo != null) {
        				sessionMap.put("basicInfo", basicInfo);
        			}
        			return SHOP_SERVICE;

        		// agent
        		} else if(IConstants.USER_TYPE_AGENT.equals(userType)) {
        			sessionMap.put("userInfo", userInfo);
        			AgentInfo agentInfo = agentInfoManager.getAgentInfo(userInfo.getAgentInfoId());
        			if (agentInfo != null) {
        				sessionMap.put("agentInfo", agentInfo);
        			}
        			return AGENT;

				// free agent
				} else if(IConstants.USER_TYPE_FREE_AGENT.equals(userType)) {
					sessionMap.put("userInfo", userInfo);
					GirlInfo girlInfo = girlInfoManager.getGirlInfo(userInfo.getGirlInfoId());
					if (girlInfo != null) {
        				sessionMap.put("freeAgentGirlInfo", girlInfo);
        			}
					return FREE_AGENT;
        		
				// client
				} else if(IConstants.USER_TYPE_CLIENT.equals(userType)) {
					if(userInfo.getActive().equals(Boolean.TRUE.toString()) 
							&& userInfo.getValidDateFrom() != null && userInfo.getValidDateTo() != null) {
	        			Date now = new Date();
	        			if (now.compareTo(userInfo.getValidDateFrom()) < 0
	        					|| now.compareTo(userInfo.getValidDateTo()) > 0) {
	        				userInfo.setActive(Boolean.FALSE.toString());
	        			}
	        		}
					sessionMap.put("userInfo", userInfo);
					ClientInfo clientInfo = clientInfoManager.getClientInfo(userInfo.getClientInfoId());
					if (clientInfo != null) {
        				sessionMap.put("clientInfo", clientInfo);
        			}
					return CLIENT;
					
				// entertain girl
				} else if(IConstants.USER_TYPE_EN_GIRL.equals(userType)) {
					sessionMap.put("userInfo", userInfo);
					GirlInfo girlInfo = girlInfoManager.getGirlInfo(userInfo.getGirlInfoId());
					if (girlInfo != null) {
        				sessionMap.put("enGirlInfo", girlInfo);
        			}
					return EN_GIRL;
				// free agent + entertain girl
				} else if(IConstants.USER_TYPE_INDEPENDENT.equals(userType)) {
					sessionMap.put("userInfo", userInfo);
					GirlInfo girlInfo = girlInfoManager.getGirlInfo(userInfo.getGirlInfoId());
					if (girlInfo != null) {
        				sessionMap.put("independentGirlInfo", girlInfo);
        			}
					return INDEPENDENT;
				}
        	} else {
        		addActionError(getText("global.username_password_not_correct"));
        	}
            
            return LOGIN;
        }
         
        // in other cases, return login page
        return LOGIN;
	} 
	
	// Log Out user
    public String logout() {
    	if (userType != null) {
	    	if(userType.equals(IConstants.USER_TYPE_ADMIN)) { 
	    		sessionMap.remove("adminInfo");
	    	} 
    	} else {
    		sessionMap.remove("userInfo");
    		sessionMap.remove("basicInfo");
    		sessionMap.remove("agentInfo");
    		sessionMap.remove("freeAgentGirlInfo");
    		sessionMap.remove("enGirlInfo");
    		sessionMap.remove("independentGirlInfo");
    	}
        addActionMessage("You have been successfully logged out");
        return SUCCESS;
    }
    
    public String signup() {
    	if (username != null && email != null && password != null) {
    		
    		if(linkController.getUserInfo(username, email) != null) {
    			addActionError(getText("global.username_email_exists")); 
    			return INPUT;
    			
    		} else {
	    		this.userInfo = new UserInfo();
	    		this.userInfo.setUserInfoId(UUID.randomUUID().toString().toUpperCase());
	    		this.userInfo.setUsername(username);
	    		this.userInfo.setEmail(email);
	    		this.userInfo.setPassword(password);
	    		if(IConstants.USER_TYPE_CLIENT.equals(chkUserType)) {
	    			userType = IConstants.USER_TYPE_CLIENT;
	    		}
	    		this.userInfo.setUserType(userType);
	    		this.userInfo.setActive(Boolean.TRUE.toString());
	    		
	    		Date currentDate = new Date();
	    		// convert date to calendar
	            Calendar c = Calendar.getInstance();
	            c.setTime(currentDate);
	            // manipulate date
	            c.add(Calendar.DATE, -1);
	            // convert calendar to date
	            Date currentDateMinusOne = c.getTime();
	            this.userInfo.setValidDateFrom(currentDateMinusOne);
	            
	            currentDate = new Date();
	    		// convert date to calendar
	            c = Calendar.getInstance();
	            c.setTime(currentDate);
	            // manipulate date
	            c.add(Calendar.MONTH, 6);
	            // convert calendar to date
	            Date currentDatePlus6Months = c.getTime();
	    		this.userInfo.setValidDateTo(currentDatePlus6Months);
	    		this.userInfo = linkController.add(userInfo);
	    		
	    		addActionMessage("You have been successfully registered");
	    		return login();
    		}
    	}
    	
    	return INPUT;
    }
    
    public String changePassword() {
    	if (oldPassword != null && password != null && confirmPassword != null) {
    		
    		if(userType.equals(IConstants.USER_TYPE_SHOP)
    				|| userType.equals(IConstants.USER_TYPE_AGENT)
    				|| userType.equals(IConstants.USER_TYPE_FREE_AGENT)
    				|| userType.equals(IConstants.USER_TYPE_CLIENT)
    				|| userType.equals(IConstants.USER_TYPE_EN_GIRL)) { 
    			this.userInfo = (UserInfo) sessionMap.get("userInfo");
    		} else if(userType.equals(IConstants.USER_TYPE_ADMIN)) { 
    			this.userInfo = (UserInfo) sessionMap.get("adminInfo");
    		}
    		
    		if(this.userInfo.getPassword().equals(oldPassword)) {
    			if(password.equals(confirmPassword)) {
    				linkController.update(this.userInfo);
    				addActionMessage(getTexts("global_th").getString("global.message_success_update"));
    	    		return SUCCESS;
    			} else {
    				addActionMessage(getTexts("global_th").getString("global.confirm_password") + getTexts("global_th").getString("global.message_match_new_password"));
    			}
    		} else {
    			addActionError(getTexts("global_th").getString("global.message_old_password_is_incorrect"));
    		}
    	}
    	return INPUT;
    }

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getChkUserType() {
		return chkUserType;
	}

	public void setChkUserType(String chkUserType) {
		this.chkUserType = chkUserType;
	}

}
