package com.egeo.components.user.vo;

public class InsertOrderPayStatusInfoAndSendVO {
	private Long infoTemplateId;
	private String orderCode;
	private Integer orderPayStatus;
	private Long userId;
	public Long getInfoTemplateId() {
		return infoTemplateId;
	}
	public void setInfoTemplateId(Long infoTemplateId) {
		this.infoTemplateId = infoTemplateId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getOrderPayStatus() {
		return orderPayStatus;
	}
	public void setOrderPayStatus(Integer orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
