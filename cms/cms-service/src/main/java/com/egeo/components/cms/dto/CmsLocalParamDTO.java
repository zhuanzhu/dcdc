package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mingqiang
 * @date 2019-01-10 11:11:52
 */
public class CmsLocalParamDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 跳转页name
	 */
	private String name;	

	/**
	 * 跳转页code
	 */
	private String code;	

	/**
	 * 平台Id
	 */
	private Long platformId;	

	/**
	 * 类型 1：页面跳转参数 2：页面操作参数
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
	 * 跳转页name
	 * @return 跳转页name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 跳转页name
	 * @param name 跳转页name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 跳转页code
	 * @return 跳转页code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 跳转页code
	 * @param code 跳转页code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 平台Id
	 * @return 平台Id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台Id
	 * @param platformId 平台Id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	/**
	 * 类型 1：页面跳转参数 2：页面操作参数
	 * @return 类型 1：页面跳转参数 2：页面操作参数
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 1：页面跳转参数 2：页面操作参数
	 * @param type 类型 1：页面跳转参数 2：页面操作参数
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
	