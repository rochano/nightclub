package com.nightclub.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.GirlReserveInfoManager;
import com.nightclub.controller.HomeInfoManager;
import com.nightclub.model.HomeInfo;
import com.nightclub.model.ReserveInfo;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class AgentReserveInfoAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	
	private Map<String, Object> sessionMap;
	private String menu;
	private String action;
	private boolean showInfo = false;
	private String lookupDate;
	private String lookupDateHeader;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdfOutput = new SimpleDateFormat("EEEE, d MMM");
	private List<ReserveInfo> reserveInfos;
	private HomeInfo homeInfo;
	
	private GirlReserveInfoManager girlReserveInfoManager;
	private HomeInfoManager homeInfoManager;

	public AgentReserveInfoAction() {
//		shopReserveInfoManager = new ShopReserveInfoManager();
		girlReserveInfoManager = new GirlReserveInfoManager();
		homeInfoManager = new HomeInfoManager();
	}
	
	public String execute() {
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		
		Date today = new Date();
		this.lookupDateHeader = sdfOutput.format(today);
//		this.reserveInfos = shopReserveInfoManager.getReserveInfosByReserveDate(userInfo.getShopInfoId(), today);
		this.reserveInfos = girlReserveInfoManager.getReserveInfosByReserveDate(userInfo.getAgentInfoId(), today);
		homeInfo = homeInfoManager.getHomeInfo("0");
		
		return SUCCESS;
	}

	public String girlReserveInfo() {
		Date date = new Date();
		UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
		try {
			date = sdf.parse(lookupDate);
			this.lookupDateHeader = sdfOutput.format(date);
//			this.reserveInfos = shopReserveInfoManager.getReserveInfosByReserveDate(userInfo.getShopInfoId(), date);
			this.reserveInfos = girlReserveInfoManager.getReserveInfosByReserveDate(userInfo.getAgentInfoId(), date);
		} catch (ParseException e) {
			e.printStackTrace();
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

	public String getLookupDateHeader() {
		return lookupDateHeader;
	}

	public void setLookupDateHeader(String lookupDateHeader) {
		this.lookupDateHeader = lookupDateHeader;
	}

	public List<ReserveInfo> getReserveInfos() {
		return reserveInfos;
	}

	public void setReserveInfos(List<ReserveInfo> reserveInfos) {
		this.reserveInfos = reserveInfos;
	}

	public String getLookupDate() {
		return lookupDate;
	}

	public void setLookupDate(String lookupDate) {
		this.lookupDate = lookupDate;
	}

	public HomeInfo getHomeInfo() {
		return homeInfo;
	}

	public void setHomeInfo(HomeInfo homeInfo) {
		this.homeInfo = homeInfo;
	}

}
