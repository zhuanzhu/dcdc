package com.egeo.components.pay.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 标准订单查询结果VO
 * @author graci
 *
 */
public class StandardOrderQueryResultVO {
	
	private Long soId;
	
	private String orderCode;
	
	/**
	 * 现金应付款金额
	 */
	private BigDecimal needPayCash;
	
	private List<SoItemVO> items;
	// 订单支付状态 0:未支付、1:已支付、2:已退款
	private Integer orderPayStatus;

	public Integer getOrderPayStatus() {
		return orderPayStatus;
	}

	public void setOrderPayStatus(Integer orderPayStatus) {
		this.orderPayStatus = orderPayStatus;
	}

	public BigDecimal getNeedPayCash() {
		return needPayCash;
	}

	public void setNeedPayCash(BigDecimal needPayCash) {
		this.needPayCash = needPayCash;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	public List<SoItemVO> getItems() {
		return items;
	}

	public void setItems(List<SoItemVO> items) {
		this.items = items;
	}
	
	
}
