package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-04-08 14:34:40
 */
public class CategoryTreeNodeCategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 前台类目节点id
	 */
	private Long frontCategoryTreeNodeId;	

	/**
	 * 后台类目节点id
	 */
	private Long queenCategoryTreeNodeId;	

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
	 * 前台类目节点id
	 * @return 前台类目节点id
	 */
	public Long getFrontCategoryTreeNodeId() {
		return frontCategoryTreeNodeId;
	}

	/**
	 * 前台类目节点id
	 * @param frontCategoryTreeNodeId 前台类目节点id
	 */
	public void setFrontCategoryTreeNodeId(Long frontCategoryTreeNodeId) {
		this.frontCategoryTreeNodeId = frontCategoryTreeNodeId;
	}
	/**
	 * 后台类目节点id
	 * @return 后台类目节点id
	 */
	public Long getQueenCategoryTreeNodeId() {
		return queenCategoryTreeNodeId;
	}

	/**
	 * 后台类目节点id
	 * @param queenCategoryTreeNodeId 后台类目节点id
	 */
	public void setQueenCategoryTreeNodeId(Long queenCategoryTreeNodeId) {
		this.queenCategoryTreeNodeId = queenCategoryTreeNodeId;
	}
}
	