package com.egeo.components.config.dto;

import java.io.Serializable;

/**
 * 
 * @author tan
 * @date 2018-12-03 18:29:13
 */
public class PolymallUserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 菠萝派商城用户token
	 */
	private String token;	

	/**
	 * 运营方ID
	 */
	private Long merchantId;	

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
	 * 菠萝派商城用户token
	 * @return 菠萝派商城用户token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * 菠萝派商城用户token
	 * @param token 菠萝派商城用户token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 运营方ID
	 * @return 运营方ID
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 运营方ID
	 * @param merchantId 运营方ID
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
}
	