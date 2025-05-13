package com.egeo.components.order.dto;

import java.util.Map;

public class DeliverOrderWithTxDTO {
	private SoChildDTO soChild;
	private SoPackageDTO soPackage;
	private Map<String,Integer> deliverMap;
	public DeliverOrderWithTxDTO(SoChildDTO soChild, SoPackageDTO soPackage, Map<String, Integer> deliverMap) {
		this.soChild = soChild;
		this.soPackage = soPackage;
		this.deliverMap = deliverMap;
	}
	public SoChildDTO getSoChild() {
		return soChild;
	}
	public void setSoChild(SoChildDTO soChild) {
		this.soChild = soChild;
	}
	public SoPackageDTO getSoPackage() {
		return soPackage;
	}
	public void setSoPackage(SoPackageDTO soPackage) {
		this.soPackage = soPackage;
	}
	public Map<String, Integer> getDeliverMap() {
		return deliverMap;
	}
	public void setDeliverMap(Map<String, Integer> deliverMap) {
		this.deliverMap = deliverMap;
	}
}
