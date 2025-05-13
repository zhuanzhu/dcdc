package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author PAUL
 * @date 2018-09-03 09:20:45
 */
public class CommodityDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 商品标识集合
	 */
	private List<Long> ids;
	/**
	 *商品名称
	 */
	private String name;
	/**
	 * merchant_product编号
	 */
	private String merchantProductSerialNumber;	
	/**
	 * 销售价格-起始值
	 */
	private BigDecimal beginSalePrice;
	/**
	 * 销售价格-结束值
	 */
	private BigDecimal endSalePrice;
	/**
	 * 促销价格-起始值
	 */
	private BigDecimal beginPromotionPrice;
	/**
	 * 促销价格-结束值
	 */
	private BigDecimal endPromotionPrice;
	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	private Integer status;
	/**
	 * 销售方式：1正常销售、2团购、3预售
	 */
	private Integer saleWay;
	/**
	 * 平台id
	 */
	private Long platformId;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}

	public BigDecimal getBeginSalePrice() {
		return beginSalePrice;
	}

	public void setBeginSalePrice(BigDecimal beginSalePrice) {
		this.beginSalePrice = beginSalePrice;
	}

	public BigDecimal getEndSalePrice() {
		return endSalePrice;
	}

	public void setEndSalePrice(BigDecimal endSalePrice) {
		this.endSalePrice = endSalePrice;
	}

	public BigDecimal getBeginPromotionPrice() {
		return beginPromotionPrice;
	}

	public void setBeginPromotionPrice(BigDecimal beginPromotionPrice) {
		this.beginPromotionPrice = beginPromotionPrice;
	}

	public BigDecimal getEndPromotionPrice() {
		return endPromotionPrice;
	}

	public void setEndPromotionPrice(BigDecimal endPromotionPrice) {
		this.endPromotionPrice = endPromotionPrice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Integer getSaleWay() {
		return saleWay;
	}

	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
}
	