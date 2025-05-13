package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:07
 */
public class CategoryTreeNodeVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 类目编号
	 */
	private String serialNumber;
	
	private Long parentId;		 
	//前端传入中文名字
	
	private String cheseName;
	/**
	 * 
	 */

	private String pids;		 
	/**
	 * 
	 */

	private Long categoryTreeId;		 
	/**
	 * 
	 */

	private Long categoryId;		 
	/**
	 * 平台id
	 */
	private Long platformId;		 

	private String name;

	/**
	 * 
	 */
	private String categoryLable;

	/**
	 * 
	 */
	private String description;
	/**
	 * 列表排序
	 */
	private Integer listSort;	
	
	private List<CategoryTreeNodeVO> children;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 
	/**
	 * 轮播图图片
	 */
	private String bannerImg;
	/**
	 * Banner类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品分类、5.商品、6.无效果
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
	 * 本地参数id
	 */
	private Long localParamId;	
	/**
	 * 类目id集合（以;分割）
	 */
	private String aggregationCategoryIds;
	/**
	 * 类目节点备注
	 */
	private String content;

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @param parentId 
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getPids() {
		return pids;
	}

	/**
	 * 
	 * @param pids 
	 */
	public void setPids(String pids) {
		this.pids = pids;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getCategoryTreeId() {
		return categoryTreeId;
	}

	/**
	 * 
	 * @param categoryTreeId 
	 */
	public void setCategoryTreeId(Long categoryTreeId) {
		this.categoryTreeId = categoryTreeId;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * 
	 * @param categoryId 
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}	
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}	
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<CategoryTreeNodeVO> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryTreeNodeVO> children) {
		this.children = children;
	}

	public String getCheseName() {
		return cheseName;
	}

	public void setCheseName(String cheseName) {
		this.cheseName = cheseName;
	}

	public Integer getListSort() {
		return listSort;
	}

	public void setListSort(Integer listSort) {
		this.listSort = listSort;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public Long getLocalParamId() {
		return localParamId;
	}

	public void setLocalParamId(Long localParamId) {
		this.localParamId = localParamId;
	}

	public String getAggregationCategoryIds() {
		return aggregationCategoryIds;
	}

	public void setAggregationCategoryIds(String aggregationCategoryIds) {
		this.aggregationCategoryIds = aggregationCategoryIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}	