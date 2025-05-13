package com.egeo.components.product.po;



/**
 * 
 * @author min
 * @date 2018-04-06 16:03:34
 */
public class StandardUnitCombinationCategoryPO {


	private Long id;

	/**
	 * su商品组合id
	 */
	private Long standardUnitCombinationId;	

	/**
	 * 前台类目节点id
	 */
	private Long categoryTreeNodeId;	

	/**
	 * 排序
	 */
	private Integer sortValue;
	/**
	 * 类型:1:后台类目树,2:前台类目树
     * 新增加关联后台类目树业务后,增加类型区别字段
	 */
	private Integer type;

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
	 * su商品组合id
	 * @return su商品组合id
	 */
	public Long getStandardUnitCombinationId() {
		return standardUnitCombinationId;
	}

	/**
	 * su商品组合id
	 * @param standardUnitCombinationId su商品组合id
	 */
	public void setStandardUnitCombinationId(Long standardUnitCombinationId) {
		this.standardUnitCombinationId = standardUnitCombinationId;
	}

	/**
	 * 前台类目节点id
	 * @return 前台类目节点id
	 */
	public Long getCategoryTreeNodeId() {
		return categoryTreeNodeId;
	}

	/**
	 * 前台类目节点id
	 * @param categoryTreeNodeId 前台类目节点id
	 */
	public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
		this.categoryTreeNodeId = categoryTreeNodeId;
	}

	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	/**
	 *
	 * @return
	 */
	public Integer getType() {
		return type;
	}

	/**
	 *
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}
}
	