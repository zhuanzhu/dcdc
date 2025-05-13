package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

public class MerchantProdSpecification implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236758350974097199L;

	private List<AttNameValueVO> attNameValueList;
	
	private List<AttName> attNameList;
	
	private List<Stock> stockList;

	public List<AttNameValueVO> getAttNameValueList() {
		return attNameValueList;
	}

	public void setAttNameValueList(List<AttNameValueVO> attNameValueList) {
		this.attNameValueList = attNameValueList;
	}

	public List<AttName> getAttNameList() {
		return attNameList;
	}

	public void setAttNameList(List<AttName> attNameList) {
		this.attNameList = attNameList;
	}

	public List<Stock> getStockList() {
		return stockList;
	}

	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	
}
