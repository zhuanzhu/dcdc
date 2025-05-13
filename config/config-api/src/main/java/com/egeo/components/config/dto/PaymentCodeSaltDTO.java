package com.egeo.components.config.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-01-14 10:33:05
 */
public class PaymentCodeSaltDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 支付密码uuid
	 */
	private String paymentCodeUuid;	

	/**
	 * 加密盐
	 */
	private String saltValue;	

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
	 * 支付密码uuid
	 * @return 支付密码uuid
	 */
	public String getPaymentCodeUuid() {
		return paymentCodeUuid;
	}

	/**
	 * 支付密码uuid
	 * @param paymentCodeUuid 支付密码uuid
	 */
	public void setPaymentCodeUuid(String paymentCodeUuid) {
		this.paymentCodeUuid = paymentCodeUuid;
	}
	/**
	 * 加密盐
	 * @return 加密盐
	 */
	public String getSaltValue() {
		return saltValue;
	}

	/**
	 * 加密盐
	 * @param saltValue 加密盐
	 */
	public void setSaltValue(String saltValue) {
		this.saltValue = saltValue;
	}
}
	