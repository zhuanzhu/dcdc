package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-02-03 17:38:35
 */
public class SoInvoiceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 子订单id
	 */
	private Long soChildId;	

	/**
	 * 发票金额
	 */
	private BigDecimal invoiceValue;	

	/**
	 * 发票编号
	 */
	private String invoiceCode;	

	/**
	 * 发票税务类型 1:增值税发票 2:其他
	 */
	private Integer invoiceTaxType;	

	/**
	 * 发票财务类型 0:发票 1:红冲发票
	 */
	private Integer invoiceFinType;	

	/**
	 * 发票形式 0:纸质发票  1:电子发票
	 */
	private Integer invoiceForm;	

	/**
	 * 发票状态：0:未开 1:已开
	 */
	private Integer invoiceStatus;	

	/**
	 * 发票抬头类型：0：个人；1：公司
	 */
	private Integer invoiceTitleType;	

	/**
	 * 抬头内容
	 */
	private String invoiceTitleContent;	

	/**
	 * 是否需要明细：1：是；0否；
	 */
	private Integer isNeedDetails;	

	/**
	 * 发票明细类型 0:商品明细  1:商品类别
	 */
	private Integer invoiceContentType;	

	/**
	 * 明细
	 */
	private String invoiceContent;	

	/**
	 * 发票备注
	 */
	private String invoiceRemark;	

	/**
	 * 纳税人识别号
	 */
	private String taxpayerIdentificationCode;	

	/**
	 * 
	 */
	private String receiverMail;	

	/**
	 * 修改时间
	 */
	private Date updateTime;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	
	private String registerAddr;
	
	private String registerTel;
	
	private String depositBank;
	
	private String bankAccount;
	
	private String businessLicenceUrl;
	
	private String invoiceAttcUrl;
	
	/**
	 * 公共发票的id
	 */
	private Long invoiceId;	
	
	public String getRegisterAddr() {
		return registerAddr;
	}

	public void setRegisterAddr(String registerAddr) {
		this.registerAddr = registerAddr;
	}

	public String getRegisterTel() {
		return registerTel;
	}

	public void setRegisterTel(String registerTel) {
		this.registerTel = registerTel;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBusinessLicenceUrl() {
		return businessLicenceUrl;
	}

	public void setBusinessLicenceUrl(String businessLicenceUrl) {
		this.businessLicenceUrl = businessLicenceUrl;
	}

	public String getInvoiceAttcUrl() {
		return invoiceAttcUrl;
	}

	public void setInvoiceAttcUrl(String invoiceAttcUrl) {
		this.invoiceAttcUrl = invoiceAttcUrl;
	}

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
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 订单id
	 * @return 订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 订单id
	 * @param soId 订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
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
	 * 发票金额
	 * @return 发票金额
	 */
	public BigDecimal getInvoiceValue() {
		return invoiceValue;
	}

	/**
	 * 发票金额
	 * @param invoiceValue 发票金额
	 */
	public void setInvoiceValue(BigDecimal invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	/**
	 * 发票编号
	 * @return 发票编号
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}

	/**
	 * 发票编号
	 * @param invoiceCode 发票编号
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * 发票税务类型 1:增值税发票 2:其他
	 * @return 发票税务类型 1:增值税发票 2:其他
	 */
	public Integer getInvoiceTaxType() {
		return invoiceTaxType;
	}

	/**
	 * 发票税务类型 1:增值税发票 2:其他
	 * @param invoiceTaxType 发票税务类型 1:增值税发票 2:其他
	 */
	public void setInvoiceTaxType(Integer invoiceTaxType) {
		this.invoiceTaxType = invoiceTaxType;
	}
	/**
	 * 发票财务类型 0:发票 1:红冲发票
	 * @return 发票财务类型 0:发票 1:红冲发票
	 */
	public Integer getInvoiceFinType() {
		return invoiceFinType;
	}

	/**
	 * 发票财务类型 0:发票 1:红冲发票
	 * @param invoiceFinType 发票财务类型 0:发票 1:红冲发票
	 */
	public void setInvoiceFinType(Integer invoiceFinType) {
		this.invoiceFinType = invoiceFinType;
	}
	/**
	 * 发票形式 0:纸质发票  1:电子发票
	 * @return 发票形式 0:纸质发票  1:电子发票
	 */
	public Integer getInvoiceForm() {
		return invoiceForm;
	}

	/**
	 * 发票形式 0:纸质发票  1:电子发票
	 * @param invoiceForm 发票形式 0:纸质发票  1:电子发票
	 */
	public void setInvoiceForm(Integer invoiceForm) {
		this.invoiceForm = invoiceForm;
	}
	/**
	 * 发票状态：0:未开 1:已开
	 * @return 发票状态：0:未开 1:已开
	 */
	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	/**
	 * 发票状态：0:未开 1:已开
	 * @param invoiceStatus 发票状态：0:未开 1:已开
	 */
	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	/**
	 * 发票抬头类型：0：个人；1：公司
	 * @return 发票抬头类型：0：个人；1：公司
	 */
	public Integer getInvoiceTitleType() {
		return invoiceTitleType;
	}

	/**
	 * 发票抬头类型：0：个人；1：公司
	 * @param invoiceTitleType 发票抬头类型：0：个人；1：公司
	 */
	public void setInvoiceTitleType(Integer invoiceTitleType) {
		this.invoiceTitleType = invoiceTitleType;
	}
	/**
	 * 抬头内容
	 * @return 抬头内容
	 */
	public String getInvoiceTitleContent() {
		return invoiceTitleContent;
	}

	/**
	 * 抬头内容
	 * @param invoiceTitleContent 抬头内容
	 */
	public void setInvoiceTitleContent(String invoiceTitleContent) {
		this.invoiceTitleContent = invoiceTitleContent;
	}
	/**
	 * 是否需要明细：1：是；0否；
	 * @return 是否需要明细：1：是；0否；
	 */
	public Integer getIsNeedDetails() {
		return isNeedDetails;
	}

	/**
	 * 是否需要明细：1：是；0否；
	 * @param isNeedDetails 是否需要明细：1：是；0否；
	 */
	public void setIsNeedDetails(Integer isNeedDetails) {
		this.isNeedDetails = isNeedDetails;
	}
	/**
	 * 发票明细类型 0:商品明细  1:商品类别
	 * @return 发票明细类型 0:商品明细  1:商品类别
	 */
	public Integer getInvoiceContentType() {
		return invoiceContentType;
	}

	/**
	 * 发票明细类型 0:商品明细  1:商品类别
	 * @param invoiceContentType 发票明细类型 0:商品明细  1:商品类别
	 */
	public void setInvoiceContentType(Integer invoiceContentType) {
		this.invoiceContentType = invoiceContentType;
	}
	/**
	 * 明细
	 * @return 明细
	 */
	public String getInvoiceContent() {
		return invoiceContent;
	}

	/**
	 * 明细
	 * @param invoiceContent 明细
	 */
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	/**
	 * 发票备注
	 * @return 发票备注
	 */
	public String getInvoiceRemark() {
		return invoiceRemark;
	}

	/**
	 * 发票备注
	 * @param invoiceRemark 发票备注
	 */
	public void setInvoiceRemark(String invoiceRemark) {
		this.invoiceRemark = invoiceRemark;
	}
	/**
	 * 纳税人识别号
	 * @return 纳税人识别号
	 */
	public String getTaxpayerIdentificationCode() {
		return taxpayerIdentificationCode;
	}

	/**
	 * 纳税人识别号
	 * @param taxpayerIdentificationCode 纳税人识别号
	 */
	public void setTaxpayerIdentificationCode(String taxpayerIdentificationCode) {
		this.taxpayerIdentificationCode = taxpayerIdentificationCode;
	}
	/**
	 * 
	 * @return 
	 */
	public String getReceiverMail() {
		return receiverMail;
	}

	/**
	 * 
	 * @param receiverMail 
	 */
	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}
	/**
	 * 修改时间
	 * @return 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
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

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
}
	