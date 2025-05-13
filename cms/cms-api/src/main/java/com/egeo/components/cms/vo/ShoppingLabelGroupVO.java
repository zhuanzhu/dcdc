package com.egeo.components.cms.vo;

import java.util.List;

public class ShoppingLabelGroupVO {

	private Long groupId;
	
	private String imgUrl;
	
	private List<ShoppingLabelVO> labelList;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<ShoppingLabelVO> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<ShoppingLabelVO> labelList) {
		this.labelList = labelList;
	}
	
	
}
