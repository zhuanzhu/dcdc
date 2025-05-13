package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoGiftcardSendDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 父订单编号
	 */
	private String parentOrderCode;	

	/**
	 * 礼金卡id
	 */
	private Long giftcardId;	

	/**
	 * 卡号
	 */
	private String giftcardNo;	

	/**
	 * 密码
	 */
	private String giftcardPass;	

	/**
	 * 礼金卡金额
	 */
	private BigDecimal cardAmount;	

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
	 * 父订单编号
	 * @return 父订单编号
	 */
	public String getParentOrderCode() {
		return parentOrderCode;
	}

	/**
	 * 父订单编号
	 * @param parentOrderCode 父订单编号
	 */
	public void setParentOrderCode(String parentOrderCode) {
		this.parentOrderCode = parentOrderCode;
	}
	/**
	 * 礼金卡id
	 * @return 礼金卡id
	 */
	public Long getGiftcardId() {
		return giftcardId;
	}

	/**
	 * 礼金卡id
	 * @param giftcardId 礼金卡id
	 */
	public void setGiftcardId(Long giftcardId) {
		this.giftcardId = giftcardId;
	}
	/**
	 * 卡号
	 * @return 卡号
	 */
	public String getGiftcardNo() {
		return giftcardNo;
	}

	/**
	 * 卡号
	 * @param giftcardNo 卡号
	 */
	public void setGiftcardNo(String giftcardNo) {
		this.giftcardNo = giftcardNo;
	}
	/**
	 * 密码
	 * @return 密码
	 */
	public String getGiftcardPass() {
		return giftcardPass;
	}

	/**
	 * 密码
	 * @param giftcardPass 密码
	 */
	public void setGiftcardPass(String giftcardPass) {
		this.giftcardPass = giftcardPass;
	}
	/**
	 * 礼金卡金额
	 * @return 礼金卡金额
	 */
	public BigDecimal getCardAmount() {
		return cardAmount;
	}

	/**
	 * 礼金卡金额
	 * @param cardAmount 礼金卡金额
	 */
	public void setCardAmount(BigDecimal cardAmount) {
		this.cardAmount = cardAmount;
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
	