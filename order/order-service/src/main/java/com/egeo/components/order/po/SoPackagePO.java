package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-02-04 11:39:41
 */
public class SoPackagePO {


	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 子订单id
	 */
	private Long soChildId;	

	/**
	 * 收件地址id
	 */
	private Long receiverAddressId;	

	/**
	 * 
	 */
	private String orderCode;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 子单运费
	 */
	private BigDecimal soChildDeliveryFee;	

	/**
	 * 物流信息类型1:订单2:发票
	 */
	private Integer packageType;	

	/**
	 * 快递单号
	 */
	private String deliveryCode;	

	/**
	 * 订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
	 */
	private Integer deliveryStatus;	

	/**
	 * 出库方式：1:物流公司 2:无需物流
	 */
	private Integer deliveryMode;	

	/**
	 * 配送公司ID
	 */
	private Long deliveryCompanyId;	

	/**
	 * 
	 */
	private String deliveryCompanyName;	

	/**
	 * 
	 */
	private String deliveryMessage;	

	/**
	 * 
	 */
	private String deliveryExpressNbr;	

	/**
	 * 
	 */
	private String orderDeliveryMethodId;	

	/**
	 * 收货人姓名
	 */
	private String goodReceiverName;	

	/**
	 * 收货人手机
	 */
	private String goodReceiverMobile;	

	/**
	 * 省市区信息
	 */
	private String proCityArea;	

	/**
	 * 配送日期
	 */
	private Date deliveryDate;	

	/**
	 * 地址详情
	 */
	private String goodReceiverAddress;	

	/**
	 * 配送人姓名
	 */
	private String deliveryName;	

	/**
	 * 配送人电话
	 */
	private String deliveryNameMobile;	

	/**
	 * 签收人姓名
	 */
	private String signName;	

	/**
	 * 
	 */
	private Date signDate;	

	/**
	 * 签收备注
	 */
	private String signRemark;	

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
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 订单id
	 * @return 订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 订单id
	 * @param soId 订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
	}

	/**
	 * 子订单id
	 * @return 子订单id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单id
	 * @param soChildId 子订单id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}

	/**
	 * 收件地址id
	 * @return 收件地址id
	 */
	public Long getReceiverAddressId() {
		return receiverAddressId;
	}

	/**
	 * 收件地址id
	 * @param receiverAddressId 收件地址id
	 */
	public void setReceiverAddressId(Long receiverAddressId) {
		this.receiverAddressId = receiverAddressId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 
	 * @param orderCode 
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * 商家id
	 * @return 商家id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家id
	 * @param merchantId 商家id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 子单运费
	 * @return 子单运费
	 */
	public BigDecimal getSoChildDeliveryFee() {
		return soChildDeliveryFee;
	}

	/**
	 * 子单运费
	 * @param soChildDeliveryFee 子单运费
	 */
	public void setSoChildDeliveryFee(BigDecimal soChildDeliveryFee) {
		this.soChildDeliveryFee = soChildDeliveryFee;
	}

	/**
	 * 物流信息类型1:订单2:发票
	 * @return 物流信息类型1:订单2:发票
	 */
	public Integer getPackageType() {
		return packageType;
	}

	/**
	 * 物流信息类型1:订单2:发票
	 * @param packageType 物流信息类型1:订单2:发票
	 */
	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}

	/**
	 * 快递单号
	 * @return 快递单号
	 */
	public String getDeliveryCode() {
		return deliveryCode;
	}

	/**
	 * 快递单号
	 * @param deliveryCode 快递单号
	 */
	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	/**
	 * 订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
	 * @return 订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
	 */
	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	/**
	 * 订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
	 * @param deliveryStatus 订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
	 */
	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	/**
	 * 出库方式：1:物流公司 2:无需物流
	 * @return 出库方式：1:物流公司 2:无需物流
	 */
	public Integer getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * 出库方式：1:物流公司 2:无需物流
	 * @param deliveryMode 出库方式：1:物流公司 2:无需物流
	 */
	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	/**
	 * 配送公司ID
	 * @return 配送公司ID
	 */
	public Long getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	/**
	 * 配送公司ID
	 * @param deliveryCompanyId 配送公司ID
	 */
	public void setDeliveryCompanyId(Long deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	/**
	 * 
	 * @param deliveryCompanyName 
	 */
	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	/**
	 * 
	 * @return 
	 */
	public String getDeliveryMessage() {
		return deliveryMessage;
	}

	/**
	 * 
	 * @param deliveryMessage 
	 */
	public void setDeliveryMessage(String deliveryMessage) {
		this.deliveryMessage = deliveryMessage;
	}

	/**
	 * 
	 * @return 
	 */
	public String getDeliveryExpressNbr() {
		return deliveryExpressNbr;
	}

	/**
	 * 
	 * @param deliveryExpressNbr 
	 */
	public void setDeliveryExpressNbr(String deliveryExpressNbr) {
		this.deliveryExpressNbr = deliveryExpressNbr;
	}

	/**
	 * 
	 * @return 
	 */
	public String getOrderDeliveryMethodId() {
		return orderDeliveryMethodId;
	}

	/**
	 * 
	 * @param orderDeliveryMethodId 
	 */
	public void setOrderDeliveryMethodId(String orderDeliveryMethodId) {
		this.orderDeliveryMethodId = orderDeliveryMethodId;
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
	 * 省市区信息
	 * @return 省市区信息
	 */
	public String getProCityArea() {
		return proCityArea;
	}

	/**
	 * 省市区信息
	 * @param proCityArea 省市区信息
	 */
	public void setProCityArea(String proCityArea) {
		this.proCityArea = proCityArea;
	}

	/**
	 * 配送日期
	 * @return 配送日期
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * 配送日期
	 * @param deliveryDate 配送日期
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * 地址详情
	 * @return 地址详情
	 */
	public String getGoodReceiverAddress() {
		return goodReceiverAddress;
	}

	/**
	 * 地址详情
	 * @param goodReceiverAddress 地址详情
	 */
	public void setGoodReceiverAddress(String goodReceiverAddress) {
		this.goodReceiverAddress = goodReceiverAddress;
	}

	/**
	 * 配送人姓名
	 * @return 配送人姓名
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * 配送人姓名
	 * @param deliveryName 配送人姓名
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * 配送人电话
	 * @return 配送人电话
	 */
	public String getDeliveryNameMobile() {
		return deliveryNameMobile;
	}

	/**
	 * 配送人电话
	 * @param deliveryNameMobile 配送人电话
	 */
	public void setDeliveryNameMobile(String deliveryNameMobile) {
		this.deliveryNameMobile = deliveryNameMobile;
	}

	/**
	 * 签收人姓名
	 * @return 签收人姓名
	 */
	public String getSignName() {
		return signName;
	}

	/**
	 * 签收人姓名
	 * @param signName 签收人姓名
	 */
	public void setSignName(String signName) {
		this.signName = signName;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getSignDate() {
		return signDate;
	}

	/**
	 * 
	 * @param signDate 
	 */
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	/**
	 * 签收备注
	 * @return 签收备注
	 */
	public String getSignRemark() {
		return signRemark;
	}

	/**
	 * 签收备注
	 * @param signRemark 签收备注
	 */
	public void setSignRemark(String signRemark) {
		this.signRemark = signRemark;
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
	