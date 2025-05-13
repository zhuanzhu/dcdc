package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author min
 * @date 2018-01-17 17:03:10
 */
public class CartItemDTO implements Serializable {

	private String snapshot;
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 购物车id
	 */
	private Long cartId;

	/**
	 * su商品id
	 */
	private Long standardUnitId;

	/**
	 * 商家id
	 */
	private Long merchantId;

	/**
	 * 购买数量
	 */
	private Long num;

	/**
	 * 是否赠品:默认0非赠品;1赠品
	 */
	private Integer isGift;
	//	1-平台，2-代理商  3-京东
	private Integer source;

	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;

	/**
	 * 优惠金额
	 */
	private BigDecimal discountAmount;

	/**
	 * 现金金额
	 */
	private BigDecimal cashAmount;

	/**
	 * 积分金额
	 */
	private BigDecimal blessingCoinAmount;

	/**
	 * 促销id
	 */
	private Long promotionId;

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;

	/**
	 * 平台id
	 */
	private Long platformId;

	/**
	 * 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 */
	private Long productUnitId;

	/**
	 * 渠道产品id（渠道商品id）
	 */
	private String channelProductId;

	public String getSnapshot() {
		return snapshot;
	}
	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}
	public Integer getSource() {
		return source;
	}
	public boolean isJd() {
		return source!=null && source.intValue()==3;
	}
	public void setSource(Integer source) {
		this.source = source;
	}

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
	 * 购物车id
	 * @return 购物车id
	 */
	public Long getCartId() {
		return cartId;
	}

	/**
	 * 购物车id
	 * @param cartId 购物车id
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	/**
	 * su商品id
	 * @return su商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * su商品id
	 * @param standardUnitId su商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
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
	 * 购买数量
	 * @return 购买数量
	 */
	public Long getNum() {
		return num;
	}

	/**
	 * 购买数量
	 * @param num 购买数量
	 */
	public void setNum(Long num) {
		this.num = num;
	}
	/**
	 * 是否赠品:默认0非赠品;1赠品
	 * @return 是否赠品:默认0非赠品;1赠品
	 */
	public Integer getIsGift() {
		return isGift;
	}

	/**
	 * 是否赠品:默认0非赠品;1赠品
	 * @param isGift 是否赠品:默认0非赠品;1赠品
	 */
	public void setIsGift(Integer isGift) {
		this.isGift = isGift;
	}
	/**
	 * 销售价格
	 * @return 销售价格
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * 销售价格
	 * @param salePrice 销售价格
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 优惠金额
	 * @return 优惠金额
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 优惠金额
	 * @param discountAmount 优惠金额
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	/**
	 * 现金金额
	 * @return 现金金额
	 */
	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	/**
	 * 现金金额
	 * @param cashAmount 现金金额
	 */
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}
	/**
	 * 积分金额
	 * @return 积分金额
	 */
	public BigDecimal getBlessingCoinAmount() {
		return blessingCoinAmount;
	}

	/**
	 * 积分金额
	 * @param blessingCoinAmount 积分金额
	 */
	public void setBlessingCoinAmount(BigDecimal blessingCoinAmount) {
		this.blessingCoinAmount = blessingCoinAmount;
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
	/**
	 * 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 * @return 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 */
	public Long getProductUnitId() {
		return productUnitId;
	}

	/**
	 * 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 * @param productUnitId 商品的pu-id，一个pu对应一个商品，一个商品对应多个pu
	 */
	public void setProductUnitId(Long productUnitId) {
		this.productUnitId = productUnitId;
	}

	public boolean isCake() {
		return source!=null && source.intValue()==4;
	}

	public boolean isWorld() {
		return source!=null && source.intValue()==5;
	}

	public String getChannelProductId() {
		return channelProductId;
	}

	public void setChannelProductId(String channelProductId) {
		this.channelProductId = channelProductId;
	}
}
