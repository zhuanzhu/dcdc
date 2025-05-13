package com.egeo.components.cms.vo;

import java.util.Date;
import java.util.List;

public class LinkableButtonVO {

	private Long id;

	/**
	 * 跳转类型 0:本地参数 1:h5内链 2:h5外链 3:商品组合 4:单个商品 5:无效果
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
	
	private List<LinkableButtonPageVO> linkableButtonPageList;


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

	public List<LinkableButtonPageVO> getLinkableButtonPageList() {
		return linkableButtonPageList;
	}

	public void setLinkableButtonPageList(List<LinkableButtonPageVO> linkableButtonPageList) {
		this.linkableButtonPageList = linkableButtonPageList;
	}
	
	
}
