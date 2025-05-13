package com.egeo.components.cms.vo;

import java.util.List;

/**
 * 客户端iconVO
 * 
 * @author graci
 *
 */
public class IconVO {

	private Long iconId;
	private String iconName;
	private String imgUrl;
	private Integer linkType;
	private Object linkId;
	private String linkUrl;
	private String linkParam;
	private Long suTmplId;
	private List<Long> companyIds;
	private boolean suCompanyAvailable;
	
	private Long cmsPageId;
	
	private List<LinkableButtonPageVO> linkableButtonPageList;
	/**
	 * 简介
	 */
	private String summary;

	public boolean isSuCompanyAvailable() {
		return suCompanyAvailable;
	}

	public void setSuCompanyAvailable(boolean suCompanyAvailable) {
		this.suCompanyAvailable = suCompanyAvailable;
	}

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public Long getSuTmplId() {
		return suTmplId;
	}

	public void setSuTmplId(Long suTmplId) {
		this.suTmplId = suTmplId;
	}

	public Long getIconId() {
		return iconId;
	}

	public void setIconId(Long iconId) {
		this.iconId = iconId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Object getLinkId() {
		return linkId;
	}

	public void setLinkId(Object linkId) {
		this.linkId = linkId;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkParam() {
		return linkParam;
	}

	public void setLinkParam(String linkParam) {
		this.linkParam = linkParam;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getCmsPageId() {
		return cmsPageId;
	}

	public void setCmsPageId(Long cmsPageId) {
		this.cmsPageId = cmsPageId;
	}

	public List<LinkableButtonPageVO> getLinkableButtonPageList() {
		return linkableButtonPageList;
	}

	public void setLinkableButtonPageList(List<LinkableButtonPageVO> linkableButtonPageList) {
		this.linkableButtonPageList = linkableButtonPageList;
	}	

}
