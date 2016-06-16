package com.nightclub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.Length;

@Entity
@Table(name="category_info")
public class CategoryInfo implements Serializable {

	private static final long serialVersionUID = 3861433140662841566L;
	private String categoryInfoId;
	private String categoryCode;
	private String categoryNameEn;
	private String categoryNameJp;
	private String description;
	
	private List<CategoryZone> categoryZones = new ArrayList<CategoryZone>();
	
	@Id
	@Column(name="category_info_id")
	@Length(max=40)
	public String getCategoryInfoId() {
		return categoryInfoId;
	}
	@Column(name="category_code", unique=true)
	public String getCategoryCode() {
		return categoryCode;
	}
	@Column(name="category_name_en")
	public String getCategoryNameEn() {
		return categoryNameEn;
	}
	@Column(name="category_name_jp")
	public String getCategoryNameJp() {
		return categoryNameJp;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	@OneToMany(mappedBy = "primaryKey.categoryInfo",
            cascade = CascadeType.ALL)
	public List<CategoryZone> getCategoryZones() {
		return categoryZones;
	}
	public void setCategoryInfoId(String categoryInfoId) {
		this.categoryInfoId = categoryInfoId;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public void setCategoryNameEn(String categoryNameEn) {
		this.categoryNameEn = categoryNameEn;
	}
	public void setCategoryNameJp(String categoryNameJp) {
		this.categoryNameJp = categoryNameJp;
	}
	public void setCategoryZones(List<CategoryZone> categoryZones) {
		this.categoryZones = categoryZones;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
