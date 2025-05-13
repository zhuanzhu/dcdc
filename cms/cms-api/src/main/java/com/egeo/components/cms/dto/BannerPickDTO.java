package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.List;

public class BannerPickDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
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
	
	/**
	 * 公司的id字符串（以逗号分割）
	 */
	private List<Long> companyIds;

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

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
