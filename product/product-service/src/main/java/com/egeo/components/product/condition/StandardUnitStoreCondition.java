package com.egeo.components.product.condition;

import java.math.BigDecimal;

import com.egeo.components.product.po.StandardUnitStorePO;

/**
 * 
 * @author min
 * @date 2018-09-11 14:59:33
 */
public class StandardUnitStoreCondition extends StandardUnitStorePO {
	private static final long serialVersionUID = 1L;
	/**
	 * su商品名称
	 */
	private String standardUnitName;
	/**
	 * 商品序列号
	 */
	private String standardUnitSerialNumber;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * 促销价格
	 */
	private BigDecimal promotionPrice;
	/**
	 * 市场价格
	 */
	private BigDecimal marketPrice;
	/**
	 * 商品状态：商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	private Integer status;
	/**
	 * 是否可见：默认0是;1否
	 */
	private Integer isVisible;
	/**
	 * 门店名称
	 */
	private String storeName;
	private String pids;
	
	public String getPids() {
		return pids;
	}
	public void setPids(String pids) {
		this.pids = pids;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStandardUnitSerialNumber() {
		return standardUnitSerialNumber;
	}
	public void setStandardUnitSerialNumber(String standardUnitSerialNumber) {
		this.standardUnitSerialNumber = standardUnitSerialNumber;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
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
	public String getStandardUnitName() {
		return standardUnitName;
	}
	public void setStandardUnitName(String standardUnitName) {
		this.standardUnitName = standardUnitName;
	}
	
}
	