package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单详情unit列表VO
 * 
 * @author graci
 *
 */
public class UnitDetailVIEW implements Serializable {
	private static final long serialVersionUID = 1L;

	private String shortUrl;
	private String passWord;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * 子订单编号
	 */
	private String childCode;

	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 分配时间
	 */
	private String allocationTime;

	/**
	 * 商品pu编号
	 */
	private String puSn;
	/**
	 * 卡号
	 */
	private String cardNo;

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAllocationTime() {
		return allocationTime;
	}

	public void setAllocationTime(String allocationTime) {
		this.allocationTime = allocationTime;
	}

	public String getPuSn() {
		return puSn;
	}

	public void setPuSn(String puSn) {
		this.puSn = puSn;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
