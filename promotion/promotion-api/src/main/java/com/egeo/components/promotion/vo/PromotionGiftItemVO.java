package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:02:51
 */
public class PromotionGiftItemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 促销ID
	 */

	private Long promotionId;		 
	/**
	 * 赠品ID
	 */

	private Long giftRef;		 
	/**
	 *  赠品类型 1：商品 
	 */

	private Integer giftType;		 
	/**
	 * 优惠层级 
	 */

	private Integer giftLevel;		 
	/**
	 * 优惠层级关联
	 */

	private Long levelRef;		 
	/**
	 * 换购金额
	 */

	private BigDecimal upgradeAmount;		 
	/**
	 * 扩展限制
	 */

	private String extendRef;		 
	/**
	 * 
	 */

	private Long platformId;		 
	/**
	 * 商家id
	 */

	private Long merchantId;		 
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
	 * 促销ID
	 * @return 促销ID
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 促销ID
	 * @param promotionId 促销ID
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}	
	/**
	 * 赠品ID
	 * @return 赠品ID
	 */
	public Long getGiftRef() {
		return giftRef;
	}

	/**
	 * 赠品ID
	 * @param giftRef 赠品ID
	 */
	public void setGiftRef(Long giftRef) {
		this.giftRef = giftRef;
	}	
	/**
	 *  赠品类型 1：商品 
	 * @return  赠品类型 1：商品 
	 */
	public Integer getGiftType() {
		return giftType;
	}

	/**
	 *  赠品类型 1：商品 
	 * @param giftType  赠品类型 1：商品 
	 */
	public void setGiftType(Integer giftType) {
		this.giftType = giftType;
	}	
	/**
	 * 优惠层级 
	 * @return 优惠层级 
	 */
	public Integer getGiftLevel() {
		return giftLevel;
	}

	/**
	 * 优惠层级 
	 * @param giftLevel 优惠层级 
	 */
	public void setGiftLevel(Integer giftLevel) {
		this.giftLevel = giftLevel;
	}	
	/**
	 * 优惠层级关联
	 * @return 优惠层级关联
	 */
	public Long getLevelRef() {
		return levelRef;
	}

	/**
	 * 优惠层级关联
	 * @param levelRef 优惠层级关联
	 */
	public void setLevelRef(Long levelRef) {
		this.levelRef = levelRef;
	}	
	/**
	 * 换购金额
	 * @return 换购金额
	 */
	public BigDecimal getUpgradeAmount() {
		return upgradeAmount;
	}

	/**
	 * 换购金额
	 * @param upgradeAmount 换购金额
	 */
	public void setUpgradeAmount(BigDecimal upgradeAmount) {
		this.upgradeAmount = upgradeAmount;
	}	
	/**
	 * 扩展限制
	 * @return 扩展限制
	 */
	public String getExtendRef() {
		return extendRef;
	}

	/**
	 * 扩展限制
	 * @param extendRef 扩展限制
	 */
	public void setExtendRef(String extendRef) {
		this.extendRef = extendRef;
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
	 * 商家id
	 * @return 商家id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家id
	 * @param merchantId 商家id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
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
	