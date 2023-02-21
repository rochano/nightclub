package com.nightclub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="agent_girl_info")
public class AgentGirlInfo extends FreeAgentGirlInfo {
	private static final long serialVersionUID = -1930927867933854705L;
	private String agentInfoId;
	private AgentInfo agentInfo;
	private String chkWorkSun;
	private String chkWorkMon;
	private String chkWorkTue;
	private String chkWorkWed;
	private String chkWorkThu;
	private String chkWorkFri;
	private String chkWorkSat;
	private String workSunTime;
	private String workMonTime;
	private String workTueTime;
	private String workWedTime;
	private String workThuTime;
	private String workFriTime;
	private String workSatTime;
	private String phone;

	@Column(name="agent_info_id")
	public String getAgentInfoId() {
		return agentInfoId;
	}
	@OneToOne
    @JoinColumn(name="agent_info_id", insertable=false, updatable=false)
	public AgentInfo getAgentInfo() {
		return agentInfo;
	}
	@Column(name="line_id", insertable=false, updatable=false)
	public String getLineId() {
		if (agentInfo == null) { 
			return "";
		}
		return agentInfo.getLineId();
	}
	@Column(name="chk_work_sun")
	public String getChkWorkSun() {
		return chkWorkSun;
	}
	@Column(name="chk_work_mon")
	public String getChkWorkMon() {
		return chkWorkMon;
	}
	@Column(name="chk_work_tue")
	public String getChkWorkTue() {
		return chkWorkTue;
	}
	@Column(name="chk_work_wed")
	public String getChkWorkWed() {
		return chkWorkWed;
	}
	@Column(name="chk_work_thu")
	public String getChkWorkThu() {
		return chkWorkThu;
	}
	@Column(name="chk_work_fri")
	public String getChkWorkFri() {
		return chkWorkFri;
	}
	@Column(name="chk_work_sat")
	public String getChkWorkSat() {
		return chkWorkSat;
	}
	@Column(name="work_sun_time")
	public String getWorkSunTime() {
		return workSunTime;
	}
	@Column(name="work_mon_time")
	public String getWorkMonTime() {
		return workMonTime;
	}
	@Column(name="work_tue_time")
	public String getWorkTueTime() {
		return workTueTime;
	}
	@Column(name="work_wed_time")
	public String getWorkWedTime() {
		return workWedTime;
	}
	@Column(name="work_thu_time")
	public String getWorkThuTime() {
		return workThuTime;
	}
	@Column(name="work_fri_time")
	public String getWorkFriTime() {
		return workFriTime;
	}
	@Column(name="work_sat_time")
	public String getWorkSatTime() {
		return workSatTime;
	}
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setAgentInfoId(String agentInfoId) {
		this.agentInfoId = agentInfoId;
	}
	public void setAgentInfo(AgentInfo agentInfo) {
		this.agentInfo = agentInfo;
	}
	public void setChkWorkSun(String chkWorkSun) {
		this.chkWorkSun = chkWorkSun;
	}
	public void setChkWorkMon(String chkWorkMon) {
		this.chkWorkMon = chkWorkMon;
	}
	public void setChkWorkTue(String chkWorkTue) {
		this.chkWorkTue = chkWorkTue;
	}
	public void setChkWorkWed(String chkWorkWed) {
		this.chkWorkWed = chkWorkWed;
	}
	public void setChkWorkThu(String chkWorkThu) {
		this.chkWorkThu = chkWorkThu;
	}
	public void setChkWorkFri(String chkWorkFri) {
		this.chkWorkFri = chkWorkFri;
	}
	public void setChkWorkSat(String chkWorkSat) {
		this.chkWorkSat = chkWorkSat;
	}
	public void setWorkSunTime(String workSunTime) {
		this.workSunTime = workSunTime;
	}
	public void setWorkMonTime(String workMonTime) {
		this.workMonTime = workMonTime;
	}
	public void setWorkTueTime(String workTueTime) {
		this.workTueTime = workTueTime;
	}
	public void setWorkWedTime(String workWedTime) {
		this.workWedTime = workWedTime;
	}
	public void setWorkThuTime(String workThuTime) {
		this.workThuTime = workThuTime;
	}
	public void setWorkFriTime(String workFriTime) {
		this.workFriTime = workFriTime;
	}
	public void setWorkSatTime(String workSatTime) {
		this.workSatTime = workSatTime;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
