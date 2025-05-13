package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:02:51
 */
public class PromotionIndividualLimitVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 促销id
	 */

	private Long promotionId;		 
	/**
	 * 规则id
	 */

	private Long ruleRef;		 
	/**
	 * 用户id（userid）
	 */

	private Long customerId;		 
	/**
	 * 限制用户购买的总数
	 */

	private Integer totalLimit;		 
	/**
	 * 用户购买的数量
	 */

	private Integer saleCount;		 
	/**
	 * 
	 */

	private Long platformId;		 
	/**
	 * 0 :促销限制 1：赠品限制
	 */

	private Integer ruleType;		 
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 
	/**
	 * 修改时间:更新时数据库会自动set值
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
	 * 促销id
	 * @return 促销id
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 促销id
	 * @param promotionId 促销id
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}	
	/**
	 * 规则id
	 * @return 规则id
	 */
	public Long getRuleRef() {
		return ruleRef;
	}

	/**
	 * 规则id
	 * @param ruleRef 规则id
	 */
	public void setRuleRef(Long ruleRef) {
		this.ruleRef = ruleRef;
	}	
	/**
	 * 用户id（userid）
	 * @return 用户id（userid）
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 用户id（userid）
	 * @param customerId 用户id（userid）
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}	
	/**
	 * 限制用户购买的总数
	 * @return 限制用户购买的总数
	 */
	public Integer getTotalLimit() {
		return totalLimit;
	}

	/**
	 * 限制用户购买的总数
	 * @param totalLimit 限制用户购买的总数
	 */
	public void setTotalLimit(Integer totalLimit) {
		this.totalLimit = totalLimit;
	}	
	/**
	 * 用户购买的数量
	 * @return 用户购买的数量
	 */
	public Integer getSaleCount() {
		return saleCount;
	}

	/**
	 * 用户购买的数量
	 * @param saleCount 用户购买的数量
	 */
	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 
	 * @param platformId 
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}	
	/**
	 * 0 :促销限制 1：赠品限制
	 * @return 0 :促销限制 1：赠品限制
	 */
	public Integer getRuleType() {
		return ruleType;
	}

	/**
	 * 0 :促销限制 1：赠品限制
	 * @param ruleType 0 :促销限制 1：赠品限制
	 */
	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}	
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
}
	