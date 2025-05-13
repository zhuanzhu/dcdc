package com.egeo.components.order.condition;

import com.egeo.components.order.po.SoItemPO;

/**
 * 
 * @author jiang
 * @date 2018-01-29 09:59:04
 */
public class SoItemCondition extends SoItemPO {
	private static final long serialVersionUID = 1L;
	/**
	 * skuId
	 */
	private Long skuId;
	
	/**
	 * 订单编号
	 */
	private String orderCode;
	
	
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}
	