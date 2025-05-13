package com.egeo.components.order.vo;

import java.io.Serializable;

public class Steps implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7249617519596930171L;

	private String acceptTime;
	
	private String remark;

	
	
	public Steps() {
		
	}
	
	public Steps(String acceptTime, String remark) {
		this.acceptTime = acceptTime;
		this.remark = remark;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
