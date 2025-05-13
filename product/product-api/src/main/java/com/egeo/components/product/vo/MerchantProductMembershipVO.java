package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-09-11 14:59:31
 */
public class MerchantProductMembershipVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 会籍id
	 */
	private Long membershipId;
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
	 * 会籍id
	 * @return 会籍id
	 */
	public Long getMembershipId() {
		return membershipId;
	}

	/**
	 * 会籍id
	 * @param membershipId 会籍id
	 */
	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
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
	