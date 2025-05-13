package com.egeo.components.pay.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2019-04-19 16:40:20
 */
public class JdOrderAwaitQueueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 子订单id
	 */
	private Long soChildId;	

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
	 * 是否支付成功：0否1是
	 */
	private Integer isPayTrue;	

	/**
	 * 京东订单号
	 */
	private Long jdOrderId;

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
	 * 京东订单号
	 * @return 京东订单号
	 */
	public Long getJdOrderId() {
		return jdOrderId;
	}

	/**
	 * 京东订单号
	 * @param jdOrderId 京东订单号
	 */
	public void setJdOrderId(Long jdOrderId) {
		this.jdOrderId = jdOrderId;
	}
}
	