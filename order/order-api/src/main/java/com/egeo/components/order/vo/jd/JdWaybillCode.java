package com.egeo.components.order.vo.jd;

/**
 * Created by 0.0 on 2019/3/25.
 */
public class JdWaybillCode {
    private String orderId;
    private String parentId;
    private String carrier;
    private String deliveryOrderId;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getDeliveryOrderId() {
		return deliveryOrderId;
	}
	public void setDeliveryOrderId(String deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

}
