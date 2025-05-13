package com.egeo.components.order.dto;

import com.egeo.dto.HttpServletRequestDTO;

import java.io.Serializable;
import java.util.List;

public class CancelAndRefundOrderExtendsWithTxDTO implements Serializable {
	private String userName;
	private SoDTO order;
	private  List<SoItemDTO> items;
	private  Long userId;
	private  String soRefundCodeByFubi;
	private String soRefundCodeByCash;
	private String soRefundCodeByJiDian;
	private  HttpServletRequestDTO req;

	private String refundAmount;

	private String reason;

	private Boolean isAutoRefundCash;

	private String thirdRefundCode;

	private String soRefundCodeByBuyCard;

	public CancelAndRefundOrderExtendsWithTxDTO() {
	}

	public CancelAndRefundOrderExtendsWithTxDTO(List<SoItemDTO> items, String reason) {
		this.items = items;
		this.reason = reason;
	}



	public CancelAndRefundOrderExtendsWithTxDTO(SoDTO order, List<SoItemDTO> items, HttpServletRequestDTO req, String refundAmount, String reason) {
		this.order = order;
		this.items = items;
		this.req = req;
		this.refundAmount = refundAmount;
		this.reason = reason;
	}

	public CancelAndRefundOrderExtendsWithTxDTO(String userName, SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi, String soRefundCodeByCash, String soRefundCodeByJiDian, HttpServletRequestDTO req, String refundAmount, String reason) {
		this.userName = userName;
		this.order = order;
		this.items = items;
		this.userId = userId;
		this.soRefundCodeByFubi = soRefundCodeByFubi;
		this.soRefundCodeByCash = soRefundCodeByCash;
		this.soRefundCodeByJiDian = soRefundCodeByJiDian;
		this.req = req;
		this.refundAmount = refundAmount;
		this.reason = reason;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SoDTO getOrder() {
		return order;
	}

	public void setOrder(SoDTO order) {
		this.order = order;
	}

	public List<SoItemDTO> getItems() {
		return items;
	}

	public void setItems(List<SoItemDTO> items) {
		this.items = items;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSoRefundCodeByFubi() {
		return soRefundCodeByFubi;
	}

	public void setSoRefundCodeByFubi(String soRefundCodeByFubi) {
		this.soRefundCodeByFubi = soRefundCodeByFubi;
	}

	public String getSoRefundCodeByCash() {
		return soRefundCodeByCash;
	}

	public void setSoRefundCodeByCash(String soRefundCodeByCash) {
		this.soRefundCodeByCash = soRefundCodeByCash;
	}

	public HttpServletRequestDTO getReq() {
		return req;
	}

	public void setReq(HttpServletRequestDTO req) {
		this.req = req;
	}

	public String getSoRefundCodeByJiDian() {
		return soRefundCodeByJiDian;
	}

	public void setSoRefundCodeByJiDian(String soRefundCodeByJiDian) {
		this.soRefundCodeByJiDian = soRefundCodeByJiDian;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getAutoRefundCash() {
		return isAutoRefundCash;
	}

	public void setAutoRefundCash(Boolean autoRefundCash) {
		isAutoRefundCash = autoRefundCash;
	}

	public String getThirdRefundCode() {
		return thirdRefundCode;
	}

	public void setThirdRefundCode(String thirdRefundCode) {
		this.thirdRefundCode = thirdRefundCode;
	}

	public String getSoRefundCodeByBuyCard() {
		return soRefundCodeByBuyCard;
	}

	public void setSoRefundCodeByBuyCard(String soRefundCodeByBuyCard) {
		this.soRefundCodeByBuyCard = soRefundCodeByBuyCard;
	}
}
