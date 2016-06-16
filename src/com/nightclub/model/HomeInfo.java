package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="home_info")
public class HomeInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String homeInfoId;
	private String description;
	
	@Id
	@Column(name="home_info_id")
	@Length(max=40)
	public String getHomeInfoId() {
		return homeInfoId;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setHomeInfoId(String homeInfoId) {
		this.homeInfoId = homeInfoId;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
