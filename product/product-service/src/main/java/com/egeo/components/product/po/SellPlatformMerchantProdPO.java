package com.egeo.components.product.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-06 14:42:44
 */
public class SellPlatformMerchantProdPO {


	private Long id;

	/**
	 * 平台id
	 */
	private Long sellPlatformId;	

	/**
	 * 商品id
	 */
	private Long merchantProductId;	

	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;	

	/**
	 * 链接
	 */
	private String path;	

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
	 * 平台id
	 * @return 平台id
	 */
	public Long getSellPlatformId() {
		return sellPlatformId;
	}

	/**
	 * 平台id
	 * @param sellPlatformId 平台id
	 */
	public void setSellPlatformId(Long sellPlatformId) {
		this.sellPlatformId = sellPlatformId;
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
	 * 链接
	 * @return 链接
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 链接
	 * @param path 链接
	 */
	public void setPath(String path) {
		this.path = path;
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
	