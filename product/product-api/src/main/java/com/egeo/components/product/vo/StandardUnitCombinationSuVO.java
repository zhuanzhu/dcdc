package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-04-06 16:03:34
 */
public class StandardUnitCombinationSuVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 商品组合id
	 */
	private Long standardUnitCombinationId;
	/**
	 * 商品id
	 */
	private Long standardUnitId;
	/**
	 * 排序
	 */
	private Integer sortValue;

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
	 * 商品组合id
	 * @return 商品组合id
	 */
	public Long getStandardUnitCombinationId() {
		return standardUnitCombinationId;
	}

	/**
	 * 商品组合id
	 * @param standardUnitCombinationId 商品组合id
	 */
	public void setStandardUnitCombinationId(Long standardUnitCombinationId) {
		this.standardUnitCombinationId = standardUnitCombinationId;
	}	
	/**
	 * 商品id
	 * @return 商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * 商品id
	 * @param standardUnitId 商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
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
}
	