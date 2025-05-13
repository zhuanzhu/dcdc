package com.egeo.components.user.fo;

import java.util.List;

public class UpdateQrCodeByCouponUnitIdsFO {
	private List<Long> couponUnitIds; 
	private Long channelActivityId; 
	private Long channelId;
	public UpdateQrCodeByCouponUnitIdsFO(List<Long> couponUnitIds, Long channelActivityId, Long channelId) {
		this.couponUnitIds = couponUnitIds;
		this.channelActivityId = channelActivityId;
		this.channelId = channelId;
	}
	public List<Long> getCouponUnitIds() {
		return couponUnitIds;
	}
	public void setCouponUnitIds(List<Long> couponUnitIds) {
		this.couponUnitIds = couponUnitIds;
	}
	public Long getChannelActivityId() {
		return channelActivityId;
	}
	public void setChannelActivityId(Long channelActivityId) {
		this.channelActivityId = channelActivityId;
	}
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
}
