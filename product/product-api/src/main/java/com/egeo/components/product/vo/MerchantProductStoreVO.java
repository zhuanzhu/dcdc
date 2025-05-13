package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-09-11 14:59:33
 */
public class MerchantProductStoreVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 门店id
	 */
	private Long storeId;
	/**
	 * 商品草稿id
	 */
	private Long merchantProductId;
	/**
	 * 平台id
	 */
	private Long platformId;

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 门店id
	 * @return 门店id
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 门店id
	 * @param storeId 门店id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}	
	/**
	 * 商品草稿id
	 * @return 商品草稿id
	 */
	public Long getMerchantProductId() {
		return merchantProductId;
	}

	/**
	 * 商品草稿id
	 * @param merchantProductId 商品草稿id
	 */
	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
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
	