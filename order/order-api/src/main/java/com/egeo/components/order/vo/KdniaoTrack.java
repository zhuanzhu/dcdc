package com.egeo.components.order.vo;

import java.io.Serializable;

public class KdniaoTrack implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4148399673402659489L;

	private String AcceptTime;
	
	private String AcceptStation;

	public String getAcceptTime() {
		return AcceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
	}

	public String getAcceptStation() {
		return AcceptStation;
	}

	public void setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
	}
	
}
