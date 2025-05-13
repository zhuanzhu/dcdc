package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 促销活动ID
	 */
	private Long promotionId;	

	/**
	 * 
	 */
	private Date ruleTimeConfig1;	

	/**
	 * 
	 */
	private Date ruleTimeConfig2;	

	/**
	 * 6: 支付方式限制
	 */
	private Integer ruleType;	

	/**
	 * 
	 */
	private BigDecimal ruleAmount;	

	/**
	 * 扩展金额字段一。保留字段
	 */
	private BigDecimal ruleAmountExt1;	

	/**
	 * 
	 */
	private Integer ruleVal;	

	/**
	 *  保留字段
	 */
	private Integer ruleVal1;	

	/**
	 * 类型为6时 1级支付方式限制
	 */
	private String ruleValStr1;	

	/**
	 * 类型为6时 2级支付方式限制
	 */
	private String ruleValStr2;	

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
	 * 促销活动ID
	 * @return 促销活动ID
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 促销活动ID
	 * @param promotionId 促销活动ID
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	/**
	 * 
	 * @return 
	 */
	public Date getRuleTimeConfig1() {
		return ruleTimeConfig1;
	}

	/**
	 * 
	 * @param ruleTimeConfig1 
	 */
	public void setRuleTimeConfig1(Date ruleTimeConfig1) {
		this.ruleTimeConfig1 = ruleTimeConfig1;
	}
	/**
	 * 
	 * @return 
	 */
	public Date getRuleTimeConfig2() {
		return ruleTimeConfig2;
	}

	/**
	 * 
	 * @param ruleTimeConfig2 
	 */
	public void setRuleTimeConfig2(Date ruleTimeConfig2) {
		this.ruleTimeConfig2 = ruleTimeConfig2;
	}
	/**
	 * 6: 支付方式限制
	 * @return 6: 支付方式限制
	 */
	public Integer getRuleType() {
		return ruleType;
	}

	/**
	 * 6: 支付方式限制
	 * @param ruleType 6: 支付方式限制
	 */
	public void setRuleType(Integer ruleType) {
		this.ruleType = ruleType;
	}
	/**
	 * 
	 * @return 
	 */
	public BigDecimal getRuleAmount() {
		return ruleAmount;
	}

	/**
	 * 
	 * @param ruleAmount 
	 */
	public void setRuleAmount(BigDecimal ruleAmount) {
		this.ruleAmount = ruleAmount;
	}
	/**
	 * 扩展金额字段一。保留字段
	 * @return 扩展金额字段一。保留字段
	 */
	public BigDecimal getRuleAmountExt1() {
		return ruleAmountExt1;
	}

	/**
	 * 扩展金额字段一。保留字段
	 * @param ruleAmountExt1 扩展金额字段一。保留字段
	 */
	public void setRuleAmountExt1(BigDecimal ruleAmountExt1) {
		this.ruleAmountExt1 = ruleAmountExt1;
	}
	/**
	 * 
	 * @return 
	 */
	public Integer getRuleVal() {
		return ruleVal;
	}

	/**
	 * 
	 * @param ruleVal 
	 */
	public void setRuleVal(Integer ruleVal) {
		this.ruleVal = ruleVal;
	}
	/**
	 *  保留字段
	 * @return  保留字段
	 */
	public Integer getRuleVal1() {
		return ruleVal1;
	}

	/**
	 *  保留字段
	 * @param ruleVal1  保留字段
	 */
	public void setRuleVal1(Integer ruleVal1) {
		this.ruleVal1 = ruleVal1;
	}
	/**
	 * 类型为6时 1级支付方式限制
	 * @return 类型为6时 1级支付方式限制
	 */
	public String getRuleValStr1() {
		return ruleValStr1;
	}

	/**
	 * 类型为6时 1级支付方式限制
	 * @param ruleValStr1 类型为6时 1级支付方式限制
	 */
	public void setRuleValStr1(String ruleValStr1) {
		this.ruleValStr1 = ruleValStr1;
	}
	/**
	 * 类型为6时 2级支付方式限制
	 * @return 类型为6时 2级支付方式限制
	 */
	public String getRuleValStr2() {
		return ruleValStr2;
	}

	/**
	 * 类型为6时 2级支付方式限制
	 * @param ruleValStr2 类型为6时 2级支付方式限制
	 */
	public void setRuleValStr2(String ruleValStr2) {
		this.ruleValStr2 = ruleValStr2;
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
	