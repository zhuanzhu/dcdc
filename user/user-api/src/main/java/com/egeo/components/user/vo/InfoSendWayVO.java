package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-08-15 10:44:50
 */
public class InfoSendWayVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 消息Id
	 */
	private Long infoTemplateId;
	/**
	 * 消息发送方式id
	 */
	private Long sendWayId;
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
	 * 消息Id
	 * @return 消息Id
	 */
	public Long getInfoTemplateId() {
		return infoTemplateId;
	}

	/**
	 * 消息Id
	 * @param infoTemplateId 消息Id
	 */
	public void setInfoTemplateId(Long infoTemplateId) {
		this.infoTemplateId = infoTemplateId;
	}	
	/**
	 * 消息发送方式id
	 * @return 消息发送方式id
	 */
	public Long getSendWayId() {
		return sendWayId;
	}

	/**
	 * 消息发送方式id
	 * @param sendWayId 消息发送方式id
	 */
	public void setSendWayId(Long sendWayId) {
		this.sendWayId = sendWayId;
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
	