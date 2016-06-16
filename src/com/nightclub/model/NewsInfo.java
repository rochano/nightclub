package com.nightclub.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="news_info")
public class NewsInfo implements Serializable {

	private static final long serialVersionUID = 2557220475954529967L;
	
	private String newsInfoId;
	private String title;
	private Date newsDate;
	private String newsTime;
	private String description;
	
	@Id
	@Column(name="news_info_id")
	@Length(max=40)
	public String getNewsInfoId() {
		return newsInfoId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	@Column(name="news_date")
	public Date getNewsDate() {
		return newsDate;
	}
	@Column(name="news_time")
	public String getNewsTime() {
		return newsTime;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setNewsInfoId(String newsInfoId) {
		this.newsInfoId = newsInfoId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
