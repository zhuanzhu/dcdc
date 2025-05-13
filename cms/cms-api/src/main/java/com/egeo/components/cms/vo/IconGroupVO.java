package com.egeo.components.cms.vo;

import java.util.List;

public class IconGroupVO {

	private Long iconGroupId;
	
	private Integer count;
	
	private List<IconVO> iconList;
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getIconGroupId() {
		return iconGroupId;
	}

	public void setIconGroupId(Long iconGroupId) {
		this.iconGroupId = iconGroupId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<IconVO> getIconList() {
		return iconList;
	}

	public void setIconList(List<IconVO> iconList) {
		this.iconList = iconList;
	}
	
	
}
