package com.nightclub.view;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.nightclub.controller.ClientInfoManager;
import com.nightclub.controller.GirlFavouriteManager;
import com.nightclub.controller.StatisticInfoManager;
import com.nightclub.model.ClientInfo;
import com.nightclub.model.StatisticInfo;
import com.nightclub.model.StatisticInfoPK;
import com.nightclub.model.StatisticModel;
import com.nightclub.model.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport implements ServletRequestAware, SessionAware  {
	
	private static final long serialVersionUID = -5029871739458132655L;
	Logger log_ = Logger.getLogger(this.getClass().getName());
	private StatisticModel statisticModel;
	private HttpServletRequest servletRequest;
	private ClientInfo clientInfo;
	private Map<String, Object> sessionMap;
	private String ipAddress;
	private String accessDt;
	
	private StatisticInfoManager statisticInfoManager;
	protected ClientInfoManager clientInfoManager;
	
	public CommonAction() {
		statisticInfoManager = new StatisticInfoManager();
		clientInfoManager = new ClientInfoManager();
	}

	protected void getStatisticInfo() {
		String ipaddress = getServletRequest().getHeader("X-FORWARDED-FOR");
		log_.info("ipaddress 1>> " + ipaddress);
		if (ipaddress == null) {  
			ipaddress = getServletRequest().getRemoteAddr();
		}
		log_.info("ipaddress 2>> " + ipaddress);
		String[] tokens = ipaddress.split(",");
		ipaddress = tokens[tokens.length - 1].trim();
		log_.info("ipaddress>> " + ipaddress);
		setIpAddress(ipaddress);
		Date systemDate = new Date();
		String accessDt = new SimpleDateFormat("yyyyMMdd").format(systemDate);
		setAccessDt(accessDt);
		String accessDtYmd = new SimpleDateFormat("yyyy-MM-dd").format(systemDate);
		Timestamp online = new Timestamp(systemDate.getTime());

		StatisticInfoPK statisticInfoPK = new StatisticInfoPK(ipaddress, accessDt);
		StatisticInfo statisticInfo = statisticInfoManager.getStatisticInfo(statisticInfoPK);
		
		if(statisticInfo != null) {
			statisticInfo.setHit(statisticInfo.getHit() + 1);
			statisticInfo.setOnline(online);
			statisticInfoManager.update(statisticInfo);
		} else {
			statisticInfo = new StatisticInfo();
			statisticInfo.setStatisticInfoPK(statisticInfoPK);
			statisticInfo.setHit(1);
			statisticInfo.setOnline(online);
			statisticInfoManager.add(statisticInfo);
		}
		
		this.statisticModel = new StatisticModel();
		
		// yesterday view
		Calendar calPreviousDt = Calendar.getInstance();
		calPreviousDt.add(Calendar.DATE, -1); 
		String previousDt = new SimpleDateFormat("yyyyMMdd", Locale.US).format(calPreviousDt.getTime());
		getStatisticModel().setYesterdayView(
				statisticInfoManager.getStatisticInfosByDate(previousDt).size());
		
		// month view
		getStatisticModel().setMonthView(
				statisticInfoManager.getStatisticInfosByDate(accessDt.substring(0, 6) + "%").size());
		
		// year view
		getStatisticModel().setYearView(
				statisticInfoManager.getStatisticInfosByDate(accessDt.substring(0, 4) + "%").size());
		
		// today view
		List<StatisticInfo> statisticInfos = statisticInfoManager.getStatisticInfosByDate(accessDt);
		getStatisticModel().setTodayView(statisticInfos.size());
		
		// hits view
		int hitViews = 0;
		for(int i = 0; i < statisticInfos.size(); i++) {
			hitViews += statisticInfos.get(i).getHit();
		}
		getStatisticModel().setHitsView(hitViews);
		
		// total view
		statisticInfos = statisticInfoManager.getStatisticInfosByDate("%%");
		getStatisticModel().setTotalView(statisticInfos.size());
		
		// total hits view
		int totalHitsViews = 0;
		for(int i = 0; i < statisticInfos.size(); i++) {
			totalHitsViews += statisticInfos.get(i).getHit();
		}
		getStatisticModel().setTotalHitsView(totalHitsViews);
		
		//online view
		Calendar calOnline = Calendar.getInstance();
		calOnline.setTimeInMillis(new Timestamp(System.currentTimeMillis()).getTime());
		calOnline.add(Calendar.MINUTE, -5);
		getStatisticModel().setOnlineView(
				statisticInfoManager.getStatisticInfosOnline(new Timestamp(calOnline.getTime().getTime())).size());
	
		getStatisticModel().setIpAddress(ipaddress);
		getStatisticModel().setAccessDt(accessDtYmd);

		if (sessionMap.containsKey("userInfo")) {
			UserInfo userInfo = (UserInfo)sessionMap.get("userInfo");
			if (userInfo.getClientInfoId() != null) {
				this.clientInfo = clientInfoManager.getClientInfo(userInfo.getClientInfoId());
				loadGirlFavourites(userInfo.getClientInfoId());
			} else {
				this.clientInfo = new ClientInfo();
			}
			this.clientInfo.setUserInfo(userInfo);
		}
	}
	
	protected void loadGirlFavourites(String clientInfoId) {}

	public StatisticModel getStatisticModel() {
		return statisticModel;
	}

	public void setStatisticModel(StatisticModel statisticModel) {
		this.statisticModel = statisticModel;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public Map<String, Object> getSession() {
		return this.sessionMap;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getAccessDt() {
		return accessDt;
	}

	public void setAccessDt(String accessDt) {
		this.accessDt = accessDt;
	}
}
