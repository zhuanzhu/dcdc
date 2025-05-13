package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:13
 */
public class DoItemPO {


	private Long id;

	/**
	 * do 编码 
	 */
	private String doCode;	

	/**
	 * 订单Code 
	 */
	private String orderCode;	

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
	 * 产品类型 
	 */
	private Integer productType;	

	/**
	 * 是否需要等货 
	 */
	private Integer virtualStockStatus;	

	/**
	 * 绑定的SO_ITEM_ID 
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
	 * ID 
	 * @param id ID 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * do 编码 
	 * @return do 编码 
	 */
	public String getDoCode() {
		return doCode;
	}

	/**
	 * do 编码 
	 * @param doCode do 编码 
	 */
	public void setDoCode(String doCode) {
		this.doCode = doCode;
	}

	/**
	 * 订单Code 
	 * @return 订单Code 
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单Code 
	 * @param orderCode 订单Code 
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	 * 产品类型 
	 * @return 产品类型 
	 */
	public Integer getProductType() {
		return productType;
	}

	/**
	 * 产品类型 
	 * @param productType 产品类型 
	 */
	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	/**
	 * 是否需要等货 
	 * @return 是否需要等货 
	 */
	public Integer getVirtualStockStatus() {
		return virtualStockStatus;
	}

	/**
	 * 是否需要等货 
	 * @param virtualStockStatus 是否需要等货 
	 */
	public void setVirtualStockStatus(Integer virtualStockStatus) {
		this.virtualStockStatus = virtualStockStatus;
	}

	/**
	 * 绑定的SO_ITEM_ID 
	 * @return 绑定的SO_ITEM_ID 
	 */
	public Long getSoItemId() {
		return soItemId;
	}

	/**
	 * 绑定的SO_ITEM_ID 
	 * @param soItemId 绑定的SO_ITEM_ID 
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
	