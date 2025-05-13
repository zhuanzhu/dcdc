package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:47
 */
public class ImagetextDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 图片链接
	 */
	private String imgUrl;

	/**
	 * 可跳转链接id
	 */
	private Long linkableId;

	/**
	 * 排序 作用于3张图的组件 按照sort大小确定左图,右上图,右下图
	 */
	private Integer sort;

	/**
	 * 图文组id
	 */
	private Long groupId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 图片链接
	 * 
	 * @return 图片链接
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 图片链接
	 * 
	 * @param imgUrl
	 *            图片链接
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * 可跳转链接id
	 * 
	 * @return 可跳转链接id
	 */
	public Long getLinkableId() {
		return linkableId;
	}

	/**
	 * 可跳转链接id
	 * 
	 * @param linkableId
	 *            可跳转链接id
	 */
	public void setLinkableId(Long linkableId) {
		this.linkableId = linkableId;
	}

	/**
	 * 排序 作用于3张图的组件 按照sort大小确定左图,右上图,右下图
	 * 
	 * @return 排序 作用于3张图的组件 按照sort大小确定左图,右上图,右下图
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序 作用于3张图的组件 按照sort大小确定左图,右上图,右下图
	 * 
	 * @param sort
	 *            排序 作用于3张图的组件 按照sort大小确定左图,右上图,右下图
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 图文组id
	 * 
	 * @return 图文组id
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * 图文组id
	 * 
	 * @param groupId
	 *            图文组id
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * 创建时间
	 * 
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
