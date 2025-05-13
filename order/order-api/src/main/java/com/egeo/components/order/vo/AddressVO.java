package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2017-09-14 15:19:51
 */
public class AddressVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 地址名称
	 */
	private String addressName;
	/**
	 * 父类id
	 */
	private Long pid;

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 地址名称
	 * @return 地址名称
	 */
	public String getAddressName() {
		return addressName;
	}

	/**
	 * 地址名称
	 * @param addressName 地址名称
	 */
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}	
	/**
	 * 父类id
	 * @return 父类id
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * 父类id
	 * @param pid 父类id
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}	
}
	