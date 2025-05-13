package com.egeo.components.product.condition;

import com.egeo.components.product.po.StandardUnitCombinationSuPO;

import java.math.BigDecimal;

/**
 * 
 * @author min
 * @date 2018-04-06 16:03:34
 */
public class StandardUnitCombinationSuCondition extends StandardUnitCombinationSuPO {
	private static final long serialVersionUID = 1L;
	/**
	 * su商品名称
	 */
	private String StandardUnitName;
	private BigDecimal salePrice;
	private String merchantProductSerialNumber;
	private Long supplierId;
	private Integer isVendible;
	private Integer isVisible;

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public String getStandardUnitName() {
		return StandardUnitName;
	}

	public void setStandardUnitName(String standardUnitName) {
		StandardUnitName = standardUnitName;
	}

	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Integer getIsVendible() {
		return isVendible;
	}

	public void setIsVendible(Integer isVendible) {
		this.isVendible = isVendible;
	}

	public Integer getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
}
	