package com.egeo.components.promotion.condition;

import java.util.Date;

import com.egeo.components.promotion.po.ActivityMerchantProdPO;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class ActivityMerchantProdCondition extends ActivityMerchantProdPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动开始时间
	 */
	private Date startTime;	

	/**
	 * 活动结束时间
	 */
	private Date finishTime;	
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	
	 

}
	