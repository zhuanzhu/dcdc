package com.egeo.components.user.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-02-02 14:37:02
 */
public class UserInformationReadVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 消息id
	 */
	private Long userInformationId;

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
	public Long getUserInformationId() {
		return userInformationId;
	}

	/**
	 * 消息id
	 * @param userInformationId 消息id
	 */
	public void setUserInformationId(Long userInformationId) {
		this.userInformationId = userInformationId;
	}	
}
	