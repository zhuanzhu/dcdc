package com.egeo.components.pay.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-05-17 18:19:01
 */
public class AlipayNativeSignLogDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 签名
	 */
	private String sign;	

	/**
	 * 状态码  成功 10000
	 */
	private String code;	

	/**
	 * 状态描述
	 */
	private String msg;	

	/**
	 * 福管家订单编号
	 */
	private String outTradeNo;	

	/**
	 * 支付宝订单编号
	 */
	private String tradeNo;	

	/**
	 * 总金额
	 */
	private String totalAmount;	

	/**
	 * 支付宝商户id
	 */
	private String sellerId;	

	/**
	 * 子状态码
	 */
	private String subCode;	

	/**
	 * 子状态描述
	 */
	private String subMsg;	

	/**
	 * 
	 */
	private Date createTime;	

	/**
	 * 
	 */
	private Date updateTime;	

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 签名
	 * @return 签名
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * 签名
	 * @param sign 签名
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * 状态码  成功 10000
	 * @return 状态码  成功 10000
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 状态码  成功 10000
	 * @param code 状态码  成功 10000
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 状态描述
	 * @return 状态描述
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 状态描述
	 * @param msg 状态描述
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * 福管家订单编号
	 * @return 福管家订单编号
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * 福管家订单编号
	 * @param outTradeNo 福管家订单编号
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	/**
	 * 支付宝订单编号
	 * @return 支付宝订单编号
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * 支付宝订单编号
	 * @param tradeNo 支付宝订单编号
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	/**
	 * 总金额
	 * @return 总金额
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 总金额
	 * @param totalAmount 总金额
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * 支付宝商户id
	 * @return 支付宝商户id
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * 支付宝商户id
	 * @param sellerId 支付宝商户id
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	/**
	 * 子状态码
	 * @return 子状态码
	 */
	public String getSubCode() {
		return subCode;
	}

	/**
	 * 子状态码
	 * @param subCode 子状态码
	 */
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	/**
	 * 子状态描述
	 * @return 子状态描述
	 */
	public String getSubMsg() {
		return subMsg;
	}

	/**
	 * 子状态描述
	 * @param subMsg 子状态描述
	 */
	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}
	/**
	 * 
	 * @return 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 
	 * @return 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	