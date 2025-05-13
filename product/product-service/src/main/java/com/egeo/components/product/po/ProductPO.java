package com.egeo.components.product.po;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 16:11:33
 */
public class ProductPO {


	private Long id;
	/**
	 * spu编号
	 */
	private String productSerialNumber;	
	/**
	 * 产品类目
	 */
	private String productCategory;	

	/**
	 * 
	 */
	private Long brandId;	

	/**
	 * 
	 */
	private Long categoryTreeNodeId;	

	/**
	 * 
	 */
	private String title;	

	/**
	 * 
	 */
	private String name;	

	/**
	 * 
	 */
	private String chineseName;	

	/**
	 * 税号(海购商品必须有税号,普通商品无)
	 */
	private String taxNo;	

	/**
	 * EAN(商品条形码)
	 */
	private String eanNo;	
	/**
	 * 市场价格
	 */
	private BigDecimal marketPrice;	

	/**
	 * 产地
	 */
	private String placeOfOrigin;	

	/**
	 * 计量单位
	 */
	private String calculationUnit;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	private Long enterpriseId;
	private Long supplierId;
	/**
	 * 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 */
	private Integer status;	
	/**
	 * 是否可用:默认0否;1是
	 */
	private Integer isAvailable;	

	/**
	 * 
	 */
	private String productDetails;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	
	// 创建开始时间
    private String beginTime;

    // 创建结束时间
    private String finishTime;

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	
	
	private String ids;
	private String referlink;
	private String extend;
	public String getReferlink() {
		return referlink;
	}

	public void setReferlink(String referlink) {
		this.referlink = referlink;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	private Long categoryId;
	/**
     * 产品描述
     */
    private String content;
    
    /**
	 * 商品模版id
	 */
	private Long commodityTemplateId;

	/**
	 * 预警天数集合
	 */
	private List<String> precautiousLineDays;

	private String priceDetail;
	public String getPriceDetail() {
		return priceDetail;
	}

	public void setPriceDetail(String priceDetail) {
		this.priceDetail = priceDetail;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getPrecautiousStart() {
		return precautiousStart;
	}

	public void setPrecautiousStart(Integer precautiousStart) {
		this.precautiousStart = precautiousStart;
	}

	public Integer getPrecautiousEnd() {
		return precautiousEnd;
	}

	public void setPrecautiousEnd(Integer precautiousEnd) {
		this.precautiousEnd = precautiousEnd;
	}

	/**
	 * 预警开始天数
	 */
	private Integer precautiousStart;
	/**
	 * 预警结束天数
	 */
	private Integer precautiousEnd;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public List<String> getPrecautiousLineDays() {
		return precautiousLineDays;
	}

	public void setPrecautiousLineDays(List<String> precautiousLineDays) {
		this.precautiousLineDays = precautiousLineDays;
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
	 * 
	 * @return 
	 */
	public Long getBrandId() {
		return brandId;
	}

	/**
	 * 
	 * @param brandId 
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getCategoryTreeNodeId() {
		return categoryTreeNodeId;
	}

	/**
	 * 
	 * @param categoryTreeNodeId 
	 */
	public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
		this.categoryTreeNodeId = categoryTreeNodeId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title 
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * 
	 * @return 
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * 
	 * @param chineseName 
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	/**
	 * 税号(海购商品必须有税号,普通商品无)
	 * @return 税号(海购商品必须有税号,普通商品无)
	 */
	public String getTaxNo() {
		return taxNo;
	}

	/**
	 * 税号(海购商品必须有税号,普通商品无)
	 * @param taxNo 税号(海购商品必须有税号,普通商品无)
	 */
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	/**
	 * EAN(商品条形码)
	 * @return EAN(商品条形码)
	 */
	public String getEanNo() {
		return eanNo;
	}

	/**
	 * EAN(商品条形码)
	 * @param eanNo EAN(商品条形码)
	 */
	public void setEanNo(String eanNo) {
		this.eanNo = eanNo;
	}

	/**
	 * 产地
	 * @return 产地
	 */
	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	/**
	 * 产地
	 * @param placeOfOrigin 产地
	 */
	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}

	/**
	 * 计量单位
	 * @return 计量单位
	 */
	public String getCalculationUnit() {
		return calculationUnit;
	}

	/**
	 * 计量单位
	 * @param calculationUnit 计量单位
	 */
	public void setCalculationUnit(String calculationUnit) {
		this.calculationUnit = calculationUnit;
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

	/**
	 * 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 * @return 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 * @param status 状态:1:待审核,2:审核中,3:已通过,4:未通过
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 
	 * @return 
	 */
	public String getProductDetails() {
		return productDetails;
	}

	/**
	 * 
	 * @param productDetails 
	 */
	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCommodityTemplateId() {
		return commodityTemplateId;
	}

	public void setCommodityTemplateId(Long commodityTemplateId) {
		this.commodityTemplateId = commodityTemplateId;
	}
	
	
	
	
	
	
	
}
	