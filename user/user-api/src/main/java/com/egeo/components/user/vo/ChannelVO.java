package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-02-04 16:05:49
 */
public class ChannelVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 渠道名称
	 */
	private String name;
	/**
	 * 渠道类型：0、安卓 1、ios 2、微信端
	 */
	private Integer type;
	/**
	 * 渠道短码
	 */
	private String shortCode;
	/**
	 * 渠道链接
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
	 * 渠道活动名称
	 */
	private String channelActivityName;

	public String getChannelActivityName() {
		return channelActivityName;
	}

	public void setChannelActivityName(String channelActivityName) {
		this.channelActivityName = channelActivityName;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 主键,其他渠道id约定为1
	 * @param id 主键,其他渠道id约定为1
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 渠道名称
	 * @return 渠道名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 渠道名称
	 * @param name 渠道名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 渠道类型：0、安卓 1、ios 2、微信端
	 * @return 渠道类型：0、安卓 1、ios 2、微信端
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 渠道类型：0、安卓 1、ios 2、微信端
	 * @param type 渠道类型：0、安卓 1、ios 2、微信端
	 */
	public void setType(Integer type) {
		this.type = type;
	}	
	/**
	 * 渠道短码
	 * @return 渠道短码
	 */
	public String getShortCode() {
		return shortCode;
	}

	/**
	 * 渠道短码
	 * @param shortCode 渠道短码
	 */
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}	
	/**
	 * 渠道链接
	 * @return 渠道链接
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 渠道链接
	 * @param path 渠道链接
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
	