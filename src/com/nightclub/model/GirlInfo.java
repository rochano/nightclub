package com.nightclub.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.Length;

@Entity
@Table(name="girl_info")
public class GirlInfo extends BaseModel implements Serializable{

	private static final long serialVersionUID = 6848161747389312861L;
	
	private String girlInfoId;
	private String shopInfoId;
	private String code;
	private String firstName;
	private String lastName;
	private String nickName;
	private Integer age;
	private String hometown;
	private Double height;
	private Double bustSize;
	private Double waistSize;
	private Double hipSize;
	private String status;
	private int ranking;
	private String description;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	
	private BasicInfo basicInfo;
	
	@Id
	@Column(name="girl_info_id")
	@Length(max=40)
	public String getGirlInfoId() {
		return girlInfoId;
	}
	@Column(name="shop_info_id")
	public String getShopInfoId() {
		return shopInfoId;
	}
	@Column(name="code")
	public String getCode() {
		return code;
	}
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	@Column(name="nick_name")
	public String getNickName() {
		return nickName;
	}
	@Column(name="age")
	public Integer getAge() {
		return age;
	}
	@Column(name="hometown")
	public String getHometown() {
		return hometown;
	}
	@Column(name="height")
	public Double getHeight() {
		return height;
	}
	@Column(name="bust_size")
	public Double getBustSize() {
		return bustSize;
	}
	@Column(name="waist_size")
	public Double getWaistSize() {
		return waistSize;
	}
	@Column(name="hip_size")
	public Double getHipSize() {
		return hipSize;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	@Column(name="ranking")
	public int getRanking() {
		return ranking;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	@Column(name="pic1")
	public String getPic1() {
		return pic1;
	}
	@Column(name="pic2")
	public String getPic2() {
		return pic2;
	}
	@Column(name="pic3")
	public String getPic3() {
		return pic3;
	}
	@Column(name="pic4")
	public String getPic4() {
		return pic4;
	}
	@Column(name="pic5")
	public String getPic5() {
		return pic5;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="shop_info_id", referencedColumnName="shop_info_id", insertable=false, updatable=false)
	public BasicInfo getBasicInfo() {
		return basicInfo;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
	public void setShopInfoId(String shopInfoId) {
		this.shopInfoId = shopInfoId;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public void setBustSize(Double bustSize) {
		this.bustSize = bustSize;
	}
	public void setWaistSize(Double waistSize) {
		this.waistSize = waistSize;
	}
	public void setHipSize(Double hipSize) {
		this.hipSize = hipSize;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}
	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}
	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
}
