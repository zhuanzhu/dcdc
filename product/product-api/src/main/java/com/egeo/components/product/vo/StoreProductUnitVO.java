package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-09-13 05:07:28
 */
public class StoreProductUnitVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 门店id
	 */
	private Long storeId;
	/**
	 * 门店名称
	 */
	private String storeName;
	/**
	 * su商品id
	 */
	private Long standardUnitId;
	/**
	 * 商品puId
	 */
	private Long commodityProductUnitId;
	/**
	 * 商品pu名称
	 */
	private String commodityProductUnitName;
	/**
	 * 商品pu序列号
	 */
	private String productUnitSerialNumber;
	/**
	 * 门店pu状态：3、已上架 4、已下架
	 */
	private Integer status;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCommodityProductUnitName() {
		return commodityProductUnitName;
	}

	public void setCommodityProductUnitName(String commodityProductUnitName) {
		this.commodityProductUnitName = commodityProductUnitName;
	}

	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}

	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
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
	 * 门店id
	 * @return 门店id
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 门店id
	 * @param storeId 门店id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}	
	/**
	 * su商品id
	 * @return su商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * su商品id
	 * @param standardUnitId su商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}	
	/**
	 * 商品puId
	 * @return 商品puId
	 */
	public Long getCommodityProductUnitId() {
		return commodityProductUnitId;
	}

	/**
	 * 商品puId
	 * @param commodityProductUnitId 商品puId
	 */
	public void setCommodityProductUnitId(Long commodityProductUnitId) {
		this.commodityProductUnitId = commodityProductUnitId;
	}	
	/**
	 * 门店pu状态：3、已上架 4、已下架
	 * @return 门店pu状态：3、已上架 4、已下架
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 门店pu状态：3、已上架 4、已下架
	 * @param status 门店pu状态：3、已上架 4、已下架
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	