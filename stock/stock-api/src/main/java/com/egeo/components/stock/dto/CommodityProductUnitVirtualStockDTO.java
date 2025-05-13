package com.egeo.components.stock.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-08 09:55:37
 */
public class CommodityProductUnitVirtualStockDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * suid
	 */
	private Long standardUnitId;	

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
	 * 线上puid
	 */
	private Long commodityProductUnitId;	

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
	 * 线上puid
	 * @return 线上puid
	 */
	public Long getCommodityProductUnitId() {
		return commodityProductUnitId;
	}

	/**
	 * 线上puid
	 * @param commodityProductUnitId 线上puid
	 */
	public void setCommodityProductUnitId(Long commodityProductUnitId) {
		this.commodityProductUnitId = commodityProductUnitId;
	}
}
	