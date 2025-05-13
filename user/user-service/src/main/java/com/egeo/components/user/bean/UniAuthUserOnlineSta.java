package com.egeo.components.user.bean;

import java.util.Date;

public class UniAuthUserOnlineSta {

	private Integer id;
	/** 关联操作员uuid */
	private String userUuid;
	/** 统计时间 */
	private Date staDate;
	/** 登录时长（单位：秒） */
	private Integer loginDuration;
	/** 登录次数 */
	private Integer loginTimes;
	/** 登出次数 */
	private Integer logoutTimes;
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

	public Integer getLoginDuration() {
		return loginDuration;
	}

	public void setLoginDuration(Integer loginDuration) {
		this.loginDuration = loginDuration;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Integer getLogoutTimes() {
		return logoutTimes;
	}

	public void setLogoutTimes(Integer logoutTimes) {
		this.logoutTimes = logoutTimes;
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
		return "UniAuthUserOnlineSta [id=" + id + ", userUuid=" + userUuid + ", staDate=" + staDate + ", loginDuration="
				+ loginDuration + ", loginTimes=" + loginTimes + ", logoutTimes=" + logoutTimes + ", loginStatus="
				+ loginStatus + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	

}
