package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryAttNameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 分组id:默认为 -1 未分组,0分组
	 */
	private Long parentId;	

	/**
	 * 
	 */
	private Long categoryId;	

	/**
	 * 
	 */
	private Long attNameId;	

	/**
	 * 
	 */
	private Integer sortValue;	

	private String name;
	
	private List<CategoryAttNameDTO> lists;

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 1、产品属性 2、商品规格属性
	 */
	private Integer type;	

	/**
	 * 是否必填：0否、1是
	 */
	private Integer isRequired;	

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
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * 
	 * @param categoryId 
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }

        public List<CategoryAttNameDTO> getLists() {
            return lists;
        }

        public void setLists(List<CategoryAttNameDTO> lists) {
            this.lists = lists;
        }
	/**
	 * 1、产品属性 2、商品规格属性
	 * @return 1、产品属性 2、商品规格属性
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 1、产品属性 2、商品规格属性
	 * @param type 1、产品属性 2、商品规格属性
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 是否必填：0否、1是
	 * @return 是否必填：0否、1是
	 */
	public Integer getIsRequired() {
		return isRequired;
	}

	/**
	 * 是否必填：0否、1是
	 * @param isRequired 是否必填：0否、1是
	 */
	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
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
	