package com.egeo.components.cms.vo;

import java.util.List;

/**
 * 客户端图文组件VO
 * 
 * @author graci
 *
 */
public class ImgTxtVO {

	private Long imagetextId;
	private String imagetextUrl;
	private Integer linkType;
	private Long linkId;
	private String linkUrl;
	private String linkParam;
	private String imagetextName;
	private Long suTmplId;
	private boolean suCompanyAvailable;
	
	private Long cmsPageId;
	
	private List<LinkableButtonPageVO> linkableButtonPageList;

	public Long getSuTmplId() {
		return suTmplId;
	}

	public void setSuTmplId(Long suTmplId) {
		this.suTmplId = suTmplId;
	}

	public String getImagetextName() {
		return imagetextName;
	}

	public void setImagetextName(String imagetextName) {
		this.imagetextName = imagetextName;
	}

	public Long getImagetextId() {
		return imagetextId;
	}

	public void setImagetextId(Long imagetextId) {
		this.imagetextId = imagetextId;
	}

	public String getImagetextUrl() {
		return imagetextUrl;
	}

	public void setImagetextUrl(String imagetextUrl) {
		this.imagetextUrl = imagetextUrl;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
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

	public boolean isSuCompanyAvailable() {
		return suCompanyAvailable;
	}

	public void setSuCompanyAvailable(boolean suCompanyAvailable) {
		this.suCompanyAvailable = suCompanyAvailable;
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
