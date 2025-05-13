package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-08 13:16:55
 */
public class MerchantProdSalesRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 当月累计销售数量
	 */
	private Long salesVolume;	

	/**
	 * 销售月分
	 */
	private String salesDate;	

	/**
	 * spuid
	 */
	private Long standardProductUnitId;	

	/**
	 * suid
	 */
	private Long standardUnitId;

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 商品中文名称
	 */
	private String chineseName;	

	/**
	 * 商品英文名称
	 */
	private String englishName;	

	/**
	 * 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 */
	private Long commodityProductUnitId;	

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
	 * 当月累计销售数量
	 * @return 当月累计销售数量
	 */
	public Long getSalesVolume() {
		return salesVolume;
	}

	/**
	 * 当月累计销售数量
	 * @param salesVolume 当月累计销售数量
	 */
	public void setSalesVolume(Long salesVolume) {
		this.salesVolume = salesVolume;
	}
	/**
	 * 销售月分
	 * @return 销售月分
	 */
	public String getSalesDate() {
		return salesDate;
	}

	/**
	 * 销售月分
	 * @param salesDate 销售月分
	 */
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
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
	 * 商品中文名称
	 * @return 商品中文名称
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * 商品中文名称
	 * @param chineseName 商品中文名称
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	/**
	 * 商品英文名称
	 * @return 商品英文名称
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * 商品英文名称
	 * @param englishName 商品英文名称
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	/**
	 * 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 * @return 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 */
	public Long getCommodityProductUnitId() {
		return commodityProductUnitId;
	}

	/**
	 * 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 * @param commodityProductUnitId 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 */
	public void setCommodityProductUnitId(Long commodityProductUnitId) {
		this.commodityProductUnitId = commodityProductUnitId;
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
	