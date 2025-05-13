package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-08 11:16:12
 */
public class CommodityProductUnitVO implements Serializable {
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
	 * 总店id
	 */
	private Long storeId;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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
	