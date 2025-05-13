package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-08-02 10:33:40
 */
public class NavigationLabelSimpleVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 导航栏标签名称
	 */
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
	