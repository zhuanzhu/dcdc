package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StandardUnitExportVO implements Serializable{
	private static final long serialVersionUID = 7118090841615823659L;
	private Integer buyType;
	
	private Long frontSerialNumber;
	
	// su商品id
	private Long id;
	// su商品名称
	private String standardUnitName;
	// su商品序列号
	private String merchantProductSerialNumber;
	// 产品类目
	private String productCategory;
	// su商品状态
	private Integer status;
	// 销售价格
	private BigDecimal salePrice;	
	// 市场价格
	private BigDecimal marketPrice;	
	// 运费说明
	private String freightExplain;
	// 发货说明
	private String shipmentsExplain;
	// 销量
	private Long salesVolume;
	// 商品详情
	private String content;
	// 是否可见：默认0是;1否
	private Integer isVisible;	
	// 是否可销售:默认0是;1否
	private Integer isVendible;
	// 已售基数
	private Long soldBase;
	// 商品模版id
	private Long commodityTemplateId;
	// 销售方式：1正常销售、2团购、3预售
	private Integer saleWay;
	// 商家id
	private Long merchantId;	
	// 促销价格
	private BigDecimal promotionPrice;	
	// spu产品名称
	private String standardProductUnitName;
	// spu产品序列号
	private String productSerialNumber;
	// 是否引用spu关键词
	private Integer isSpuKeyword;
	// 演示销售价格
	private BigDecimal demoSalePrice;
	// 竞品销售价格
	private BigDecimal competingSalePrice;
	// su图片路径集合
	private List<String> pictureList;
	// suweb轮播图
	private List<String> webBannerPictureList;
	private List<Map<String, Object>> companyList = new ArrayList<>(); // 正式公司集合
	private List<Map<String, Object>> demoCompanyList = new ArrayList<>(); // 演示公司集合
	private List<Map<String, Object>> competingCompanyList = new ArrayList<>(); // 竞品公司集合
	// 客户端集合
	private List<Map<String, Object>> clientList;
	private List<Long> companyIds = new ArrayList<>(); // 正式公司id集合
	private List<Long> demoCompanyIds = new ArrayList<>(); // 演示公司id集合
	private List<Long> competingCompanyIds = new ArrayList<>(); // 竞品公司id集合
	// su客户端id集合
	private List<Long> clientIds;
	// pu信息集合
	private List<PuExportVO> productUnitList;
	// 库存预警
	private Long stockWarning;
	// 最后修改人ID
	private Long updateUserid;	
	// 最后修改人姓名
	private String updateUsername;	
	// 最后修改人IP
	private String updateUserip;	
	// 修改时间:更新时数据库会自动set值
	private Date updateTime;	
	// 标签id集合
	private List<Long> tagList;
	// su关键词
	private List<String> suKeywords;
	// spu关键词
	private List<String> spuKeywords;
	// 总店id
	private Long storeId;
	// 预售期
	private Long presellPeriod;
	// 关联suId
	private Long relevanceSuId;
	// 关联su名称
	private String relevanceSuName;
	// 门店id集合
	private List<Long> storeIdList;
	// 会籍id集合
	private List<Long> membershipIdList; 
	
	public List<Map<String, Object>> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<Map<String, Object>> companyList) {
		this.companyList = companyList;
	}
	public List<Map<String, Object>> getDemoCompanyList() {
		return demoCompanyList;
	}
	public void setDemoCompanyList(List<Map<String, Object>> demoCompanyList) {
		this.demoCompanyList = demoCompanyList;
	}
	public List<Map<String, Object>> getCompetingCompanyList() {
		return competingCompanyList;
	}
	public void setCompetingCompanyList(List<Map<String, Object>> competingCompanyList) {
		this.competingCompanyList = competingCompanyList;
	}
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
	public String getRelevanceSuName() {
		return relevanceSuName;
	}
	public void setRelevanceSuName(String relevanceSuName) {
		this.relevanceSuName = relevanceSuName;
	}
	public List<Long> getStoreIdList() {
		return storeIdList;
	}
	public void setStoreIdList(List<Long> storeIdList) {
		this.storeIdList = storeIdList;
	}
	public List<Long> getMembershipIdList() {
		return membershipIdList;
	}
	public void setMembershipIdList(List<Long> membershipIdList) {
		this.membershipIdList = membershipIdList;
	}
	public List<String> getWebBannerPictureList() {
		return webBannerPictureList;
	}
	public void setWebBannerPictureList(List<String> webBannerPictureList) {
		this.webBannerPictureList = webBannerPictureList;
	}
	public List<Long> getDemoCompanyIds() {
		return demoCompanyIds;
	}
	public void setDemoCompanyIds(List<Long> demoCompanyIds) {
		this.demoCompanyIds = demoCompanyIds;
	}
	public List<Long> getCompetingCompanyIds() {
		return competingCompanyIds;
	}
	public void setCompetingCompanyIds(List<Long> competingCompanyIds) {
		this.competingCompanyIds = competingCompanyIds;
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
	public List<String> getSuKeywords() {
		return suKeywords;
	}
	public void setSuKeywords(List<String> suKeywords) {
		this.suKeywords = suKeywords;
	}
	public List<String> getSpuKeywords() {
		return spuKeywords;
	}
	public void setSpuKeywords(List<String> spuKeywords) {
		this.spuKeywords = spuKeywords;
	}
	public List<Long> getTagList() {
		return tagList;
	}
	public void setTagList(List<Long> tagList) {
		this.tagList = tagList;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	public Long getStockWarning() {
		return stockWarning;
	}
	public void setStockWarning(Long stockWarning) {
		this.stockWarning = stockWarning;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStandardUnitName() {
		return standardUnitName;
	}
	public void setStandardUnitName(String standardUnitName) {
		this.standardUnitName = standardUnitName;
	}
	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}
	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getFreightExplain() {
		return freightExplain;
	}
	public void setFreightExplain(String freightExplain) {
		this.freightExplain = freightExplain;
	}
	public String getShipmentsExplain() {
		return shipmentsExplain;
	}
	public void setShipmentsExplain(String shipmentsExplain) {
		this.shipmentsExplain = shipmentsExplain;
	}
	public Long getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}
	public Integer getIsVendible() {
		return isVendible;
	}
	public void setIsVendible(Integer isVendible) {
		this.isVendible = isVendible;
	}
	public Long getSoldBase() {
		return soldBase;
	}
	public void setSoldBase(Long soldBase) {
		this.soldBase = soldBase;
	}
	public Long getCommodityTemplateId() {
		return commodityTemplateId;
	}
	public void setCommodityTemplateId(Long commodityTemplateId) {
		this.commodityTemplateId = commodityTemplateId;
	}
	public Integer getSaleWay() {
		return saleWay;
	}
	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getStandardProductUnitName() {
		return standardProductUnitName;
	}
	public void setStandardProductUnitName(String standardProductUnitName) {
		this.standardProductUnitName = standardProductUnitName;
	}
	public String getProductSerialNumber() {
		return productSerialNumber;
	}
	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}
	public List<String> getPictureList() {
		return pictureList;
	}
	public void setPictureList(List<String> pictureList) {
		this.pictureList = pictureList;
	}
	public List<Map<String, Object>> getClientList() {
		return clientList;
	}
	public void setClientList(List<Map<String, Object>> clientList) {
		this.clientList = clientList;
	}
	public List<Long> getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}
	public List<Long> getClientIds() {
		return clientIds;
	}
	public void setClientIds(List<Long> clientIds) {
		this.clientIds = clientIds;
	}
	public List<PuExportVO> getProductUnitList() {
		return productUnitList;
	}
	public void setProductUnitList(List<PuExportVO> productUnitList) {
		this.productUnitList = productUnitList;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
	public Long getFrontSerialNumber() {
		return frontSerialNumber;
	}
	public void setFrontSerialNumber(Long frontSerialNumber) {
		this.frontSerialNumber = frontSerialNumber;
	}
	
}
