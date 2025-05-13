package com.egeo.components.cms.vo;

import java.io.Serializable;

/**
 * 
 * @author jzh
 * @date 2018-04-18 18:44:36
 */
public class LocalParamVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 名称
	 */
	private String name;

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
	 * 名称
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称
	 * @param name 名称
	 */
	public void setName(String name) {
		this.name = name;
	}	

}
	