package com.nightclub.model;

import java.io.Serializable;

public class FormGirlServiceInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private GirlServiceInfo girlServiceInfo;
	private GirlService girlService;

	public GirlServiceInfo getGirlServiceInfo() {
		return girlServiceInfo;
	}
	public GirlService getGirlService() {
		return girlService;
	}
	public void setGirlServiceInfo(GirlServiceInfo girlServiceInfo) {
		this.girlServiceInfo = girlServiceInfo;
	}
	public void setGirlService(GirlService girlService) {
		this.girlService = girlService;
	}

}
