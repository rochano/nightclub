package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="statistic_girl_info")
public class StatisticGirlInfo implements Serializable{

	private static final long serialVersionUID = 7565802516538473333L;
	private StatisticGirlInfoPK statisticGirlInfoPK;
	private Integer hit;

	@EmbeddedId
	public StatisticGirlInfoPK getStatisticGirlInfoPK() {
		return statisticGirlInfoPK;
	}
	@Column(name="hit")
	public Integer getHit() {
		return hit;
	}
	public void setStatisticGirlInfoPK(StatisticGirlInfoPK statisticGirlInfoPK) {
		this.statisticGirlInfoPK = statisticGirlInfoPK;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
}
