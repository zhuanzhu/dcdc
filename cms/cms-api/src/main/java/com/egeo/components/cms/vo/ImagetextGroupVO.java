package com.egeo.components.cms.vo;

import java.util.List;

/**
 * 后台图文组件组vo
 * @author graci
 *
 */
public class ImagetextGroupVO {

	private Long groupId;
	
	private Integer type;
	
	private List<ImgTxtVO> imagetextList;
	
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ImgTxtVO> getImagetextList() {
		return imagetextList;
	}

	public void setImagetextList(List<ImgTxtVO> imagetextList) {
		this.imagetextList = imagetextList;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
