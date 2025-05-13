package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:49
 */
public class LinkableButtonDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 跳转类型 1.本地参数、2.H5链接（内部）、3.H5链接（外部）、4.商品池组(商品列表)、5.商品、6.无效果'
	 */
	private Integer linkType;	

	/**
	 * 跳转目标id 如商品id,列表id,外链id等等
	 */
	private Long linkId;	

	/**
	 * 跳转参数json
	 */
	private String linkParam;	

	/**
	 * 跳转连接
	 */
	private String linkUrl;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	
	
	private List<LinkableButtonPageDTO> linkableButtonPageDTOList;

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
	 * 跳转类型 0:本地参数 1:h5内链 2:h5外链 3:商品组合 4:单个商品 5:无效果
	 * @return 跳转类型 0:本地参数 1:h5内链 2:h5外链 3:商品组合 4:单个商品 5:无效果
	 */
	public Integer getLinkType() {
		return linkType;
	}

	/**
	 * 跳转类型 0:本地参数 1:h5内链 2:h5外链 3:商品组合 4:单个商品 5:无效果
	 * @param linkType 跳转类型 0:本地参数 1:h5内链 2:h5外链 3:商品组合 4:单个商品 5:无效果
	 */
	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}
	/**
	 * 跳转目标id 如商品id,列表id,外链id等等
	 * @return 跳转目标id 如商品id,列表id,外链id等等
	 */
	public Long getLinkId() {
		return linkId;
	}

	/**
	 * 跳转目标id 如商品id,列表id,外链id等等
	 * @param linkId 跳转目标id 如商品id,列表id,外链id等等
	 */
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	/**
	 * 跳转参数json
	 * @return 跳转参数json
	 */
	public String getLinkParam() {
		return linkParam;
	}

	/**
	 * 跳转参数json
	 * @param linkParam 跳转参数json
	 */
	public void setLinkParam(String linkParam) {
		this.linkParam = linkParam;
	}
	/**
	 * 跳转连接
	 * @return 跳转连接
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * 跳转连接
	 * @param linkUrl 跳转连接
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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

	public List<LinkableButtonPageDTO> getLinkableButtonPageDTOList() {
		return linkableButtonPageDTOList;
	}

	public void setLinkableButtonPageDTOList(List<LinkableButtonPageDTO> linkableButtonPageDTOList) {
		this.linkableButtonPageDTOList = linkableButtonPageDTOList;
	}
	
	
}
	