package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:25
 */
public class PromotionRuleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 促销活动id
	 */
	private Long promotionId;	

	/**
	 * 条件类型 1 满金额 2 满数量
	 */
	private Integer conditionType;	

	/**
	 * 金额／数量限制  金额单位是分 如满100元 这里就是 10000
	 */
	private Long conditionValue;	

	/**
	 * 优惠的内容 1 特价 2 折扣 3 折价 4 搭增
	 */
	private Integer contentType;	

	/**
	 * 优惠内容 打8折或减10元  特价10元（搭增没有）
	 */
	private Integer contentValue;	

	/**
	 * 
	 */
	private Long platformId;	

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
	 * 促销活动id
	 * @return 促销活动id
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 促销活动id
	 * @param promotionId 促销活动id
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	/**
	 * 条件类型 1 满金额 2 满数量
	 * @return 条件类型 1 满金额 2 满数量
	 */
	public Integer getConditionType() {
		return conditionType;
	}

	/**
	 * 条件类型 1 满金额 2 满数量
	 * @param conditionType 条件类型 1 满金额 2 满数量
	 */
	public void setConditionType(Integer conditionType) {
		this.conditionType = conditionType;
	}
	/**
	 * 金额／数量限制  金额单位是分 如满100元 这里就是 10000
	 * @return 金额／数量限制  金额单位是分 如满100元 这里就是 10000
	 */
	public Long getConditionValue() {
		return conditionValue;
	}

	/**
	 * 金额／数量限制  金额单位是分 如满100元 这里就是 10000
	 * @param conditionValue 金额／数量限制  金额单位是分 如满100元 这里就是 10000
	 */
	public void setConditionValue(Long conditionValue) {
		this.conditionValue = conditionValue;
	}
	/**
	 * 优惠的内容 1 特价 2 折扣 3 折价 4 搭增
	 * @return 优惠的内容 1 特价 2 折扣 3 折价 4 搭增
	 */
	public Integer getContentType() {
		return contentType;
	}

	/**
	 * 优惠的内容 1 特价 2 折扣 3 折价 4 搭增
	 * @param contentType 优惠的内容 1 特价 2 折扣 3 折价 4 搭增
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	/**
	 * 优惠内容 打8折或减10元  特价10元（搭增没有）
	 * @return 优惠内容 打8折或减10元  特价10元（搭增没有）
	 */
	public Integer getContentValue() {
		return contentValue;
	}

	/**
	 * 优惠内容 打8折或减10元  特价10元（搭增没有）
	 * @param contentValue 优惠内容 打8折或减10元  特价10元（搭增没有）
	 */
	public void setContentValue(Integer contentValue) {
		this.contentValue = contentValue;
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
	