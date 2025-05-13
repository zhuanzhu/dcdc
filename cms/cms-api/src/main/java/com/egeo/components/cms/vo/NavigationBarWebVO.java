package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author wyy
 * @date 2018-08-01 17:43:32
 */
public class NavigationBarWebVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 导航栏类型 0:顶部导航栏 1:底部信息栏
	 */
	private Integer navigationBarType;
	
	/**
	 * 导航栏主题名称
	 */
	private String navigationBarName;
	
	private List<NavigationLabelWebVO> navigationLabelList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNavigationBarName() {
		return navigationBarName;
	}

	public void setNavigationBarName(String navigationBarName) {
		this.navigationBarName = navigationBarName;
	}

	public List<NavigationLabelWebVO> getNavigationLabelList() {
		return navigationLabelList;
	}

	public void setNavigationLabelList(List<NavigationLabelWebVO> navigationLabelList) {
		this.navigationLabelList = navigationLabelList;
	}

	public Integer getNavigationBarType() {
		return navigationBarType;
	}

	public void setNavigationBarType(Integer navigationBarType) {
		this.navigationBarType = navigationBarType;
	}

	
}
	