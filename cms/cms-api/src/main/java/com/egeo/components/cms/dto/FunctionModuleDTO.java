package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-26 14:56:07
 */
public class FunctionModuleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 功能名称
	 */
	private String functionName;	

	/**
	 * 功能类型：1、Native 2、H5
	 */
	private Integer type;	

	/**
	 * 链接路径
	 */
	private String path;	

	/**
	 * 图片路径
	 */
	private String pictureUrl;	

	/**
	 * 排序
	 */
	private Integer sortValue;	

	/**
	 * 是否显示：0、否 1、是
	 */
	private Integer isShow;	

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
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 功能名称
	 * @return 功能名称
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * 功能名称
	 * @param functionName 功能名称
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	/**
	 * 功能类型：1、Native 2、H5
	 * @return 功能类型：1、Native 2、H5
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 功能类型：1、Native 2、H5
	 * @param type 功能类型：1、Native 2、H5
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 链接路径
	 * @return 链接路径
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 链接路径
	 * @param path 链接路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 图片路径
	 * @return 图片路径
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * 图片路径
	 * @param pictureUrl 图片路径
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
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
	 * 是否显示：0、否 1、是
	 * @return 是否显示：0、否 1、是
	 */
	public Integer getIsShow() {
		return isShow;
	}

	/**
	 * 是否显示：0、否 1、是
	 * @param isShow 是否显示：0、否 1、是
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
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
	