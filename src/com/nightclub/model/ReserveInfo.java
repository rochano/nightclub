package com.nightclub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name="reserve_info")
public class ReserveInfo implements Serializable{

	private static final long serialVersionUID = 6848161747389312861L;

	private String reserveInfoId;
	private String girlInfoId;
	private String email;
	private String clientName;
	private String contactMethod;
	private String contactId;
	private Date reserveDate;
	private String reserveTime;
	private String chkFlexible;
	private String hotel;
	private String roomNo;
	private String chkNotCheckedIn;
	private String incallOutcall;
	private String duration;
	private List<ReserveGirlService> reserveGirlServices = new ArrayList<ReserveGirlService>();
	private String inquiry;

	private GirlInfo girlInfo; 

	@Id
	@Column(name="reserve_info_id")
	@Length(max=40)
	public String getReserveInfoId() {
		return reserveInfoId;
	}
	@Column(name="girl_info_id")
	public String getGirlInfoId() {
		return girlInfoId;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	@Column(name="client_name")
	public String getClientName() {
		return clientName;
	}
	@Column(name="contact_method")
	public String getContactMethod() {
		return contactMethod;
	}
	@Column(name="contact_id")
	public String getContactId() {
		return contactId;
	}
	@Column(name="reserve_date")
	public Date getReserveDate() {
		return reserveDate;
	}
	@Column(name="reserve_time")
	public String getReserveTime() {
		return reserveTime;
	}
	@Column(name="chk_flexible")
	public String getChkFlexible() {
		return chkFlexible;
	}
	@Column(name="hotel")
	public String getHotel() {
		return hotel;
	}
	@Column(name="room_no")
	public String getRoomNo() {
		return roomNo;
	}
	@Column(name="chk_not_checked_in")
	public String getChkNotCheckedIn() {
		return chkNotCheckedIn;
	}
	@Column(name="incall_outcall")
	public String getIncallOutcall() {
		return incallOutcall;
	}
	@Column(name="duration")
	public String getDuration() {
		return duration;
	}
	@OneToMany(mappedBy = "primaryKey.reserveInfo",
            cascade = CascadeType.ALL)
	public List<ReserveGirlService> getReserveGirlServices() {
		return reserveGirlServices;
	}
	@Column(name="inquiry")
	public String getInquiry() {
		return inquiry;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="girl_info_id", insertable=false, updatable=false)
	public GirlInfo getGirlInfo() {
		return girlInfo;
	}
	public void setReserveInfoId(String reserveInfoId) {
		this.reserveInfoId = reserveInfoId;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public void setChkFlexible(String chkFlexible) {
		this.chkFlexible = chkFlexible;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public void setChkNotCheckedIn(String chkNotCheckedIn) {
		this.chkNotCheckedIn = chkNotCheckedIn;
	}
	public void setIncallOutcall(String incallOutcall) {
		this.incallOutcall = incallOutcall;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setReserveGirlServices(List<ReserveGirlService> reserveGirlServices) {
		this.reserveGirlServices = reserveGirlServices;
	}
	public void setInquiry(String inquiry) {
		this.inquiry = inquiry;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}
}
