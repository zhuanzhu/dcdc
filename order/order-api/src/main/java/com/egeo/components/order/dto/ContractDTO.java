package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class ContractDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	private String orderCode;	

	/**
	 * 签约地点
	 */
	private String contractingPlace;	

	/**
	 * 销售方类型
	 */
	private String sellersType;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 采购方名称
	 */
	private String purchaserCompanyName;	

	/**
	 * 采购方联系电话
	 */
	private String purchaserPhone;	

	/**
	 * 采购方传真
	 */
	private String purchaserFax;	

	/**
	 * 采购方详细地址
	 */
	private String purchaserAddress;	

	/**
	 * 销售方名称
	 */
	private String sellersCompanyName;	

	/**
	 * 销售方联系电话
	 */
	private String sellersPhone;	

	/**
	 * 销售方传真
	 */
	private String sellersFax;	

	/**
	 * 销售方详细地址
	 */
	private String sellersAddress;	

	/**
	 * 交货日期
	 */
	private String deliveryDate;	

	/**
	 * 交货地点
	 */
	private String deliveryAddress;	

	/**
	 * 运费及提货费用结算方式
	 */
	private String freightAndDeliveryCharges;	

	/**
	 * 付款方式及期限
	 */
	private String termsOfPaymentAndTimeLimit;	

	/**
	 * 订单商品总金额
	 */
	private BigDecimal productAmount;	

	/**
	 * 备注
	 */
	private String remarks;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 合同编号dd+年月+0001（四位，依次递增）
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
	 * 签约地点
	 * @return 签约地点
	 */
	public String getContractingPlace() {
		return contractingPlace;
	}

	/**
	 * 签约地点
	 * @param contractingPlace 签约地点
	 */
	public void setContractingPlace(String contractingPlace) {
		this.contractingPlace = contractingPlace;
	}
	/**
	 * 销售方类型
	 * @return 销售方类型
	 */
	public String getSellersType() {
		return sellersType;
	}

	/**
	 * 销售方类型
	 * @param sellersType 销售方类型
	 */
	public void setSellersType(String sellersType) {
		this.sellersType = sellersType;
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
	 * 采购方名称
	 * @return 采购方名称
	 */
	public String getPurchaserCompanyName() {
		return purchaserCompanyName;
	}

	/**
	 * 采购方名称
	 * @param purchaserCompanyName 采购方名称
	 */
	public void setPurchaserCompanyName(String purchaserCompanyName) {
		this.purchaserCompanyName = purchaserCompanyName;
	}
	/**
	 * 采购方联系电话
	 * @return 采购方联系电话
	 */
	public String getPurchaserPhone() {
		return purchaserPhone;
	}

	/**
	 * 采购方联系电话
	 * @param purchaserPhone 采购方联系电话
	 */
	public void setPurchaserPhone(String purchaserPhone) {
		this.purchaserPhone = purchaserPhone;
	}
	/**
	 * 采购方传真
	 * @return 采购方传真
	 */
	public String getPurchaserFax() {
		return purchaserFax;
	}

	/**
	 * 采购方传真
	 * @param purchaserFax 采购方传真
	 */
	public void setPurchaserFax(String purchaserFax) {
		this.purchaserFax = purchaserFax;
	}
	/**
	 * 采购方详细地址
	 * @return 采购方详细地址
	 */
	public String getPurchaserAddress() {
		return purchaserAddress;
	}

	/**
	 * 采购方详细地址
	 * @param purchaserAddress 采购方详细地址
	 */
	public void setPurchaserAddress(String purchaserAddress) {
		this.purchaserAddress = purchaserAddress;
	}
	/**
	 * 销售方名称
	 * @return 销售方名称
	 */
	public String getSellersCompanyName() {
		return sellersCompanyName;
	}

	/**
	 * 销售方名称
	 * @param sellersCompanyName 销售方名称
	 */
	public void setSellersCompanyName(String sellersCompanyName) {
		this.sellersCompanyName = sellersCompanyName;
	}
	/**
	 * 销售方联系电话
	 * @return 销售方联系电话
	 */
	public String getSellersPhone() {
		return sellersPhone;
	}

	/**
	 * 销售方联系电话
	 * @param sellersPhone 销售方联系电话
	 */
	public void setSellersPhone(String sellersPhone) {
		this.sellersPhone = sellersPhone;
	}
	/**
	 * 销售方传真
	 * @return 销售方传真
	 */
	public String getSellersFax() {
		return sellersFax;
	}

	/**
	 * 销售方传真
	 * @param sellersFax 销售方传真
	 */
	public void setSellersFax(String sellersFax) {
		this.sellersFax = sellersFax;
	}
	/**
	 * 销售方详细地址
	 * @return 销售方详细地址
	 */
	public String getSellersAddress() {
		return sellersAddress;
	}

	/**
	 * 销售方详细地址
	 * @param sellersAddress 销售方详细地址
	 */
	public void setSellersAddress(String sellersAddress) {
		this.sellersAddress = sellersAddress;
	}
	/**
	 * 交货日期
	 * @return 交货日期
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * 交货日期
	 * @param deliveryDate 交货日期
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * 交货地点
	 * @return 交货地点
	 */
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	/**
	 * 交货地点
	 * @param deliveryAddress 交货地点
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	/**
	 * 运费及提货费用结算方式
	 * @return 运费及提货费用结算方式
	 */
	public String getFreightAndDeliveryCharges() {
		return freightAndDeliveryCharges;
	}

	/**
	 * 运费及提货费用结算方式
	 * @param freightAndDeliveryCharges 运费及提货费用结算方式
	 */
	public void setFreightAndDeliveryCharges(String freightAndDeliveryCharges) {
		this.freightAndDeliveryCharges = freightAndDeliveryCharges;
	}
	/**
	 * 付款方式及期限
	 * @return 付款方式及期限
	 */
	public String getTermsOfPaymentAndTimeLimit() {
		return termsOfPaymentAndTimeLimit;
	}

	/**
	 * 付款方式及期限
	 * @param termsOfPaymentAndTimeLimit 付款方式及期限
	 */
	public void setTermsOfPaymentAndTimeLimit(String termsOfPaymentAndTimeLimit) {
		this.termsOfPaymentAndTimeLimit = termsOfPaymentAndTimeLimit;
	}
	/**
	 * 订单商品总金额
	 * @return 订单商品总金额
	 */
	public BigDecimal getProductAmount() {
		return productAmount;
	}

	/**
	 * 订单商品总金额
	 * @param productAmount 订单商品总金额
	 */
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注
	 * @param remarks 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	 * 合同编号dd+年月+0001（四位，依次递增）
	 * @return 合同编号dd+年月+0001（四位，依次递增）
	 */
	public String getContractCode() {
		return contractCode;
	}

	/**
	 * 合同编号dd+年月+0001（四位，依次递增）
	 * @param contractCode 合同编号dd+年月+0001（四位，依次递增）
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
}
	