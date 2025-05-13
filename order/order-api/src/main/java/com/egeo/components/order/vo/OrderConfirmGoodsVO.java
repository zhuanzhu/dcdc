package com.egeo.components.order.vo;

import java.math.BigDecimal;

/**
 * 订单确认时查看的商品vo,也用于查询用户商品列表时的商品vo
 * @author GRACIA
 *
 */
public class OrderConfirmGoodsVO {

	private Integer buyType;
	private Long puId;
	private String puImg;
	private String puName;
	private Integer num;//数量
	private double price;//单价
	private Integer saleWay;
	private Long preSellDay;
	private String productUnitSerialNumber;
	private Integer isOwnMerchant;

	private Integer source;

	private String channelProductId;

	public Integer getSaleWay() {
		return saleWay;
	}

	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}

	public Long getPreSellDay() {
		return preSellDay;
	}

	public void setPreSellDay(Long preSellDay) {
		this.preSellDay = preSellDay;
	}

	private Long standardUnitId; // su商品id
	private boolean isUnit;  //是否是unit商品,true是,false不是

	public boolean isUnit() {
		return isUnit;
	}

	public void setUnit(boolean unit) {
		isUnit = unit;
	}

	public Long getStandardUnitId() {
		return standardUnitId;
	}
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}
	public Long getPuId() {
		return puId;
	}
	public void setPuId(Long puId) {
		this.puId = puId;
	}
	public String getPuImg() {
		return puImg;
	}
	public void setPuImg(String puImg) {
		this.puImg = puImg;
	}
	public String getPuName() {
		return puName;
	}
	public void setPuName(String puName) {
		this.puName = puName;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}

	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}

	public Integer getIsOwnMerchant() {
		return isOwnMerchant;
	}

	public void setIsOwnMerchant(Integer isOwnMerchant) {
		this.isOwnMerchant = isOwnMerchant;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getChannelProductId() {
		return channelProductId;
	}

	public void setChannelProductId(String channelProductId) {
		this.channelProductId = channelProductId;
	}
}
