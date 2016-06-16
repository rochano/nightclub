package com.nightclub.interceptor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nightclub.controller.StatisticInfoManager;
import com.nightclub.model.StatisticInfo;
import com.nightclub.model.StatisticInfoPK;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class StatisticInterceptor extends AbstractInterceptor {
	
	private static final long serialVersionUID = -2788812323728898190L;

	private StatisticInfoManager statisticInfoManager;
	
	@Override
	public void init() {
		super.init();
		statisticInfoManager = new StatisticInfoManager();
	}
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String ipaddress = "";
		Date systemDate = new Date();
		String accessDt = new SimpleDateFormat("yyyyMMdd").format(systemDate);
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
		
		return invocation.invoke();
	}

}
