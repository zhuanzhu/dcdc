package com.egeo.components.product.po;

import java.math.BigDecimal;

/**
 * 
 * @author min
 * @date 2018-09-11 14:59:33
 */
public class StandardUnitStorePO {


	private Long id;

	/**
	 * 门店id
	 */
	private Long storeId;	
	/**
	 * 总店名称
	 */
	private String storeName;

	/**
	 * 商品id
	 */
	private Long standardUnitId;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * 商品名称
	 */
	private String standardUnitName;
	/**
	 * 商品序列号
	 */
	private String standardUnitSerialNumber;
	/**
	 * 销售价格开始
	 */
	private BigDecimal salePriceStart;
	/**
	 * 销售价格结束
	 */
	private BigDecimal salePriceStop;
	/**
	 * 促销销售价格开始
	 */
	private BigDecimal promotionPriceStart;
	/**
	 * 促销销售价格结束
	 */
	private BigDecimal promotionPriceStop;
	/**
	 * 商品状态：商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	private Integer status;
	/**
	 * 是否可见：默认0是;1否
	 */
	private Integer isVisible;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStandardUnitName() {
		return standardUnitName;
	}

	public void setStandardUnitName(String standardUnitName) {
		this.standardUnitName = standardUnitName;
	}

	public String getStandardUnitSerialNumber() {
		return standardUnitSerialNumber;
	}

	public void setStandardUnitSerialNumber(String standardUnitSerialNumber) {
		this.standardUnitSerialNumber = standardUnitSerialNumber;
	}

	public BigDecimal getSalePriceStart() {
		return salePriceStart;
	}

	public void setSalePriceStart(BigDecimal salePriceStart) {
		this.salePriceStart = salePriceStart;
	}

	public BigDecimal getSalePriceStop() {
		return salePriceStop;
	}

	public void setSalePriceStop(BigDecimal salePriceStop) {
		this.salePriceStop = salePriceStop;
	}

	public BigDecimal getPromotionPriceStart() {
		return promotionPriceStart;
	}

	public void setPromotionPriceStart(BigDecimal promotionPriceStart) {
		this.promotionPriceStart = promotionPriceStart;
	}

	public BigDecimal getPromotionPriceStop() {
		return promotionPriceStop;
	}

	public void setPromotionPriceStop(BigDecimal promotionPriceStop) {
		this.promotionPriceStop = promotionPriceStop;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 门店id
	 * @return 门店id
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 门店id
	 * @param storeId 门店id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	/**
	 * 商品id
	 * @return 商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * 商品id
	 * @param standardUnitId 商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
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
}
	