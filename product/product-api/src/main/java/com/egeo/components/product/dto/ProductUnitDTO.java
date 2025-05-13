package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author min
 * @date 2018-01-07 15:35:31
 */
public class ProductUnitDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private BigDecimal demoSalePrice;
	private BigDecimal competingSalePrice;

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

	/**
	 * pu编号
	 */
	private String productUnitSerialNumber;	

	/**
	 * 
	 */
	private Long skuId;	

	/**
	 * 商品id
	 */
	private Long merchantProductId;	

	/**
	 * pu名称
	 */
	private String name;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;	

	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	private Integer status;	

	/**
	 * 是否可销售:默认0是;1否
	 */
	private Integer isVendible;	

	/**
	 * 商品编码
	 */
	private String code;	

	/**
	 * pu图片路径
	 */
	private String puPicUrl;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * 是否选中
	 */
	private boolean checked;
	/**
	 * 是否可用:默认0否;1是
	 */
	private Integer isAvailable;	
	/**
	 * 是否有效
	 */
	private Integer isValid;

	/**
	 * sku名称
	 */
	private String skuName;


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
	 * pu编号
	 * @return pu编号
	 */
	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}

	/**
	 * pu编号
	 * @param productUnitSerialNumber pu编号
	 */
	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * 
	 * @param skuId 
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	/**
	 * 商品id
	 * @return 商品id
	 */
	public Long getMerchantProductId() {
		return merchantProductId;
	}

	/**
	 * 商品id
	 * @param merchantProductId 商品id
	 */
	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
	}
	/**
	 * pu名称
	 * @return pu名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * pu名称
	 * @param name pu名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 销售价格
	 * @return 销售价格
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * 销售价格
	 * @param salePrice 销售价格
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 * @return 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 * @param status 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 是否可销售:默认0是;1否
	 * @return 是否可销售:默认0是;1否
	 */
	public Integer getIsVendible() {
		return isVendible;
	}

	/**
	 * 是否可销售:默认0是;1否
	 * @param isVendible 是否可销售:默认0是;1否
	 */
	public void setIsVendible(Integer isVendible) {
		this.isVendible = isVendible;
	}
	/**
	 * 商品编码
	 * @return 商品编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 商品编码
	 * @param code 商品编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * pu图片路径
	 * @return pu图片路径
	 */
	public String getPuPicUrl() {
		return puPicUrl;
	}

	/**
	 * pu图片路径
	 * @param puPicUrl pu图片路径
	 */
	public void setPuPicUrl(String puPicUrl) {
		this.puPicUrl = puPicUrl;
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
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
	