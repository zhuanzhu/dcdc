package com.egeo.components.cms.vo;

import java.io.Serializable;

/**
 * 
 * @author wyy
 * @date 2018-08-01 17:43:33
 */
public class NavigationBarLabelVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 导航栏标签名称
	 */
	private String navigationLabelName;

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

	public String getNavigationLabelName() {
		return navigationLabelName;
	}

	public void setNavigationLabelName(String navigationLabelName) {
		this.navigationLabelName = navigationLabelName;
	}
}
	