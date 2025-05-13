package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:24
 */
public class SoExchangeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 售后id
	 */
	private Long returnId;	

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	private String orderCode;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 换货编码
	 */
	private String exchangeCode;	

	/**
	 * 收件地址省份id
	 */
	private Long provinceId;	

	/**
	 * 省
	 */
	private String province;	

	/**
	 * 收件地址市id
	 */
	private Long cityId;	

	/**
	 * 市
	 */
	private String city;	

	/**
	 * 收件地址县id
	 */
	private Long countyId;	

	/**
	 * 县
	 */
	private String county;	

	/**
	 * 收件地址区id
	 */
	private Long areaId;	

	/**
	 * 区
	 */
	private String area;	

	/**
	 * 收件地址
	 */
	private String consigneeAddress;	

	/**
	 * 收件人姓名
	 */
	private String consigneeName;	

	/**
	 * 收件人的联系电话/手机
	 */
	private String consigneeMobile;	

	/**
	 * 快递单号/运单号
	 */
	private String courierNumber;	

	/**
	 * 物流公司
	 */
	private String logisticsCompanyId;	

	/**
	 * 换货状态  1:待出库  2:已出库  3:已完成
	 */
	private Integer exchangeStatus;	

	/**
	 * 出库时间
	 */
	private Date deliveryTime;	

	/**
	 * 出库操作人userId
	 */
	private Long deliveryUserId;	

	/**
	 * 确认收货时间
	 */
	private Date confirmTime;	

	/**
	 * 确认收货操作人userId
	 */
	private Long confirmUserId;	

	/**
	 * 退货编码
	 */
	private String returnCode;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 售后id
	 * @return 售后id
	 */
	public Long getReturnId() {
		return returnId;
	}

	/**
	 * 售后id
	 * @param returnId 售后id
	 */
	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}
	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 * @return 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 * @param orderCode 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	 * 换货编码
	 * @return 换货编码
	 */
	public String getExchangeCode() {
		return exchangeCode;
	}

	/**
	 * 换货编码
	 * @param exchangeCode 换货编码
	 */
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}
	/**
	 * 收件地址省份id
	 * @return 收件地址省份id
	 */
	public Long getProvinceId() {
		return provinceId;
	}

	/**
	 * 收件地址省份id
	 * @param provinceId 收件地址省份id
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 省
	 * @return 省
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 省
	 * @param province 省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 收件地址市id
	 * @return 收件地址市id
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * 收件地址市id
	 * @param cityId 收件地址市id
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	/**
	 * 市
	 * @return 市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 市
	 * @param city 市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 收件地址县id
	 * @return 收件地址县id
	 */
	public Long getCountyId() {
		return countyId;
	}

	/**
	 * 收件地址县id
	 * @param countyId 收件地址县id
	 */
	public void setCountyId(Long countyId) {
		this.countyId = countyId;
	}
	/**
	 * 县
	 * @return 县
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * 县
	 * @param county 县
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * 收件地址区id
	 * @return 收件地址区id
	 */
	public Long getAreaId() {
		return areaId;
	}

	/**
	 * 收件地址区id
	 * @param areaId 收件地址区id
	 */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	/**
	 * 区
	 * @return 区
	 */
	public String getArea() {
		return area;
	}

	/**
	 * 区
	 * @param area 区
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 收件地址
	 * @return 收件地址
	 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	/**
	 * 收件地址
	 * @param consigneeAddress 收件地址
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/**
	 * 收件人姓名
	 * @return 收件人姓名
	 */
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * 收件人姓名
	 * @param consigneeName 收件人姓名
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	/**
	 * 收件人的联系电话/手机
	 * @return 收件人的联系电话/手机
	 */
	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	/**
	 * 收件人的联系电话/手机
	 * @param consigneeMobile 收件人的联系电话/手机
	 */
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}
	/**
	 * 快递单号/运单号
	 * @return 快递单号/运单号
	 */
	public String getCourierNumber() {
		return courierNumber;
	}

	/**
	 * 快递单号/运单号
	 * @param courierNumber 快递单号/运单号
	 */
	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}
	/**
	 * 物流公司
	 * @return 物流公司
	 */
	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	/**
	 * 物流公司
	 * @param logisticsCompanyId 物流公司
	 */
	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}
	/**
	 * 换货状态  1:待出库  2:已出库  3:已完成
	 * @return 换货状态  1:待出库  2:已出库  3:已完成
	 */
	public Integer getExchangeStatus() {
		return exchangeStatus;
	}

	/**
	 * 换货状态  1:待出库  2:已出库  3:已完成
	 * @param exchangeStatus 换货状态  1:待出库  2:已出库  3:已完成
	 */
	public void setExchangeStatus(Integer exchangeStatus) {
		this.exchangeStatus = exchangeStatus;
	}
	/**
	 * 出库时间
	 * @return 出库时间
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * 出库时间
	 * @param deliveryTime 出库时间
	 */
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	/**
	 * 出库操作人userId
	 * @return 出库操作人userId
	 */
	public Long getDeliveryUserId() {
		return deliveryUserId;
	}

	/**
	 * 出库操作人userId
	 * @param deliveryUserId 出库操作人userId
	 */
	public void setDeliveryUserId(Long deliveryUserId) {
		this.deliveryUserId = deliveryUserId;
	}
	/**
	 * 确认收货时间
	 * @return 确认收货时间
	 */
	public Date getConfirmTime() {
		return confirmTime;
	}

	/**
	 * 确认收货时间
	 * @param confirmTime 确认收货时间
	 */
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}
	/**
	 * 确认收货操作人userId
	 * @return 确认收货操作人userId
	 */
	public Long getConfirmUserId() {
		return confirmUserId;
	}

	/**
	 * 确认收货操作人userId
	 * @param confirmUserId 确认收货操作人userId
	 */
	public void setConfirmUserId(Long confirmUserId) {
		this.confirmUserId = confirmUserId;
	}
	/**
	 * 退货编码
	 * @return 退货编码
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * 退货编码
	 * @param returnCode 退货编码
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
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
}
	