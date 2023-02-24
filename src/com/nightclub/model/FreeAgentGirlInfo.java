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
	private Double priceIncall40Mins;
	private Double priceOutcall40Mins;
	private String crcy40Mins;
	private String chk60Mins;
	private Double priceIncall60Mins;
	private Double priceOutcall60Mins;
	private String crcy60Mins;
	private String chk90Mins;
	private Double priceIncall90Mins;
	private Double priceOutcall90Mins;
	private String crcy90Mins;
	private String chk120Mins;
	private Double priceIncall120Mins;
	private Double priceOutcall120Mins;
	private String crcy120Mins;
	private String chk6Hrs;
	private Double priceIncall6Hrs;
	private Double priceOutcall6Hrs;
	private String crcy6Hrs;
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
		if (chk40Mins == null) {
			return Boolean.FALSE.toString().toLowerCase();
		}
		return chk40Mins;
	}
	@Column(name="price_incall_40_mins")
	public Double getPriceIncall40Mins() {
		if (priceIncall40Mins == null) {
			return 0.0;
		} else {
			return priceIncall40Mins;
		}
	}
	@Column(name="price_outcall_40_mins")
	public Double getPriceOutcall40Mins() {
		if (priceOutcall40Mins == null) {
			return 0.0;
		} else {
			return priceOutcall40Mins;
		}
	}
	@Column(name="crcy_40_mins")
	public String getCrcy40Mins() {
		return crcy40Mins;
	}
	@Column(name="chk_60_mins")
	public String getChk60Mins() {
		if (chk60Mins == null) {
			return Boolean.FALSE.toString().toLowerCase();
		}
		return chk60Mins;
	}
	@Column(name="price_incall_60_mins")
	public Double getPriceIncall60Mins() {
		if (priceIncall60Mins == null) {
			return 0.0;
		} else {
			return priceIncall60Mins;
		}
	}
	@Column(name="price_outcall_60_mins")
	public Double getPriceOutcall60Mins() {
		if (priceOutcall60Mins == null) {
			return 0.0;
		} else {
			return priceOutcall60Mins;
		}
	}
	@Column(name="crcy_60_mins")
	public String getCrcy60Mins() {
		return crcy60Mins;
	}
	@Column(name="chk_90_mins")
	public String getChk90Mins() {
		if (chk90Mins == null) {
			return Boolean.FALSE.toString().toLowerCase();
		}
		return chk90Mins;
	}
	@Column(name="price_incall_90_mins")
	public Double getPriceIncall90Mins() {
		if (priceIncall90Mins == null) {
			return 0.0;
		} else {
			return priceIncall90Mins;
		}
	}
	@Column(name="price_outcall_90_mins")
	public Double getPriceOutcall90Mins() {
		if (priceOutcall90Mins == null) {
			return 0.0;
		} else {
			return priceOutcall90Mins;
		}
	}
	@Column(name="crcy_90_mins")
	public String getCrcy90Mins() {
		return crcy90Mins;
	}
	@Column(name="chk_120_mins")
	public String getChk120Mins() {
		if (chk120Mins == null) {
			return Boolean.FALSE.toString().toLowerCase();
		}
		return chk120Mins;
	}
	@Column(name="price_incall_120_mins")
	public Double getPriceIncall120Mins() {
		if (priceIncall120Mins == null) {
			return 0.0;
		} else {
			return priceIncall120Mins;
		}
	}
	@Column(name="price_outcall_120_mins")
	public Double getPriceOutcall120Mins() {
		if (priceOutcall120Mins == null) {
			return 0.0;
		} else {
			return priceOutcall120Mins;
		}
	}
	@Column(name="crcy_120_mins")
	public String getCrcy120Mins() {
		return crcy120Mins;
	}
	@Column(name="chk_6_hrs")
	public String getChk6Hrs() {
		if (chk6Hrs == null) {
			return Boolean.FALSE.toString().toLowerCase();
		}
		return chk6Hrs;
	}
	@Column(name="price_incall_6_hrs")
	public Double getPriceIncall6Hrs() {
		if (priceIncall6Hrs == null) {
			return 0.0;
		} else {
			return priceIncall6Hrs;
		}
	}
	@Column(name="price_outcall_6_hrs")
	public Double getPriceOutcall6Hrs() {
		if (priceOutcall6Hrs == null) {
			return 0.0;
		} else {
			return priceOutcall6Hrs;
		}
	}
	@Column(name="crcy_6_hrs")
	public String getCrcy6Hrs() {
		return crcy6Hrs;
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
	public void setPriceIncall40Mins(Double priceIncall40Mins) {
		this.priceIncall40Mins = priceIncall40Mins;
	}
	public void setPriceOutcall40Mins(Double priceOutcall40Mins) {
		this.priceOutcall40Mins = priceOutcall40Mins;
	}
	public void setCrcy40Mins(String crcy40Mins) {
		this.crcy40Mins = crcy40Mins;
	}
	public void setChk60Mins(String chk60Mins) {
		this.chk60Mins = chk60Mins;
	}
	public void setPriceIncall60Mins(Double priceIncall60Mins) {
		this.priceIncall60Mins = priceIncall60Mins;
	}
	public void setPriceOutcall60Mins(Double priceOutcall60Mins) {
		this.priceOutcall60Mins = priceOutcall60Mins;
	}
	public void setCrcy60Mins(String crcy60Mins) {
		this.crcy60Mins = crcy60Mins;
	}
	public void setChk90Mins(String chk90Mins) {
		this.chk90Mins = chk90Mins;
	}
	public void setPriceIncall90Mins(Double priceIncall90Mins) {
		this.priceIncall90Mins = priceIncall90Mins;
	}
	public void setPriceOutcall90Mins(Double priceOutcall90Mins) {
		this.priceOutcall90Mins = priceOutcall90Mins;
	}
	public void setCrcy90Mins(String crcy90Mins) {
		this.crcy90Mins = crcy90Mins;
	}
	public void setChk120Mins(String chk120Mins) {
		this.chk120Mins = chk120Mins;
	}
	public void setPriceIncall120Mins(Double priceIncall120Mins) {
		this.priceIncall120Mins = priceIncall120Mins;
	}
	public void setPriceOutcall120Mins(Double priceOutcall120Mins) {
		this.priceOutcall120Mins = priceOutcall120Mins;
	}
	public void setCrcy120Mins(String crcy120Mins) {
		this.crcy120Mins = crcy120Mins;
	}
	public void setChk6Hrs(String chk6Hrs) {
		this.chk6Hrs = chk6Hrs;
	}
	public void setPriceIncall6Hrs(Double priceIncall6Hrs) {
		this.priceIncall6Hrs = priceIncall6Hrs;
	}
	public void setPriceOutcall6Hrs(Double priceOutcall6Hrs) {
		this.priceOutcall6Hrs = priceOutcall6Hrs;
	}
	public void setCrcy6Hrs(String crcy6Hrs) {
		this.crcy6Hrs = crcy6Hrs;
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
