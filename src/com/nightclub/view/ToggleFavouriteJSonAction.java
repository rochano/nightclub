package com.nightclub.view;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.GirlFavouriteManager;
import com.nightclub.model.ClientInfo;
import com.nightclub.model.GirlFavourite;
import com.nightclub.model.GirlInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.Action;

public class ToggleFavouriteJSonAction implements SessionAware {
	private String girlInfoId;
	private String favourite;
	private Map<String, Object> sessionMap;
	private GirlFavouriteManager girlFavouriteManager;
	
	public ToggleFavouriteJSonAction() {
		girlFavouriteManager = new GirlFavouriteManager();
	}
	
	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		GirlFavourite girlFavourite = girlFavouriteManager.getGirlFavourite(userInfo.getClientInfoId(), getGirlInfoId());
		if ("1".equals(getFavourite())) {
			if (girlFavourite == null) {
				girlFavourite = new GirlFavourite();
				ClientInfo clientInfo = new ClientInfo();
				GirlInfo girlInfo = new GirlInfo();
				clientInfo.setClientInfoId(userInfo.getClientInfoId());
				girlInfo.setGirlInfoId(getGirlInfoId());
				girlFavourite.setClientInfo(clientInfo);
				girlFavourite.setGirlInfo(girlInfo);
				girlFavouriteManager.add(girlFavourite);
			}
		} else {
			if (girlFavourite != null) {
				girlFavouriteManager.delete(userInfo.getClientInfoId(), getGirlInfoId());
			}
		}
        return Action.SUCCESS;
	}
	
	public String getGirlInfoId() {
		return girlInfoId;
	}
	public String getFavourite() {
		return favourite;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
	public void setFavourite(String favourite) {
		this.favourite = favourite;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
}
