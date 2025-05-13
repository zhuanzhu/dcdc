package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-05-11 15:04:04
 */
public class StandardUnitTagRecordVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * su记录Id
	 */
	private Long standardUnitRecordId;
	/**
	 * 标签Id
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
	 * su记录Id
	 * @return su记录Id
	 */
	public Long getStandardUnitRecordId() {
		return standardUnitRecordId;
	}

	/**
	 * su记录Id
	 * @param standardUnitRecordId su记录Id
	 */
	public void setStandardUnitRecordId(Long standardUnitRecordId) {
		this.standardUnitRecordId = standardUnitRecordId;
	}	
	/**
	 * 标签Id
	 * @return 标签Id
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * 标签Id
	 * @param tagId 标签Id
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}	
}
	