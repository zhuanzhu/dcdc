package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-18 18:40:39
 */
public class LocalParamDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 名称
	 */
	private String name;	

	/**
	 * 类型 1:健步走首页 2:阅读会首页 3:给你点赞首页 4:闲置交换首页
	 */
	private Integer type;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
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
	 * 类型 1:健步走首页 2:阅读会首页 3:给你点赞首页 4:闲置交换首页
	 * @return 类型 1:健步走首页 2:阅读会首页 3:给你点赞首页 4:闲置交换首页
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 1:健步走首页 2:阅读会首页 3:给你点赞首页 4:闲置交换首页
	 * @param type 类型 1:健步走首页 2:阅读会首页 3:给你点赞首页 4:闲置交换首页
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	