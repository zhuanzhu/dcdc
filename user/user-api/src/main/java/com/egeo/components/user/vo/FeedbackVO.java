package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-22 14:41:30
 */
public class FeedbackVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */
	private String feedback;

	private String feedbackRslt;	
	private String orderCode;	
	private Integer feedStatus;	
	private Long updateUserid;	
	private String updateUsername;	
	private Date updateTime;	
	/**
	 * 手机系统版本号
	 */
	private String systemVersion;
	/**
	 * 手机型号
	 */
	private String deviceModel;
	/**
	 * 手机唯一标识符
	 */
	private String deviceId;
	/**
	 * 用户id
	 */
	private Long createUserid;
	/**
	 * 用户名称
	 */
	private String createUsername;
	/**
	 * 创建人ip
	 */
	private String createUserip;
	/**
	 * 创建人mac地址
	 */
	private String createUsermac;
	/**
	 * 客户端程序的版本号
	 */
	private String clientVersionno;
	/**
	 * 平台id
	 */
	private Long platformId;

	public String getFeedbackRslt() {
		return feedbackRslt;
	}

	public void setFeedbackRslt(String feedbackRslt) {
		this.feedbackRslt = feedbackRslt;
	}

	public Integer getFeedStatus() {
		return feedStatus;
	}

	public void setFeedStatus(Integer feedStatus) {
		this.feedStatus = feedStatus;
	}

	public Long getUpdateUserid() {
		return updateUserid;
	}

	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}

	public String getUpdateUsername() {
		return updateUsername;
	}

	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return 
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * 
	 * @param feedback 
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}	
	/**
	 * 手机系统版本号
	 * @return 手机系统版本号
	 */
	public String getSystemVersion() {
		return systemVersion;
	}

	/**
	 * 手机系统版本号
	 * @param systemVersion 手机系统版本号
	 */
	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}	
	/**
	 * 手机型号
	 * @return 手机型号
	 */
	public String getDeviceModel() {
		return deviceModel;
	}

	/**
	 * 手机型号
	 * @param deviceModel 手机型号
	 */
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}	
	/**
	 * 手机唯一标识符
	 * @return 手机唯一标识符
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 手机唯一标识符
	 * @param deviceId 手机唯一标识符
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}	
	/**
	 * 用户id
	 * @return 用户id
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 用户id
	 * @param createUserid 用户id
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}	
	/**
	 * 用户名称
	 * @return 用户名称
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 用户名称
	 * @param createUsername 用户名称
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}	
	/**
	 * 创建人ip
	 * @return 创建人ip
	 */
	public String getCreateUserip() {
		return createUserip;
	}

	/**
	 * 创建人ip
	 * @param createUserip 创建人ip
	 */
	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}	
	/**
	 * 创建人mac地址
	 * @return 创建人mac地址
	 */
	public String getCreateUsermac() {
		return createUsermac;
	}

	/**
	 * 创建人mac地址
	 * @param createUsermac 创建人mac地址
	 */
	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
	}	
	/**
	 * 客户端程序的版本号
	 * @return 客户端程序的版本号
	 */
	public String getClientVersionno() {
		return clientVersionno;
	}

	/**
	 * 客户端程序的版本号
	 * @param clientVersionno 客户端程序的版本号
	 */
	public void setClientVersionno(String clientVersionno) {
		this.clientVersionno = clientVersionno;
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
	