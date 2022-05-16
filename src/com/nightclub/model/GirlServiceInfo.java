package com.nightclub.model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name="girl_service_info")
public class GirlServiceInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String girlServiceInfoId;
	private String girlServiceName;
	private String girlServiceNameJp;
	private BigInteger orderNo;

	private List<GirlService> girlServiceList = new ArrayList<GirlService>();

	@Id
	@Column(name="girl_service_info_id")
	@Length(max=40)
	public String getGirlServiceInfoId() {
		return girlServiceInfoId;
	}
	@Column(name="girl_service_name")
	public String getGirlServiceName() {
		return girlServiceName;
	}
	@Column(name="girl_service_name_jp")
	public String getGirlServiceNameJp() {
		return girlServiceNameJp;
	}
	@Column(name = "order_no")
	public BigInteger getOrderNo() {
		return orderNo;
	}
	@OneToMany(mappedBy = "primaryKey.girlServiceInfo",
            cascade = CascadeType.ALL)
	public List<GirlService> getGirlServiceList() {
		return girlServiceList;
	}
	public void setGirlServiceInfoId(String girlServiceInfoId) {
		this.girlServiceInfoId = girlServiceInfoId;
	}
	public void setGirlServiceName(String girlServiceName) {
		this.girlServiceName = girlServiceName;
	}
	public void setGirlServiceNameJp(String girlServiceNameJp) {
		this.girlServiceNameJp = girlServiceNameJp;
	}
	public void setOrderNo(BigInteger orderNo) {
		this.orderNo = orderNo;
	}
	public void setGirlServiceList(List<GirlService> girlServiceList) {
		this.girlServiceList = girlServiceList;
	}
}
