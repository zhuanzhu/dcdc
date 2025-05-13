package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:08
 */
public class ProductCateTreeNodeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */

	private Long productId;		 
	/**
	 * 
	 */

	private Long cateTreeNodeId;		 
	/**
	 * 
	 */

	private Long sortValue;		 
	/**
	 * 
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
	public Long getProductId() {
		return productId;
	}

	/**
	 * 
	 * @param productId 
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getCateTreeNodeId() {
		return cateTreeNodeId;
	}

	/**
	 * 
	 * @param cateTreeNodeId 
	 */
	public void setCateTreeNodeId(Long cateTreeNodeId) {
		this.cateTreeNodeId = cateTreeNodeId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getSortValue() {
		return sortValue;
	}

	/**
	 * 
	 * @param sortValue 
	 */
	public void setSortValue(Long sortValue) {
		this.sortValue = sortValue;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 
	 * @param platformId 
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
	