package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-05-10 09:53:53
 */
public class MerchantProductFeedbackReVO implements Serializable {
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
	 * 商品价格
	 */
	private BigDecimal salePrice;
	
	/**
	 * 凭证链接
	 */
	private String certificateUrl;

	/**
	 * 受理状态 0:未受理 1:已受理
	 */
	private Integer feedbackStatus;
	/**
	 * 创建时间(反馈时间)
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	/**
	 * 低价平台
	 */
	private String sellPlatformName;
	
	/**
	 * 用户
	 */
	private String userName;
	
	private String userMail;
	
	private String userMobile;
	
	/**
	 * 受理人
	 */
	private String receiverMail;

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
	/**
	 * 受理状态 0:未受理 1:已受理
	 * @return 受理状态 0:未受理 1:已受理
	 */
	public Integer getFeedbackStatus() {
		return feedbackStatus;
	}

	/**
	 * 受理状态 0:未受理 1:已受理
	 * @param feedbackStatus 受理状态 0:未受理 1:已受理
	 */
	public void setFeedbackStatus(Integer feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
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

	public String getSellPlatformName() {
		return sellPlatformName;
	}

	public void setSellPlatformName(String sellPlatformName) {
		this.sellPlatformName = sellPlatformName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}

}

	