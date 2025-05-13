package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-06 09:20:44
 */
public class MerchantProdAttValuePO {


	private Long id;

	/**
	 * 
	 */
	private Long merchantProdAttNameId;	

	/**
	 * 
	 */
	private Long attValueId;	

	/**
	 * 
	 */
	private String attValueCustom;	

	/**
	 * 
	 */
	private Integer sortValue;	

	/**
	 * 商品图片路径
	 */
	private String pictureUrl;	

	/**
	 * 是否可用
	 */
	private Integer isWith;	

	/**
	 * 公司id
	 */
	private Long platformId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	public Long getMerchantProdAttNameId() {
		return merchantProdAttNameId;
	}

	/**
	 * 
	 * @param merchantProdAttNameId 
	 */
	public void setMerchantProdAttNameId(Long merchantProdAttNameId) {
		this.merchantProdAttNameId = merchantProdAttNameId;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getAttValueId() {
		return attValueId;
	}

	/**
	 * 
	 * @param attValueId 
	 */
	public void setAttValueId(Long attValueId) {
		this.attValueId = attValueId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getAttValueCustom() {
		return attValueCustom;
	}

	/**
	 * 
	 * @param attValueCustom 
	 */
	public void setAttValueCustom(String attValueCustom) {
		this.attValueCustom = attValueCustom;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 
	 * @param sortValue 
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	/**
	 * 商品图片路径
	 * @return 商品图片路径
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * 商品图片路径
	 * @param pictureUrl 商品图片路径
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * 是否可用
	 * @return 是否可用
	 */
	public Integer getIsWith() {
		return isWith;
	}

	/**
	 * 是否可用
	 * @param isWith 是否可用
	 */
	public void setIsWith(Integer isWith) {
		this.isWith = isWith;
	}

	/**
	 * 公司id
	 * @return 公司id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 公司id
	 * @param platformId 公司id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
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
}
	