package com.egeo.components.user.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-08-15 10:44:50
 */
public class UserInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 消息id
	 */
	private Long infoId;	

	/**
	 * 是否阅读：0否1是
	 */
	private Integer isRead;	


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
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 消息id
	 * @return 消息id
	 */
	public Long getInfoId() {
		return infoId;
	}

	/**
	 * 消息id
	 * @param infoId 消息id
	 */
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}
	/**
	 * 是否阅读：0否1是
	 * @return 是否阅读：0否1是
	 */
	public Integer getIsRead() {
		return isRead;
	}

	/**
	 * 是否阅读：0否1是
	 * @param isRead 是否阅读：0否1是
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
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
	