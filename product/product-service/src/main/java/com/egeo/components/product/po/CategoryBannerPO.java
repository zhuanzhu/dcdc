package com.egeo.components.product.po;



/**
 * 
 * @author min
 * @date 2018-04-20 10:38:52
 */
public class CategoryBannerPO {


	private Long id;

	/**
	 * banner名称
	 */
	private String bannerName;	

	/**
	 * Banner图片
	 */
	private String bannerImg;	

	/**
	 * Banner类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
	 */
	private Integer linkType;	

	/**
	 * 跳转url
	 */
	private String linkUrl;	

	/**
	 * 跳转参数
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
	 * 本地参数id
	 */
	private Long localParamId;	
	
	private Long linkableId;

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * banner名称
	 * @return banner名称
	 */
	public String getBannerName() {
		return bannerName;
	}

	/**
	 * banner名称
	 * @param bannerName banner名称
	 */
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	/**
	 * Banner图片
	 * @return Banner图片
	 */
	public String getBannerImg() {
		return bannerImg;
	}

	/**
	 * Banner图片
	 * @param bannerImg Banner图片
	 */
	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	/**
	 * Banner类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
	 * @return Banner类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
	 */
	public Integer getLinkType() {
		return linkType;
	}

	/**
	 * Banner类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
	 * @param linkType Banner类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
	 */
	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	/**
	 * 跳转url
	 * @return 跳转url
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * 跳转url
	 * @param linkUrl 跳转url
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * 跳转参数
	 * @return 跳转参数
	 */
	public String getLinkParam() {
		return linkParam;
	}

	/**
	 * 跳转参数
	 * @param linkParam 跳转参数
	 */
	public void setLinkParam(String linkParam) {
		this.linkParam = linkParam;
	}

	/**
	 * 外部链接id
	 * @return 外部链接id
	 */
	public Long getExternalLinkId() {
		return externalLinkId;
	}

	/**
	 * 外部链接id
	 * @param externalLinkId 外部链接id
	 */
	public void setExternalLinkId(Long externalLinkId) {
		this.externalLinkId = externalLinkId;
	}

	/**
	 * 商品组合id
	 * @return 商品组合id
	 */
	public Long getStandardUnitCombinationId() {
		return standardUnitCombinationId;
	}

	/**
	 * 商品组合id
	 * @param standardUnitCombinationId 商品组合id
	 */
	public void setStandardUnitCombinationId(Long standardUnitCombinationId) {
		this.standardUnitCombinationId = standardUnitCombinationId;
	}

	/**
	 * 商品id
	 * @return 商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * 商品id
	 * @param standardUnitId 商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}

	/**
	 * 本地参数id
	 * @return 本地参数id
	 */
	public Long getLocalParamId() {
		return localParamId;
	}

	/**
	 * 本地参数id
	 * @param localParamId 本地参数id
	 */
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
	