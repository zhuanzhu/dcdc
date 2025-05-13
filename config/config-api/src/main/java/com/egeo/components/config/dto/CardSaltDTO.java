package com.egeo.components.config.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2017-12-26 10:43:17
 */
public class CardSaltDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * uuid
	 */
	private String uuid;	

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
	 * uuid
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * uuid
	 * @param uuid uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	