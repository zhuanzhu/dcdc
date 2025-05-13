package com.egeo.components.cms.vo;

import java.util.List;

/**
 * 客户端bannerVO
 * 
 * @author graci
 *
 */
public class BannerVO {

	/**
	 * 轮播图id
	 */
	private Long id;
	/**
	 * 图片url
	 */
	private String imgUrl;
	/**
	 * 跳转类型 0:本地参数 1:h5内链 2:h5外链 3:商品组合 4:单个商品 5:无效果'
	 */
	private Integer linkType;
	/**
	 * 跳转目标id
	 */
	private Long linkId;
	/**
	 * 跳转链接
	 */
	private String linkUrl;
	/**
	 * 跳转参数json
	 */
	private String linkParam;
	
	/**
	 * 单个商品模板id
	 */
	private Long suTmplId;
	
	private List<Long> companyIds;
	
	private boolean suCompanyAvailable;
	
	private List<LinkableButtonPageVO> linkableButtonPageList;
	
	private Long cmsPageId;
	
	private String extParam1;
	
	private String extParam2;
	
	private String extParam3;
	
	private boolean available;
	
	private String msg;
	
	public String getExtParam1() {
		return extParam1;
	}

	public void setExtParam1(String extParam1) {
		this.extParam1 = extParam1;
	}

	public String getExtParam2() {
		return extParam2;
	}

	public void setExtParam2(String extParam2) {
		this.extParam2 = extParam2;
	}

	public String getExtParam3() {
		return extParam3;
	}

	public void setExtParam3(String extParam3) {
		this.extParam3 = extParam3;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getSuTmplId() {
		return suTmplId;
	}

	public void setSuTmplId(Long suTmplId) {
		this.suTmplId = suTmplId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public List<LinkableButtonPageVO> getLinkableButtonPageList() {
		return linkableButtonPageList;
	}

	public void setLinkableButtonPageList(List<LinkableButtonPageVO> linkableButtonPageList) {
		this.linkableButtonPageList = linkableButtonPageList;
	}

	public Long getCmsPageId() {
		return cmsPageId;
	}

	public void setCmsPageId(Long cmsPageId) {
		this.cmsPageId = cmsPageId;
	}

}
