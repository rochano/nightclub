package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

public class FormDay implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private Date date;
	private boolean sunday;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;
	private boolean saturday;

	public Date getDate() {
		return date;
	}
	public boolean isSunday() {
		return sunday;
	}
	public boolean isMonday() {
		return monday;
	}
	public boolean isTuesday() {
		return tuesday;
	}
	public boolean isWednesday() {
		return wednesday;
	}
	public boolean isThursday() {
		return thursday;
	}
	public boolean isFriday() {
		return friday;
	}
	public boolean isSaturday() {
		return saturday;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setSunday(boolean sunday) {
		this.sunday = sunday;
	}
	public void setMonday(boolean monday) {
		this.monday = monday;
	}
	public void setTuesday(boolean tuesday) {
		this.tuesday = tuesday;
	}
	public void setWednesday(boolean wednesday) {
		this.wednesday = wednesday;
	}
	public void setThursday(boolean thursday) {
		this.thursday = thursday;
	}
	public void setFriday(boolean friday) {
		this.friday = friday;
	}
	public void setSaturday(boolean saturday) {
		this.saturday = saturday;
	}
	
}
