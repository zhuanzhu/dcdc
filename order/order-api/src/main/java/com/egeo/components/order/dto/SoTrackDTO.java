package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:25
 */
public class SoTrackDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单编号(子单)
	 */
	private String orderCode;	

	/**
	 * 订单创建
	 */
	private Date orderCreateTime;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 商家ID
	 */
	private Long merchantId;	

	/**
	 * track 类型
	 */
	private Integer trackType;	

	/**
	 * 操作步骤
	 */
	private Integer operateNo;	

	/**
	 * 操作属性
	 */
	private Integer operateAttr;	

	/**
	 * 操作内容
	 */
	private String operateContent;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 操作人类型
	 */
	private Integer operateType;	

	/**
	 * 操作人id
	 */
	private Long operatorId;	

	/**
	 * 操作人姓名
	 */
	private String operatorName;	

	/**
	 * 来源系统
	 */
	private String sourceSystem;	

	/**
	 * 日志查看级别
	 */
	private Integer trackLevel;	

	/**
	 * 配送公司ID
	 */
	private Long deliveryCompanyId;	

	/**
	 * 配送公司名称
	 */
	private String deliveryCompanyName;	

	/**
	 * 配送公司简称
	 */
	private String deliveryCompanyShortName;	

	/**
	 * 配送公司URL
	 */
	private String deliveryCompanyUrl;	

	/**
	 * 配送单号
	 */
	private String deliveryExpressNbr;	

	/**
	 * 配送人
	 */
	private String deliverierName;	

	/**
	 * 配送人手机号
	 */
	private String deliverierMobile;	

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
	 * 订单编号(子单)
	 * @return 订单编号(子单)
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号(子单)
	 * @param orderCode 订单编号(子单)
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 订单创建
	 * @return 订单创建
	 */
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	/**
	 * 订单创建
	 * @param orderCreateTime 订单创建
	 */
	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
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
	 * track 类型
	 * @return track 类型
	 */
	public Integer getTrackType() {
		return trackType;
	}

	/**
	 * track 类型
	 * @param trackType track 类型
	 */
	public void setTrackType(Integer trackType) {
		this.trackType = trackType;
	}
	/**
	 * 操作步骤
	 * @return 操作步骤
	 */
	public Integer getOperateNo() {
		return operateNo;
	}

	/**
	 * 操作步骤
	 * @param operateNo 操作步骤
	 */
	public void setOperateNo(Integer operateNo) {
		this.operateNo = operateNo;
	}
	/**
	 * 操作属性
	 * @return 操作属性
	 */
	public Integer getOperateAttr() {
		return operateAttr;
	}

	/**
	 * 操作属性
	 * @param operateAttr 操作属性
	 */
	public void setOperateAttr(Integer operateAttr) {
		this.operateAttr = operateAttr;
	}
	/**
	 * 操作内容
	 * @return 操作内容
	 */
	public String getOperateContent() {
		return operateContent;
	}

	/**
	 * 操作内容
	 * @param operateContent 操作内容
	 */
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 操作人类型
	 * @return 操作人类型
	 */
	public Integer getOperateType() {
		return operateType;
	}

	/**
	 * 操作人类型
	 * @param operateType 操作人类型
	 */
	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}
	/**
	 * 操作人id
	 * @return 操作人id
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * 操作人id
	 * @param operatorId 操作人id
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	/**
	 * 操作人姓名
	 * @return 操作人姓名
	 */
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * 操作人姓名
	 * @param operatorName 操作人姓名
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	/**
	 * 来源系统
	 * @return 来源系统
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * 来源系统
	 * @param sourceSystem 来源系统
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	/**
	 * 日志查看级别
	 * @return 日志查看级别
	 */
	public Integer getTrackLevel() {
		return trackLevel;
	}

	/**
	 * 日志查看级别
	 * @param trackLevel 日志查看级别
	 */
	public void setTrackLevel(Integer trackLevel) {
		this.trackLevel = trackLevel;
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
	 * 配送公司名称
	 * @return 配送公司名称
	 */
	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	/**
	 * 配送公司名称
	 * @param deliveryCompanyName 配送公司名称
	 */
	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}
	/**
	 * 配送公司简称
	 * @return 配送公司简称
	 */
	public String getDeliveryCompanyShortName() {
		return deliveryCompanyShortName;
	}

	/**
	 * 配送公司简称
	 * @param deliveryCompanyShortName 配送公司简称
	 */
	public void setDeliveryCompanyShortName(String deliveryCompanyShortName) {
		this.deliveryCompanyShortName = deliveryCompanyShortName;
	}
	/**
	 * 配送公司URL
	 * @return 配送公司URL
	 */
	public String getDeliveryCompanyUrl() {
		return deliveryCompanyUrl;
	}

	/**
	 * 配送公司URL
	 * @param deliveryCompanyUrl 配送公司URL
	 */
	public void setDeliveryCompanyUrl(String deliveryCompanyUrl) {
		this.deliveryCompanyUrl = deliveryCompanyUrl;
	}
	/**
	 * 配送单号
	 * @return 配送单号
	 */
	public String getDeliveryExpressNbr() {
		return deliveryExpressNbr;
	}

	/**
	 * 配送单号
	 * @param deliveryExpressNbr 配送单号
	 */
	public void setDeliveryExpressNbr(String deliveryExpressNbr) {
		this.deliveryExpressNbr = deliveryExpressNbr;
	}
	/**
	 * 配送人
	 * @return 配送人
	 */
	public String getDeliverierName() {
		return deliverierName;
	}

	/**
	 * 配送人
	 * @param deliverierName 配送人
	 */
	public void setDeliverierName(String deliverierName) {
		this.deliverierName = deliverierName;
	}
	/**
	 * 配送人手机号
	 * @return 配送人手机号
	 */
	public String getDeliverierMobile() {
		return deliverierMobile;
	}

	/**
	 * 配送人手机号
	 * @param deliverierMobile 配送人手机号
	 */
	public void setDeliverierMobile(String deliverierMobile) {
		this.deliverierMobile = deliverierMobile;
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
	