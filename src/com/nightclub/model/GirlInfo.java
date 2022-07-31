package com.nightclub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
//	private String shopInfoId;
//	private String code;
	private String nickName;
//	private String category;
//	private String location;
	private Integer age;
	private Integer bustSize;
	private Integer waistSize;
	private Integer hipSize;
	private Integer height;
	private Integer weight;
	private String description;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	private String available;
	private String allSame;
	private String lineId;
	private String nationalityInfoId;
	
	private NationalityInfo nationalityInfo;
	
//	private ZoneInfo zoneInfo;
	
//	private BasicInfo basicInfo;
	
	private List<GirlLocation> girlLocations = new ArrayList<GirlLocation>();
	
	@Id
	@Column(name="girl_info_id")
	@Length(max=40)
	public String getGirlInfoId() {
		return girlInfoId;
	}
//	@Column(name="shop_info_id")
//	public String getShopInfoId() {
//		return shopInfoId;
//	}
//	@Column(name="code")
//	public String getCode() {
//		return code;
//	}
	@Column(name="nick_name")
	public String getNickName() {
		return nickName;
	}
//	@Column(name="category")
//	public String getCategory() {
//		return category;
//	}
//	@Column(name="location")
//	public String getLocation() {
//		return location;
//	}
	@Column(name="age")
	public Integer getAge() {
		if (age == null) {
			return 0;
		} else {
			return age;
		}
	}
	@Column(name="bust_size")
	public Integer getBustSize() {
		return bustSize;
	}
	@Column(name="waist_size")
	public Integer getWaistSize() {
		return waistSize;
	}
	@Column(name="hip_size")
	public Integer getHipSize() {
		return hipSize;
	}
	@Column(name="height")
	public Integer getHeight() {
		return height;
	}
	@Column(name="weight")
	public Integer getWeight() {
		return weight;
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
	@Column(name="available")
	public String getAvailable() {
		return available;
	}
//	@OneToOne
//	@NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name="shop_info_id", referencedColumnName="shop_info_id", insertable=false, updatable=false)
//	public BasicInfo getBasicInfo() {
//		return basicInfo;
//	}
	@OneToMany(mappedBy = "primaryKey.girlInfo",
            cascade = CascadeType.ALL)
	public List<GirlLocation> getGirlLocations() {
		return girlLocations;
	}
	@Column(name="all_same")
	public String getAllSame() {
		return allSame;
	}
	@Column(name="line_id")
	public String getLineId() {
		if (lineId == null) {
			return "";
		}
		return lineId;
	}
	@Column(name="nationality_info_id")
	public String getNationalityInfoId() {
		return nationalityInfoId;
	}
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="nationality_info_id", referencedColumnName="nationality_info_id", insertable=false, updatable=false)
	public NationalityInfo getNationalityInfo() {
		return nationalityInfo;
	}
	public void setGirlInfoId(String girlInfoId) {
		this.girlInfoId = girlInfoId;
	}
//	public void setShopInfoId(String shopInfoId) {
//		this.shopInfoId = shopInfoId;
//	}
//	public void setCode(String code) {
//		this.code = code;
//	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
//	public void setCategory(String category) {
//		this.category = category;
//	}
//	public void setLocation(String location) {
//		this.location = location;
//	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setBustSize(Integer bustSize) {
		this.bustSize = bustSize;
	}
	public void setWaistSize(Integer waistSize) {
		this.waistSize = waistSize;
	}
	public void setHipSize(Integer hipSize) {
		this.hipSize = hipSize;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
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
	public void setAvailable(String available) {
		this.available = available;
	}
//	public void setBasicInfo(BasicInfo basicInfo) {
//		this.basicInfo = basicInfo;
//	}
//	@OneToOne
//	@NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name="location", referencedColumnName="zone_info_id", insertable=false, updatable=false)
//	public ZoneInfo getZoneInfo() {
//		return zoneInfo;
//	}
//	public void setZoneInfo(ZoneInfo zoneInfo) {
//		this.zoneInfo = zoneInfo;
//	}
	public void setGirlLocations(List<GirlLocation> girlLocations) {
		this.girlLocations = girlLocations;
	}
	public void setAllSame(String allSame) {
		this.allSame = allSame;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	public void setNationalityInfoId(String nationalityInfoId) {
		this.nationalityInfoId = nationalityInfoId;
	}
	public void setNationalityInfo(NationalityInfo nationalityInfo) {
		this.nationalityInfo = nationalityInfo;
	}
}
