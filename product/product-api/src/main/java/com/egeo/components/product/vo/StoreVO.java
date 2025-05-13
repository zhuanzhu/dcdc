package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xia
 * @date 2018-09-11 15:11:44
 */
public class StoreVO implements Serializable {
	
	


	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 门店编号
	 */
	private String serialNumber;
	/**
	 * 平台id
	 */
	private Long companyId;
	/**
	 * 活动短码
	 */
	private String activityCode;
	
	/**
	 * 活动短码
	 */
	private String nodeCode;	
	/**
	 * 
	 */
	private String name;
	/**
	 * 0 否， 1是
	 */
	private Long isDetail;
	/**
	 * 收货人省份ID
	 */
	private Long provinceId;
	/**
	 * 
	 */
	private String province;
	/**
	 * 收货人城市ID
	 */
	private Long cityId;
	/**
	 * 
	 */
	private String city;
	/**
	 * 收货人地区ID
	 */
	private Long countyId;
	/**
	 * 
	 */
	private String county;
	/**
	 * 收货人四级区域ID
	 */
	private Long areaId;
	/**
	 * 
	 */
	private String area;
	/**
	 * 地址详情
	 */
	private String detailAddress;
	/**
	 * 门店折扣，如不填写，默认为上一级门店折扣率
	 */
	private Long discount;
	/**
	 * 0 否， 1是
	 */
	private Long hasStoreMenu;
	/**
	 * 
	 */
	private String description;
	/**
	 * 类目bannerId
	 */
	private Long categoryBannerId;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 门店标识id集合
	 */
	private List ids;
	/**
	 * 优惠券标识
	 */
	private Long couponId;
	
	
	private Long storeTreeId;
	
	private Long nodeId;	

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 门店编号
	 * @return 门店编号
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * 门店编号
	 * @param serialNumber 门店编号
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}	
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 平台id
	 * @param companyId 平台id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}	
	/**
	 * 活动短码
	 * @return 活动短码
	 */
	public String getActivityCode() {
		return activityCode;
	}

	/**
	 * 活动短码
	 * @param activityCode 活动短码
	 */
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 0 否， 1是
	 * @return 0 否， 1是
	 */
	public Long getIsDetail() {
		return isDetail;
	}

	/**
	 * 0 否， 1是
	 * @param isDetail 0 否， 1是
	 */
	public void setIsDetail(Long isDetail) {
		this.isDetail = isDetail;
	}	
	/**
	 * 收货人省份ID
	 * @return 收货人省份ID
	 */
	public Long getProvinceId() {
		return provinceId;
	}

	/**
	 * 收货人省份ID
	 * @param provinceId 收货人省份ID
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 
	 * @param province 
	 */
	public void setProvince(String province) {
		this.province = province;
	}	
	/**
	 * 收货人城市ID
	 * @return 收货人城市ID
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * 收货人城市ID
	 * @param cityId 收货人城市ID
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city 
	 */
	public void setCity(String city) {
		this.city = city;
	}	
	/**
	 * 收货人地区ID
	 * @return 收货人地区ID
	 */
	public Long getCountyId() {
		return countyId;
	}

	/**
	 * 收货人地区ID
	 * @param countyId 收货人地区ID
	 */
	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * 
	 * @param county 
	 */
	public void setCounty(String county) {
		this.county = county;
	}	
	/**
	 * 收货人四级区域ID
	 * @return 收货人四级区域ID
	 */
	public Long getAreaId() {
		return areaId;
	}

	/**
	 * 收货人四级区域ID
	 * @param areaId 收货人四级区域ID
	 */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getArea() {
		return area;
	}

	/**
	 * 
	 * @param area 
	 */
	public void setArea(String area) {
		this.area = area;
	}	
	/**
	 * 地址详情
	 * @return 地址详情
	 */
	public String getDetailAddress() {
		return detailAddress;
	}

	/**
	 * 地址详情
	 * @param detailAddress 地址详情
	 */
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}	
	/**
	 * 门店折扣，如不填写，默认为上一级门店折扣率
	 * @return 门店折扣，如不填写，默认为上一级门店折扣率
	 */
	public Long getDiscount() {
		return discount;
	}

	/**
	 * 门店折扣，如不填写，默认为上一级门店折扣率
	 * @param discount 门店折扣，如不填写，默认为上一级门店折扣率
	 */
	public void setDiscount(Long discount) {
		this.discount = discount;
	}	
	/**
	 * 0 否， 1是
	 * @return 0 否， 1是
	 */
	public Long getHasStoreMenu() {
		return hasStoreMenu;
	}

	/**
	 * 0 否， 1是
	 * @param hasStoreMenu 0 否， 1是
	 */
	public void setHasStoreMenu(Long hasStoreMenu) {
		this.hasStoreMenu = hasStoreMenu;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description 
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	/**
	 * 类目bannerId
	 * @return 类目bannerId
	 */
	public Long getCategoryBannerId() {
		return categoryBannerId;
	}

	/**
	 * 类目bannerId
	 * @param categoryBannerId 类目bannerId
	 */
	public void setCategoryBannerId(Long categoryBannerId) {
		this.categoryBannerId = categoryBannerId;
	}	
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public List getIds() {
		return ids;
	}

	public void setIds(List ids) {
		this.ids = ids;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getStoreTreeId() {
		return storeTreeId;
	}

	public void setStoreTreeId(Long storeTreeId) {
		this.storeTreeId = storeTreeId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	
	
}
	