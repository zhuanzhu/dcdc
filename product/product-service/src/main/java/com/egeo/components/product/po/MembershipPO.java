package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author whf
 * @date 2018-09-06 16:12:28
 */
public class MembershipPO {


	private Long id;

	/**
	 * 会籍码
	 */
	private String membershipCode;	

	/**
	 * 会籍名
	 */
	private String membershipName;	

	/**
	 * 会籍log的图片url
	 */
	private String membershipLogImgUrl;	

	/**
	 * 会籍关联的sku的id
	 */
	private Long linkedSkuId;	

	/**
	 * 会籍分类id
	 */
	private Long categoryId;	

	/**
	 * 会籍分类名
	 */
	private String categoryName;	

	/**
	 * 有效期值
	 */
	private Integer validPeriodVal;	

	/**
	 * 有效期单位(1.年   2.月    3.日)
	 */
	private Integer validPeriodUnit;	

	/**
	 * 备注
	 */
	private String remarks;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 创建时间(自动创建时间)
	 */
	private Date createTime;	

	/**
	 * 更新时间(自动更新)
	 */
	private Date updateTime;	

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
	 * 会籍码
	 * @return 会籍码
	 */
	public String getMembershipCode() {
		return membershipCode;
	}

	/**
	 * 会籍码
	 * @param membershipCode 会籍码
	 */
	public void setMembershipCode(String membershipCode) {
		this.membershipCode = membershipCode;
	}

	/**
	 * 会籍名
	 * @return 会籍名
	 */
	public String getMembershipName() {
		return membershipName;
	}

	/**
	 * 会籍名
	 * @param membershipName 会籍名
	 */
	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}

	/**
	 * 会籍log的图片url
	 * @return 会籍log的图片url
	 */
	public String getMembershipLogImgUrl() {
		return membershipLogImgUrl;
	}

	/**
	 * 会籍log的图片url
	 * @param membershipLogImgUrl 会籍log的图片url
	 */
	public void setMembershipLogImgUrl(String membershipLogImgUrl) {
		this.membershipLogImgUrl = membershipLogImgUrl;
	}

	/**
	 * 会籍关联的sku的id
	 * @return 会籍关联的sku的id
	 */
	public Long getLinkedSkuId() {
		return linkedSkuId;
	}

	/**
	 * 会籍关联的sku的id
	 * @param linkedSkuId 会籍关联的sku的id
	 */
	public void setLinkedSkuId(Long linkedSkuId) {
		this.linkedSkuId = linkedSkuId;
	}

	/**
	 * 会籍分类id
	 * @return 会籍分类id
	 */
	public Long getCategoryId() {
		return categoryId;
	}

	/**
	 * 会籍分类id
	 * @param categoryId 会籍分类id
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 会籍分类名
	 * @return 会籍分类名
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 会籍分类名
	 * @param categoryName 会籍分类名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * 有效期值
	 * @return 有效期值
	 */
	public Integer getValidPeriodVal() {
		return validPeriodVal;
	}

	/**
	 * 有效期值
	 * @param validPeriodVal 有效期值
	 */
	public void setValidPeriodVal(Integer validPeriodVal) {
		this.validPeriodVal = validPeriodVal;
	}

	/**
	 * 有效期单位(1.年   2.月    3.日)
	 * @return 有效期单位(1.年   2.月    3.日)
	 */
	public Integer getValidPeriodUnit() {
		return validPeriodUnit;
	}

	/**
	 * 有效期单位(1.年   2.月    3.日)
	 * @param validPeriodUnit 有效期单位(1.年   2.月    3.日)
	 */
	public void setValidPeriodUnit(Integer validPeriodUnit) {
		this.validPeriodUnit = validPeriodUnit;
	}

	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注
	 * @param remarks 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	/**
	 * 创建时间(自动创建时间)
	 * @return 创建时间(自动创建时间)
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间(自动创建时间)
	 * @param createTime 创建时间(自动创建时间)
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间(自动更新)
	 * @return 更新时间(自动更新)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间(自动更新)
	 * @param updateTime 更新时间(自动更新)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	