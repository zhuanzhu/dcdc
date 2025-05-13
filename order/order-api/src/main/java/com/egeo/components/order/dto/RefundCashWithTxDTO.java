package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class RefundCashWithTxDTO implements Serializable {

	private Long userId;
	private SoDTO order;
	private  BigDecimal refundCashAmount;
	private List<SoItemDTO> items;
	private  String soRefundCodeByCashCode;

	public RefundCashWithTxDTO() {
	}


	public RefundCashWithTxDTO(Long userId, SoDTO order, BigDecimal refundCashAmount, List<SoItemDTO> items, String soRefundCodeByCashCode) {
		this.userId = userId;
		this.order = order;
		this.refundCashAmount = refundCashAmount;
		this.items = items;
		this.soRefundCodeByCashCode = soRefundCodeByCashCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public SoDTO getOrder() {
		return order;
	}

	public void setOrder(SoDTO order) {
		this.order = order;
	}

	public BigDecimal getRefundCashAmount() {
		return refundCashAmount;
	}

	public void setRefundCashAmount(BigDecimal refundCashAmount) {
		this.refundCashAmount = refundCashAmount;
	}

	public List<SoItemDTO> getItems() {
		return items;
	}

	public void setItems(List<SoItemDTO> items) {
		this.items = items;
	}

	public String getSoRefundCodeByCashCode() {
		return soRefundCodeByCashCode;
	}

	public void setSoRefundCodeByCashCode(String soRefundCodeByCashCode) {
		this.soRefundCodeByCashCode = soRefundCodeByCashCode;
	}
}
