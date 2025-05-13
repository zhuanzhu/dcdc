package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-23 11:20:03
 */
public class AdCodeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 广告页面
	 */
	private Integer pageType;
	/**
	 * 广告代码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 上级广告代码
	 */
	private String parentCode;
	/**
	 * 级别
	 */
	private Integer level;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 平台1:PC, 2:H5
	 */
	private Integer platform;
	/**
	 * 广告位宽度
	 */
	private Integer width;
	/**
	 * 广告位高度
	 */
	private Integer height;
	/**
	 * 素材大小限制KB
	 */
	private Integer sourceSizeLimit;
	/**
	 * 创建人
	 */
	private Integer createBy;
	/**
	 * 修改人
	 */
	private Date updateBy;
	/**
	 * 版本
	 */
	private Integer version;
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
	 * 广告页面
	 * @return 广告页面
	 */
	public Integer getPageType() {
		return pageType;
	}

	/**
	 * 广告页面
	 * @param pageType 广告页面
	 */
	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}	
	/**
	 * 广告代码
	 * @return 广告代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 广告代码
	 * @param code 广告代码
	 */
	public void setCode(String code) {
		this.code = code;
	}	
	/**
	 * 名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 上级广告代码
	 * @return 上级广告代码
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * 上级广告代码
	 * @param parentCode 上级广告代码
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}	
	/**
	 * 级别
	 * @return 级别
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 级别
	 * @param level 级别
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}	
	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序
	 * @param sort 排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}	
	/**
	 * 平台1:PC, 2:H5
	 * @return 平台1:PC, 2:H5
	 */
	public Integer getPlatform() {
		return platform;
	}

	/**
	 * 平台1:PC, 2:H5
	 * @param platform 平台1:PC, 2:H5
	 */
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}	
	/**
	 * 广告位宽度
	 * @return 广告位宽度
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * 广告位宽度
	 * @param width 广告位宽度
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}	
	/**
	 * 广告位高度
	 * @return 广告位高度
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * 广告位高度
	 * @param height 广告位高度
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}	
	/**
	 * 素材大小限制KB
	 * @return 素材大小限制KB
	 */
	public Integer getSourceSizeLimit() {
		return sourceSizeLimit;
	}

	/**
	 * 素材大小限制KB
	 * @param sourceSizeLimit 素材大小限制KB
	 */
	public void setSourceSizeLimit(Integer sourceSizeLimit) {
		this.sourceSizeLimit = sourceSizeLimit;
	}	
	/**
	 * 创建人
	 * @return 创建人
	 */
	public Integer getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人
	 * @param createBy 创建人
	 */
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}	
	/**
	 * 修改人
	 * @return 修改人
	 */
	public Date getUpdateBy() {
		return updateBy;
	}

	/**
	 * 修改人
	 * @param updateBy 修改人
	 */
	public void setUpdateBy(Date updateBy) {
		this.updateBy = updateBy;
	}	
	/**
	 * 版本
	 * @return 版本
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * 版本
	 * @param version 版本
	 */
	public void setVersion(Integer version) {
		this.version = version;
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
	