package com.egeo.components.order.vo;

/**
 * 简单卡券VO
 * 用于客户端订单详情的卡密展示
 * @author graci
 *
 */
public class SimpleECardVO {

	private String cardNumber;
	
	private String cardThick;
	private String shortUrl;
	private String endTime;

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardThick() {
		return cardThick;
	}

	public void setCardThick(String cardThick) {
		this.cardThick = cardThick;
	}
	
	
}
