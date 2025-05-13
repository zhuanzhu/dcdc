package com.egeo.components.order.vo;

public class ReceiverAddressView {

	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 收货人地址
	 */
	private String goodReceiverAddress;	


	/**
	 * 收货人姓名
	 */
	private String goodReceiverName;	

	/**
	 * 收货人手机
	 */
	private String goodReceiverMobile;	

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
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 子订单code
	 */
	private String soChildCode;

	/**
	 * 子订单code
	 * @return soChildCode
	 */
	public String getSoChildCode() {
		return soChildCode;
	}

	/**
	 * 子订单code
	 * @param soChildCode
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

}
