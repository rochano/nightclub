package com.nightclub.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="free_agent_girl_info")
public class FreeAgentGirlInfo extends GirlInfo {
	private static final long serialVersionUID = -1930927867933854705L;
	private List<GirlService> girlServices = new ArrayList<GirlService>();
	private String chk40Mins;
	private Double price40Mins;
	private String chk60Mins;
	private Double price60Mins;
	private String chk90Mins;
	private Double price90Mins;
	private String chk120Mins;
	private Double price120Mins;
	private String chk6Hrs;
	private Double price6Hrs;
	private UserInfo userInfo;
	private String incall;
	private String outcall;

	@OneToMany(mappedBy = "primaryKey.freeAgentGirlInfo",
            cascade = CascadeType.ALL)
	public List<GirlService> getGirlServices() {
		return girlServices;
	}
	@Column(name="chk_40_mins")
	public String getChk40Mins() {
		return chk40Mins;
	}
	@Column(name="price_40_mins")
	public Double getPrice40Mins() {
		if (price40Mins == null) {
			return 0.0;
		} else {
			return price40Mins;
		}
	}
	@Column(name="chk_60_mins")
	public String getChk60Mins() {
		return chk60Mins;
	}
	@Column(name="price_60_mins")
	public Double getPrice60Mins() {
		if (price60Mins == null) {
			return 0.0;
		} else {
			return price60Mins;
		}
	}
	@Column(name="chk_90_mins")
	public String getChk90Mins() {
		return chk90Mins;
	}
	@Column(name="price_90_mins")
	public Double getPrice90Mins() {
		if (price90Mins == null) {
			return 0.0;
		} else {
			return price90Mins;
		}
	}
	@Column(name="chk_120_mins")
	public String getChk120Mins() {
		return chk120Mins;
	}
	@Column(name="price_120_mins")
	public Double getPrice120Mins() {
		if (price120Mins == null) {
			return 0.0;
		} else {
			return price120Mins;
		}
	}
	@Column(name="chk_6_hrs")
	public String getChk6Hrs() {
		return chk6Hrs;
	}
	@Column(name="price_6_hrs")
	public Double getPrice6Hrs() {
		if (price6Hrs == null) {
			return 0.0;
		} else {
			return price6Hrs;
		}
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	public UserInfo getUserInfo() {
		return userInfo;
	}
	@Column(name="incall")
	public String getIncall() {
		return incall;
	}
	@Column(name="outcall")
	public String getOutcall() {
		return outcall;
	}
	public void setGirlServices(List<GirlService> girlServices) {
		this.girlServices = girlServices;
	}
	public void setChk40Mins(String chk40Mins) {
		this.chk40Mins = chk40Mins;
	}
	public void setPrice40Mins(Double price40Mins) {
		this.price40Mins = price40Mins;
	}
	public void setChk60Mins(String chk60Mins) {
		this.chk60Mins = chk60Mins;
	}
	public void setPrice60Mins(Double price60Mins) {
		this.price60Mins = price60Mins;
	}
	public void setChk90Mins(String chk90Mins) {
		this.chk90Mins = chk90Mins;
	}
	public void setPrice90Mins(Double price90Mins) {
		this.price90Mins = price90Mins;
	}
	public void setChk120Mins(String chk120Mins) {
		this.chk120Mins = chk120Mins;
	}
	public void setPrice120Mins(Double price120Mins) {
		this.price120Mins = price120Mins;
	}
	public void setChk6Hrs(String chk6Hrs) {
		this.chk6Hrs = chk6Hrs;
	}
	public void setPrice6Hrs(Double price6Hrs) {
		this.price6Hrs = price6Hrs;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public void setIncall(String incall) {
		this.incall = incall;
	}
	public void setOutcall(String outcall) {
		this.outcall = outcall;
	}
}
