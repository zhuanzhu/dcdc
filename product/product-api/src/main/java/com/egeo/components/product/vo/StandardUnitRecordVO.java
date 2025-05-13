package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-08 06:29:10
 */
public class StandardUnitRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer buyType;

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	private Long id;
	/**
	 * merchant_product编号
	 */
	private String merchantProductSerialNumber;
	/**
	 * suId
	 */
	private Long standardUnitId;
	/**
	 * 
	 */
	private Long merchantId;
	/**
	 * spuid
	 */
	private Long standardProductUnitId;
	/**
	 * 
	 */
	private Long merchantCateTreeNodeId;
	/**
	 * 产品类目
	 */
	private String productCategory;
	/**
	 * 
	 */
	private Long merchantSeriesId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 别名
	 */
	private String alias;
	/**
	 * 是否可见：默认0是;1否
	 */
	private Integer isVisible;
	/**
	 * 副标题(商家自定义名称)
	 */
	private String subtitle;
	/**
	 * 供应商Id
	 */
	private Long supplierId;
	/**
	 * 商品类型
	 */
	private Integer type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;
	/**
	 * 销售税率
	 */
	private BigDecimal saleTaxRate;
	/**
	 * 包退天数
	 */
	private Integer returnDays;
	/**
	 * 包换天数
	 */
	private Integer replacementDays;
	/**
	 * 保修天数
	 */
	private Integer guaranteeDays;
	/**
	 * 是否可开增值税发票
	 */
	private Integer isVatInvoice;
	/**
	 * 是否支持VIP
	 */
	private Integer isVipCard;
	/**
	 * 是否启用保质期控制
	 */
	private Integer isEnableShelflife;
	/**
	 * 保质期天数
	 */
	private Integer shelflifeDays;
	/**
	 * 是否可销售:默认0是;1否
	 */
	private Integer isVendible;
	/**
	 * 商品毛重
	 */
	private BigDecimal grossWeight;
	/**
	 * 商品净重
	 */
	private BigDecimal netWeight;
	/**
	 * 商品编码
	 */
	private String code;
	/**
	 * 商家品牌Id
	 */
	private Long merchantBrandId;
	/**
	 * 市场价格
	 */
	private BigDecimal marketPrice;
	/**
	 * 促销价格
	 */
	private BigDecimal promotionPrice;
	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	private Integer status;
	/**
	 * 销售方式：1正常销售、2团购、3预售
	 */
	private Integer saleWay;
	/**
	 * 已售基数
	 */
	private Long soldBase;
	/**
	 * 运费说明
	 */
	private String freightExplain;
	/**
	 * 发货说明
	 */
	private String shipmentsExplain;
	/**
	 * 
	 */
	private Long freightTemplateId;
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
	/**
	 * 创建人ID
	 */
	private Long createUserid;	

	/**
	 * 创建人姓名
	 */
	private String createUsername;	

	/**
	 * 创建人IP
	 */
	private String createUserip;	

	/**
	 * 创建人MAC地址
	 */
	private String createUsermac;
	/**
	 * 最后修改人ID
	 */
	private Long updateUserid;	

	/**
	 * 最后修改人姓名
	 */
	private String updateUsername;	

	/**
	 * 最后修改人IP
	 */
	private String updateUserip;	

	/**
	 * 最后修改人MAC
	 */
	private String updateUsermac;	
	/**
	 * 是否引用spu关键词
	 */
	private Integer isSpuKeyword;
	/**
	 * 演示销售价格
	 */
	private BigDecimal demoSalePrice;
	/**
	 * 竞品销售价格
	 */
	private BigDecimal competingSalePrice;
	/**
	 * 总店id
	 */
	private Long storeId;
	/**
	 * 预期值
	 */
	private Long presellPeriod;
	/**
	 * 关联su商品id
	 */
	private Long relevanceSuId;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getPresellPeriod() {
		return presellPeriod;
	}

	public void setPresellPeriod(Long presellPeriod) {
		this.presellPeriod = presellPeriod;
	}

	public Long getRelevanceSuId() {
		return relevanceSuId;
	}

	public void setRelevanceSuId(Long relevanceSuId) {
		this.relevanceSuId = relevanceSuId;
	}

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

	public Integer getIsSpuKeyword() {
		return isSpuKeyword;
	}

	public void setIsSpuKeyword(Integer isSpuKeyword) {
		this.isSpuKeyword = isSpuKeyword;
	}

	public Long getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public String getCreateUserip() {
		return createUserip;
	}

	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}

	public String getCreateUsermac() {
		return createUsermac;
	}

	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
	}

	public Long getUpdateUserid() {
		return updateUserid;
	}

	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}

	public String getUpdateUsername() {
		return updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	public String getUpdateUserip() {
		return updateUserip;
	}

	public void setUpdateUserip(String updateUserip) {
		this.updateUserip = updateUserip;
	}

	public String getUpdateUsermac() {
		return updateUsermac;
	}

	public void setUpdateUsermac(String updateUsermac) {
		this.updateUsermac = updateUsermac;
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
	 * merchant_product编号
	 * @return merchant_product编号
	 */
	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	/**
	 * merchant_product编号
	 * @param merchantProductSerialNumber merchant_product编号
	 */
	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}	
	/**
	 * suId
	 * @return suId
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * suId
	 * @param standardUnitId suId
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 
	 * @param merchantId 
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}	
	/**
	 * spuid
	 * @return spuid
	 */
	public Long getStandardProductUnitId() {
		return standardProductUnitId;
	}

	/**
	 * spuid
	 * @param standardProductUnitId spuid
	 */
	public void setStandardProductUnitId(Long standardProductUnitId) {
		this.standardProductUnitId = standardProductUnitId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getMerchantCateTreeNodeId() {
		return merchantCateTreeNodeId;
	}

	/**
	 * 
	 * @param merchantCateTreeNodeId 
	 */
	public void setMerchantCateTreeNodeId(Long merchantCateTreeNodeId) {
		this.merchantCateTreeNodeId = merchantCateTreeNodeId;
	}	
	/**
	 * 产品类目
	 * @return 产品类目
	 */
	public String getProductCategory() {
		return productCategory;
	}

	/**
	 * 产品类目
	 * @param productCategory 产品类目
	 */
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getMerchantSeriesId() {
		return merchantSeriesId;
	}

	/**
	 * 
	 * @param merchantSeriesId 
	 */
	public void setMerchantSeriesId(Long merchantSeriesId) {
		this.merchantSeriesId = merchantSeriesId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 别名
	 * @return 别名
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * 别名
	 * @param alias 别名
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}	
	/**
	 * 是否可见：默认0是;1否
	 * @return 是否可见：默认0是;1否
	 */
	public Integer getIsVisible() {
		return isVisible;
	}

	/**
	 * 是否可见：默认0是;1否
	 * @param isVisible 是否可见：默认0是;1否
	 */
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}	
	/**
	 * 副标题(商家自定义名称)
	 * @return 副标题(商家自定义名称)
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * 副标题(商家自定义名称)
	 * @param subtitle 副标题(商家自定义名称)
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}	
	/**
	 * 供应商Id
	 * @return 供应商Id
	 */
	public Long getSupplierId() {
		return supplierId;
	}

	/**
	 * 供应商Id
	 * @param supplierId 供应商Id
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}	
	/**
	 * 商品类型
	 * @return 商品类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 商品类型
	 * @param type 商品类型
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * 销售税率
	 * @return 销售税率
	 */
	public BigDecimal getSaleTaxRate() {
		return saleTaxRate;
	}

	/**
	 * 销售税率
	 * @param saleTaxRate 销售税率
	 */
	public void setSaleTaxRate(BigDecimal saleTaxRate) {
		this.saleTaxRate = saleTaxRate;
	}	
	/**
	 * 包退天数
	 * @return 包退天数
	 */
	public Integer getReturnDays() {
		return returnDays;
	}

	/**
	 * 包退天数
	 * @param returnDays 包退天数
	 */
	public void setReturnDays(Integer returnDays) {
		this.returnDays = returnDays;
	}	
	/**
	 * 包换天数
	 * @return 包换天数
	 */
	public Integer getReplacementDays() {
		return replacementDays;
	}

	/**
	 * 包换天数
	 * @param replacementDays 包换天数
	 */
	public void setReplacementDays(Integer replacementDays) {
		this.replacementDays = replacementDays;
	}	
	/**
	 * 保修天数
	 * @return 保修天数
	 */
	public Integer getGuaranteeDays() {
		return guaranteeDays;
	}

	/**
	 * 保修天数
	 * @param guaranteeDays 保修天数
	 */
	public void setGuaranteeDays(Integer guaranteeDays) {
		this.guaranteeDays = guaranteeDays;
	}	
	/**
	 * 是否可开增值税发票
	 * @return 是否可开增值税发票
	 */
	public Integer getIsVatInvoice() {
		return isVatInvoice;
	}

	/**
	 * 是否可开增值税发票
	 * @param isVatInvoice 是否可开增值税发票
	 */
	public void setIsVatInvoice(Integer isVatInvoice) {
		this.isVatInvoice = isVatInvoice;
	}	
	/**
	 * 是否支持VIP
	 * @return 是否支持VIP
	 */
	public Integer getIsVipCard() {
		return isVipCard;
	}

	/**
	 * 是否支持VIP
	 * @param isVipCard 是否支持VIP
	 */
	public void setIsVipCard(Integer isVipCard) {
		this.isVipCard = isVipCard;
	}	
	/**
	 * 是否启用保质期控制
	 * @return 是否启用保质期控制
	 */
	public Integer getIsEnableShelflife() {
		return isEnableShelflife;
	}

	/**
	 * 是否启用保质期控制
	 * @param isEnableShelflife 是否启用保质期控制
	 */
	public void setIsEnableShelflife(Integer isEnableShelflife) {
		this.isEnableShelflife = isEnableShelflife;
	}	
	/**
	 * 保质期天数
	 * @return 保质期天数
	 */
	public Integer getShelflifeDays() {
		return shelflifeDays;
	}

	/**
	 * 保质期天数
	 * @param shelflifeDays 保质期天数
	 */
	public void setShelflifeDays(Integer shelflifeDays) {
		this.shelflifeDays = shelflifeDays;
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
	 * 商品毛重
	 * @return 商品毛重
	 */
	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	/**
	 * 商品毛重
	 * @param grossWeight 商品毛重
	 */
	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}	
	/**
	 * 商品净重
	 * @return 商品净重
	 */
	public BigDecimal getNetWeight() {
		return netWeight;
	}

	/**
	 * 商品净重
	 * @param netWeight 商品净重
	 */
	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
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
	 * 商家品牌Id
	 * @return 商家品牌Id
	 */
	public Long getMerchantBrandId() {
		return merchantBrandId;
	}

	/**
	 * 商家品牌Id
	 * @param merchantBrandId 商家品牌Id
	 */
	public void setMerchantBrandId(Long merchantBrandId) {
		this.merchantBrandId = merchantBrandId;
	}	
	/**
	 * 市场价格
	 * @return 市场价格
	 */
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 市场价格
	 * @param marketPrice 市场价格
	 */
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}	
	/**
	 * 促销价格
	 * @return 促销价格
	 */
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	/**
	 * 促销价格
	 * @param promotionPrice 促销价格
	 */
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
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
	 * 销售方式：1正常销售、2团购、3预售
	 * @return 销售方式：1正常销售、2团购、3预售
	 */
	public Integer getSaleWay() {
		return saleWay;
	}

	/**
	 * 销售方式：1正常销售、2团购、3预售
	 * @param saleWay 销售方式：1正常销售、2团购、3预售
	 */
	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}	
	/**
	 * 已售基数
	 * @return 已售基数
	 */
	public Long getSoldBase() {
		return soldBase;
	}

	/**
	 * 已售基数
	 * @param soldBase 已售基数
	 */
	public void setSoldBase(Long soldBase) {
		this.soldBase = soldBase;
	}	
	/**
	 * 运费说明
	 * @return 运费说明
	 */
	public String getFreightExplain() {
		return freightExplain;
	}

	/**
	 * 运费说明
	 * @param freightExplain 运费说明
	 */
	public void setFreightExplain(String freightExplain) {
		this.freightExplain = freightExplain;
	}	
	/**
	 * 发货说明
	 * @return 发货说明
	 */
	public String getShipmentsExplain() {
		return shipmentsExplain;
	}

	/**
	 * 发货说明
	 * @param shipmentsExplain 发货说明
	 */
	public void setShipmentsExplain(String shipmentsExplain) {
		this.shipmentsExplain = shipmentsExplain;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getFreightTemplateId() {
		return freightTemplateId;
	}

	/**
	 * 
	 * @param freightTemplateId 
	 */
	public void setFreightTemplateId(Long freightTemplateId) {
		this.freightTemplateId = freightTemplateId;
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
}
	