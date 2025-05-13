package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-11-13 10:32:47
 */
public class FunctionTreeNodeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 父级编号 顶级为-1
	 */
	private Long parentId;
	/**
	 * 功能模块名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sortValue;
	/**
	 * 是否叶子节点 0-否 1-是
	 */
	private Integer isLeaf;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 最后操作用户ID
	 */
	private Long updateUserId;
	/**
	 * 最后操作用户名称
	 */
	private String updateUserName;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 父级编号 顶级为-1
	 * @return 父级编号 顶级为-1
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 父级编号 顶级为-1
	 * @param parentId 父级编号 顶级为-1
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	
	/**
	 * 功能模块名称
	 * @return 功能模块名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 功能模块名称
	 * @param name 功能模块名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}	
	/**
	 * 是否叶子节点 0-否 1-是
	 * @return 是否叶子节点 0-否 1-是
	 */
	public Integer getIsLeaf() {
		return isLeaf;
	}

	/**
	 * 是否叶子节点 0-否 1-是
	 * @param isLeaf 是否叶子节点 0-否 1-是
	 */
	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
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
	 * 最后操作用户ID
	 * @return 最后操作用户ID
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 最后操作用户ID
	 * @param updateUserId 最后操作用户ID
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}	
	/**
	 * 最后操作用户名称
	 * @return 最后操作用户名称
	 */
	public String getUpdateUserName() {
		return updateUserName;
	}

	/**
	 * 最后操作用户名称
	 * @param updateUserName 最后操作用户名称
	 */
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
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
	