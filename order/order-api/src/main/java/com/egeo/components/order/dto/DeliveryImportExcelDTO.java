package com.egeo.components.order.dto;

import java.io.Serializable;

public class DeliveryImportExcelDTO implements Serializable{

	private static final long serialVersionUID = 1938161103908714568L;

	private Long soId;

	private String orderCode;

	private Long soChildId;

	private String childCode;

	private Long deliveryCompanyId;

	private String deliveryCompany;

	private String deliveryCode;

	private Long boxCode;
	
	private Long receiverAddressId;
	
	/**
	 * 订单用户id
	 */
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReceiverAddressId() {
		return receiverAddressId;
	}

	public void setReceiverAddressId(Long receiverAddressId) {
		this.receiverAddressId = receiverAddressId;
	}

	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	public Long getSoChildId() {
		return soChildId;
	}

	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}

	public Long getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	public void setDeliveryCompanyId(Long deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public String getDeliveryCompany() {
		return deliveryCompany;
	}

	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public Long getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(Long boxCode) {
		this.boxCode = boxCode;
	}
}
