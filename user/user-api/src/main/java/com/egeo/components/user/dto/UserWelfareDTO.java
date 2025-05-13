package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-11-15 18:32:41
 */
public class UserWelfareDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long userId;
	private Long departmentId;
	private Date entryTime;
	private Integer praiseDayCount;
	private Integer praiseMonthCount;
	private Date lastPraiseTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getPraiseDayCount() {
		return praiseDayCount;
	}
	public void setPraiseDayCount(Integer praiseDayCount) {
		this.praiseDayCount = praiseDayCount;
	}
	public Integer getPraiseMonthCount() {
		return praiseMonthCount;
	}
	public void setPraiseMonthCount(Integer praiseMonthCount) {
		this.praiseMonthCount = praiseMonthCount;
	}
	public Date getLastPraiseTime() {
		return lastPraiseTime;
	}
	public void setLastPraiseTime(Date lastPraiseTime) {
		this.lastPraiseTime = lastPraiseTime;
	}
	
	
}
	