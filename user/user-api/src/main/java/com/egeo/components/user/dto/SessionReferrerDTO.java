package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-02-04 16:22:23
 */
public class SessionReferrerDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 来源id
	 */
	private Long referrerId;	

	/**
	 * 渠道id
	 */
	private Long channelId;	

	/**
	 * 活动id
	 */
	private Long activityId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

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
	 * 来源id
	 * @return 来源id
	 */
	public Long getReferrerId() {
		return referrerId;
	}

	/**
	 * 来源id
	 * @param referrerId 来源id
	 */
	public void setReferrerId(Long referrerId) {
		this.referrerId = referrerId;
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
	 * 活动id
	 * @return 活动id
	 */
	public Long getActivityId() {
		return activityId;
	}

	/**
	 * 活动id
	 * @param activityId 活动id
	 */
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
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
}
	