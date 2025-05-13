package com.egeo.components.cms.po;

import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-04-17 16:46:43
 */
public class ExternalLinkPO {

	private Long id;

	/**
	 * 外部链接名称
	 */
	private String name;

	/**
	 * 外部链接url
	 */
	private String externalLinkUrl;

	/**
	 * 链接类型
	 */
	private Integer linkType;

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

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalLinkUrl() {
		return externalLinkUrl;
	}

	public void setExternalLinkUrl(String externalLinkUrl) {
		this.externalLinkUrl = externalLinkUrl;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
