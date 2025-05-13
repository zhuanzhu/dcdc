package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:13
 */
public class ContractItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 合同表主键id
	 */
	private Long contractId;	

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	private String orderCode;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 商品id
	 */
	private Long merchantProductId;	

	/**
	 * 商品名称
	 */
	private String merchantProductName;	

	/**
	 * 商品规格型号
	 */
	private String merchantProductStandard;	

	/**
	 * 商品材质
	 */
	private String merchantProductMaterial;	

	/**
	 * 产地(国)
	 */
	private String merchantProductPlaceOfOrigin;	

	/**
	 * 商品总金额
	 */
	private BigDecimal merchantProductItemAmount;	

	/**
	 * 商品销售单价
	 */
	private BigDecimal merchantProductPriceFinal;	

	/**
	 * 备注
	 */
	private String merchantProductRemark;	

	/**
	 * 商品购买数量
	 */
	private Integer merchantProductItemNum;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 合同编号
	 */
	private String contractCode;	

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
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 合同表主键id
	 * @return 合同表主键id
	 */
	public Long getContractId() {
		return contractId;
	}

	/**
	 * 合同表主键id
	 * @param contractId 合同表主键id
	 */
	public void setContractId(Long contractId) {
		this.contractId = contractId;
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
	 * 商品id
	 * @return 商品id
	 */
	public Long getMerchantProductId() {
		return merchantProductId;
	}

	/**
	 * 商品id
	 * @param merchantProductId 商品id
	 */
	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
	}
	/**
	 * 商品名称
	 * @return 商品名称
	 */
	public String getMerchantProductName() {
		return merchantProductName;
	}

	/**
	 * 商品名称
	 * @param merchantProductName 商品名称
	 */
	public void setMerchantProductName(String merchantProductName) {
		this.merchantProductName = merchantProductName;
	}
	/**
	 * 商品规格型号
	 * @return 商品规格型号
	 */
	public String getMerchantProductStandard() {
		return merchantProductStandard;
	}

	/**
	 * 商品规格型号
	 * @param merchantProductStandard 商品规格型号
	 */
	public void setMerchantProductStandard(String merchantProductStandard) {
		this.merchantProductStandard = merchantProductStandard;
	}
	/**
	 * 商品材质
	 * @return 商品材质
	 */
	public String getMerchantProductMaterial() {
		return merchantProductMaterial;
	}

	/**
	 * 商品材质
	 * @param merchantProductMaterial 商品材质
	 */
	public void setMerchantProductMaterial(String merchantProductMaterial) {
		this.merchantProductMaterial = merchantProductMaterial;
	}
	/**
	 * 产地(国)
	 * @return 产地(国)
	 */
	public String getMerchantProductPlaceOfOrigin() {
		return merchantProductPlaceOfOrigin;
	}

	/**
	 * 产地(国)
	 * @param merchantProductPlaceOfOrigin 产地(国)
	 */
	public void setMerchantProductPlaceOfOrigin(String merchantProductPlaceOfOrigin) {
		this.merchantProductPlaceOfOrigin = merchantProductPlaceOfOrigin;
	}
	/**
	 * 商品总金额
	 * @return 商品总金额
	 */
	public BigDecimal getMerchantProductItemAmount() {
		return merchantProductItemAmount;
	}

	/**
	 * 商品总金额
	 * @param merchantProductItemAmount 商品总金额
	 */
	public void setMerchantProductItemAmount(BigDecimal merchantProductItemAmount) {
		this.merchantProductItemAmount = merchantProductItemAmount;
	}
	/**
	 * 商品销售单价
	 * @return 商品销售单价
	 */
	public BigDecimal getMerchantProductPriceFinal() {
		return merchantProductPriceFinal;
	}

	/**
	 * 商品销售单价
	 * @param merchantProductPriceFinal 商品销售单价
	 */
	public void setMerchantProductPriceFinal(BigDecimal merchantProductPriceFinal) {
		this.merchantProductPriceFinal = merchantProductPriceFinal;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getMerchantProductRemark() {
		return merchantProductRemark;
	}

	/**
	 * 备注
	 * @param merchantProductRemark 备注
	 */
	public void setMerchantProductRemark(String merchantProductRemark) {
		this.merchantProductRemark = merchantProductRemark;
	}
	/**
	 * 商品购买数量
	 * @return 商品购买数量
	 */
	public Integer getMerchantProductItemNum() {
		return merchantProductItemNum;
	}

	/**
	 * 商品购买数量
	 * @param merchantProductItemNum 商品购买数量
	 */
	public void setMerchantProductItemNum(Integer merchantProductItemNum) {
		this.merchantProductItemNum = merchantProductItemNum;
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
	 * 合同编号
	 * @return 合同编号
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * 合同编号
	 * @param contractCode 合同编号
	 */
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
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
	