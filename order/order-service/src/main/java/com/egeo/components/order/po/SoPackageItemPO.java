package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoPackageItemPO {


	private Long id;

	/**
	 * 包裹编号
	 */
	private Long soPackageId;	

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	private String orderCode;	

	/**
	 * 父订单编号
	 */
	private String parentOrderCode;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 商家ID
	 */
	private Long merchantId;	

	/**
	 * 产品ID
	 */
	private Long productId;	

	/**
	 * 商品ID
	 */
	private Long mpId;	

	/**
	 * 仓库ID
	 */
	private Long warehouseId;	

	/**
	 * 商品总金额
	 */
	private BigDecimal productItemAmount;	

	/**
	 * 商品销售单价
	 */
	private BigDecimal productPriceFinal;	

	/**
	 * 商品购买数量
	 */
	private Integer productItemNum;	

	/**
	 * 出库数量
	 */
	private Integer productItemOutNum;	

	/**
	 * 产品中文名
	 */
	private String productCname;	

	/**
	 * 产品英文名
	 */
	private String productEname;	

	/**
	 * 产品图片URL
	 */
	private String productPicPath;	

	/**
	 * 产品信息版本号
	 */
	private Long productVersionNo;	

	/**
	 * 商品销售类型 1普通、2海购、3精品、4赠品
	 */
	private Integer productSaleType;	

	/**
	 * 商品原始价
	 */
	private BigDecimal productPriceOriginal;	

	/**
	 * 商品市场价
	 */
	private BigDecimal productPriceMarket;	

	/**
	 * 商品销售价
	 */
	private BigDecimal productPriceSale;	

	/**
	 * 行费税
	 */
	private BigDecimal productTaxAmount;	

	/**
	 * 0,普通 2积分兑换 3 抽奖 4 满赠
	 */
	private Integer buyType;	

	/**
	 * 产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡
	 */
	private Integer productType;	

	/**
	 * 分摊到此ITEM的抵用券金额
	 */
	private BigDecimal amountShareCoupon;	

	/**
	 * 分摊到此ITEM的优惠金额(满立减)
	 */
	private BigDecimal amountSharePromotion;	

	/**
	 * 分摊到此ITEM的运费金额
	 */
	private BigDecimal amountShareDeliveryFee;	

	/**
	 * 分摊到此ITEM的核算运费金额
	 */
	private BigDecimal amountShareDeliveryFeeAccounting;	

	/**
	 * 产品毛重
	 */
	private BigDecimal productGrossWeight;	

	/**
	 * 整车质保
	 */
	private String vehicleWarranty;	

	/**
	 * 交货时间
	 */
	private String deliveryTime;	

	/**
	 * 发货方式
	 */
	private String deliveryMethod;	

	/**
	 * 违约责任
	 */
	private String violationResponsibility;	

	/**
	 * 商品来源id（询价单id 或者 租赁单id）
	 */
	private Long sourceId;	

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
	 * 消费税
	 */
	private BigDecimal exciseTaxAmount;	

	/**
	 * 增值税
	 */
	private BigDecimal incrementTaxAmount;	

	/**
	 * 关税
	 */
	private BigDecimal customsDutiesAmount;	

	/**
	 * 扩展信息，以json形式存储
	 */
	private String extInfo;	

	/**
	 * 订单itemId
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

	public Long getSoPackageId() {
		return soPackageId;
	}

	public void setSoPackageId(Long soPackageId) {
		this.soPackageId = soPackageId;
	}

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 * @return 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 * @param orderCode 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * 父订单编号
	 * @return 父订单编号
	 */
	public String getParentOrderCode() {
		return parentOrderCode;
	}

	/**
	 * 父订单编号
	 * @param parentOrderCode 父订单编号
	 */
	public void setParentOrderCode(String parentOrderCode) {
		this.parentOrderCode = parentOrderCode;
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
	 * 产品ID
	 * @return 产品ID
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * 产品ID
	 * @param productId 产品ID
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
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
	 * 仓库ID
	 * @return 仓库ID
	 */
	public Long getWarehouseId() {
		return warehouseId;
	}

	/**
	 * 仓库ID
	 * @param warehouseId 仓库ID
	 */
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
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
	 * 出库数量
	 * @return 出库数量
	 */
	public Integer getProductItemOutNum() {
		return productItemOutNum;
	}

	/**
	 * 出库数量
	 * @param productItemOutNum 出库数量
	 */
	public void setProductItemOutNum(Integer productItemOutNum) {
		this.productItemOutNum = productItemOutNum;
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
	 * 产品图片URL
	 * @return 产品图片URL
	 */
	public String getProductPicPath() {
		return productPicPath;
	}

	/**
	 * 产品图片URL
	 * @param productPicPath 产品图片URL
	 */
	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}

	/**
	 * 产品信息版本号
	 * @return 产品信息版本号
	 */
	public Long getProductVersionNo() {
		return productVersionNo;
	}

	/**
	 * 产品信息版本号
	 * @param productVersionNo 产品信息版本号
	 */
	public void setProductVersionNo(Long productVersionNo) {
		this.productVersionNo = productVersionNo;
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
	 * 商品原始价
	 * @return 商品原始价
	 */
	public BigDecimal getProductPriceOriginal() {
		return productPriceOriginal;
	}

	/**
	 * 商品原始价
	 * @param productPriceOriginal 商品原始价
	 */
	public void setProductPriceOriginal(BigDecimal productPriceOriginal) {
		this.productPriceOriginal = productPriceOriginal;
	}

	/**
	 * 商品市场价
	 * @return 商品市场价
	 */
	public BigDecimal getProductPriceMarket() {
		return productPriceMarket;
	}

	/**
	 * 商品市场价
	 * @param productPriceMarket 商品市场价
	 */
	public void setProductPriceMarket(BigDecimal productPriceMarket) {
		this.productPriceMarket = productPriceMarket;
	}

	/**
	 * 商品销售价
	 * @return 商品销售价
	 */
	public BigDecimal getProductPriceSale() {
		return productPriceSale;
	}

	/**
	 * 商品销售价
	 * @param productPriceSale 商品销售价
	 */
	public void setProductPriceSale(BigDecimal productPriceSale) {
		this.productPriceSale = productPriceSale;
	}

	/**
	 * 行费税
	 * @return 行费税
	 */
	public BigDecimal getProductTaxAmount() {
		return productTaxAmount;
	}

	/**
	 * 行费税
	 * @param productTaxAmount 行费税
	 */
	public void setProductTaxAmount(BigDecimal productTaxAmount) {
		this.productTaxAmount = productTaxAmount;
	}

	/**
	 * 0,普通 2积分兑换 3 抽奖 4 满赠
	 * @return 0,普通 2积分兑换 3 抽奖 4 满赠
	 */
	public Integer getBuyType() {
		return buyType;
	}

	/**
	 * 0,普通 2积分兑换 3 抽奖 4 满赠
	 * @param buyType 0,普通 2积分兑换 3 抽奖 4 满赠
	 */
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	/**
	 * 产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡
	 * @return 产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡
	 */
	public Integer getProductType() {
		return productType;
	}

	/**
	 * 产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡
	 * @param productType 产品类型 0:普通产品 1:主系列产品 2:子系列产品 3:捆绑产品 4:实物礼品卡 5:虚拟商品 7:电子礼品卡
	 */
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	/**
	 * 分摊到此ITEM的抵用券金额
	 * @return 分摊到此ITEM的抵用券金额
	 */
	public BigDecimal getAmountShareCoupon() {
		return amountShareCoupon;
	}

	/**
	 * 分摊到此ITEM的抵用券金额
	 * @param amountShareCoupon 分摊到此ITEM的抵用券金额
	 */
	public void setAmountShareCoupon(BigDecimal amountShareCoupon) {
		this.amountShareCoupon = amountShareCoupon;
	}

	/**
	 * 分摊到此ITEM的优惠金额(满立减)
	 * @return 分摊到此ITEM的优惠金额(满立减)
	 */
	public BigDecimal getAmountSharePromotion() {
		return amountSharePromotion;
	}

	/**
	 * 分摊到此ITEM的优惠金额(满立减)
	 * @param amountSharePromotion 分摊到此ITEM的优惠金额(满立减)
	 */
	public void setAmountSharePromotion(BigDecimal amountSharePromotion) {
		this.amountSharePromotion = amountSharePromotion;
	}

	/**
	 * 分摊到此ITEM的运费金额
	 * @return 分摊到此ITEM的运费金额
	 */
	public BigDecimal getAmountShareDeliveryFee() {
		return amountShareDeliveryFee;
	}

	/**
	 * 分摊到此ITEM的运费金额
	 * @param amountShareDeliveryFee 分摊到此ITEM的运费金额
	 */
	public void setAmountShareDeliveryFee(BigDecimal amountShareDeliveryFee) {
		this.amountShareDeliveryFee = amountShareDeliveryFee;
	}

	/**
	 * 分摊到此ITEM的核算运费金额
	 * @return 分摊到此ITEM的核算运费金额
	 */
	public BigDecimal getAmountShareDeliveryFeeAccounting() {
		return amountShareDeliveryFeeAccounting;
	}

	/**
	 * 分摊到此ITEM的核算运费金额
	 * @param amountShareDeliveryFeeAccounting 分摊到此ITEM的核算运费金额
	 */
	public void setAmountShareDeliveryFeeAccounting(BigDecimal amountShareDeliveryFeeAccounting) {
		this.amountShareDeliveryFeeAccounting = amountShareDeliveryFeeAccounting;
	}

	/**
	 * 产品毛重
	 * @return 产品毛重
	 */
	public BigDecimal getProductGrossWeight() {
		return productGrossWeight;
	}

	/**
	 * 产品毛重
	 * @param productGrossWeight 产品毛重
	 */
	public void setProductGrossWeight(BigDecimal productGrossWeight) {
		this.productGrossWeight = productGrossWeight;
	}

	/**
	 * 整车质保
	 * @return 整车质保
	 */
	public String getVehicleWarranty() {
		return vehicleWarranty;
	}

	/**
	 * 整车质保
	 * @param vehicleWarranty 整车质保
	 */
	public void setVehicleWarranty(String vehicleWarranty) {
		this.vehicleWarranty = vehicleWarranty;
	}

	/**
	 * 交货时间
	 * @return 交货时间
	 */
	public String getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * 交货时间
	 * @param deliveryTime 交货时间
	 */
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * 发货方式
	 * @return 发货方式
	 */
	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	/**
	 * 发货方式
	 * @param deliveryMethod 发货方式
	 */
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	/**
	 * 违约责任
	 * @return 违约责任
	 */
	public String getViolationResponsibility() {
		return violationResponsibility;
	}

	/**
	 * 违约责任
	 * @param violationResponsibility 违约责任
	 */
	public void setViolationResponsibility(String violationResponsibility) {
		this.violationResponsibility = violationResponsibility;
	}

	/**
	 * 商品来源id（询价单id 或者 租赁单id）
	 * @return 商品来源id（询价单id 或者 租赁单id）
	 */
	public Long getSourceId() {
		return sourceId;
	}

	/**
	 * 商品来源id（询价单id 或者 租赁单id）
	 * @param sourceId 商品来源id（询价单id 或者 租赁单id）
	 */
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
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
	 * 消费税
	 * @return 消费税
	 */
	public BigDecimal getExciseTaxAmount() {
		return exciseTaxAmount;
	}

	/**
	 * 消费税
	 * @param exciseTaxAmount 消费税
	 */
	public void setExciseTaxAmount(BigDecimal exciseTaxAmount) {
		this.exciseTaxAmount = exciseTaxAmount;
	}

	/**
	 * 增值税
	 * @return 增值税
	 */
	public BigDecimal getIncrementTaxAmount() {
		return incrementTaxAmount;
	}

	/**
	 * 增值税
	 * @param incrementTaxAmount 增值税
	 */
	public void setIncrementTaxAmount(BigDecimal incrementTaxAmount) {
		this.incrementTaxAmount = incrementTaxAmount;
	}

	/**
	 * 关税
	 * @return 关税
	 */
	public BigDecimal getCustomsDutiesAmount() {
		return customsDutiesAmount;
	}

	/**
	 * 关税
	 * @param customsDutiesAmount 关税
	 */
	public void setCustomsDutiesAmount(BigDecimal customsDutiesAmount) {
		this.customsDutiesAmount = customsDutiesAmount;
	}

	/**
	 * 扩展信息，以json形式存储
	 * @return 扩展信息，以json形式存储
	 */
	public String getExtInfo() {
		return extInfo;
	}

	/**
	 * 扩展信息，以json形式存储
	 * @param extInfo 扩展信息，以json形式存储
	 */
	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}

	/**
	 * 订单itemId
	 * @return 订单itemId
	 */
	public Long getSoItemId() {
		return soItemId;
	}

	/**
	 * 订单itemId
	 * @param soItemId 订单itemId
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
	