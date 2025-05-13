package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class TagDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 标签名称
	 */
	private String name;	

	/**
	 * 标签类型：1、商品标签 2、节点标签 3、活动标签
	 */
	private Integer type;	

	/**
	 * 是否启用：0否1是
	 */
	private Integer isValid;	

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
	 * 标签类型集合：1、商品标签 2、节点标签 3、活动标签
	 */
	private List<Integer> typeList;

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
	 * 标签名称
	 * @return 标签名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 标签名称
	 * @param name 标签名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 标签类型：1、商品标签 2、节点标签 3、活动标签
	 * @return 标签类型：1、商品标签 2、节点标签 3、活动标签
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 标签类型：1、商品标签 2、节点标签 3、活动标签
	 * @param type 标签类型：1、商品标签 2、节点标签 3、活动标签
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 是否启用：0否1是
	 * @return 是否启用：0否1是
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * 是否启用：0否1是
	 * @param isValid 是否启用：0否1是
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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

	public List<Integer> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Integer> typeList) {
		this.typeList = typeList;
	}
}
	