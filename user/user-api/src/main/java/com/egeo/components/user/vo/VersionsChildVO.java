package com.egeo.components.user.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-02-04 18:35:19
 */
public class VersionsChildVO implements Serializable {
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
	