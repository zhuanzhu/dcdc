package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-07-06 00:09:30
 */
public class SerachSortDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 排序
	 */
	private Integer sortValue;	

	/**
	 * 排序规则
	 */
	private String regulation;	

	/**
	 * 描述
	 */
	private String description;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 排序规则
	 * @return 排序规则
	 */
	public String getRegulation() {
		return regulation;
	}

	/**
	 * 排序规则
	 * @param regulation 排序规则
	 */
	public void setRegulation(String regulation) {
		this.regulation = regulation;
	}
	/**
	 * 描述
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}
	