package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class MerchantProductTagDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * spuId
	 */
	private Long merchantProductId;	

	/**
	 * 标签id
	 */
	private Long tagId;	
	/**
	 * 标签名称
	 */
	private String tagName;

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
	 * spuId
	 * @return spuId
	 */
	public Long getMerchantProductId() {
		return merchantProductId;
	}

	/**
	 * spuId
	 * @param merchantProductId spuId
	 */
	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
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

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
}
	