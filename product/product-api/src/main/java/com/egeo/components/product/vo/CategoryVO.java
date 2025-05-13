package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:07
 */
public class CategoryVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 类目编号
	 */
	private String serialNumber;
	/**
	 * 
	 */
	private String name;		 
	
	//前端传入id数组，包括该节点所有的父节点
	
	private String pids;

	private String categoryLable;	
	
	private Object listSort;
	
	/**
	 * 
	 */
	private String description;		 
	/**
	 * 平台id
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

	//产品属性
	private List<AttributeNameVO> productAttName;
	
	//商品规格属性
	private List<AttributeNameVO> merchantProductAttName;
	
	//参数属性集合
	private List<AttributeNameVO> parameterAttNameList;

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
	public String getCategoryLable() {
		return categoryLable;
	}

	/**
	 * 
	 * @param categoryLable 
	 */
	public void setCategoryLable(String categoryLable) {
		this.categoryLable = categoryLable;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description 
	 */
	public void setDescription(String description) {
		this.description = description;
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

	public String getPids() {
		return pids;
}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public List<AttributeNameVO> getProductAttName() {
		return productAttName;
	}

	public void setProductAttName(List<AttributeNameVO> productAttName) {
		this.productAttName = productAttName;
	}

	public List<AttributeNameVO> getMerchantProductAttName() {
		return merchantProductAttName;
	}

	public void setMerchantProductAttName(List<AttributeNameVO> merchantProductAttName) {
		this.merchantProductAttName = merchantProductAttName;
	}

	public Object getListSort() {
		return listSort;
	}

	public void setListSort(Object listSort) {
		this.listSort = listSort;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public List<AttributeNameVO> getParameterAttNameList() {
		return parameterAttNameList;
	}

	public void setParameterAttNameList(List<AttributeNameVO> parameterAttNameList) {
		this.parameterAttNameList = parameterAttNameList;
	}
	
	
	


}
	