package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-06-29 18:16:48
 */
public class SuSerachKeywordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * suId
	 */
	private Long standardUnitId;	

	/**
	 * 关键词
	 */
	private String keyword;	

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
	 * 关键词
	 * @return 关键词
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 关键词
	 * @param keyword 关键词
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	