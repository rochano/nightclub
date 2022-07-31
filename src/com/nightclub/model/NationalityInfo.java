package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="nationality_info")
public class NationalityInfo implements Serializable {

	private static final long serialVersionUID = -3880761944268621852L;
	
	private String nationalityInfoId;
	private String nationalityNameEn;
	private String nationalityNameJp;

	@Id
	@Column(name="nationality_info_id")
	@Length(max=40)
	public String getNationalityInfoId() {
		return nationalityInfoId;
	}

	@Column(name="nationality_name_en")
	public String getNationalityNameEn() {
		return nationalityNameEn;
	}
	@Column(name="nationality_name_jp")
	public String getNationalityNameJp() {
		return nationalityNameJp;
	}
	public void setNationalityInfoId(String nationalityInfoId) {
		this.nationalityInfoId = nationalityInfoId;
	}
	public void setNationalityNameEn(String nationalityNameEn) {
		this.nationalityNameEn = nationalityNameEn;
	}
	public void setNationalityNameJp(String nationalityNameJp) {
		this.nationalityNameJp = nationalityNameJp;
	}
}
