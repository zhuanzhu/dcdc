package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionLimitRuleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 促销id
	 */
	private Long promotionId;	

	/**
	 * 限制类型：0 不限，1 促销，2 商家，3 商品
	 */
	private Integer limitType;	

	/**
	 * 限制类型对应的id：促销，商家，商品
	 */
	private Long limitRef;	

	/**
	 * 全网限制数量总数
	 */
	private Integer totalLimit;	

	/**
	 * limit_ref对应的限制数量
	 */
	private Integer individualLimit;	

	/**
	 * 规则的状态
	 */
	private Integer status;	

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
	 * 促销限制规则
	 * @param id 促销限制规则
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
	 * 限制类型对应的id：促销，商家，商品
	 * @return 限制类型对应的id：促销，商家，商品
	 */
	public Long getLimitRef() {
		return limitRef;
	}

	/**
	 * 限制类型对应的id：促销，商家，商品
	 * @param limitRef 限制类型对应的id：促销，商家，商品
	 */
	public void setLimitRef(Long limitRef) {
		this.limitRef = limitRef;
	}
	/**
	 * 全网限制数量总数
	 * @return 全网限制数量总数
	 */
	public Integer getTotalLimit() {
		return totalLimit;
	}

	/**
	 * 全网限制数量总数
	 * @param totalLimit 全网限制数量总数
	 */
	public void setTotalLimit(Integer totalLimit) {
		this.totalLimit = totalLimit;
	}
	/**
	 * limit_ref对应的限制数量
	 * @return limit_ref对应的限制数量
	 */
	public Integer getIndividualLimit() {
		return individualLimit;
	}

	/**
	 * limit_ref对应的限制数量
	 * @param individualLimit limit_ref对应的限制数量
	 */
	public void setIndividualLimit(Integer individualLimit) {
		this.individualLimit = individualLimit;
	}
	/**
	 * 规则的状态
	 * @return 规则的状态
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 规则的状态
	 * @param status 规则的状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	