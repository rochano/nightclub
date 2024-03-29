package com.nightclub.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.Length;

@Entity
@Table(name="province_info")
public class ProvinceInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String provinceInfoId;
	private String provinceNameEn;
	private String provinceNameJp;
	private String countryInfoId;
	private CountryInfo countryInfo;
	private List<ZoneInfo> zoneInfos;

	@Id
	@Column(name="province_info_id")
	@Length(max=40)
	public String getProvinceInfoId() {
		return provinceInfoId;
	}
	@Column(name="province_name_en")
	public String getProvinceNameEn() {
		if (provinceNameEn == null) {
			return "";
		}
		return provinceNameEn;
	}
	@Column(name="province_name_jp")
	public String getProvinceNameJp() {
		if (provinceNameJp == null) {
			return "";
		}
		return provinceNameJp;
	}
	@Column(name="country_info_id")
	public String getCountryInfoId() {
		return countryInfoId;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="country_info_id", referencedColumnName="country_info_id", insertable=false, updatable=false)
	public CountryInfo getCountryInfo() {
		return countryInfo;
	}
	@OneToMany(mappedBy = "provinceInfo",
            cascade = CascadeType.ALL)
	public List<ZoneInfo> getZoneInfos() {
		return zoneInfos;
	}
	public void setProvinceInfoId(String provinceInfoId) {
		this.provinceInfoId = provinceInfoId;
	}
	public void setProvinceNameEn(String provinceNameEn) {
		this.provinceNameEn = provinceNameEn;
	}
	public void setProvinceNameJp(String provinceNameJp) {
		this.provinceNameJp = provinceNameJp;
	}
	public void setCountryInfo(CountryInfo countryInfo) {
		this.countryInfo = countryInfo;
	}
	public void setCountryInfoId(String countryInfoId) {
		this.countryInfoId = countryInfoId;
	}
	public void setZoneInfos(List<ZoneInfo> zoneInfos) {
		this.zoneInfos = zoneInfos;
	}
}
