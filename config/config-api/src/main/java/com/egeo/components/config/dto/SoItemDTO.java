package com.egeo.components.config.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-29 09:59:04
 */
public class SoItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String childCode;

	private String orderCode;
	
	/**
	 * 子订单id
	 */
	private Long soChildId;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 是否存在unit库存商品 0:否  1:是
	 */
	private Integer unitExist;	

	/**
	 * puid
	 */
	private Long puId;	
	/**
	 * skuId
	 */
	private Long skuId;

	/**
	 * pu数量
	 */
	private Integer puCount;	

	/**
	 * 商品加入购物车类型  1 正常品 2 赠品 3 换购品 4 实物券
	 */
	private Integer cartType;	

	/**
	 * 商品促销id
	 */
	private Long promotionId;	

	/**
	 * 销售价格显示值,提交结算时候的商品销售价/促销价
	 */
	private BigDecimal price;	

	/**
	 * 商品平摊优惠金额
	 */
	private BigDecimal promotionAver;	

	/**
	 * 最终平摊金额
	 */
	private BigDecimal finalPromotionAver;	

	/**
	 * 母订单id
	 */
	private Long soId;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	
	/**
	 * 商品图片
	 */
	private String puPicUrl;
	
	/**
	 * 商品名
	 */
	private String puName;
	
	/**
	 * 运营方ID
	 */
	private Long merchantId;
	/**
	 * 预售期
	 */
	private Long preDay;
	/**
	 * 外部SKU ID
	 */
	private String externalSkuId;
	/**
	 * 单个商品运费平摊金额（不含运费优惠）
	 */
	private BigDecimal afterDiscountPriceAver;
	/**
	 * 单个商品优惠券平摊优惠金额
	 */
	private BigDecimal deliveryFeeAver;
	/**
	 * 单个商品门店优惠平摊优惠金额
	 */
	private BigDecimal storeDiscountAver;
	/**
	 * 单个商品运费优惠平摊优惠金额
	 */
	private BigDecimal deliveryFeeDiscountAver;
	
	public BigDecimal getAfterDiscountPriceAver() {
		return afterDiscountPriceAver;
	}

	public void setAfterDiscountPriceAver(BigDecimal afterDiscountPriceAver) {
		this.afterDiscountPriceAver = afterDiscountPriceAver;
	}

	public BigDecimal getDeliveryFeeAver() {
		return deliveryFeeAver;
	}

	public void setDeliveryFeeAver(BigDecimal deliveryFeeAver) {
		this.deliveryFeeAver = deliveryFeeAver;
	}

	public BigDecimal getStoreDiscountAver() {
		return storeDiscountAver;
	}

	public void setStoreDiscountAver(BigDecimal storeDiscountAver) {
		this.storeDiscountAver = storeDiscountAver;
	}

	public BigDecimal getDeliveryFeeDiscountAver() {
		return deliveryFeeDiscountAver;
	}

	public void setDeliveryFeeDiscountAver(BigDecimal deliveryFeeDiscountAver) {
		this.deliveryFeeDiscountAver = deliveryFeeDiscountAver;
	}
	
	public String getExternalSkuId() {
		return externalSkuId;
	}

	public void setExternalSkuId(String externalSkuId) {
		this.externalSkuId = externalSkuId;
	}

	public String getPuPicUrl() {
		return puPicUrl;
	}

	public void setPuPicUrl(String puPicUrl) {
		this.puPicUrl = puPicUrl;
	}

	public String getPuName() {
		return puName;
	}

	public void setPuName(String puName) {
		this.puName = puName;
	}

	public Long getId() {
		return id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/**
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 子订单id
	 * @return 子订单id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单id
	 * @param soChildId 子订单id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}
	/**
	 * 用户ID
	 * @return 用户ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 是否存在unit库存商品 0:否  1:是
	 * @return 是否存在unit库存商品 0:否  1:是
	 */
	public Integer getUnitExist() {
		return unitExist;
	}

	/**
	 * 是否存在unit库存商品 0:否  1:是
	 * @param unitExist 是否存在unit库存商品 0:否  1:是
	 */
	public void setUnitExist(Integer unitExist) {
		this.unitExist = unitExist;
	}
	/**
	 * puid
	 * @return puid
	 */
	public Long getPuId() {
		return puId;
	}

	/**
	 * puid
	 * @param puId puid
	 */
	public void setPuId(Long puId) {
		this.puId = puId;
	}
	/**
	 * pu数量
	 * @return pu数量
	 */
	public Integer getPuCount() {
		return puCount;
	}

	/**
	 * pu数量
	 * @param puCount pu数量
	 */
	public void setPuCount(Integer puCount) {
		this.puCount = puCount;
	}
	/**
	 * 商品加入购物车类型  1 正常品 2 赠品 3 换购品 4 实物券
	 * @return 商品加入购物车类型  1 正常品 2 赠品 3 换购品 4 实物券
	 */
	public Integer getCartType() {
		return cartType;
	}

	/**
	 * 商品加入购物车类型  1 正常品 2 赠品 3 换购品 4 实物券
	 * @param cartType 商品加入购物车类型  1 正常品 2 赠品 3 换购品 4 实物券
	 */
	public void setCartType(Integer cartType) {
		this.cartType = cartType;
	}
	/**
	 * 商品促销id
	 * @return 商品促销id
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 商品促销id
	 * @param promotionId 商品促销id
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}
	/**
	 * 销售价格显示值,提交结算时候的商品销售价/促销价
	 * @return 销售价格显示值,提交结算时候的商品销售价/促销价
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 销售价格显示值,提交结算时候的商品销售价/促销价
	 * @param price 销售价格显示值,提交结算时候的商品销售价/促销价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 商品平摊优惠金额
	 * @return 商品平摊优惠金额
	 */
	public BigDecimal getPromotionAver() {
		return promotionAver;
	}

	/**
	 * 商品平摊优惠金额
	 * @param promotionAver 商品平摊优惠金额
	 */
	public void setPromotionAver(BigDecimal promotionAver) {
		this.promotionAver = promotionAver;
	}
	/**
	 * 最终平摊金额
	 * @return 最终平摊金额
	 */
	public BigDecimal getFinalPromotionAver() {
		return finalPromotionAver;
	}

	/**
	 * 最终平摊金额
	 * @param finalPromotionAver 最终平摊金额
	 */
	public void setFinalPromotionAver(BigDecimal finalPromotionAver) {
		this.finalPromotionAver = finalPromotionAver;
	}
	/**
	 * 母订单id
	 * @return 母订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 母订单id
	 * @param soId 母订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
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
	
	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getPreDay() {
		return preDay;
	}

	public void setPreDay(Long preDay) {
		this.preDay = preDay;
	}

	/**
	 * 拷贝当前订单项以备插入
	 * 不需要拷贝的成员变量注释掉
	 * (拆单逻辑使用)
	 * @return
	 */
	public SoItemDTO copyThis() {
		SoItemDTO tar=new SoItemDTO();
		//tar.setId(this.getId());
		//tar.setSoChildId(this.getSoChildId());
		tar.setUserId(this.getUserId());
		tar.setUnitExist(this.getUnitExist());
		tar.setPuId(this.getPuId());
		//tar.setPuCount(this.getPuCount());
		tar.setCartType(this.getCartType());
		tar.setPromotionId(this.getPromotionId());
		tar.setPrice(this.getPrice());
		tar.setPromotionAver(this.getPromotionAver());
		tar.setFinalPromotionAver(this.getFinalPromotionAver());
		tar.setSoId(this.getSoId());
		//tar.setUpdateTime(this.getUpdateTime());
		//tar.setCreateTime(this.getCreateTime());
		tar.setPlatformId(this.getPlatformId());
		tar.setPuPicUrl(this.getPuPicUrl());
		tar.setPuName(this.getPuName());
		return tar;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
}
	