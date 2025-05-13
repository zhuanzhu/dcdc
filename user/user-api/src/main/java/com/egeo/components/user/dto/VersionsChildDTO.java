package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-02-04 18:35:18
 */
public class VersionsChildDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 父版本id
	 */
	private Long versionsId;	

	/**
	 * 渠道id
	 */
	private Long channelId;	
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道链接
	 */
	private String path;	
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	
	/**
	 * 平台id
	 */
	private Long platformId;		

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
	 * 父版本id
	 * @return 父版本id
	 */
	public Long getVersionsId() {
		return versionsId;
	}

	/**
	 * 父版本id
	 * @param versionsId 父版本id
	 */
	public void setVersionsId(Long versionsId) {
		this.versionsId = versionsId;
	}
	/**
	 * 渠道id
	 * @return 渠道id
	 */
	public Long getChannelId() {
		return channelId;
	}

	/**
	 * 渠道id
	 * @param channelId 渠道id
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	
}
	