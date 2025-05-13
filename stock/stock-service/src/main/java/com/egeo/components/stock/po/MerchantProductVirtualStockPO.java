package com.egeo.components.stock.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 09:13:19
 */
public class MerchantProductVirtualStockPO {


	private Long id;

	/**
	 * 产品id
	 */
	private Long standardProductUnitId;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 是否支持虚拟库存 0不支持，1支持
	 */
	private Integer isSupportVirtualstock;	

	/**
	 * 是否支持无限库存 0不支持，1支持
	 */
	private Integer isSupportUnlimitedstock;	

	/**
	 * 真实库存
	 */
	private Long realStockNum;	

	/**
	 * 真实冻结库存
	 */
	private Long realFrozenStockNum;	

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
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	private Long skuId;	

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


	public Long getStandardProductUnitId() {
		return standardProductUnitId;
	}

	public void setStandardProductUnitId(Long standardProductUnitId) {
		this.standardProductUnitId = standardProductUnitId;
	}

	/**
	 * 商家id
	 * @return 商家id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家id
	 * @param merchantId 商家id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 是否支持虚拟库存 0不支持，1支持
	 * @return 是否支持虚拟库存 0不支持，1支持
	 */
	public Integer getIsSupportVirtualstock() {
		return isSupportVirtualstock;
	}

	/**
	 * 是否支持虚拟库存 0不支持，1支持
	 * @param isSupportVirtualstock 是否支持虚拟库存 0不支持，1支持
	 */
	public void setIsSupportVirtualstock(Integer isSupportVirtualstock) {
		this.isSupportVirtualstock = isSupportVirtualstock;
	}

	/**
	 * 是否支持无限库存 0不支持，1支持
	 * @return 是否支持无限库存 0不支持，1支持
	 */
	public Integer getIsSupportUnlimitedstock() {
		return isSupportUnlimitedstock;
	}

	/**
	 * 是否支持无限库存 0不支持，1支持
	 * @param isSupportUnlimitedstock 是否支持无限库存 0不支持，1支持
	 */
	public void setIsSupportUnlimitedstock(Integer isSupportUnlimitedstock) {
		this.isSupportUnlimitedstock = isSupportUnlimitedstock;
	}

	/**
	 * 真实库存
	 * @return 真实库存
	 */
	public Long getRealStockNum() {
		return realStockNum;
	}

	/**
	 * 真实库存
	 * @param realStockNum 真实库存
	 */
	public void setRealStockNum(Long realStockNum) {
		this.realStockNum = realStockNum;
	}

	/**
	 * 真实冻结库存
	 * @return 真实冻结库存
	 */
	public Long getRealFrozenStockNum() {
		return realFrozenStockNum;
	}

	/**
	 * 真实冻结库存
	 * @param realFrozenStockNum 真实冻结库存
	 */
	public void setRealFrozenStockNum(Long realFrozenStockNum) {
		this.realFrozenStockNum = realFrozenStockNum;
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

	/**
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 * @return 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 * @param skuId 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
}
	