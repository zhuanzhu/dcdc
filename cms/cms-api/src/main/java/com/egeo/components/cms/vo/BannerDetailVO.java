package com.egeo.components.cms.vo;

import java.util.List;
import java.util.Map;

/**
 * 后台轮播图详情vo
 */
public class BannerDetailVO {

	private Long id;
	
	private String name;
	
	private List<Long> companyIds;
	
	private Integer linkType;
	
	private Long linkId;
	
	private String linkParam;
	
	private String linkUrl;
	
	private String imgUrl;
	
	private String remark;
	
	private Integer enabled;

	private Integer sort;
	
	private List<LinkableButtonPageVO> linkableButtonPageList;
	
	private List<Map<String, Object>> extParam;
	/**
	 * 所属页面
	 */
	private Integer belongPage;
	
	public Integer getBelongPage() {
		return belongPage;
	}

	public void setBelongPage(Integer belongPage) {
		this.belongPage = belongPage;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
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

	public String getLinkParam() {
		return linkParam;
	}

	public void setLinkParam(String linkParam) {
		this.linkParam = linkParam;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public List<LinkableButtonPageVO> getLinkableButtonPageList() {
		return linkableButtonPageList;
	}

	public void setLinkableButtonPageList(List<LinkableButtonPageVO> linkableButtonPageList) {
		this.linkableButtonPageList = linkableButtonPageList;
	}

	public List<Map<String, Object>> getExtParam() {
		return extParam;
	}

	public void setExtParam(List<Map<String, Object>> extParam) {
		this.extParam = extParam;
	}
	
	
}
