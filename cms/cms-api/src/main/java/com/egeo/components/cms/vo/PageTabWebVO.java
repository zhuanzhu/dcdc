package com.egeo.components.cms.vo;

import java.io.Serializable;

/**
 * 
 * @author wyy
 * @date 2018-08-01 12:25:54
 */
public class PageTabWebVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 分页tab名称
	 */
	private String pageTabName;
	
	/**
	 * tab排序号  -1:空值占位
	 */
	private Integer pageTabSort;
	/**
	 * 是否设为首页 0:否 1:是
	 */
	private Integer isHomePage;
	
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
	 * 分页tab名称
	 * @return 分页tab名称
	 */
	public String getPageTabName() {
		return pageTabName;
	}

	/**
	 * 分页tab名称
	 * @param pageTabName 分页tab名称
	 */
	public void setPageTabName(String pageTabName) {
		this.pageTabName = pageTabName;
	}	
	/**
	 * tab排序号  -1:空值占位
	 * @return tab排序号  -1:空值占位
	 */
	public Integer getPageTabSort() {
		return pageTabSort;
	}

	/**
	 * tab排序号  -1:空值占位
	 * @param pageTabSort tab排序号  -1:空值占位
	 */
	public void setPageTabSort(Integer pageTabSort) {
		this.pageTabSort = pageTabSort;
	}	
	/**
	 * 是否设为首页 0:否 1:是
	 * @return 是否设为首页 0:否 1:是
	 */
	public Integer getIsHomePage() {
		return isHomePage;
	}

	/**
	 * 是否设为首页 0:否 1:是
	 * @param isHomePage 是否设为首页 0:否 1:是
	 */
	public void setIsHomePage(Integer isHomePage) {
		this.isHomePage = isHomePage;
	}	
	
	

	
}
	