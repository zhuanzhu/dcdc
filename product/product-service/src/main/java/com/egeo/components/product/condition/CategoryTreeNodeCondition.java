package com.egeo.components.product.condition;

import com.egeo.components.product.po.CategoryTreeNodePO;

/**
 * 
 * @author xiaping
 * @date 2017-07-17 11:21:22
 */
public class CategoryTreeNodeCondition extends CategoryTreeNodePO {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 类目类型
	 */
	private Integer type;

	/**
	 * 类目名字
	 */
	private String name;
	/**
	 * 轮播图图片
	 */
	private String bannerImg;
	/**
	 * banner类型
	 */
	private Integer linkType;
	/**
	 * 链接url
	 */
	private String linkUrl;
	/**
	 *链接参数
	 */
	private String linkParam;
	/**
	 * 外部链接id
	 */
	private Long externalLinkId;
	/**
	 * 商品组合id
	 */
	private Long standardUnitCombinationId;
	/**
	 * 商品id
	 */
	private Long standardUnitId;
	/**
	 * 类目序列号
	 */
	private String serialNumber;
	
	private String categoryLable;
	/**
	 * 类目备注
	 */
	private String description;
	/**
	 * 类目bannerId
	 */
	private Long categoryBannerId;
	/**
	 * 本地参数id
	 */
	private Long localParamId;	
	
	private Long linkableId;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
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

	public Long getExternalLinkId() {
		return externalLinkId;
	}

	public void setExternalLinkId(Long externalLinkId) {
		this.externalLinkId = externalLinkId;
	}

	public Long getStandardUnitCombinationId() {
		return standardUnitCombinationId;
	}

	public void setStandardUnitCombinationId(Long standardUnitCombinationId) {
		this.standardUnitCombinationId = standardUnitCombinationId;
	}

	public Long getStandardUnitId() {
		return standardUnitId;
	}

	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}


	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCategoryLable() {
		return categoryLable;
	}

	public void setCategoryLable(String categoryLable) {
		this.categoryLable = categoryLable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryBannerId() {
		return categoryBannerId;
	}

	public void setCategoryBannerId(Long categoryBannerId) {
		this.categoryBannerId = categoryBannerId;
	}

	public Long getLocalParamId() {
		return localParamId;
	}

	public void setLocalParamId(Long localParamId) {
		this.localParamId = localParamId;
	}

	public Long getLinkableId() {
		return linkableId;
	}

	public void setLinkableId(Long linkableId) {
		this.linkableId = linkableId;
	}
	
	
	

}
	