package com.nightclub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="country_info")
public class CountryInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String countryInfoId;
	private String countryNameEn;
	private String countryNameJp;

	private List<ProvinceInfo> provinceInfos = new ArrayList<ProvinceInfo>();
	
	@Id
	@Column(name="country_info_id")
	@Length(max=40)
	public String getCountryInfoId() {
		return countryInfoId;
	}
	@Column(name="country_name_en")
	public String getCountryNameEn() {
		return countryNameEn;
	}
	@Column(name="country_name_jp")
	public String getCountryNameJp() {
		return countryNameJp;
	}
	@OneToMany(mappedBy = "countryInfo",
            cascade = CascadeType.ALL)
	public List<ProvinceInfo> getProvinceInfos() {
		return provinceInfos;
	}
	public void setCountryInfoId(String countryInfoId) {
		this.countryInfoId = countryInfoId;
	}
	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
	}
	public void setCountryNameJp(String countryNameJp) {
		this.countryNameJp = countryNameJp;
	}
	public void setProvinceInfos(List<ProvinceInfo> provinceInfos) {
		this.provinceInfos = provinceInfos;
	}
}
