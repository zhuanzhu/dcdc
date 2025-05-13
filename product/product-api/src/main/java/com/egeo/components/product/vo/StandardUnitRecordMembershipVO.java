package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-09-11 14:59:32
 */
public class StandardUnitRecordMembershipVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 会籍id
	 */
	private Long membershipId;
	/**
	 * 商品记录id
	 */
	private Long standardUnitRecordId;
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
	 * 商品记录id
	 * @return 商品记录id
	 */
	public Long getStandardUnitRecordId() {
		return standardUnitRecordId;
	}

	/**
	 * 商品记录id
	 * @param standardUnitRecordId 商品记录id
	 */
	public void setStandardUnitRecordId(Long standardUnitRecordId) {
		this.standardUnitRecordId = standardUnitRecordId;
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
	