package com.egeo.components.order.vo;

import java.util.List;

public class SoDetailPackageVo {

	private Long id;
	private String deliveryCompanyName;
	private String deliveryExpressNbr;
	private List<String> itemNames;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}
	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}
	public String getDeliveryExpressNbr() {
		return deliveryExpressNbr;
	}
	public void setDeliveryExpressNbr(String deliveryExpressNbr) {
		this.deliveryExpressNbr = deliveryExpressNbr;
	}
	public List<String> getItemNames() {
		return itemNames;
	}
	public void setItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
	}
	
	
}
