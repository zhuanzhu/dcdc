package com.egeo.components.finance.po;

import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-06 15:24:26
 */
public class AdjustReasonPO {

	private Long id;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 原因内容
	 */
	private String name;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private Integer disabled;
	/**
	 * 平台id
	 */
	private Long platformId;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 类型
	 * 
	 * @return 类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 原因内容
	 * 
	 * @return 原因内容
	 */
	public String getName() {
		return name;
	}

	/**
	 * 原因内容
	 * 
	 * @param name
	 *            原因内容
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 创建时间
	 * 
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
