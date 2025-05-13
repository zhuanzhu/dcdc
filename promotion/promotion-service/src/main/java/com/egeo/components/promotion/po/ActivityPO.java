package com.egeo.components.promotion.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class ActivityPO {


	private Long id;

	/**
	 * 活动名称
	 */
	private String name;	

	/**
	 * 
	 */
	private Integer sortValue;	

	/**
	 * 活动开始时间
	 */
	private Date startTime;	

	/**
	 * 活动结束时间
	 */
	private Date finishTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	
	private Date time;

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
	 * 活动名称
	 * @return 活动名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 活动名称
	 * @param name 活动名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return 
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 
	 * @param sortValue 
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	/**
	 * 活动开始时间
	 * @return 活动开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 活动开始时间
	 * @param startTime 活动开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 活动结束时间
	 * @return 活动结束时间
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * 活动结束时间
	 * @param finishTime 活动结束时间
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
	