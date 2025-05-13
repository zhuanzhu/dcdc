package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author min
 * @date 2018-01-08 11:16:12
 */
public class CommodityProductUnitDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long limitBuyNum;

	public Long getLimitBuyNum() {
		return limitBuyNum;
	}

	public void setLimitBuyNum(Long limitBuyNum) {
		this.limitBuyNum = limitBuyNum;
	}

	private Long id;
	private BigDecimal demoSalePrice;
	private BigDecimal supplierPrice;
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
	 * puid
	 */
	private Long productUnitId;	

	/**
	 * 
	 */
	private Long skuId;	

	/**
	 * suid
	 */
	private Long standardUnitId;	

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
	 * 是否可销售:默认0否;1是
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
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	private Long enterpriseId;	
	/**
	 * spuid
	 */
	private Long standardProductUnitId;	

	/**
	 * 商家id
	 */
	private Long merchantId;
	/**
	 * pu规格值id
	 */
	private List<Long> puAttValueIds;
	/**
	 * 排序后的pu规格值id
	 */
	private List<Long> puAttValueIdsSort;
	
	/**
	 * 商品模版id
	 */
	private Long commodityTemplateId;
	/**
	 * 剩余库存数量
	 */
	private Long realStockNum;
	/**
	 * 总店id
	 */
	private Long storeId;
	/**
	 * 总店名称
	 */
	private String storeName;
	/**
	 * su商品名称
	 */
	private String standardUnitName;
	/**
	 * 是否可见：默认0是;1否
	 */
	private Integer isVisible;	
	/**
	 * 外部SKU ID
	 */
	private String externalSkuId;
	/**
	 * SKU名称
	 */
	private String skuName;
	
	private Long puId;
	
	private BigDecimal puSalePrice;
	/**
	 * SU下PU的最大销售价格
	 */
	private BigDecimal maxSalePrice;
	/**
	 * SU下PU的最小销售价格
	 */
	private BigDecimal minSalePrice;

	private Long jdSpuId;

	private String productCategory;

	private String merchantProductSerialNumber;

	/**
	 * 商品税率
	 */
	private BigDecimal taxRate;

	public BigDecimal getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(BigDecimal supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getJdSpuId() {
		return jdSpuId;
	}

	public void setJdSpuId(Long jdSpuId) {
		this.jdSpuId = jdSpuId;
	}

	public Long getPuId() {
		return puId;
	}
	public void setPuId(Long puId) {
		this.puId = puId;
	}
	public BigDecimal getPuSalePrice() {
		return puSalePrice;
	}
	public void setPuSalePrice(BigDecimal puSalePrice) {
		this.puSalePrice = puSalePrice;
	}
	public BigDecimal getMaxSalePrice() {
		return maxSalePrice;
	}
	public void setMaxSalePrice(BigDecimal maxSalePrice) {
		this.maxSalePrice = maxSalePrice;
	}
	public BigDecimal getMinSalePrice() {
		return minSalePrice;
	}
	public void setMinSalePrice(BigDecimal minSalePrice) {
		this.minSalePrice = minSalePrice;
	}
	
	public String getExternalSkuId() {
		return externalSkuId;
	}
	public void setExternalSkuId(String externalSkuId) {
		this.externalSkuId = externalSkuId;
	}
	public String getSkuName() {
		return skuName;
	}
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getRealStockNum() {
		return realStockNum;
	}

	public void setRealStockNum(Long realStockNum) {
		this.realStockNum = realStockNum;
	}

	public Long getCommodityTemplateId() {
		return commodityTemplateId;
	}

	public void setCommodityTemplateId(Long commodityTemplateId) {
		this.commodityTemplateId = commodityTemplateId;
	}

	public List<Long> getPuAttValueIdsSort() {
		return puAttValueIdsSort;
	}

	public void setPuAttValueIdsSort(List<Long> puAttValueIdsSort) {
		this.puAttValueIdsSort = puAttValueIdsSort;
	}

	public List<Long> getPuAttValueIds() {
		return puAttValueIds;
	}

	public void setPuAttValueIds(List<Long> puAttValueIds) {
		this.puAttValueIds = puAttValueIds;
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
	 * puid
	 * @return puid
	 */
	public Long getProductUnitId() {
		return productUnitId;
	}

	/**
	 * puid
	 * @param productUnitId puid
	 */
	public void setProductUnitId(Long productUnitId) {
		this.productUnitId = productUnitId;
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
	 * suid
	 * @return suid
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * suid
	 * @param standardUnitId suid
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
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
	 * 是否可销售:默认0否;1是
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
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public Long getStandardProductUnitId() {
		return standardProductUnitId;
	}

	public void setStandardProductUnitId(Long standardProductUnitId) {
		this.standardProductUnitId = standardProductUnitId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
}
	