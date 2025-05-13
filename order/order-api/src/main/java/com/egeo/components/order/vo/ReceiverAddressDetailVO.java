package com.egeo.components.order.vo;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class ReceiverAddressDetailVO extends ReceiverAddressVO {

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
