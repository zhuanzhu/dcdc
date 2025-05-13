package com.egeo.components.user.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-01-06 16:04:43
 */
public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 客户端名称
	 */
	private String name;	

	/**
	 * 客户端描述
	 */
	private String description;

	private String clientPayTypeRemarks;

	public String getClientPayTypeRemarks() {
		return clientPayTypeRemarks;
	}

	public void setClientPayTypeRemarks(String clientPayTypeRemarks) {
		this.clientPayTypeRemarks = clientPayTypeRemarks;
	}

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
	 * 客户端名称
	 * @return 客户端名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 客户端名称
	 * @param name 客户端名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 客户端描述
	 * @return 客户端描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 客户端描述
	 * @param description 客户端描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
	