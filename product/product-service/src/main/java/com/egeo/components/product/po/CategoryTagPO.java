package com.egeo.components.product.po;



/**
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class CategoryTagPO {


	private Long id;

	/**
	 * 类目id
	 */
	private Long categoryId;	

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
	 * 类目id
	 * @return 类目id
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * 类目id
	 * @param categoryId 类目id
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	