package com.nightclub.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import com.nightclub.controller.BasicInfoManager;
import com.nightclub.model.BasicInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ShopController extends ActionSupport implements ModelDriven<Object> {
	Logger log_ = Logger.getLogger(this.getClass().getName());
	private String id;
	private Object model;
	private BasicInfo basicInfo;
	private List<BasicInfo> basicInfos = new ArrayList<BasicInfo>();
	
	private BasicInfoManager basicInfoManager;
	
	public ShopController() {
		basicInfoManager = new BasicInfoManager();
	}
	
	public HttpHeaders index() {
		List<BasicInfo> _basicInfos = basicInfoManager.list();
		for(BasicInfo _basicInfo : _basicInfos) {
			basicInfo = _basicInfo.clone();
			basicInfos.add(basicInfo);
		}
		model = basicInfos;
		
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	public HttpHeaders show() {
		model = basicInfoManager.getBasicInfoByCode(getId()).clone();
		
		return new DefaultHttpHeaders("show").disableCaching();
	}

	@Override
	public Object getModel() {
		return model;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
