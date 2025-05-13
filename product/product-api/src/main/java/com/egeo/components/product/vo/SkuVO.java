package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-06 11:34:03
 */
public class SkuVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * sku编号
	 */
	private String skuSerialNumber;
	/**
	 * 条形码
	 */
	private String barCode;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getExternalSkuId() {
		return externalSkuId;
	}

	public void setExternalSkuId(String externalSkuId) {
		this.externalSkuId = externalSkuId;
	}

	/**

	 * 外部skuid
	 */
	private String externalSkuId;
	/**
	 * 
	 */
	private Long merchantId;
	/**
	 * spuid
	 */
	private Long standardProductUnitId;
	/**
	 * sku名称
	 */
	private String skuName;
	/**
	 * sku商品名称
	 */
	private String skuProductName;
	/**
	 * sku市场价格
	 */
	private BigDecimal skuMarketPrice;
	/**
	 * 参考成本价
	 */
	private BigDecimal skuCostingPrice;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否可用:默认0否;1是
	 */
	private Integer isAvailable;
	/**
	 * 商品编码
	 */
	private String code;
	/**
	 * sku图片
	 */
	private String skuPicUrl;
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
	 * spu名称
	 */
	private String standardProductUnitName;
	/**
	 * 最后同步时间
	 */
	private Date synchronizationTime;	
	/**
	 * 是否有效
	 */
	private Integer isValid;	

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
	 * sku编号
	 * @return sku编号
	 */
	public String getSkuSerialNumber() {
		return skuSerialNumber;
	}

	/**
	 * sku编号
	 * @param skuSerialNumber sku编号
	 */
	public void setSkuSerialNumber(String skuSerialNumber) {
		this.skuSerialNumber = skuSerialNumber;
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
	 * sku名称
	 * @return sku名称
	 */
	public String getSkuName() {
		return skuName;
	}

	/**
	 * sku名称
	 * @param skuName sku名称
	 */
	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}	
	/**
	 * sku商品名称
	 * @return sku商品名称
	 */
	public String getSkuProductName() {
		return skuProductName;
	}

	/**
	 * sku商品名称
	 * @param skuProductName sku商品名称
	 */
	public void setSkuProductName(String skuProductName) {
		this.skuProductName = skuProductName;
	}	
	/**
	 * sku市场价格
	 * @return sku市场价格
	 */
	public BigDecimal getSkuMarketPrice() {
		return skuMarketPrice;
	}

	/**
	 * sku市场价格
	 * @param skuMarketPrice sku市场价格
	 */
	public void setSkuMarketPrice(BigDecimal skuMarketPrice) {
		this.skuMarketPrice = skuMarketPrice;
	}	
	/**
	 * 参考成本价
	 * @return 参考成本价
	 */
	public BigDecimal getSkuCostingPrice() {
		return skuCostingPrice;
	}

	/**
	 * 参考成本价
	 * @param skuCostingPrice 参考成本价
	 */
	public void setSkuCostingPrice(BigDecimal skuCostingPrice) {
		this.skuCostingPrice = skuCostingPrice;
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
	 * sku图片
	 * @return sku图片
	 */
	public String getSkuPicUrl() {
		return skuPicUrl;
	}

	/**
	 * sku图片
	 * @param skuPicUrl sku图片
	 */
	public void setSkuPicUrl(String skuPicUrl) {
		this.skuPicUrl = skuPicUrl;
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

	public String getStandardProductUnitName() {
		return standardProductUnitName;
	}

	public void setStandardProductUnitName(String standardProductUnitName) {
		this.standardProductUnitName = standardProductUnitName;
	}

	public Date getSynchronizationTime() {
		return synchronizationTime;
	}

	public void setSynchronizationTime(Date synchronizationTime) {
		this.synchronizationTime = synchronizationTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}	
	
	
}
	