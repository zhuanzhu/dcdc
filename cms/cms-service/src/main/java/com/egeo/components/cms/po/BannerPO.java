package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:44
 */
public class BannerPO {


	private Long id;

	/**
	 * 轮播图lurl
	 */
	private String imgUrl;	

	/**
	 * 轮播图名称
	 */
	private String name;	

	/**
	 * 排序
	 */
	private Integer sort;	

	/**
	 * 链接属性外键
	 */
	private Long linkableId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	
	
	private Integer enabled;
	
	private String remark;
	/**
	 * 所属页面
	 */
	private Integer belongPage;
	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 公司类型 0:正式公司(默认值) 1:测试公司 2:竞品公司
	 */
	private Integer companyType;

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Integer getBelongPage() {
		return belongPage;
	}

	public void setBelongPage(Integer belongPage) {
		this.belongPage = belongPage;
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
	 * 轮播图lurl
	 * @return 轮播图lurl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 轮播图lurl
	 * @param imgUrl 轮播图lurl
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * 轮播图名称
	 * @return 轮播图名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 轮播图名称
	 * @param name 轮播图名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序
	 * @param sort 排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 链接属性外键
	 * @return 链接属性外键
	 */
	public Long getLinkableId() {
		return linkableId;
	}

	/**
	 * 链接属性外键
	 * @param linkableId 链接属性外键
	 */
	public void setLinkableId(Long linkableId) {
		this.linkableId = linkableId;
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
}
	