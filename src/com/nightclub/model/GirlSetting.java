package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="girl_setting")
public class GirlSetting implements Serializable {

	private static final long serialVersionUID = -1026685026726834603L;
	private String girlSettingId;
	private Integer ageFrom;
	private Integer ageTo;
	private Integer bustSizeFrom;
	private Integer bustSizeTo;
	private Integer waistSizeFrom;
	private Integer waistSizeTo;
	private Integer hipSizeFrom;
	private Integer hipSizeTo;
	private Integer heightFrom;
	private Integer heightTo;
	private Integer weightFrom;
	private Integer weightTo;

	@Id
	@Column(name="girl_setting_id")
	@Length(max=40)
	public String getGirlSettingId() {
		return girlSettingId;
	}
	@Column(name="age_from")
	public Integer getAgeFrom() {
		return ageFrom;
	}
	@Column(name="age_to")
	public Integer getAgeTo() {
		return ageTo;
	}
	@Column(name="bust_size_from")
	public Integer getBustSizeFrom() {
		return bustSizeFrom;
	}
	@Column(name="bust_size_to")
	public Integer getBustSizeTo() {
		return bustSizeTo;
	}
	@Column(name="waist_size_from")
	public Integer getWaistSizeFrom() {
		return waistSizeFrom;
	}
	@Column(name="waist_size_to")
	public Integer getWaistSizeTo() {
		return waistSizeTo;
	}
	@Column(name="hip_size_from")
	public Integer getHipSizeFrom() {
		return hipSizeFrom;
	}
	@Column(name="hip_size_to")
	public Integer getHipSizeTo() {
		return hipSizeTo;
	}
	@Column(name="height_from")
	public Integer getHeightFrom() {
		return heightFrom;
	}
	@Column(name="height_to")
	public Integer getHeightTo() {
		return heightTo;
	}
	@Column(name="weight_from")
	public Integer getWeightFrom() {
		return weightFrom;
	}
	@Column(name="weight_to")
	public Integer getWeightTo() {
		return weightTo;
	}
	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}
	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}
	public void setBustSizeFrom(Integer bustSizeFrom) {
		this.bustSizeFrom = bustSizeFrom;
	}
	public void setBustSizeTo(Integer bustSizeTo) {
		this.bustSizeTo = bustSizeTo;
	}
	public void setWaistSizeFrom(Integer waistSizeFrom) {
		this.waistSizeFrom = waistSizeFrom;
	}
	public void setWaistSizeTo(Integer waistSizeTo) {
		this.waistSizeTo = waistSizeTo;
	}
	public void setHipSizeFrom(Integer hipSizeFrom) {
		this.hipSizeFrom = hipSizeFrom;
	}
	public void setHipSizeTo(Integer hipSizeTo) {
		this.hipSizeTo = hipSizeTo;
	}
	public void setHeightFrom(Integer heightFrom) {
		this.heightFrom = heightFrom;
	}
	public void setHeightTo(Integer heightTo) {
		this.heightTo = heightTo;
	}
	public void setWeightFrom(Integer weightFrom) {
		this.weightFrom = weightFrom;
	}
	public void setWeightTo(Integer weightTo) {
		this.weightTo = weightTo;
	}
	public void setGirlSettingId(String girlSettingId) {
		this.girlSettingId = girlSettingId;
	}

}
