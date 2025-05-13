package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoReturnItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 退货id
	 */
	private Long returnId;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 商家ID
	 */
	private Long merchantId;	

	/**
	 * 商品ID
	 */
	private Long mpId;	

	/**
	 * 商品销售单价
	 */
	private BigDecimal productPriceFinal;	

	/**
	 * 商品购买数量
	 */
	private Integer productItemNum;	

	/**
	 * 计件单位
	 */
	private String pieceworkUnit;	

	/**
	 * 商品总金额
	 */
	private BigDecimal productTotalAmount;	

	/**
	 * 退回商品购买数量
	 */
	private Integer returnProductItemNum;	

	/**
	 * 折扣金额
	 */
	private BigDecimal discountAmount;	

	/**
	 * 优惠券金额
	 */
	private BigDecimal couponAmount;	

	/**
	 * 购买金额
	 */
	private BigDecimal purchaseAmount;	

	/**
	 * 产品中文名
	 */
	private String productCname;	

	/**
	 * 图片URL
	 */
	private String productPicPath;	

	/**
	 * so_item表id
	 */
	private Long soItemId;	

	/**
	 * 商品销售单价
	 */
	private BigDecimal productPriceSale;	

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
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	private Long skuId;	

	public Long getId() {
		return id;
	}

	/**
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 退货id
	 * @return 退货id
	 */
	public Long getReturnId() {
		return returnId;
	}

	/**
	 * 退货id
	 * @param returnId 退货id
	 */
	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}
	/**
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 商家ID
	 * @return 商家ID
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家ID
	 * @param merchantId 商家ID
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * 商品ID
	 * @return 商品ID
	 */
	public Long getMpId() {
		return mpId;
	}

	/**
	 * 商品ID
	 * @param mpId 商品ID
	 */
	public void setMpId(Long mpId) {
		this.mpId = mpId;
	}
	/**
	 * 商品销售单价
	 * @return 商品销售单价
	 */
	public BigDecimal getProductPriceFinal() {
		return productPriceFinal;
	}

	/**
	 * 商品销售单价
	 * @param productPriceFinal 商品销售单价
	 */
	public void setProductPriceFinal(BigDecimal productPriceFinal) {
		this.productPriceFinal = productPriceFinal;
	}
	/**
	 * 商品购买数量
	 * @return 商品购买数量
	 */
	public Integer getProductItemNum() {
		return productItemNum;
	}

	/**
	 * 商品购买数量
	 * @param productItemNum 商品购买数量
	 */
	public void setProductItemNum(Integer productItemNum) {
		this.productItemNum = productItemNum;
	}
	/**
	 * 计件单位
	 * @return 计件单位
	 */
	public String getPieceworkUnit() {
		return pieceworkUnit;
	}

	/**
	 * 计件单位
	 * @param pieceworkUnit 计件单位
	 */
	public void setPieceworkUnit(String pieceworkUnit) {
		this.pieceworkUnit = pieceworkUnit;
	}
	/**
	 * 商品总金额
	 * @return 商品总金额
	 */
	public BigDecimal getProductTotalAmount() {
		return productTotalAmount;
	}

	/**
	 * 商品总金额
	 * @param productTotalAmount 商品总金额
	 */
	public void setProductTotalAmount(BigDecimal productTotalAmount) {
		this.productTotalAmount = productTotalAmount;
	}
	/**
	 * 退回商品购买数量
	 * @return 退回商品购买数量
	 */
	public Integer getReturnProductItemNum() {
		return returnProductItemNum;
	}

	/**
	 * 退回商品购买数量
	 * @param returnProductItemNum 退回商品购买数量
	 */
	public void setReturnProductItemNum(Integer returnProductItemNum) {
		this.returnProductItemNum = returnProductItemNum;
	}
	/**
	 * 折扣金额
	 * @return 折扣金额
	 */
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 折扣金额
	 * @param discountAmount 折扣金额
	 */
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	/**
	 * 优惠券金额
	 * @return 优惠券金额
	 */
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	/**
	 * 优惠券金额
	 * @param couponAmount 优惠券金额
	 */
	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}
	/**
	 * 购买金额
	 * @return 购买金额
	 */
	public BigDecimal getPurchaseAmount() {
		return purchaseAmount;
	}

	/**
	 * 购买金额
	 * @param purchaseAmount 购买金额
	 */
	public void setPurchaseAmount(BigDecimal purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	/**
	 * 产品中文名
	 * @return 产品中文名
	 */
	public String getProductCname() {
		return productCname;
	}

	/**
	 * 产品中文名
	 * @param productCname 产品中文名
	 */
	public void setProductCname(String productCname) {
		this.productCname = productCname;
	}
	/**
	 * 图片URL
	 * @return 图片URL
	 */
	public String getProductPicPath() {
		return productPicPath;
	}

	/**
	 * 图片URL
	 * @param productPicPath 图片URL
	 */
	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}
	/**
	 * so_item表id
	 * @return so_item表id
	 */
	public Long getSoItemId() {
		return soItemId;
	}

	/**
	 * so_item表id
	 * @param soItemId so_item表id
	 */
	public void setSoItemId(Long soItemId) {
		this.soItemId = soItemId;
	}
	/**
	 * 商品销售单价
	 * @return 商品销售单价
	 */
	public BigDecimal getProductPriceSale() {
		return productPriceSale;
	}

	/**
	 * 商品销售单价
	 * @param productPriceSale 商品销售单价
	 */
	public void setProductPriceSale(BigDecimal productPriceSale) {
		this.productPriceSale = productPriceSale;
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
	/**
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 * @return 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 * @param skuId 商品的sku-id，一个sku对应一个商品，一个商品对应多个sku
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
}
	