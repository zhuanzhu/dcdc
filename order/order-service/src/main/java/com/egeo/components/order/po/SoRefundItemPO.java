package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 *
 */
public class SoRefundItemPO {


	/**
	 * id
	 */
	private Long id;

	/**
	 * 退款单id
	 */
	private Long refundId;

	/**
	 * 退款订单编号
	 */
	private String refundCode;

	/**
	 * 商品ID
	 */
	private String skuId;

	/**
	 * 商品名称
	 */
	private String skuName;

	/**
	 * 退款数量
	 */
	private Integer refundNum;

	/**
	 * 商品单价
	 */
	private BigDecimal price;

	/**
	 * 商品实际退款金额
	 */
	private BigDecimal refundAmount;

	/**
	 * 退运费金额
	 */
	private BigDecimal refundDeliveryFee;

	/**
	 * 退回商品购买数量
	 */
	private Long soItemId;

	/**
	 * 来源
	 */
	private Integer source;


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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRefundId() {
		return refundId;
	}

	public void setRefundId(Long refundId) {
		this.refundId = refundId;
	}

	public String getRefundCode() {
		return refundCode;
	}

	public void setRefundCode(String refundCode) {
		this.refundCode = refundCode;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Integer getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public BigDecimal getRefundDeliveryFee() {
		return refundDeliveryFee;
	}

	public void setRefundDeliveryFee(BigDecimal refundDeliveryFee) {
		this.refundDeliveryFee = refundDeliveryFee;
	}

	public Long getSoItemId() {
		return soItemId;
	}

	public void setSoItemId(Long soItemId) {
		this.soItemId = soItemId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}
	