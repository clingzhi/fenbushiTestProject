package cc.ysf.dx.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * >>> 酒店实体类
 */
@ApiModel(value = "酒店实体对象")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 2460539388787142885L;
	@ApiModelProperty("[1] 酒店ID")
	private Long id;
	@ApiModelProperty("[1] 酒店名字")
	private String hotelName;
	@ApiModelProperty("[1] 国家ID")
	private Long countryId;
	@ApiModelProperty("[1]  省份ID")
	private Long provinceId;
	@ApiModelProperty("[1] 城市ID ")
	private Long cityId;
	@ApiModelProperty("[1]  酒店地址")
	private String address;
	@ApiModelProperty("[1] 酒店详情 ")
	private String details;
	@ApiModelProperty("[1] 酒店设施 ")
	private String facilities;
	@ApiModelProperty("[1] 酒店政策 ")
	private String hotelPolicy;
	@ApiModelProperty("[1]  酒店风格")
	private Integer hotelType;
	@ApiModelProperty("[1] 酒店星级 ")
	private Integer hotelLevel;
	@ApiModelProperty("[1]  ")
	private Integer isGroupPurchase;
	@ApiModelProperty("[1]  ")
	private String redundantCityName;
	@ApiModelProperty(value = "[2]  ",required = false)
	private String redundantProvinceName;
	@ApiModelProperty(value ="[2]  ",required = false)
	private String redundantCountryName;
	@ApiModelProperty(value ="[2]  ",required = false)
	private Integer redundantHotelStore;
	@ApiModelProperty(value ="[1]  ")
	private Date creationDate;
	@ApiModelProperty(value ="[2]  ",required = false)
	private Long createdBy;
	@ApiModelProperty(value ="[2]  ",required = false)
	private Date modifyDate;
	@ApiModelProperty(value ="[2]  ",required = false)
	private Long modifiedBy;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getHotelPolicy() {
		return hotelPolicy;
	}

	public void setHotelPolicy(String hotelPolicy) {
		this.hotelPolicy = hotelPolicy;
	}

	public Integer getHotelType() {
		return hotelType;
	}

	public void setHotelType(Integer hotelType) {
		this.hotelType = hotelType;
	}

	public Integer getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(Integer hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public Integer getIsGroupPurchase() {
		return isGroupPurchase;
	}

	public void setIsGroupPurchase(Integer isGroupPurchase) {
		this.isGroupPurchase = isGroupPurchase;
	}

	public String getRedundantCityName() {
		return redundantCityName;
	}

	public void setRedundantCityName(String redundantCityName) {
		this.redundantCityName = redundantCityName;
	}

	public String getRedundantProvinceName() {
		return redundantProvinceName;
	}

	public void setRedundantProvinceName(String redundantProvinceName) {
		this.redundantProvinceName = redundantProvinceName;
	}

	public String getRedundantCountryName() {
		return redundantCountryName;
	}

	public void setRedundantCountryName(String redundantCountryName) {
		this.redundantCountryName = redundantCountryName;
	}

	public Integer getRedundantHotelStore() {
		return redundantHotelStore;
	}

	public void setRedundantHotelStore(Integer redundantHotelStore) {
		this.redundantHotelStore = redundantHotelStore;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
