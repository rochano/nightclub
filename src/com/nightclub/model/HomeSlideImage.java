package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="home_slide_image")
public class HomeSlideImage implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer homeSlideImageId;
	private String slideImg;
	
	@Id
	@Column(name="home_slide_image_id")
	public Integer getHomeSlideImageId() {
		return homeSlideImageId;
	}
	@Column(name="slide_img")
	public String getSlideImg() {
		return slideImg;
	}
	public void setHomeSlideImageId(Integer homeSlideImageId) {
		this.homeSlideImageId = homeSlideImageId;
	}
	public void setSlideImg(String slideImg) {
		this.slideImg = slideImg;
	}
}
