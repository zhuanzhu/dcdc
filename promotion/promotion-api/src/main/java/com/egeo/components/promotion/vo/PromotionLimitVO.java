package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:02:51
 */
public class PromotionLimitVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 促销id
	 */

	private Long promotionId;		 
	/**
	 * 商品id
	 */

	private Long ruleRef;		 
	/**
	 * 个人限定购买数
	 */

	private Integer totalLimit;		 
	/**
	 * 全网已销售数量
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
	 * 限制类型：0 不限，1 促销，2 商家，3 商品
	 */

	private Integer limitType;		 
	/**
	 * 限制类型对应的id：促销，商家，商品mpId 产品ID
	 */

	private Long limitRef;		 
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
	 * 促销限制表
	 * @param id 促销限制表
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
	 * 商品id
	 * @return 商品id
	 */
	public Long getRuleRef() {
		return ruleRef;
	}

	/**
	 * 商品id
	 * @param ruleRef 商品id
	 */
	public void setRuleRef(Long ruleRef) {
		this.ruleRef = ruleRef;
	}	
	/**
	 * 个人限定购买数
	 * @return 个人限定购买数
	 */
	public Integer getTotalLimit() {
		return totalLimit;
	}

	/**
	 * 个人限定购买数
	 * @param totalLimit 个人限定购买数
	 */
	public void setTotalLimit(Integer totalLimit) {
		this.totalLimit = totalLimit;
	}	
	/**
	 * 全网已销售数量
	 * @return 全网已销售数量
	 */
	public Integer getSaleCount() {
		return saleCount;
	}

	/**
	 * 全网已销售数量
	 * @param saleCount 全网已销售数量
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
	 * 限制类型：0 不限，1 促销，2 商家，3 商品
	 * @return 限制类型：0 不限，1 促销，2 商家，3 商品
	 */
	public Integer getLimitType() {
		return limitType;
	}

	/**
	 * 限制类型：0 不限，1 促销，2 商家，3 商品
	 * @param limitType 限制类型：0 不限，1 促销，2 商家，3 商品
	 */
	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
	}	
	/**
	 * 限制类型对应的id：促销，商家，商品mpId 产品ID
	 * @return 限制类型对应的id：促销，商家，商品mpId 产品ID
	 */
	public Long getLimitRef() {
		return limitRef;
	}

	/**
	 * 限制类型对应的id：促销，商家，商品mpId 产品ID
	 * @param limitRef 限制类型对应的id：促销，商家，商品mpId 产品ID
	 */
	public void setLimitRef(Long limitRef) {
		this.limitRef = limitRef;
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
	