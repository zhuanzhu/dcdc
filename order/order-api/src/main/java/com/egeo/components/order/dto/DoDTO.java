package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class DoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * DO 编号 
	 */
	private String doCode;	

	/**
	 * 订单Code 
	 */
	private String orderCode;	

	/**
	 * 用户ID 
	 */
	private Long userId;	

	/**
	 * 商家ID 
	 */
	private Long merchantId;	

	/**
	 * 仓库ID 
	 */
	private Long warehouseId;	

	/**
	 * 订单金额(不含运费/运费险) 
	 */
	private BigDecimal orderAmount;	

	/**
	 * 订单商品总金额 
	 */
	private BigDecimal productAmount;	

	/**
	 * 运费(实收) 
	 */
	private BigDecimal orderDeliveryFee;	

	/**
	 * 运费险类型 
	 */
	private Integer orderDeliveryFeeInsuranceType;	

	/**
	 * 运费险金额 
	 */
	private BigDecimal orderDeliveryFeeInsuranceAmount;	

	/**
	 * 订单已优惠金额(满立减) 
	 */
	private BigDecimal orderPromotionDiscount;	

	/**
	 * 订单赠送的积分 
	 */
	private BigDecimal orderGivePoints;	

	/**
	 * 核算运费(应收) 
	 */
	private BigDecimal orderDeliveryFeeAccounting;	

	/**
	 * 用户选择的配送类型 
	 */
	private Integer orderDeliveryServiceType;	

	/**
	 * 配送方式类型  
	 */
	private String orderDeliveryMethodId;	

	/**
	 * 期望收货日期_开始
	 */
	private Date expectReceiveDateStart;	

	/**
	 * 期望收货日期_截止
	 */
	private Date expectReceiveDateEnd;	

	/**
	 * 期望收货日期-时间段_开始
	 */
	private Integer expectReceiveTimeStart;	

	/**
	 * 期望收货日期-时间段_截止
	 */
	private Integer expectReceiveTimeEnd;	

	/**
	 * 订单备注(用户) 
	 */
	private String orderRemarkUser;	

	/**
	 * 订单备注(商家给用户看的) 
	 */
	private String orderRemarkMerchant2user;	

	/**
	 * 订单备注(商家自己看的) 
	 */
	private String orderRemarkMerchant;	

	/**
	 * 订单业务类型 
	 */
	private Integer orderBusinessType;	

	/**
	 * 短信接收号码 
	 */
	private String orderMessagePhone;	

	/**
	 * 收货人地址ID 
	 */
	private Long goodReceiverId;	

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
	 * 发票类型 
	 */
	private Integer orderInvoiceType;	

	/**
	 * do单状态，0 未同步  1 已同步 10 拣货 11 打包 12 装车 13 发运
	 */
	private Integer status;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	public Long getId() {
		return id;
	}

	/**
	 * 订单ID 
	 * @param id 订单ID 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * DO 编号 
	 * @return DO 编号 
	 */
	public String getDoCode() {
		return doCode;
	}

	/**
	 * DO 编号 
	 * @param doCode DO 编号 
	 */
	public void setDoCode(String doCode) {
		this.doCode = doCode;
	}
	/**
	 * 订单Code 
	 * @return 订单Code 
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单Code 
	 * @param orderCode 订单Code 
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
	 * 商家ID 
	 * @return 商家ID 
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家ID 
	 * @param merchantId 商家ID 
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * 仓库ID 
	 * @return 仓库ID 
	 */
	public Long getWarehouseId() {
		return warehouseId;
	}

	/**
	 * 仓库ID 
	 * @param warehouseId 仓库ID 
	 */
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	/**
	 * 订单金额(不含运费/运费险) 
	 * @return 订单金额(不含运费/运费险) 
	 */
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	/**
	 * 订单金额(不含运费/运费险) 
	 * @param orderAmount 订单金额(不含运费/运费险) 
	 */
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * 订单商品总金额 
	 * @return 订单商品总金额 
	 */
	public BigDecimal getProductAmount() {
		return productAmount;
	}

	/**
	 * 订单商品总金额 
	 * @param productAmount 订单商品总金额 
	 */
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	/**
	 * 运费(实收) 
	 * @return 运费(实收) 
	 */
	public BigDecimal getOrderDeliveryFee() {
		return orderDeliveryFee;
	}

	/**
	 * 运费(实收) 
	 * @param orderDeliveryFee 运费(实收) 
	 */
	public void setOrderDeliveryFee(BigDecimal orderDeliveryFee) {
		this.orderDeliveryFee = orderDeliveryFee;
	}
	/**
	 * 运费险类型 
	 * @return 运费险类型 
	 */
	public Integer getOrderDeliveryFeeInsuranceType() {
		return orderDeliveryFeeInsuranceType;
	}

	/**
	 * 运费险类型 
	 * @param orderDeliveryFeeInsuranceType 运费险类型 
	 */
	public void setOrderDeliveryFeeInsuranceType(Integer orderDeliveryFeeInsuranceType) {
		this.orderDeliveryFeeInsuranceType = orderDeliveryFeeInsuranceType;
	}
	/**
	 * 运费险金额 
	 * @return 运费险金额 
	 */
	public BigDecimal getOrderDeliveryFeeInsuranceAmount() {
		return orderDeliveryFeeInsuranceAmount;
	}

	/**
	 * 运费险金额 
	 * @param orderDeliveryFeeInsuranceAmount 运费险金额 
	 */
	public void setOrderDeliveryFeeInsuranceAmount(BigDecimal orderDeliveryFeeInsuranceAmount) {
		this.orderDeliveryFeeInsuranceAmount = orderDeliveryFeeInsuranceAmount;
	}
	/**
	 * 订单已优惠金额(满立减) 
	 * @return 订单已优惠金额(满立减) 
	 */
	public BigDecimal getOrderPromotionDiscount() {
		return orderPromotionDiscount;
	}

	/**
	 * 订单已优惠金额(满立减) 
	 * @param orderPromotionDiscount 订单已优惠金额(满立减) 
	 */
	public void setOrderPromotionDiscount(BigDecimal orderPromotionDiscount) {
		this.orderPromotionDiscount = orderPromotionDiscount;
	}
	/**
	 * 订单赠送的积分 
	 * @return 订单赠送的积分 
	 */
	public BigDecimal getOrderGivePoints() {
		return orderGivePoints;
	}

	/**
	 * 订单赠送的积分 
	 * @param orderGivePoints 订单赠送的积分 
	 */
	public void setOrderGivePoints(BigDecimal orderGivePoints) {
		this.orderGivePoints = orderGivePoints;
	}
	/**
	 * 核算运费(应收) 
	 * @return 核算运费(应收) 
	 */
	public BigDecimal getOrderDeliveryFeeAccounting() {
		return orderDeliveryFeeAccounting;
	}

	/**
	 * 核算运费(应收) 
	 * @param orderDeliveryFeeAccounting 核算运费(应收) 
	 */
	public void setOrderDeliveryFeeAccounting(BigDecimal orderDeliveryFeeAccounting) {
		this.orderDeliveryFeeAccounting = orderDeliveryFeeAccounting;
	}
	/**
	 * 用户选择的配送类型 
	 * @return 用户选择的配送类型 
	 */
	public Integer getOrderDeliveryServiceType() {
		return orderDeliveryServiceType;
	}

	/**
	 * 用户选择的配送类型 
	 * @param orderDeliveryServiceType 用户选择的配送类型 
	 */
	public void setOrderDeliveryServiceType(Integer orderDeliveryServiceType) {
		this.orderDeliveryServiceType = orderDeliveryServiceType;
	}
	/**
	 * 配送方式类型  
	 * @return 配送方式类型  
	 */
	public String getOrderDeliveryMethodId() {
		return orderDeliveryMethodId;
	}

	/**
	 * 配送方式类型  
	 * @param orderDeliveryMethodId 配送方式类型  
	 */
	public void setOrderDeliveryMethodId(String orderDeliveryMethodId) {
		this.orderDeliveryMethodId = orderDeliveryMethodId;
	}
	/**
	 * 期望收货日期_开始
	 * @return 期望收货日期_开始
	 */
	public Date getExpectReceiveDateStart() {
		return expectReceiveDateStart;
	}

	/**
	 * 期望收货日期_开始
	 * @param expectReceiveDateStart 期望收货日期_开始
	 */
	public void setExpectReceiveDateStart(Date expectReceiveDateStart) {
		this.expectReceiveDateStart = expectReceiveDateStart;
	}
	/**
	 * 期望收货日期_截止
	 * @return 期望收货日期_截止
	 */
	public Date getExpectReceiveDateEnd() {
		return expectReceiveDateEnd;
	}

	/**
	 * 期望收货日期_截止
	 * @param expectReceiveDateEnd 期望收货日期_截止
	 */
	public void setExpectReceiveDateEnd(Date expectReceiveDateEnd) {
		this.expectReceiveDateEnd = expectReceiveDateEnd;
	}
	/**
	 * 期望收货日期-时间段_开始
	 * @return 期望收货日期-时间段_开始
	 */
	public Integer getExpectReceiveTimeStart() {
		return expectReceiveTimeStart;
	}

	/**
	 * 期望收货日期-时间段_开始
	 * @param expectReceiveTimeStart 期望收货日期-时间段_开始
	 */
	public void setExpectReceiveTimeStart(Integer expectReceiveTimeStart) {
		this.expectReceiveTimeStart = expectReceiveTimeStart;
	}
	/**
	 * 期望收货日期-时间段_截止
	 * @return 期望收货日期-时间段_截止
	 */
	public Integer getExpectReceiveTimeEnd() {
		return expectReceiveTimeEnd;
	}

	/**
	 * 期望收货日期-时间段_截止
	 * @param expectReceiveTimeEnd 期望收货日期-时间段_截止
	 */
	public void setExpectReceiveTimeEnd(Integer expectReceiveTimeEnd) {
		this.expectReceiveTimeEnd = expectReceiveTimeEnd;
	}
	/**
	 * 订单备注(用户) 
	 * @return 订单备注(用户) 
	 */
	public String getOrderRemarkUser() {
		return orderRemarkUser;
	}

	/**
	 * 订单备注(用户) 
	 * @param orderRemarkUser 订单备注(用户) 
	 */
	public void setOrderRemarkUser(String orderRemarkUser) {
		this.orderRemarkUser = orderRemarkUser;
	}
	/**
	 * 订单备注(商家给用户看的) 
	 * @return 订单备注(商家给用户看的) 
	 */
	public String getOrderRemarkMerchant2user() {
		return orderRemarkMerchant2user;
	}

	/**
	 * 订单备注(商家给用户看的) 
	 * @param orderRemarkMerchant2user 订单备注(商家给用户看的) 
	 */
	public void setOrderRemarkMerchant2user(String orderRemarkMerchant2user) {
		this.orderRemarkMerchant2user = orderRemarkMerchant2user;
	}
	/**
	 * 订单备注(商家自己看的) 
	 * @return 订单备注(商家自己看的) 
	 */
	public String getOrderRemarkMerchant() {
		return orderRemarkMerchant;
	}

	/**
	 * 订单备注(商家自己看的) 
	 * @param orderRemarkMerchant 订单备注(商家自己看的) 
	 */
	public void setOrderRemarkMerchant(String orderRemarkMerchant) {
		this.orderRemarkMerchant = orderRemarkMerchant;
	}
	/**
	 * 订单业务类型 
	 * @return 订单业务类型 
	 */
	public Integer getOrderBusinessType() {
		return orderBusinessType;
	}

	/**
	 * 订单业务类型 
	 * @param orderBusinessType 订单业务类型 
	 */
	public void setOrderBusinessType(Integer orderBusinessType) {
		this.orderBusinessType = orderBusinessType;
	}
	/**
	 * 短信接收号码 
	 * @return 短信接收号码 
	 */
	public String getOrderMessagePhone() {
		return orderMessagePhone;
	}

	/**
	 * 短信接收号码 
	 * @param orderMessagePhone 短信接收号码 
	 */
	public void setOrderMessagePhone(String orderMessagePhone) {
		this.orderMessagePhone = orderMessagePhone;
	}
	/**
	 * 收货人地址ID 
	 * @return 收货人地址ID 
	 */
	public Long getGoodReceiverId() {
		return goodReceiverId;
	}

	/**
	 * 收货人地址ID 
	 * @param goodReceiverId 收货人地址ID 
	 */
	public void setGoodReceiverId(Long goodReceiverId) {
		this.goodReceiverId = goodReceiverId;
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
	 * 发票类型 
	 * @return 发票类型 
	 */
	public Integer getOrderInvoiceType() {
		return orderInvoiceType;
	}

	/**
	 * 发票类型 
	 * @param orderInvoiceType 发票类型 
	 */
	public void setOrderInvoiceType(Integer orderInvoiceType) {
		this.orderInvoiceType = orderInvoiceType;
	}
	/**
	 * do单状态，0 未同步  1 已同步 10 拣货 11 打包 12 装车 13 发运
	 * @return do单状态，0 未同步  1 已同步 10 拣货 11 打包 12 装车 13 发运
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * do单状态，0 未同步  1 已同步 10 拣货 11 打包 12 装车 13 发运
	 * @param status do单状态，0 未同步  1 已同步 10 拣货 11 打包 12 装车 13 发运
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
}
	