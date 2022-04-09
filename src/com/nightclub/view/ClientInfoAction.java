package com.nightclub.view;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.ClientInfoManager;
import com.nightclub.controller.UserInfoManager;
import com.nightclub.model.ClientInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class ClientInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());

	private Map<String, Object> sessionMap;
	private ClientInfo clientInfo;
	private String clientInfoId;
	private String menu;
	private ArrayList<String> ageList;
 
	private ClientInfoManager clientInfoManager;
	private UserInfoManager userInfoManager;

	public ClientInfoAction() {
		clientInfoManager = new ClientInfoManager();
		userInfoManager = new UserInfoManager();
	}

	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		this.clientInfo = clientInfoManager.getClientInfo(userInfo.getClientInfoId());
		this.ageList = makeList(18, 50);

		return SUCCESS;
	}
	
	public String update() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");

		if(clientInfoManager.getClientInfo(userInfo.getClientInfoId()) != null) {
			this.clientInfo.setClientInfoId(userInfo.getClientInfoId());
			this.clientInfo = clientInfoManager.update(this.clientInfo);
		} else {
			if(userInfo.getClientInfoId() != null) {
				this.clientInfo.setClientInfoId(userInfo.getClientInfoId());
				clientInfoManager.add(this.clientInfo);
			} else {
				this.clientInfo.setClientInfoId(UUID.randomUUID().toString().toUpperCase());
				clientInfoManager.add(this.clientInfo);
				userInfo.setClientInfoId(this.clientInfo.getClientInfoId());
				userInfo = userInfoManager.update(userInfo);
			}
		}
		
		addActionMessage("You have been successfully updated");
		
		this.execute();
		
		return SUCCESS;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	public String getClientInfoId() {
		return clientInfoId;
	}

	public void setClientInfoId(String clientInfoId) {
		this.clientInfoId = clientInfoId;
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

	private ArrayList<String> makeList(Integer from, Integer to) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=from; i<=to;i++) {
			list.add(String.valueOf(i));
		}
		return list;
	}

	public ArrayList<String> getAgeList() {
		return ageList;
	}

	public void setAgeList(ArrayList<String> ageList) {
		this.ageList = ageList;
	}
}
