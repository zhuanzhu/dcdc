package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PuExportVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -74951273687692965L;
	/**
	 * puId
	 */
	private Long id;
	/**
	 * pu编码
	 */
	private String productUnitSerialNumber;
	/**
	 * pu名称
	 */
	private String name;
	/**
	 * pu编码
	 */
	private String code;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * pu商品图片路径
	 */
	private String puPicUrl;
	/**
	 * pu商品状态
	 */
	private Integer status;
	/**
	 * pu商品是否可销售
	 */
	private Integer isVendible;
	/**
	 * 前端是否选中
	 */
	private Boolean checked;
	/**
	 * 对应sku是否可用
	 */
	private Integer isAvailable;
	private BigDecimal demoSalePrice;

	public BigDecimal getDemoSalePrice() {
		return demoSalePrice;
	}

	public void setDemoSalePrice(BigDecimal demoSalePrice) {
		this.demoSalePrice = demoSalePrice;
	}

	public BigDecimal getCompetingSalePrice() {
		return competingSalePrice;
	}

	public void setCompetingSalePrice(BigDecimal competingSalePrice) {
		this.competingSalePrice = competingSalePrice;
	}

	private BigDecimal competingSalePrice;
	/**
	 * 对应sku是否有效
	 */
	private Integer isValid;

	/**
	 * 对应sku名称
	 * @return
	 */
	private String skuName;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}
	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public String getPuPicUrl() {
		return puPicUrl;
	}
	public void setPuPicUrl(String puPicUrl) {
		this.puPicUrl = puPicUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsVendible() {
		return isVendible;
	}
	public void setIsVendible(Integer isVendible) {
		this.isVendible = isVendible;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Integer getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}
}
