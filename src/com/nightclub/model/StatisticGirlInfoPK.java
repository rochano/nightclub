package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;

public class StatisticGirlInfoPK  implements Serializable {
	
	private static final long serialVersionUID = 7032713885449936736L;
	private String ipaddress;
	private String accessDt;
	private String girlInfoId;
	
	public StatisticGirlInfoPK() {};
	public StatisticGirlInfoPK(String ipaddress, String accessDt, String girlInfoId) {
		this.ipaddress = ipaddress;
		this.accessDt = accessDt;
		this.girlInfoId = girlInfoId;
	}
	
	@Column(name="ip_addr")
	public String getIpaddress() {
		return ipaddress;
	}
	@Column(name="access_dt")
	public String getAccessDt() {
		return accessDt;
	}
	@Column(name="girl_info_id")
	public String getGirlInfoId() {
		return girlInfoId;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public void setAccessDt(String accessDt) {
		this.accessDt = accessDt;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
}
