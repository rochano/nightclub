package com.nightclub.model;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="schedule_info")
public class ScheduleInfo implements Serializable{

	private static final long serialVersionUID = -2654459264005632131L;
	private Logger log_ = Logger.getLogger(this.getClass().getName());

	private String scheduleInfoId;
	private String girlInfoId;
	private String shopInfoId;
	private String workMon;
	private String workTue;
	private String workWed;
	private String workThu;
	private String workFri;
	private String workSat;
	private String workSun;
	private String startTime;
	private String endTime;
	
	private GirlInfo girlInfo;
	
	@Id
	@Column(name="schedule_info_id")
	@Length(max=40)
	public String getScheduleInfoId() {
		return scheduleInfoId;
	}
	@Column(name="girl_info_id")
	public String getGirlInfoId() {
		return girlInfoId;
	}
	@Column(name="shop_info_id")
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="work_mon")
	public String getWorkMon() {
		return workMon;
	}
	@Column(name="work_tue")
	public String getWorkTue() {
		return workTue;
	}
	@Column(name="work_wed")
	public String getWorkWed() {
		return workWed;
	}
	@Column(name="work_thu")
	public String getWorkThu() {
		return workThu;
	}
	@Column(name="work_fri")
	public String getWorkFri() {
		return workFri;
	}
	@Column(name="work_sat")
	public String getWorkSat() {
		return workSat;
	}
	@Column(name="work_sun")
	public String getWorkSun() {
		return workSun;
	}
	@Column(name="start_time")
	public String getStartTime() {
		return startTime;
	}
	@Column(name="end_time")
	public String getEndTime() {
		return endTime;
	}
	@OneToOne
    @JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	public GirlInfo getGirlInfo() {
		return girlInfo;
	}
	public void setScheduleInfoId(String scheduleInfoId) {
		this.scheduleInfoId = scheduleInfoId;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setWorkMon(String workMon) {
		this.workMon = workMon;
	}
	public void setWorkTue(String workTue) {
		this.workTue = workTue;
	}
	public void setWorkWed(String workWed) {
		this.workWed = workWed;
	}
	public void setWorkThu(String workThu) {
		this.workThu = workThu;
	}
	public void setWorkFri(String workFri) {
		this.workFri = workFri;
	}
	public void setWorkSat(String workSat) {
		this.workSat = workSat;
	}
	public void setWorkSun(String workSun) {
		this.workSun = workSun;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}
}
