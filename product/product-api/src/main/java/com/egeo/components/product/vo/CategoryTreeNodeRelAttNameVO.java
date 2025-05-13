package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:07
 */
public class CategoryTreeNodeRelAttNameVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 分组id:默认为 -1 未分组,0分组
	 */

	private Long parentId;		 
	/**
	 * 
	 */

	private Long cateTreeNodeRelId;		 
	/**
	 * 
	 */

	private Long attNameId;		 
	/**
	 * 
	 */

	private Integer sortValue;		 
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
	 * 分组id:默认为 -1 未分组,0分组
	 * @return 分组id:默认为 -1 未分组,0分组
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 分组id:默认为 -1 未分组,0分组
	 * @param parentId 分组id:默认为 -1 未分组,0分组
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getCateTreeNodeRelId() {
		return cateTreeNodeRelId;
	}

	/**
	 * 
	 * @param cateTreeNodeRelId 
	 */
	public void setCateTreeNodeRelId(Long cateTreeNodeRelId) {
		this.cateTreeNodeRelId = cateTreeNodeRelId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getAttNameId() {
		return attNameId;
	}

	/**
	 * 
	 * @param attNameId 
	 */
	public void setAttNameId(Long attNameId) {
		this.attNameId = attNameId;
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
}
	