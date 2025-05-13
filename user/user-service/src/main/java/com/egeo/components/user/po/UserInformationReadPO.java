package com.egeo.components.user.po;



/**
 * 
 * @author min
 * @date 2018-02-02 14:37:02
 */
public class UserInformationReadPO {


	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 消息id
	 */
	private Long userInformationId;	

	/**
	 * 是否逻辑删除 0、否 1、是
	 */
	private Integer isDeleted = 0;

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

	/**
	 * 是否逻辑删除 0、否 1、是
	 * @return 是否逻辑删除 0、否 1、是
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * 是否逻辑删除 0、否 1、是
	 * @param isDeleted 是否逻辑删除 0、否 1、是
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
}
	