package com.egeo.components.user.condition;

import com.egeo.components.user.po.ChannelActivityPO;

/**
 * 
 * @author min
 * @date 2018-09-06 17:27:54
 */
public class ChannelActivityCondition extends ChannelActivityPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 渠道类型：0、安卓 1、ios 2、微信端
	 */
	private Integer type;	
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道短码
	 */
	private String channelShortCode;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelShortCode() {
		return channelShortCode;
	}
	public void setChannelShortCode(String channelShortCode) {
		this.channelShortCode = channelShortCode;
	}	
	
}
	