package com.egeo.components.cms.vo;

import java.io.Serializable;

public class BannerPickVO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 轮播图名称
	 */
	private String name;
	
	/**
	 * 所属公司名称
	 */
	private String companyName;
	
	/**
	 * 跳转类型: 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
	 */
	private Integer linkType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}
	
	
	
}
