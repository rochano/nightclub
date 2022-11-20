package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name = "girl_comment")
public class GirlComment extends BaseModel implements Serializable {
	private String girlCommentId;
	private String girlInfoId;
	private GirlInfo girlInfo;
	private String comment;
	private int rating;

	@Id
	@Column(name="girl_comment_id")
	@Length(max=40)
	public String getGirlCommentId() {
		return girlCommentId;
	}
	@Column(name = "girl_info_id")
	public String getGirlInfoId() {
		return girlInfoId;
	}
	@OneToOne
	@JoinColumn(name = "girl_info_id", insertable = false, updatable = false)
	public GirlInfo getGirlInfo() {
		return girlInfo;
	}
	@Column(name = "comment")
	public String getComment() {
		return comment;
	}
	@Column(name = "rating")
	public int getRating() {
		return rating;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
	public void setGirlInfo(GirlInfo girlInfo) {
		this.girlInfo = girlInfo;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public void setGirlCommentId(String girlCommentId) {
		this.girlCommentId = girlCommentId;
	}
}
