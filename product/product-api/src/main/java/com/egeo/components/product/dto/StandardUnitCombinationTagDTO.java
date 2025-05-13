package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-05-11 20:08:11
 */
public class StandardUnitCombinationTagDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * su组合id
	 */
	private Long standardUnitCombinationId;	

	/**
	 * 标签id
	 */
	private Long tagId;	
	/**
	 * 标签名称
	 */
	private Long tagName;

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
	 * su组合id
	 * @return su组合id
	 */
	public Long getStandardUnitCombinationId() {
		return standardUnitCombinationId;
	}

	/**
	 * su组合id
	 * @param standardUnitCombinationId su组合id
	 */
	public void setStandardUnitCombinationId(Long standardUnitCombinationId) {
		this.standardUnitCombinationId = standardUnitCombinationId;
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

	public Long getTagName() {
		return tagName;
	}

	public void setTagName(Long tagName) {
		this.tagName = tagName;
	}
	
}
	