package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:50
 */
public class SuListPO {


	private Long id;

	/**
	 * 标题名
	 */
	private String titleName;
	
	/**
	 * 标题颜色
	 */
	private Long titleColor;

	/**
	 * 关联类型 0:前台类目树 1:商品分组 2:商品标签
	 */
	private Integer relationType;	

	/**
	 * 排序方式  0:销量 1:上架时间
	 */
	private Integer sortType;	

	/**
	 * 实例id
	 */
	private Long instId;	

	/**
	 * 客户端最大显示数
	 */
	private Integer maxShow;	

	/**
	 * 展示方式 0:单行无更多 1:单行有更多 2:单行无更多带banner 3:单行有更多带banner 4:2列无更多 5:2列有更多 6:2列无更多带banner 7:2列有更多带banner 8:3列无更多 9:3列有更多 10:3列无更多带banner 11:3列有更多带banner 12:单列无更多 13:单列有更多
	 */
	private Integer showType;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 商品组合id
	 */
	private Long sucId;
	
	/**
	 * banner链接id
	 */
	private Long linkableId;

	/**
	 * banner图片链接
	 */
	private String bannerUrl;

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}


	public Long getLinkableId() {
		return linkableId;
	}

	public void setLinkableId(Long linkableId) {
		this.linkableId = linkableId;
	}

	public Long getSucId() {
		return sucId;
	}

	public void setSucId(Long sucId) {
		this.sucId = sucId;
	}

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
	 * 标题名
	 * @return 标题名
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * 标题名
	 * @param titleName 标题名
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * 关联类型 0:前台类目树 1:商品分组 2:商品标签
	 * @return 关联类型 0:前台类目树 1:商品分组 2:商品标签
	 */
	public Integer getRelationType() {
		return relationType;
	}

	/**
	 * 关联类型 0:前台类目树 1:商品分组 2:商品标签
	 * @param relationType 关联类型 0:前台类目树 1:商品分组 2:商品标签
	 */
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	/**
	 * 排序方式  0:销量 1:上架时间
	 * @return 排序方式  0:销量 1:上架时间
	 */
	public Integer getSortType() {
		return sortType;
	}

	/**
	 * 排序方式  0:销量 1:上架时间
	 * @param sortType 排序方式  0:销量 1:上架时间
	 */
	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	/**
	 * 实例id
	 * @return 实例id
	 */
	public Long getInstId() {
		return instId;
	}

	/**
	 * 实例id
	 * @param instId 实例id
	 */
	public void setInstId(Long instId) {
		this.instId = instId;
	}

	/**
	 * 客户端最大显示数
	 * @return 客户端最大显示数
	 */
	public Integer getMaxShow() {
		return maxShow;
	}

	/**
	 * 客户端最大显示数
	 * @param maxShow 客户端最大显示数
	 */
	public void setMaxShow(Integer maxShow) {
		this.maxShow = maxShow;
	}

	/**
	 * 展示方式 0:单行无更多 1:单行有更多 2:单行无更多带banner 3:单行有更多带banner 4:2列无更多 5:2列有更多 6:2列无更多带banner 7:2列有更多带banner 8:3列无更多 9:3列有更多 10:3列无更多带banner 11:3列有更多带banner 12:单列无更多 13:单列有更多
	 * @return 展示方式 0:单行无更多 1:单行有更多 2:单行无更多带banner 3:单行有更多带banner 4:2列无更多 5:2列有更多 6:2列无更多带banner 7:2列有更多带banner 8:3列无更多 9:3列有更多 10:3列无更多带banner 11:3列有更多带banner 12:单列无更多 13:单列有更多
	 */
	public Integer getShowType() {
		return showType;
	}

	/**
	 * 展示方式 0:单行无更多 1:单行有更多 2:单行无更多带banner 3:单行有更多带banner 4:2列无更多 5:2列有更多 6:2列无更多带banner 7:2列有更多带banner 8:3列无更多 9:3列有更多 10:3列无更多带banner 11:3列有更多带banner 12:单列无更多 13:单列有更多
	 * @param showType 展示方式 0:单行无更多 1:单行有更多 2:单行无更多带banner 3:单行有更多带banner 4:2列无更多 5:2列有更多 6:2列无更多带banner 7:2列有更多带banner 8:3列无更多 9:3列有更多 10:3列无更多带banner 11:3列有更多带banner 12:单列无更多 13:单列有更多
	 */
	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(Long titleColor) {
		this.titleColor = titleColor;
	}
}
	