package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MerchantProdAttName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1437701957015185584L;

	private Long mode;
	
	private List<AttName> attNameList = new ArrayList<AttName>();

	public Long getMode() {
		return mode;
	}

	public void setMode(Long mode) {
		this.mode = mode;
	}

	public List<AttName> getAttNameList() {
		return attNameList;
	}

	public void setAttNameList(List<AttName> attNameList) {
		this.attNameList = attNameList;
	}
	
}
