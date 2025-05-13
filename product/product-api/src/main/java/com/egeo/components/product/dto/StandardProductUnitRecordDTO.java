package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-05 19:55:45
 */
public class StandardProductUnitRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * spuid
	 */
	private Long standardProductUnitId;	

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
	 * 创建人ID
	 * @return 创建人ID
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 创建人ID
	 * @param createUserid 创建人ID
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	/**
	 * 创建人姓名
	 * @return 创建人姓名
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 创建人姓名
	 * @param createUsername 创建人姓名
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	/**
	 * 创建人IP
	 * @return 创建人IP
	 */
	public String getCreateUserip() {
		return createUserip;
	}

	/**
	 * 创建人IP
	 * @param createUserip 创建人IP
	 */
	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}
	/**
	 * 创建人MAC地址
	 * @return 创建人MAC地址
	 */
	public String getCreateUsermac() {
		return createUsermac;
	}

	/**
	 * 创建人MAC地址
	 * @param createUsermac 创建人MAC地址
	 */
	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	
}
	