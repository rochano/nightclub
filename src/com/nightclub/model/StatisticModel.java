package com.nightclub.model;

public class StatisticModel {
	private int todayView;
	private int yesterdayView;
	private int monthView;
	private int yearView;
	private int totalView;
	private int hitsView;
	private int totalHitsView;
	private int onlineView;
	private int contStart;
	private int hitsStart;
	private String ipAddress;
	private String accessDt;
	 
	public int getTodayView() {
		return todayView;
	}
	public int getYesterdayView() {
		return yesterdayView;
	}
	public int getMonthView() {
		return monthView;
	}
	public int getYearView() {
		return yearView;
	}
	public int getTotalView() {
		return totalView;
	}
	public int getHitsView() {
		return hitsView;
	}
	public int getTotalHitsView() {
		return totalHitsView;
	}
	public int getOnlineView() {
		return onlineView;
	}
	public int getContStart() {
		return contStart;
	}
	public int getHitsStart() {
		return hitsStart;
	}
	public void setTodayView(int todayView) {
		this.todayView = todayView;
	}
	public void setYesterdayView(int yesterdayView) {
		this.yesterdayView = yesterdayView;
	}
	public void setMonthView(int monthView) {
		this.monthView = monthView;
	}
	public void setYearView(int yearView) {
		this.yearView = yearView;
	}
	public void setTotalView(int totalView) {
		this.totalView = totalView;
	}
	public void setHitsView(int hitsView) {
		this.hitsView = hitsView;
	}
	public void setTotalHitsView(int totalHitsView) {
		this.totalHitsView = totalHitsView;
	}
	public void setOnlineView(int onlineView) {
		this.onlineView = onlineView;
	}
	public void setContStart(int contStart) {
		this.contStart = contStart;
	}
	public void setHitsStart(int hitsStart) {
		this.hitsStart = hitsStart;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public String getAccessDt() {
		return accessDt;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public void setAccessDt(String accessDt) {
		this.accessDt = accessDt;
	}
}
