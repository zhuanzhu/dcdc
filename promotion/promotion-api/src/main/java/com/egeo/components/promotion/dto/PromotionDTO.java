package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class PromotionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 促销活动所属的主题id
	 */
	private Long themeId;	

	/**
	 * 促销活动开始时间
	 */
	private Date startTime;	

	/**
	 * 促销活动结束时间
	 */
	private Date endTime;	

	/**
	 * 促销标题
	 */
	private String promTitle;	

	/**
	 * 促销范围 1 PC和无线通用 2 无线专享
	 */
	private Integer promPlatform;	

	/**
	 * 促销类型 1，单一商品促销 2， 商品满额促销 3，商品组合促销
	 */
	private Integer promType;	

	/**
	 * 1 不限制 2 限制老用户 3 限制新用户
	 */
	private Integer userScope;	

	/**
	 * 参与方式 1 整单促销 2 范围促销
	 */
	private Integer joinType;	

	/**
	 * 参与次数限制 0 不限制 其他 限定次数N
	 */
	private Integer joinTimesLimit;	

	/**
	 * 活动状态 0 未审核 1 审核通过 2 活动生效
	 */
	private Integer status;	

	/**
	 * 
	 */
	private Long platformId;	

	/**
	 * 限定支付方式：1 支付宝、2 微信、3 银联、4 货到付款（多种支付方式，用;分割）
	 */
	private String payType;	

	/**
	 * 限制类型：0 不限，1 促销，2 商家，3 商品
	 */
	private Integer limitType;	

	/**
	 * 促销方式（优惠的内容） 1 特价 2 折扣 3 折价 4 搭增
	 */
	private Integer contentType;	

	/**
	 * 0:无赠品 1：超量赠品 2：倍量赠品
	 */
	private Integer giftRule;	

	/**
	 * 0:无限制 1：一级支付方式限制 2：二级支付方式限制
	 */
	private Integer payTypeRule;	

	/**
	 * 赠品最多个数，gift_rule＝2（倍量） 用到
	 */
	private Integer giftLimit4Multy;	

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
	 * 促销id
	 * @param id 促销id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 促销活动所属的主题id
	 * @return 促销活动所属的主题id
	 */
	public Long getThemeId() {
		return themeId;
	}

	/**
	 * 促销活动所属的主题id
	 * @param themeId 促销活动所属的主题id
	 */
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}
	/**
	 * 促销活动开始时间
	 * @return 促销活动开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 促销活动开始时间
	 * @param startTime 促销活动开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 促销活动结束时间
	 * @return 促销活动结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 促销活动结束时间
	 * @param endTime 促销活动结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 促销标题
	 * @return 促销标题
	 */
	public String getPromTitle() {
		return promTitle;
	}

	/**
	 * 促销标题
	 * @param promTitle 促销标题
	 */
	public void setPromTitle(String promTitle) {
		this.promTitle = promTitle;
	}
	/**
	 * 促销范围 1 PC和无线通用 2 无线专享
	 * @return 促销范围 1 PC和无线通用 2 无线专享
	 */
	public Integer getPromPlatform() {
		return promPlatform;
	}

	/**
	 * 促销范围 1 PC和无线通用 2 无线专享
	 * @param promPlatform 促销范围 1 PC和无线通用 2 无线专享
	 */
	public void setPromPlatform(Integer promPlatform) {
		this.promPlatform = promPlatform;
	}
	/**
	 * 促销类型 1，单一商品促销 2， 商品满额促销 3，商品组合促销
	 * @return 促销类型 1，单一商品促销 2， 商品满额促销 3，商品组合促销
	 */
	public Integer getPromType() {
		return promType;
	}

	/**
	 * 促销类型 1，单一商品促销 2， 商品满额促销 3，商品组合促销
	 * @param promType 促销类型 1，单一商品促销 2， 商品满额促销 3，商品组合促销
	 */
	public void setPromType(Integer promType) {
		this.promType = promType;
	}
	/**
	 * 1 不限制 2 限制老用户 3 限制新用户
	 * @return 1 不限制 2 限制老用户 3 限制新用户
	 */
	public Integer getUserScope() {
		return userScope;
	}

	/**
	 * 1 不限制 2 限制老用户 3 限制新用户
	 * @param userScope 1 不限制 2 限制老用户 3 限制新用户
	 */
	public void setUserScope(Integer userScope) {
		this.userScope = userScope;
	}
	/**
	 * 参与方式 1 整单促销 2 范围促销
	 * @return 参与方式 1 整单促销 2 范围促销
	 */
	public Integer getJoinType() {
		return joinType;
	}

	/**
	 * 参与方式 1 整单促销 2 范围促销
	 * @param joinType 参与方式 1 整单促销 2 范围促销
	 */
	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}
	/**
	 * 参与次数限制 0 不限制 其他 限定次数N
	 * @return 参与次数限制 0 不限制 其他 限定次数N
	 */
	public Integer getJoinTimesLimit() {
		return joinTimesLimit;
	}

	/**
	 * 参与次数限制 0 不限制 其他 限定次数N
	 * @param joinTimesLimit 参与次数限制 0 不限制 其他 限定次数N
	 */
	public void setJoinTimesLimit(Integer joinTimesLimit) {
		this.joinTimesLimit = joinTimesLimit;
	}
	/**
	 * 活动状态 0 未审核 1 审核通过 2 活动生效
	 * @return 活动状态 0 未审核 1 审核通过 2 活动生效
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 活动状态 0 未审核 1 审核通过 2 活动生效
	 * @param status 活动状态 0 未审核 1 审核通过 2 活动生效
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
	 * 限定支付方式：1 支付宝、2 微信、3 银联、4 货到付款（多种支付方式，用;分割）
	 * @return 限定支付方式：1 支付宝、2 微信、3 银联、4 货到付款（多种支付方式，用;分割）
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 限定支付方式：1 支付宝、2 微信、3 银联、4 货到付款（多种支付方式，用;分割）
	 * @param payType 限定支付方式：1 支付宝、2 微信、3 银联、4 货到付款（多种支付方式，用;分割）
	 */
	public void setPayType(String payType) {
		this.payType = payType;
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
	 * 促销方式（优惠的内容） 1 特价 2 折扣 3 折价 4 搭增
	 * @return 促销方式（优惠的内容） 1 特价 2 折扣 3 折价 4 搭增
	 */
	public Integer getContentType() {
		return contentType;
	}

	/**
	 * 促销方式（优惠的内容） 1 特价 2 折扣 3 折价 4 搭增
	 * @param contentType 促销方式（优惠的内容） 1 特价 2 折扣 3 折价 4 搭增
	 */
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	/**
	 * 0:无赠品 1：超量赠品 2：倍量赠品
	 * @return 0:无赠品 1：超量赠品 2：倍量赠品
	 */
	public Integer getGiftRule() {
		return giftRule;
	}

	/**
	 * 0:无赠品 1：超量赠品 2：倍量赠品
	 * @param giftRule 0:无赠品 1：超量赠品 2：倍量赠品
	 */
	public void setGiftRule(Integer giftRule) {
		this.giftRule = giftRule;
	}
	/**
	 * 0:无限制 1：一级支付方式限制 2：二级支付方式限制
	 * @return 0:无限制 1：一级支付方式限制 2：二级支付方式限制
	 */
	public Integer getPayTypeRule() {
		return payTypeRule;
	}

	/**
	 * 0:无限制 1：一级支付方式限制 2：二级支付方式限制
	 * @param payTypeRule 0:无限制 1：一级支付方式限制 2：二级支付方式限制
	 */
	public void setPayTypeRule(Integer payTypeRule) {
		this.payTypeRule = payTypeRule;
	}
	/**
	 * 赠品最多个数，gift_rule＝2（倍量） 用到
	 * @return 赠品最多个数，gift_rule＝2（倍量） 用到
	 */
	public Integer getGiftLimit4Multy() {
		return giftLimit4Multy;
	}

	/**
	 * 赠品最多个数，gift_rule＝2（倍量） 用到
	 * @param giftLimit4Multy 赠品最多个数，gift_rule＝2（倍量） 用到
	 */
	public void setGiftLimit4Multy(Integer giftLimit4Multy) {
		this.giftLimit4Multy = giftLimit4Multy;
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
	