package com.nightclub.view;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.BasicInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.model.BasicInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -3950995967069630711L;
	
	private Map<String, Object> sessionMap;
	private UserInfo userInfo;
	private String username;
	private String password;
	private String userType;
	private String menu;
	
	private String oldPassword;
	private String confirmPassword;
	
	private UserInfoManager linkController;

	public UserInfoAction() {
		linkController = new UserInfoManager();
	}

	public String login() {
        if (username != null && password != null) {
        	this.userInfo = linkController.authenticate(username, password, userType);
        	
        	if(this.userInfo != null) {
        		// add userName to the session
        		// user
        		if(userType.equals("1")) { 
        			sessionMap.put("userInfo", userInfo);
        			
        		// admin
        		} else if(userType.equals("2")) {
        			sessionMap.put("adminInfo", userInfo);
        		}
                
                return SUCCESS; // return welcome page
        	}
        	
        	addActionError("Your username or password is incorrect !"); 
            
            return LOGIN;
        }
         
        // in other cases, return login page
        return LOGIN;
	} 
	
	// Log Out user
    public String logout() {
    	if(userType.equals("1")) { 
    		sessionMap.remove("userInfo");
    	} else if(userType.equals("2")) { 
    		sessionMap.remove("adminInfo");
    	}
        addActionMessage("You have been successfully logged out");
        return SUCCESS;
    }
    
    public String signup() {
    	if (username != null && password != null) {
    		
    		if(linkController.getUserInfo(username) != null) {
    			addActionError("Your username has been already existed !"); 
    			return INPUT;
    			
    		} else {
	    		this.userInfo = new UserInfo();
	    		this.userInfo.setUsername(username);
	    		this.userInfo.setPassword(password);
	    		this.userInfo.setUserType(userType);
	    		this.userInfo = linkController.add(userInfo);
	    		
	    		addActionMessage("You have been successfully registered");
	    		return SUCCESS;
    		}
    	}
    	
    	return INPUT;
    }
    
    public String changePassword() {
    	if (oldPassword != null && password != null && confirmPassword != null) {
    		
    		if(userType.equals("1")) { 
    			this.userInfo = (UserInfo) sessionMap.get("userInfo");
    		} else if(userType.equals("2")) { 
    			this.userInfo = (UserInfo) sessionMap.get("adminInfo");
    		}
    		
    		if(this.userInfo.getPassword().equals(oldPassword)) {
    			if(password.equals(confirmPassword)) {
    				linkController.update(this.userInfo);
    				addActionMessage("Your password have been successfully updated");
    	    		return SUCCESS;
    			} else {
    				addActionError("New password is mismatch."); 
    			}
    		} else {
    			addActionError("Your old password is incorrect."); 
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

}
