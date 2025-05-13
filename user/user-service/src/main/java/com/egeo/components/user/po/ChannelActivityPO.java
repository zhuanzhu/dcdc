package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-09-06 17:27:54
 */
public class ChannelActivityPO {


	private Long id;

	/**
	 * 活动名称
	 */
	private String name;	

	/**
	 * 活动短码
	 */
	private String shortCode;	

	/**
	 * 
	 */
	private String path;	

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
	 * 渠道id
	 */
	private Long channelId;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

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
	 * 活动名称
	 * @return 活动名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 活动名称
	 * @param name 活动名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 活动短码
	 * @return 活动短码
	 */
	public String getShortCode() {
		return shortCode;
	}

	/**
	 * 活动短码
	 * @param shortCode 活动短码
	 */
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	/**
	 * 
	 * @return 
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 
	 * @param path 
	 */
	public void setPath(String path) {
		this.path = path;
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
	