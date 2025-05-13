package com.egeo.components.product.po;



/**
 * 
 * @author min
 * @date 2018-09-11 14:59:31
 */
public class StandardUnitMembershipPO {


	private Long id;

	/**
	 * 会籍id
	 */
	private Long membershipId;	

	/**
	 * 商品id
	 */
	private Long standardUnitId;	

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
	