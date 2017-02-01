package com.nightclub.custom.ckfinder.connector.configuration;

import javax.servlet.http.HttpServletRequest;

import com.ckfinder.connector.configuration.ConfigurationPathBuilder;
import com.nightclub.model.UserInfo;

public class CustomConfigurationPathBuilder extends ConfigurationPathBuilder {
	public String getBaseUrl(HttpServletRequest request) {
		String userName = "";
		if(request.getSession().getAttribute("userInfo") != null) {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
			userName = userInfo.getUsername() + "/";
		} else if(request.getSession().getAttribute("adminInfo") != null) {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute("adminInfo");
			userName = userInfo.getUsername() + "/";
		}
		return super.getBaseUrl(request) + userName;
	}
	
	@Override
	public String getBaseDir(HttpServletRequest request) {
		String userName = "";
		if(request.getSession().getAttribute("userInfo") != null) {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute("userInfo");
			userName = userInfo.getUsername() + "/";
		} else if(request.getSession().getAttribute("adminInfo") != null) {
			UserInfo userInfo = (UserInfo)request.getSession().getAttribute("adminInfo");
			userName = userInfo.getUsername() + "/";
		}
		return super.getBaseDir(request) + userName;
	}
}
