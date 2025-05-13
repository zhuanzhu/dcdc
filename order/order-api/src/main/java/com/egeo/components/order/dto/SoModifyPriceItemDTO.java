package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoModifyPriceItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 父item的id
	 */
	private String parentOrderCode;	

	/**
	 * 商品ID
	 */
	private Long mpId;	

	/**
	 * 商品总金额
	 */
	private BigDecimal productItemAmount;	

	/**
	 * 改价之后商品总金额
	 */
	private BigDecimal productItemAmountAfter;	

	/**
	 * 商品折扣总金额
	 */
	private BigDecimal productItemDiscountAmount;	

	/**
	 * 商品销售单价
	 */
	private BigDecimal productPriceFinal;	

	/**
	 * 折扣后的单价(购买单价)
	 */
	private BigDecimal productPriceDiscount;	

	/**
	 * 商品购买数量
	 */
	private Integer productItemNum;	

	/**
	 * 产品中文名
	 */
	private String productCname;	

	/**
	 * 产品英文名
	 */
	private String productEname;	

	/**
	 * 商品编码
	 */
	private String code;	

	/**
	 * 计量单位
	 */
	private String unit;	

	/**
	 * 产地(国)
	 */
	private String placeOfOrigin;	

	/**
	 * 商品销售类型 1普通、2海购、3精品、4赠品
	 */
	private Integer productSaleType;	

	/**
	 * modify表的ID
	 */
	private Long modifyId;	

	/**
	 * so_item主键id
	 */
	private Long soItemId;	

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
	 * 父item的id
	 * @return 父item的id
	 */
	public String getParentOrderCode() {
		return parentOrderCode;
	}

	/**
	 * 父item的id
	 * @param parentOrderCode 父item的id
	 */
	public void setParentOrderCode(String parentOrderCode) {
		this.parentOrderCode = parentOrderCode;
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
	 * 商品总金额
	 * @return 商品总金额
	 */
	public BigDecimal getProductItemAmount() {
		return productItemAmount;
	}

	/**
	 * 商品总金额
	 * @param productItemAmount 商品总金额
	 */
	public void setProductItemAmount(BigDecimal productItemAmount) {
		this.productItemAmount = productItemAmount;
	}
	/**
	 * 改价之后商品总金额
	 * @return 改价之后商品总金额
	 */
	public BigDecimal getProductItemAmountAfter() {
		return productItemAmountAfter;
	}

	/**
	 * 改价之后商品总金额
	 * @param productItemAmountAfter 改价之后商品总金额
	 */
	public void setProductItemAmountAfter(BigDecimal productItemAmountAfter) {
		this.productItemAmountAfter = productItemAmountAfter;
	}
	/**
	 * 商品折扣总金额
	 * @return 商品折扣总金额
	 */
	public BigDecimal getProductItemDiscountAmount() {
		return productItemDiscountAmount;
	}

	/**
	 * 商品折扣总金额
	 * @param productItemDiscountAmount 商品折扣总金额
	 */
	public void setProductItemDiscountAmount(BigDecimal productItemDiscountAmount) {
		this.productItemDiscountAmount = productItemDiscountAmount;
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
	 * 折扣后的单价(购买单价)
	 * @return 折扣后的单价(购买单价)
	 */
	public BigDecimal getProductPriceDiscount() {
		return productPriceDiscount;
	}

	/**
	 * 折扣后的单价(购买单价)
	 * @param productPriceDiscount 折扣后的单价(购买单价)
	 */
	public void setProductPriceDiscount(BigDecimal productPriceDiscount) {
		this.productPriceDiscount = productPriceDiscount;
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
	 * 产品英文名
	 * @return 产品英文名
	 */
	public String getProductEname() {
		return productEname;
	}

	/**
	 * 产品英文名
	 * @param productEname 产品英文名
	 */
	public void setProductEname(String productEname) {
		this.productEname = productEname;
	}
	/**
	 * 商品编码
	 * @return 商品编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 商品编码
	 * @param code 商品编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 计量单位
	 * @return 计量单位
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 计量单位
	 * @param unit 计量单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 产地(国)
	 * @return 产地(国)
	 */
	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}

	/**
	 * 产地(国)
	 * @param placeOfOrigin 产地(国)
	 */
	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}
	/**
	 * 商品销售类型 1普通、2海购、3精品、4赠品
	 * @return 商品销售类型 1普通、2海购、3精品、4赠品
	 */
	public Integer getProductSaleType() {
		return productSaleType;
	}

	/**
	 * 商品销售类型 1普通、2海购、3精品、4赠品
	 * @param productSaleType 商品销售类型 1普通、2海购、3精品、4赠品
	 */
	public void setProductSaleType(Integer productSaleType) {
		this.productSaleType = productSaleType;
	}
	/**
	 * modify表的ID
	 * @return modify表的ID
	 */
	public Long getModifyId() {
		return modifyId;
	}

	/**
	 * modify表的ID
	 * @param modifyId modify表的ID
	 */
	public void setModifyId(Long modifyId) {
		this.modifyId = modifyId;
	}
	/**
	 * so_item主键id
	 * @return so_item主键id
	 */
	public Long getSoItemId() {
		return soItemId;
	}

	/**
	 * so_item主键id
	 * @param soItemId so_item主键id
	 */
	public void setSoItemId(Long soItemId) {
		this.soItemId = soItemId;
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
	