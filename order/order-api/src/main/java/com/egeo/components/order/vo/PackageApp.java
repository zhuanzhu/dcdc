package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PackageApp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 818421065740990125L;
	/**
	 * 配送公司名称
	 */
	private String deliveryCompanyName;	
	/**
	 * 物流单号
	 */
	private String deliveryExpressNbr;
	/**
	 * 物流状态
	 */
	private Integer deliveryType;
	
	private List<Map<String, Object>> soitems;
	
	private List<Steps> stepsList;

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

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public List<Map<String, Object>> getSoitems() {
		return soitems;
	}

	public void setSoitems(List<Map<String, Object>> soitems) {
		this.soitems = soitems;
	}

	public List<Steps> getStepsList() {
		return stepsList;
	}

	public void setStepsList(List<Steps> stepsList) {
		this.stepsList = stepsList;
	}


	
}
