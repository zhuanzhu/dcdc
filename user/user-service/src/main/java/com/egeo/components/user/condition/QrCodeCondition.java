package com.egeo.components.user.condition;

import com.egeo.components.user.po.QrCodePO;

/**
 * 
 * @author xia
 * @date 2018-09-13 17:09:31
 */
public class QrCodeCondition extends QrCodePO {
	private static final long serialVersionUID = 1L;
	private String shortCode;
	private String activityName;



	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	private String channelName;


}
	