package com.nightclub.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="statistic_info")
public class StatisticInfo implements Serializable{

	private static final long serialVersionUID = 7565802516538473333L;
	private StatisticInfoPK statisticInfoPK;
	private Integer hit;
	private Timestamp online;

	@EmbeddedId
	public StatisticInfoPK getStatisticInfoPK() {
		return statisticInfoPK;
	}
	@Column(name="hit")
	public Integer getHit() {
		return hit;
	}
	@Column(name="online")
	public Timestamp getOnline() {
		return online;
	}
	public void setStatisticInfoPK(StatisticInfoPK statisticInfoPK) {
		this.statisticInfoPK = statisticInfoPK;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public void setOnline(Timestamp online) {
		this.online = online;
	}
}
