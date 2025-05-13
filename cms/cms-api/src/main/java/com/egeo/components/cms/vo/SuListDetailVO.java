package com.egeo.components.cms.vo;

/**
 * 商品列表组件后台详情vo
 * @author graci
 *
 */
public class SuListDetailVO {

	private Long SuListId;
	
	private String titleName;
	
	private Long sucId;
	
	private Integer maxShow;
	
	private String bannerUrl;
	
	private LinkableButtonVO linkableInfo;
	
	private Long titleColor;

	public LinkableButtonVO getLinkableInfo() {
		return linkableInfo;
	}

	public void setLinkableInfo(LinkableButtonVO linkableInfo) {
		this.linkableInfo = linkableInfo;
	}

	public Long getSuListId() {
		return SuListId;
	}

	public void setSuListId(Long suListId) {
		SuListId = suListId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public Long getSucId() {
		return sucId;
	}

	public void setSucId(Long sucId) {
		this.sucId = sucId;
	}

	public Integer getMaxShow() {
		return maxShow;
	}

	public void setMaxShow(Integer maxShow) {
		this.maxShow = maxShow;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public Long getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Long titleColor) {
		this.titleColor = titleColor;
	}

}
