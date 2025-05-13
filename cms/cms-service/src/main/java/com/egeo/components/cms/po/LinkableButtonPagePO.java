package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author mingqiang.luo
 * @date 2018-12-14 14:35:33
 */
public class LinkableButtonPagePO {


	private Long id;

	/**
	 * 页面ID(cms_page.id)
	 */
	private Long cmsPageId;	

	/**
	 * 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 */
	private Integer clientType;	

	/**
	 * 链接属性ID（linkbale_button.id）
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
	 * 页面ID(cms_page.id)
	 * @return 页面ID(cms_page.id)
	 */
	public Long getCmsPageId() {
		return cmsPageId;
	}

	/**
	 * 页面ID(cms_page.id)
	 * @param cmsPageId 页面ID(cms_page.id)
	 */
	public void setCmsPageId(Long cmsPageId) {
		this.cmsPageId = cmsPageId;
	}

	/**
	 * 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 * @return 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 * @param clientType 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * 链接属性ID（linkbale_button.id）
	 * @return 链接属性ID（linkbale_button.id）
	 */
	public Long getLinkableId() {
		return linkableId;
	}

	/**
	 * 链接属性ID（linkbale_button.id）
	 * @param linkableId 链接属性ID（linkbale_button.id）
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

	public LinkableButtonPagePO() {
		
	}

	public LinkableButtonPagePO(Long linkableId) {
		this.linkableId = linkableId;
	}
	
	
}
	