package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoCancelConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 支付方式
	 */
	private Integer orderPaymentType;	

	/**
	 * 0 普通 1 拼团 2拼钢
	 */
	private Integer orderPromotionType;	

	/**
	 * 秒
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
	 * 支付方式
	 * @return 支付方式
	 */
	public Integer getOrderPaymentType() {
		return orderPaymentType;
	}

	/**
	 * 支付方式
	 * @param orderPaymentType 支付方式
	 */
	public void setOrderPaymentType(Integer orderPaymentType) {
		this.orderPaymentType = orderPaymentType;
	}
	/**
	 * 0 普通 1 拼团 2拼钢
	 * @return 0 普通 1 拼团 2拼钢
	 */
	public Integer getOrderPromotionType() {
		return orderPromotionType;
	}

	/**
	 * 0 普通 1 拼团 2拼钢
	 * @param orderPromotionType 0 普通 1 拼团 2拼钢
	 */
	public void setOrderPromotionType(Integer orderPromotionType) {
		this.orderPromotionType = orderPromotionType;
	}
	/**
	 * 秒
	 * @return 秒
	 */
	public Long getTimeOut() {
		return timeOut;
	}

	/**
	 * 秒
	 * @param timeOut 秒
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
	