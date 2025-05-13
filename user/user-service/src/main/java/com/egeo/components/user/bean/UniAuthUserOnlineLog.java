package com.egeo.components.user.bean;

import java.util.Date;

public class UniAuthUserOnlineLog {

	private Integer id;
	/** 关联操作员uuid */
	private String userUuid;
	/** 统计时间 */
	private Date staDate;
	/** token有效截止时间 */
	private Long tokenExpireAt;
	/** 登录时长（单位：秒） */
	private Long loginDuration;
	/** 登录时间 */
	private Long loginDate;
	/** 登出时间 */
	private Long logoutDate;
	/** 状态（0：离线；1：在线） */
	private Integer loginStatus;
	/** 创建时间 */
	private Long createDate;
	/** 更新时间 */
	private Long updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public Date getStaDate() {
		return staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	public Long getTokenExpireAt() {
		return tokenExpireAt;
	}

	public void setTokenExpireAt(Long tokenExpireAt) {
		this.tokenExpireAt = tokenExpireAt;
	}

	public Long getLoginDuration() {
		return loginDuration;
	}

	public void setLoginDuration(Long loginDuration) {
		this.loginDuration = loginDuration;
	}

	public Long getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Long loginDate) {
		this.loginDate = loginDate;
	}

	public Long getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Long logoutDate) {
		this.logoutDate = logoutDate;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Long updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "UniAuthUserOnlineLog [id=" + id + ", userUuid=" + userUuid + ", staDate=" + staDate + ", tokenExpireAt="
				+ tokenExpireAt + ", loginDuration=" + loginDuration + ", loginDate=" + loginDate + ", logoutDate="
				+ logoutDate + ", loginStatus=" + loginStatus + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}

}
