package com.egeo.components.pay.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-11-13 11:02:10
 */
public class ThirdpartyAwaitQueueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 随机数
	 */
	private String randomNumber;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 现金付款方式 1 支付宝 2 微信 3 银联 4 建行 
	 */
	private Integer cashPayType;	

	/**
	 * 是否支付成功：0否1是
	 */
	private Integer isPayTrue;	

	/**
	 * 第三方订单类型
	 */
	private Integer thirdpartyType;	

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 随机数
	 * @return 随机数
	 */
	public String getRandomNumber() {
		return randomNumber;
	}

	/**
	 * 随机数
	 * @param randomNumber 随机数
	 */
	public void setRandomNumber(String randomNumber) {
		this.randomNumber = randomNumber;
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
	 * 现金付款方式 1 支付宝 2 微信 3 银联 4 建行 
	 * @return 现金付款方式 1 支付宝 2 微信 3 银联 4 建行 
	 */
	public Integer getCashPayType() {
		return cashPayType;
	}

	/**
	 * 现金付款方式 1 支付宝 2 微信 3 银联 4 建行 
	 * @param cashPayType 现金付款方式 1 支付宝 2 微信 3 银联 4 建行 
	 */
	public void setCashPayType(Integer cashPayType) {
		this.cashPayType = cashPayType;
	}
	/**
	 * 是否支付成功：0否1是
	 * @return 是否支付成功：0否1是
	 */
	public Integer getIsPayTrue() {
		return isPayTrue;
	}

	/**
	 * 是否支付成功：0否1是
	 * @param isPayTrue 是否支付成功：0否1是
	 */
	public void setIsPayTrue(Integer isPayTrue) {
		this.isPayTrue = isPayTrue;
	}
	/**
	 * 第三方订单类型
	 * @return 第三方订单类型
	 */
	public Integer getThirdpartyType() {
		return thirdpartyType;
	}

	/**
	 * 第三方订单类型
	 * @param thirdpartyType 第三方订单类型
	 */
	public void setThirdpartyType(Integer thirdpartyType) {
		this.thirdpartyType = thirdpartyType;
	}
}
	