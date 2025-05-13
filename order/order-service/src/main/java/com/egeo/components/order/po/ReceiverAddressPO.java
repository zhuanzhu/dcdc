package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-09-14 11:26:49
 */
public class ReceiverAddressPO {


	private Long id;

	/**
	 * 收货人地址
	 */
	private String goodReceiverAddress;	

	/**
	 * 收货人地址邮编
	 */
	private String goodReceiverPostcode;	

	/**
	 * 收货人姓名
	 */
	private String goodReceiverName;	

	/**
	 * 收货人手机
	 */
	private String goodReceiverMobile;	

	/**
	 * 收货人电话
	 */
	private String goodReceiverPhone;	

	/**
	 * 收货人国家ID
	 */
	private Long goodReceiverCountryId;	

	/**
	 * 收货人国家
	 */
	private String goodReceiverCountry;	

	/**
	 * 收货人省份ID
	 */
	private Long goodReceiverProvinceId;	

	/**
	 * 收货人省份
	 */
	private String goodReceiverProvince;	

	/**
	 * 收货人城市ID
	 */
	private Long goodReceiverCityId;	

	/**
	 * 收货人城市
	 */
	private String goodReceiverCity;	

	/**
	 * 收货人地区ID
	 */
	private Long goodReceiverCountyId;	

	/**
	 * 收货人地区
	 */
	private String goodReceiverCounty;	

	/**
	 * 收货人四级区域ID
	 */
	private Long goodReceiverAreaId;	

	/**
	 * 收货人四级区域
	 */
	private String goodReceiverArea;	

	/**
	 * 用户ID
	 */
	private Long userId;	

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
	 * 是否是默认收货地址 0:否 1:是
	 */
	private Integer isDefault;	
	/**
	 * 创建类型：1、用户创建 2、运营创建
	 */
	private Integer type;
	
	/**
	 * 修改人账户邮箱
	 */
	private String modifyMail;

	/**
	 *	子订单code
	 */
	private String soChildCode;

	/**
	 * 子订单code
	 * @return soChildCode 子订单code
	 */
	public String getSoChildCode() {
		return soChildCode;
	}

