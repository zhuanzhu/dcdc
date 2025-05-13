package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author tan
 * @date 2019-01-04 17:04:48
 */
public class LinkableParamPO {


	private Long id;

	/**
	 * 跳转Id
	 */
	private Long linkButtonId;	

	/**
	 * 参数名称
	 */
	private String name;	

	/**
	 * 参数值
	 */
	private String value;	

	/**
	 * 备注
	 */
	private String remark;	

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
	 * 跳转Id
	 * @return 跳转Id
	 */
	public Long getLinkButtonId() {
		return linkButtonId;
	}

	/**
	 * 跳转Id
	 * @param linkButtonId 跳转Id
	 */
	public void setLinkButtonId(Long linkButtonId) {
		this.linkButtonId = linkButtonId;
	}

	/**
	 * 参数名称
	 * @return 参数名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 参数名称
	 * @param name 参数名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 参数值
	 * @return 参数值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 参数值
	 * @param value 参数值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	