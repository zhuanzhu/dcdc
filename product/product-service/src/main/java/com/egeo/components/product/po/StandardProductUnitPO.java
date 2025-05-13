package com.egeo.components.product.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-05 18:51:01
 */
public class StandardProductUnitPO {


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
	 * 市场价格
	 */
	private BigDecimal marketPrice;	

	/**
	 * 税号(海购商品必须有税号,普通商品无)
	 */
	private String taxNo;	

	/**
	 * EAN(商品条形码)
	 */
	private String eanNo;	

	/**
	 * 产地
	 */
	private String placeOfOrigin;	

	/**
	 * 计量单位
	 */
	private String calculationUnit;	

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

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	private Long enterpriseId;
	private Long supplierId;

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

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	/**
     * spu描述
     */
    private String content;
    /**
     * 商品模版id
     */
    private Long commodityTemplateId;

   	public Long getCommodityTemplateId() {
   		return commodityTemplateId;
   	}

   	public void setCommodityTemplateId(Long commodityTemplateId) {
   		this.commodityTemplateId = commodityTemplateId;
   	}

	public Long getId() {
		return id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * spu编号
	 * @return spu编号
	 */
	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	/**
	 * spu编号
	 * @param productSerialNumber spu编号
	 */
	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
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
	 * 是否可用:默认0否;1是
	 * @return 是否可用:默认0否;1是
	 */
	public Integer getIsAvailable() {
		return isAvailable;
	}

	/**
	 * 是否可用:默认0否;1是
	 * @param isAvailable 是否可用:默认0否;1是
	 */
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

}
	