package com.egeo.components.pay.vo;

/**
 * 支付宝支付调用结果类
 * @author graci
 *
 */
public class AlipaySignResultVO {

	private String outTradeNo;
	
	private String tradeNo;
	
	private double totalAmout;
	
	private String sellerId;

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public double getTotalAmout() {
		return totalAmout;
	}

	public void setTotalAmout(double totalAmout) {
		this.totalAmout = totalAmout;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	
}
