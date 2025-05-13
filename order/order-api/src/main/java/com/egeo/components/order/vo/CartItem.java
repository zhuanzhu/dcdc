package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 80553031619078821L;

	private Long id;
	/**
	 * 商品名称
	 */
	private String merchantProdName;
	
	/**
	 * 商品id
	 */
	private Long merchantProdId;
	
	/**
	 * 购买数量
	 */
	private Integer num;
	
	/**
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	private Long skuId;
	
	private String pictureUrl;
	
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * 属性和属性值信息
	 */
	private String attNameValue;
	/**
	 * sku库存数量
	 */
	private Long stock;
	/**
	 * 1、活动已过期
	 * 0、活动未过期
	 */
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantProdName() {
		return merchantProdName;
	}

	public void setMerchantProdName(String merchantProdName) {
		this.merchantProdName = merchantProdName;
	}

	public Long getMerchantProdId() {
		return merchantProdId;
	}

	public void setMerchantProdId(Long merchantProdId) {
		this.merchantProdId = merchantProdId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getAttNameValue() {
		return attNameValue;
	}

	public void setAttNameValue(String attNameValue) {
		this.attNameValue = attNameValue;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
