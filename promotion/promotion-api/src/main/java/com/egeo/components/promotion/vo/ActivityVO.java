package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:02:50
 */
public class ActivityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 活动名称
	 */

	private String name;		 
	
	/**
	 * 活动排序
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

	public Integer getSortValue() {
		return sortValue;
	}

	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

}
	