package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:48
 */
public class InstDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 实例名
	 */
	private String name;	

	/**
	 * 实例上边距
	 */
	private Integer instMargin;	

	/**
	 * 组件id
	 */
	private Long elementId;	

	/**
	 * 配置类型 0:轮播图 1:商品列表 2:icon(商城)无标题 3:icon(商城)有标题 4:图文组件无标题 5:图文组建有标题 6:icon(应用)
	 */
	private Integer configType;	

	/**
	 * 分页tab的id
	 */
	private Long pageTabId;

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 创建时间
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
	 * 实例名
	 * @return 实例名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 实例名
	 * @param name 实例名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 实例上边距
	 * @return 实例上边距
	 */
	public Integer getInstMargin() {
		return instMargin;
	}

	/**
	 * 实例上边距
	 * @param instMargin 实例上边距
	 */
	public void setInstMargin(Integer instMargin) {
		this.instMargin = instMargin;
	}
	/**
	 * 组件id
	 * @return 组件id
	 */
	public Long getElementId() {
		return elementId;
	}

	/**
	 * 组件id
	 * @param elementId 组件id
	 */
	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}
	/**
	 * 配置类型 0:轮播图 1:商品列表 2:icon(商城)无标题 3:icon(商城)有标题 4:图文组件无标题 5:图文组建有标题 6:icon(应用)
	 * @return 配置类型 0:轮播图 1:商品列表 2:icon(商城)无标题 3:icon(商城)有标题 4:图文组件无标题 5:图文组建有标题 6:icon(应用)
	 */
	public Integer getConfigType() {
		return configType;
	}

	/**
	 * 配置类型 0:轮播图 1:商品列表 2:icon(商城)无标题 3:icon(商城)有标题 4:图文组件无标题 5:图文组建有标题 6:icon(应用)
	 * @param configType 配置类型 0:轮播图 1:商品列表 2:icon(商城)无标题 3:icon(商城)有标题 4:图文组件无标题 5:图文组建有标题 6:icon(应用)
	 */
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 创建时间
	 * @param updateTime 创建时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getPageTabId() {
		if (pageTabId.equals(-1L))
			return null;
		
		return pageTabId;
	}

	public void setPageTabId(Long pageTabId) {
		this.pageTabId = pageTabId;
	}
}
	