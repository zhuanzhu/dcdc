package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-08-10 16:53:30
 */
public class InvoiceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 抬头内容/名称
	 */
	private String titleContent;	

	/**
	 * 纳税人识别号
	 */
	private String taxpayerIdentificationCode;	

	/**
	 * 注册地址
	 */
	private String registerAddr;	

	/**
	 * 注册电话
	 */
	private String registerTel;	

	/**
	 * 开户银行
	 */
	private String depositBank;	

	/**
	 * 银行账号
	 */
	private String bankAccount;	

	/**
	 * 营业执照的链接
	 */
	private String businessLicenceUrl;	

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 是否设为默认  0:否 1:是
	 */
	private Integer isDefault;	

	/**
	 * 创建类型 0:用户创建 1:运营创建
	 */
	private Integer createType;	

	/**
	 * 更新时间
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
	
	/**
	 * 发票抬头类型：0：个人；1：公司
	 */
	private Integer titleType;
	
	/**
	 * 是否删除  0:否 1:是
	 */
	private Integer isDelete;

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
	 * 抬头内容/名称
	 * @return 抬头内容/名称
	 */
	public String getTitleContent() {
		return titleContent;
	}

	/**
	 * 抬头内容/名称
	 * @param titleContent 抬头内容/名称
	 */
	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
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
	 * 注册地址
	 * @return 注册地址
	 */
	public String getRegisterAddr() {
		return registerAddr;
	}

	/**
	 * 注册地址
	 * @param registerAddr 注册地址
	 */
	public void setRegisterAddr(String registerAddr) {
		this.registerAddr = registerAddr;
	}
	/**
	 * 注册电话
	 * @return 注册电话
	 */
	public String getRegisterTel() {
		return registerTel;
	}

	/**
	 * 注册电话
	 * @param registerTel 注册电话
	 */
	public void setRegisterTel(String registerTel) {
		this.registerTel = registerTel;
	}
	/**
	 * 开户银行
	 * @return 开户银行
	 */
	public String getDepositBank() {
		return depositBank;
	}

	/**
	 * 开户银行
	 * @param depositBank 开户银行
	 */
	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}
	/**
	 * 银行账号
	 * @return 银行账号
	 */
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * 银行账号
	 * @param bankAccount 银行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	/**
	 * 营业执照的链接
	 * @return 营业执照的链接
	 */
	public String getBusinessLicenceUrl() {
		return businessLicenceUrl;
	}

	/**
	 * 营业执照的链接
	 * @param businessLicenceUrl 营业执照的链接
	 */
	public void setBusinessLicenceUrl(String businessLicenceUrl) {
		this.businessLicenceUrl = businessLicenceUrl;
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
	 * 是否设为默认  0:否 1:是
	 * @return 是否设为默认  0:否 1:是
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 是否设为默认  0:否 1:是
	 * @param isDefault 是否设为默认  0:否 1:是
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 创建类型 0:用户创建 1:运营创建
	 * @return 创建类型 0:用户创建 1:运营创建
	 */
	public Integer getCreateType() {
		return createType;
	}

	/**
	 * 创建类型 0:用户创建 1:运营创建
	 * @param createType 创建类型 0:用户创建 1:运营创建
	 */
	public void setCreateType(Integer createType) {
		this.createType = createType;
	}
	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
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

	public Integer getTitleType() {
		return titleType;
	}

	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
	