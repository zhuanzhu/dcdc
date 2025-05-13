package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-05-11 15:04:04
 */
public class StandardUnitTagVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * suId
	 */
	private Long standardUnitId;
	/**
	 * 标签id
	 */
	private Long tagId;

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
	 * suId
	 * @return suId
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * suId
	 * @param standardUnitId suId
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}	
	/**
	 * 标签id
	 * @return 标签id
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * 标签id
	 * @param tagId 标签id
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}	
}
	