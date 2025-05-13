package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoSignConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单状态 0	未支付 1	已支付待审核 3	已支付
	 */
	private Integer orderStatus;	

	/**
	 * 支付状态 0	未支付 1	已支付待审核 3	已支付
	 */
	private Integer orderPaymentStatus;	

	/**
	 * 支付方式 1	网上支付  2	货到付款  22	银行转账
	 */
	private Integer orderPaymentType;	

	/**
	 * 多长时间后自动签收，单位秒
	 */
	private Long timeOut;	

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
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 订单状态 0	未支付 1	已支付待审核 3	已支付
	 * @return 订单状态 0	未支付 1	已支付待审核 3	已支付
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 订单状态 0	未支付 1	已支付待审核 3	已支付
	 * @param orderStatus 订单状态 0	未支付 1	已支付待审核 3	已支付
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 支付状态 0	未支付 1	已支付待审核 3	已支付
	 * @return 支付状态 0	未支付 1	已支付待审核 3	已支付
	 */
	public Integer getOrderPaymentStatus() {
		return orderPaymentStatus;
	}

	/**
	 * 支付状态 0	未支付 1	已支付待审核 3	已支付
	 * @param orderPaymentStatus 支付状态 0	未支付 1	已支付待审核 3	已支付
	 */
	public void setOrderPaymentStatus(Integer orderPaymentStatus) {
		this.orderPaymentStatus = orderPaymentStatus;
	}
	/**
	 * 支付方式 1	网上支付  2	货到付款  22	银行转账
	 * @return 支付方式 1	网上支付  2	货到付款  22	银行转账
	 */
	public Integer getOrderPaymentType() {
		return orderPaymentType;
	}

	/**
	 * 支付方式 1	网上支付  2	货到付款  22	银行转账
	 * @param orderPaymentType 支付方式 1	网上支付  2	货到付款  22	银行转账
	 */
	public void setOrderPaymentType(Integer orderPaymentType) {
		this.orderPaymentType = orderPaymentType;
	}
	/**
	 * 多长时间后自动签收，单位秒
	 * @return 多长时间后自动签收，单位秒
	 */
	public Long getTimeOut() {
		return timeOut;
	}

	/**
	 * 多长时间后自动签收，单位秒
	 * @param timeOut 多长时间后自动签收，单位秒
	 */
	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
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
	