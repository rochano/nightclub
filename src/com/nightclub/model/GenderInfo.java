package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="gender_info")
public class GenderInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String genderInfoId;
	private String genderNameEn;
	private String genderNameJp;

	@Id
	@Column(name="gender_info_id")
	@Length(max=40)
	public String getGenderInfoId() {
		return genderInfoId;
	}
	@Column(name="gender_name_en")
	public String getGenderNameEn() {
		return genderNameEn;
	}
	@Column(name="gender_name_jp")
	public String getGenderNameJp() {
		return genderNameJp;
	}
	public void setGenderInfoId(String genderInfoId) {
		this.genderInfoId = genderInfoId;
	}
	public void setGenderNameEn(String genderNameEn) {
		this.genderNameEn = genderNameEn;
	}
	public void setGenderNameJp(String genderNameJp) {
		this.genderNameJp = genderNameJp;
	}
}
