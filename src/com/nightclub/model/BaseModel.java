package com.nightclub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {
	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	
	@Column(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	@Column(name="updated_by")
	public String getUpdatedBy() {
		return updatedBy;
	}
	@Column(name="updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
