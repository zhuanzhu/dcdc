package com.egeo.components.promotion.po;


import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-11-16 03:28:54
 */
public class ECardTempPO {


	private Long id;

	/**
	 * 卡号
	 */
	private String cardNumber;	

	/**
	 * 卡密
	 */
	private String cardThick;	

	/**
	 * 分配给的用户id
	 */
	private Long userId;	

	/**
	 * skuid
	 */
	private Long skuId;	

	/**
	 * 结束时间
	 */
	private Date endTime;	

	/**
	 * 短链
	 */
	private String shortUrl;	

	/**
	 * 卡券类型
	 */
	private Integer type;	

	/**
	 * sku名
	 */
	private String skuName;	

	/**
	 * 
	 */
	private String skuSerialNumber;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

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
	 * 卡号
	 * @return 卡号
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * 卡号
	 * @param cardNumber 卡号
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * 卡密
	 * @return 卡密
	 */
	public String getCardThick() {
		return cardThick;
	}

	/**
	 * 卡密
	 * @param cardThick 卡密
	 */
	public void setCardThick(String cardThick) {
		this.cardThick = cardThick;
	}

	/**
	 * 分配给的用户id
	 * @return 分配给的用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 分配给的用户id
	 * @param userId 分配给的用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * skuid
	 * @return skuid
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * skuid
	 * @param skuId skuid
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/**
	 * 结束时间
	 * @return 结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 结束时间
	 * @param endTime 结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 短链
	 * @return 短链
	 */
	public String getShortUrl() {
		return shortUrl;
	}

	/**
	 * 短链
	 * @param shortUrl 短链
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	/**
	 * 卡券类型
	 * @return 卡券类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 卡券类型
	 * @param type 卡券类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * sku名
	 * @return sku名
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * sku名
	 * @param skuName sku名
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	/**
	 * 
	 * @return 
	 */
	public String getSkuSerialNumber() {
		return skuSerialNumber;
	}

	/**
	 * 
	 * @param skuSerialNumber 
	 */
	public void setSkuSerialNumber(String skuSerialNumber) {
		this.skuSerialNumber = skuSerialNumber;
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
}
	