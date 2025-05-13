package com.egeo.components.cms.vo;

/**
 * 后台轮播图列表VO
 * @author graci
 *
 */
public class BannerListVO {

	private Long id;
	
	private String name;
	
	private String imgUrl;
	
	private Integer linkType;
	
	private Integer sort;
	
	private Integer enabled;
	/**
	 * 所属页面
	 */
	private Integer belongPage;
	
	private String remark;

	public Integer getBelongPage() {
		return belongPage;
	}

	public void setBelongPage(Integer belongPage) {
		this.belongPage = belongPage;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
