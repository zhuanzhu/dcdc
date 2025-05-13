package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author paul
 * @date 2018-09-13 13:59:31
 */
public class MerchantProductFeedbackVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 商品id
	 */
	private Long merchantProductId;
	/**
	 * 商品编号
	 */
	private String merchantProductSerialNumber;
	/**
	 * 商品名称
	 */
	private String merchantProductName;
	/**
	 * 商品规格
	 */
	private String attributeName;
	/**
	 * 商品价格
	 */
	private BigDecimal salePrice;
	/**
	 * 低价平台id
	 */
	private Long sellPlatformId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户账号
	 */
	private String account;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 反馈内容
	 */
	private String content;
	/**
	 * 创建时间(反馈时间)
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 受理状态 0:未受理(默认值) 1:已受理
	 */
	private Integer feedbackStatus;
	/**
	 * 受理人id
	 */
	private Long receiverId;
	/**
	 * 受理结果
	 */
	private String acceptResult;
	/**
	 * 凭证链接
	 */
	private String certificateUrl;
	/**
	 * 平台id
	 */
	private Long platformId;

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
	 * 商品编号
	 * @return 商品编号
	 */
	public String getMerchantProductSerialNumber() {
		return merchantProductSerialNumber;
	}

	/**
	 * 商品编号
	 * @param merchantProductSerialNumber 商品编号
	 */
	public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
		this.merchantProductSerialNumber = merchantProductSerialNumber;
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
	 * 
	 * @return 
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * 
	 * @param attributeName 
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}	
	/**
	 * 商品价格
	 * @return 商品价格
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * 商品价格
	 * @param salePrice 商品价格
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}	
	/**
	 * 低价平台id
	 * @return 低价平台id
	 */
	public Long getSellPlatformId() {
		return sellPlatformId;
	}

	/**
	 * 低价平台id
	 * @param sellPlatformId 低价平台id
	 */
	public void setSellPlatformId(Long sellPlatformId) {
		this.sellPlatformId = sellPlatformId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	/**
	 * 用户id
	 * @return 用户id
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 用户id
	 * @param account 用户id
	 */
	public void setAccount(String account) {
		this.account = account;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 
	 * @param phone 
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content 
	 */
	public void setContent(String content) {
		this.content = content;
	}	
	/**
	 * 创建时间(反馈时间)
	 * @return 创建时间(反馈时间)
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间(反馈时间)
	 * @param createTime 创建时间(反馈时间)
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	 * 受理状态 0:未受理(默认值) 1:已受理
	 * @return 受理状态 0:未受理(默认值) 1:已受理
	 */
	public Integer getFeedbackStatus() {
		return feedbackStatus;
	}

	/**
	 * 受理状态 0:未受理(默认值) 1:已受理
	 * @param feedbackStatus 受理状态 0:未受理(默认值) 1:已受理
	 */
	public void setFeedbackStatus(Integer feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}	
	/**
	 * 受理人id
	 * @return 受理人id
	 */
	public Long getReceiverId() {
		return receiverId;
	}

	/**
	 * 受理人id
	 * @param receiverId 受理人id
	 */
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getAcceptResult() {
		return acceptResult;
	}

	/**
	 * 
	 * @param acceptResult 
	 */
	public void setAcceptResult(String acceptResult) {
		this.acceptResult = acceptResult;
	}	
	/**
	 * 凭证链接
	 * @return 凭证链接
	 */
	public String getCertificateUrl() {
		return certificateUrl;
	}

	/**
	 * 凭证链接
	 * @param certificateUrl 凭证链接
	 */
	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}	
	
}
	