	/**
	 * 子订单code
	 * @param soChildCode 子订单code
	 */
	public void setSoChildCode(String soChildCode) {
		this.soChildCode = soChildCode;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 收货人地址
	 * @return 收货人地址
	 */
	public String getGoodReceiverAddress() {
		return goodReceiverAddress;
	}

	/**
	 * 收货人地址
	 * @param goodReceiverAddress 收货人地址
	 */
	public void setGoodReceiverAddress(String goodReceiverAddress) {
		this.goodReceiverAddress = goodReceiverAddress;
	}

	/**
	 * 收货人地址邮编
	 * @return 收货人地址邮编
	 */
	public String getGoodReceiverPostcode() {
		return goodReceiverPostcode;
	}

	/**
	 * 收货人地址邮编
	 * @param goodReceiverPostcode 收货人地址邮编
	 */
	public void setGoodReceiverPostcode(String goodReceiverPostcode) {
		this.goodReceiverPostcode = goodReceiverPostcode;
	}

	/**
	 * 收货人姓名
	 * @return 收货人姓名
	 */
	public String getGoodReceiverName() {
		return goodReceiverName;
	}

	/**
	 * 收货人姓名
	 * @param goodReceiverName 收货人姓名
	 */
	public void setGoodReceiverName(String goodReceiverName) {
		this.goodReceiverName = goodReceiverName;
	}

	/**
	 * 收货人手机
	 * @return 收货人手机
	 */
	public String getGoodReceiverMobile() {
		return goodReceiverMobile;
	}

	/**
	 * 收货人手机
	 * @param goodReceiverMobile 收货人手机
	 */
	public void setGoodReceiverMobile(String goodReceiverMobile) {
		this.goodReceiverMobile = goodReceiverMobile;
	}

	/**
	 * 收货人电话
	 * @return 收货人电话
	 */
	public String getGoodReceiverPhone() {
		return goodReceiverPhone;
	}

	/**
	 * 收货人电话
	 * @param goodReceiverPhone 收货人电话
	 */
	public void setGoodReceiverPhone(String goodReceiverPhone) {
		this.goodReceiverPhone = goodReceiverPhone;
	}

	/**
	 * 收货人国家ID
	 * @return 收货人国家ID
	 */
	public Long getGoodReceiverCountryId() {
		return goodReceiverCountryId;
	}

	/**
	 * 收货人国家ID
	 * @param goodReceiverCountryId 收货人国家ID
	 */
	public void setGoodReceiverCountryId(Long goodReceiverCountryId) {
		this.goodReceiverCountryId = goodReceiverCountryId;
	}

	/**
	 * 收货人国家
	 * @return 收货人国家
	 */
	public String getGoodReceiverCountry() {
		return goodReceiverCountry;
	}

	/**
	 * 收货人国家
	 * @param goodReceiverCountry 收货人国家
	 */
	public void setGoodReceiverCountry(String goodReceiverCountry) {
		this.goodReceiverCountry = goodReceiverCountry;
	}

	/**
	 * 收货人省份ID
	 * @return 收货人省份ID
	 */
	public Long getGoodReceiverProvinceId() {
		return goodReceiverProvinceId;
	}

	/**
	 * 收货人省份ID
	 * @param goodReceiverProvinceId 收货人省份ID
	 */
	public void setGoodReceiverProvinceId(Long goodReceiverProvinceId) {
		this.goodReceiverProvinceId = goodReceiverProvinceId;
	}

	/**
	 * 收货人省份
	 * @return 收货人省份
	 */
	public String getGoodReceiverProvince() {
		return goodReceiverProvince;
	}

	/**
	 * 收货人省份
	 * @param goodReceiverProvince 收货人省份
	 */
	public void setGoodReceiverProvince(String goodReceiverProvince) {
		this.goodReceiverProvince = goodReceiverProvince;
	}

	/**
	 * 收货人城市ID
	 * @return 收货人城市ID
	 */
	public Long getGoodReceiverCityId() {
		return goodReceiverCityId;
	}

	/**
	 * 收货人城市ID
	 * @param goodReceiverCityId 收货人城市ID
	 */
	public void setGoodReceiverCityId(Long goodReceiverCityId) {
		this.goodReceiverCityId = goodReceiverCityId;
	}

	/**
	 * 收货人城市
	 * @return 收货人城市
	 */
	public String getGoodReceiverCity() {
		return goodReceiverCity;
	}

	/**
	 * 收货人城市
	 * @param goodReceiverCity 收货人城市
	 */
	public void setGoodReceiverCity(String goodReceiverCity) {
		this.goodReceiverCity = goodReceiverCity;
	}

	/**
	 * 收货人地区ID
	 * @return 收货人地区ID
	 */
	public Long getGoodReceiverCountyId() {
		return goodReceiverCountyId;
	}

	/**
	 * 收货人地区ID
	 * @param goodReceiverCountyId 收货人地区ID
	 */
	public void setGoodReceiverCountyId(Long goodReceiverCountyId) {
		this.goodReceiverCountyId = goodReceiverCountyId;
	}

	/**
	 * 收货人地区
	 * @return 收货人地区
	 */
	public String getGoodReceiverCounty() {
		return goodReceiverCounty;
	}

	/**
	 * 收货人地区
	 * @param goodReceiverCounty 收货人地区
	 */
	public void setGoodReceiverCounty(String goodReceiverCounty) {
		this.goodReceiverCounty = goodReceiverCounty;
	}

	/**
	 * 收货人四级区域ID
	 * @return 收货人四级区域ID
	 */
	public Long getGoodReceiverAreaId() {
		return goodReceiverAreaId;
	}

	/**
	 * 收货人四级区域ID
	 * @param goodReceiverAreaId 收货人四级区域ID
	 */
	public void setGoodReceiverAreaId(Long goodReceiverAreaId) {
		this.goodReceiverAreaId = goodReceiverAreaId;
	}

	/**
	 * 收货人四级区域
	 * @return 收货人四级区域
	 */
	public String getGoodReceiverArea() {
		return goodReceiverArea;
	}

	/**
	 * 收货人四级区域
	 * @param goodReceiverArea 收货人四级区域
	 */
	public void setGoodReceiverArea(String goodReceiverArea) {
		this.goodReceiverArea = goodReceiverArea;
	}

	/**
	 * 用户ID
	 * @return 用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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

	/**
	 * 是否是默认收货地址 0:否 1:是
	 * @return 是否是默认收货地址 0:否 1:是
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 是否是默认收货地址 0:否 1:是
	 * @param isDefault 是否是默认收货地址 0:否 1:是
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getModifyMail() {
		return modifyMail;
	}

	public void setModifyMail(String modifyMail) {
		this.modifyMail = modifyMail;
	}
	
}
	