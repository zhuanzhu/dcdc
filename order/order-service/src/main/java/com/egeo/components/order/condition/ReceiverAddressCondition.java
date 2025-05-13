package com.egeo.components.order.condition;

import java.util.Date;

import com.egeo.components.order.po.ReceiverAddressPO;

/**
 * 
 * @author min
 * @date 2017-09-14 11:26:49
 */
public class ReceiverAddressCondition extends ReceiverAddressPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 子订单的id
	 */
	private Long soChildId;
	/**配送日期
	 */
	private Date deliveryDate;
	public Long getSoChildId() {
		return soChildId;
	}
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
}
	