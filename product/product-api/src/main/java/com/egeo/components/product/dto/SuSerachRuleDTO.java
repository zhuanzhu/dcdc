package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-07-25 14:32:19
 */
public class SuSerachRuleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long categoryTreeNodeId;
	private Long merchantId;
	private String companyType;
	private String companyId;
	private String clientId;
	private String pictureUrl;

	public Long getCategoryTreeNodeId() {
		return categoryTreeNodeId;
	}

	public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
		this.categoryTreeNodeId = categoryTreeNodeId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	private Long id;

	/**
	 * suId
	 */
	private Long standardUnitId;	

	/**
	 * su名称
	 */
	private String standardUnitName;	

	/**
	 * su关键词
	 */
	private String standardUnitKeyword;	

	/**
	 * su标签名称
	 */
	private String standardUnitTag;	

	/**
	 * su所属类目
	 */
	private String standardUnitCategory;	

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
	 * su名称
	 * @return su名称
	 */
	public String getStandardUnitName() {
		return standardUnitName;
	}

	/**
	 * su名称
	 * @param standardUnitName su名称
	 */
	public void setStandardUnitName(String standardUnitName) {
		this.standardUnitName = standardUnitName;
	}
	/**
	 * su关键词
	 * @return su关键词
	 */
	public String getStandardUnitKeyword() {
		return standardUnitKeyword;
	}

	/**
	 * su关键词
	 * @param standardUnitKeyword su关键词
	 */
	public void setStandardUnitKeyword(String standardUnitKeyword) {
		this.standardUnitKeyword = standardUnitKeyword;
	}
	/**
	 * su标签名称
	 * @return su标签名称
	 */
	public String getStandardUnitTag() {
		return standardUnitTag;
	}

	/**
	 * su标签名称
	 * @param standardUnitTag su标签名称
	 */
	public void setStandardUnitTag(String standardUnitTag) {
		this.standardUnitTag = standardUnitTag;
	}
	/**
	 * su所属类目
	 * @return su所属类目
	 */
	public String getStandardUnitCategory() {
		return standardUnitCategory;
	}

	/**
	 * su所属类目
	 * @param standardUnitCategory su所属类目
	 */
	public void setStandardUnitCategory(String standardUnitCategory) {
		this.standardUnitCategory = standardUnitCategory;
	}
}
	