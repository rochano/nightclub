package com.nightclub.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="girl_service_info")
public class GirlServiceInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String girlServiceInfoId;
	private String girlServiceName;
	private BigInteger orderNo;

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
	@Column(name = "order_no")
	public BigInteger getOrderNo() {
		return orderNo;
	}
	public void setGirlServiceInfoId(String girlServiceInfoId) {
		this.girlServiceInfoId = girlServiceInfoId;
	}
	public void setGirlServiceName(String girlServiceName) {
		this.girlServiceName = girlServiceName;
	}
	public void setOrderNo(BigInteger orderNo) {
		this.orderNo = orderNo;
	}
}
