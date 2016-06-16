package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

public class NewsSearch implements Serializable {
	
	private static final long serialVersionUID = 1161922909374025230L;
	
	private String title;
	private Date newsDateFrom;
	private Date newsDateTo;
	
	public String getTitle() {
		return title;
	}
	public Date getNewsDateFrom() {
		return newsDateFrom;
	}
	public Date getNewsDateTo() {
		return newsDateTo;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setNewsDateFrom(Date newsDateFrom) {
		this.newsDateFrom = newsDateFrom;
	}
	public void setNewsDateTo(Date newsDateTo) {
		this.newsDateTo = newsDateTo;
	}
	
}
