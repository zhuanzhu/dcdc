package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsCfgValuePO {


	private Long id;

	/**
	 * 配置值代码
	 */
	private String code;	

	/**
	 * 配置值名称
	 */
	private String name;	

	/**
	 * 
	 */
	private Long cfgKeyId;	

	/**
	 * 排序值
	 */
	private Integer sort;	

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
	 * 配置值代码
	 * @return 配置值代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 配置值代码
	 * @param code 配置值代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 配置值名称
	 * @return 配置值名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 配置值名称
	 * @param name 配置值名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getCfgKeyId() {
		return cfgKeyId;
	}

	/**
	 * 
	 * @param cfgKeyId 
	 */
	public void setCfgKeyId(Long cfgKeyId) {
		this.cfgKeyId = cfgKeyId;
	}

	/**
	 * 排序值
	 * @return 排序值
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序值
	 * @param sort 排序值
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
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
	