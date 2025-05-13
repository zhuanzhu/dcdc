package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-11-09 15:50:11
 */
public class PointsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 积分
	 */
	private BigDecimal points;	

	/**
	 * 操作描述
	 */
	private String desc;	

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
	 * 唯一主键
	 * @param id 唯一主键
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
	 * 积分
	 * @return 积分
	 */
	public BigDecimal getPoints() {
		return points;
	}

	/**
	 * 积分
	 * @param points 积分
	 */
	public void setPoints(BigDecimal points) {
		this.points = points;
	}
	/**
	 * 操作描述
	 * @return 操作描述
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 操作描述
	 * @param desc 操作描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
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
	