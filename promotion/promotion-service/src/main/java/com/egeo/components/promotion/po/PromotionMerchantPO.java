package com.egeo.components.promotion.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:25
 */
public class PromotionMerchantPO {


	private Long id;

	/**
	 * 主题id，当商家关联标识为1，值有效
	 */
	private Long themeId;	

	/**
	 * 促销活动id，当商家关联标识为2，值有效
	 */
	private Long promotionId;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 商家关联标识 1.主题，2. 促销活动
	 */
	private Integer merchantIndicator;	

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
	 * 主题id，当商家关联标识为1，值有效
	 * @return 主题id，当商家关联标识为1，值有效
	 */
	public Long getThemeId() {
		return themeId;
	}

	/**
	 * 主题id，当商家关联标识为1，值有效
	 * @param themeId 主题id，当商家关联标识为1，值有效
	 */
	public void setThemeId(Long themeId) {
		this.themeId = themeId;
	}

	/**
	 * 促销活动id，当商家关联标识为2，值有效
	 * @return 促销活动id，当商家关联标识为2，值有效
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 促销活动id，当商家关联标识为2，值有效
	 * @param promotionId 促销活动id，当商家关联标识为2，值有效
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
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
	 * 商家关联标识 1.主题，2. 促销活动
	 * @return 商家关联标识 1.主题，2. 促销活动
	 */
	public Integer getMerchantIndicator() {
		return merchantIndicator;
	}

	/**
	 * 商家关联标识 1.主题，2. 促销活动
	 * @param merchantIndicator 商家关联标识 1.主题，2. 促销活动
	 */
	public void setMerchantIndicator(Integer merchantIndicator) {
		this.merchantIndicator = merchantIndicator;
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
	