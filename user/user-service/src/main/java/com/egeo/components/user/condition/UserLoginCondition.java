package com.egeo.components.user.condition;

import java.util.Date;

import com.egeo.components.user.po.UserLoginPO;

/**
 * 
 * @author xiaping
 * @date 2017-07-24 09:49:12
 */
public class UserLoginCondition extends UserLoginPO {
	private static final long serialVersionUID = 1L;
	
	private Date startTime;
	
	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
	