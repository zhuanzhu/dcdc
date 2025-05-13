package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoEnquiryPO {


	private Long id;

	/**
	 * 询价单编号
	 */
	private String enquiryCode;	

	/**
	 * 询价单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 */
	private Integer enquiryStatus;	

	/**
	 * 询价单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 */
	private Integer enquiryAuditStatus;	

	/**
	 * 确认状态  0: 未保存 1:已保存 2:已提交
	 */
	private Integer affirmStatus;	

	/**
	 * 商品ID
	 */
	private Long productId;	

	/**
	 * 商品名称
	 */
	private String productName;	

	/**
	 * 商品价格
	 */
	private BigDecimal productPrice;	

	/**
	 * 商品数量
	 */
	private Integer productNum;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 用户名称
	 */
	private String userName;	

	/**
	 * 用户手机号码
	 */
	private String userPhone;	

	/**
	 * 客服ID
	 */
	private Long cId;	

	/**
	 * 客服名称
	 */
	private String cName;	

	/**
	 * 询价时间
	 */
	private Date askTime;	

	/**
	 * 报价时间
	 */
	private Date offerTime;	

	/**
	 * 买家备注
	 */
	private String enquiryRemarkUser;	

	/**
	 * 客服备注
	 */
	private String enquiryRemarkC;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 审核原因
	 */
	private String auditReason;	

	/**
	 * 联系号码
	 */
	private String linkPhone;	

	/**
	 * 用户取消原因
	 */
	private String cancelReasonUser;	

	/**
	 * 客服取消原因
	 */
	private String cancelReasonC;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 商家名称
	 */
	private String merchantName;	

	/**
	 * 客服给用户的备注
	 */
	private String enquiryRemarkCUser;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	 * 询价单编号
	 * @return 询价单编号
	 */
	public String getEnquiryCode() {
		return enquiryCode;
	}

	/**
	 * 询价单编号
	 * @param enquiryCode 询价单编号
	 */
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}

	/**
	 * 询价单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 * @return 询价单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 */
	public Integer getEnquiryStatus() {
		return enquiryStatus;
	}

	/**
	 * 询价单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 * @param enquiryStatus 询价单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 */
	public void setEnquiryStatus(Integer enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}

	/**
	 * 询价单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 * @return 询价单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 */
	public Integer getEnquiryAuditStatus() {
		return enquiryAuditStatus;
	}

	/**
	 * 询价单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 * @param enquiryAuditStatus 询价单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 */
	public void setEnquiryAuditStatus(Integer enquiryAuditStatus) {
		this.enquiryAuditStatus = enquiryAuditStatus;
	}

	/**
	 * 确认状态  0: 未保存 1:已保存 2:已提交
	 * @return 确认状态  0: 未保存 1:已保存 2:已提交
	 */
	public Integer getAffirmStatus() {
		return affirmStatus;
	}

	/**
	 * 确认状态  0: 未保存 1:已保存 2:已提交
	 * @param affirmStatus 确认状态  0: 未保存 1:已保存 2:已提交
	 */
	public void setAffirmStatus(Integer affirmStatus) {
		this.affirmStatus = affirmStatus;
	}

	/**
	 * 商品ID
	 * @return 商品ID
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * 商品ID
	 * @param productId 商品ID
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * 商品名称
	 * @return 商品名称
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * 商品名称
	 * @param productName 商品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 商品价格
	 * @return 商品价格
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	/**
	 * 商品价格
	 * @param productPrice 商品价格
	 */
	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * 商品数量
	 * @return 商品数量
	 */
	public Integer getProductNum() {
		return productNum;
	}

	/**
	 * 商品数量
	 * @param productNum 商品数量
	 */
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
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
	 * 用户名称
	 * @return 用户名称
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户名称
	 * @param userName 用户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 用户手机号码
	 * @return 用户手机号码
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * 用户手机号码
	 * @param userPhone 用户手机号码
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * 客服ID
	 * @return 客服ID
	 */
	public Long getCId() {
		return cId;
	}

	/**
	 * 客服ID
	 * @param cId 客服ID
	 */
	public void setCId(Long cId) {
		this.cId = cId;
	}

	/**
	 * 客服名称
	 * @return 客服名称
	 */
	public String getCName() {
		return cName;
	}

	/**
	 * 客服名称
	 * @param cName 客服名称
	 */
	public void setCName(String cName) {
		this.cName = cName;
	}

	/**
	 * 询价时间
	 * @return 询价时间
	 */
	public Date getAskTime() {
		return askTime;
	}

	/**
	 * 询价时间
	 * @param askTime 询价时间
	 */
	public void setAskTime(Date askTime) {
		this.askTime = askTime;
	}

	/**
	 * 报价时间
	 * @return 报价时间
	 */
	public Date getOfferTime() {
		return offerTime;
	}

	/**
	 * 报价时间
	 * @param offerTime 报价时间
	 */
	public void setOfferTime(Date offerTime) {
		this.offerTime = offerTime;
	}

	/**
	 * 买家备注
	 * @return 买家备注
	 */
	public String getEnquiryRemarkUser() {
		return enquiryRemarkUser;
	}

	/**
	 * 买家备注
	 * @param enquiryRemarkUser 买家备注
	 */
	public void setEnquiryRemarkUser(String enquiryRemarkUser) {
		this.enquiryRemarkUser = enquiryRemarkUser;
	}

	/**
	 * 客服备注
	 * @return 客服备注
	 */
	public String getEnquiryRemarkC() {
		return enquiryRemarkC;
	}

	/**
	 * 客服备注
	 * @param enquiryRemarkC 客服备注
	 */
	public void setEnquiryRemarkC(String enquiryRemarkC) {
		this.enquiryRemarkC = enquiryRemarkC;
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
	 * 审核原因
	 * @return 审核原因
	 */
	public String getAuditReason() {
		return auditReason;
	}

	/**
	 * 审核原因
	 * @param auditReason 审核原因
	 */
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	/**
	 * 联系号码
	 * @return 联系号码
	 */
	public String getLinkPhone() {
		return linkPhone;
	}

	/**
	 * 联系号码
	 * @param linkPhone 联系号码
	 */
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	/**
	 * 用户取消原因
	 * @return 用户取消原因
	 */
	public String getCancelReasonUser() {
		return cancelReasonUser;
	}

	/**
	 * 用户取消原因
	 * @param cancelReasonUser 用户取消原因
	 */
	public void setCancelReasonUser(String cancelReasonUser) {
		this.cancelReasonUser = cancelReasonUser;
	}

	/**
	 * 客服取消原因
	 * @return 客服取消原因
	 */
	public String getCancelReasonC() {
		return cancelReasonC;
	}

	/**
	 * 客服取消原因
	 * @param cancelReasonC 客服取消原因
	 */
	public void setCancelReasonC(String cancelReasonC) {
		this.cancelReasonC = cancelReasonC;
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
	 * 商家名称
	 * @return 商家名称
	 */
	public String getMerchantName() {
		return merchantName;
	}

	/**
	 * 商家名称
	 * @param merchantName 商家名称
	 */
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	/**
	 * 客服给用户的备注
	 * @return 客服给用户的备注
	 */
	public String getEnquiryRemarkCUser() {
		return enquiryRemarkCUser;
	}

	/**
	 * 客服给用户的备注
	 * @param enquiryRemarkCUser 客服给用户的备注
	 */
	public void setEnquiryRemarkCUser(String enquiryRemarkCUser) {
		this.enquiryRemarkCUser = enquiryRemarkCUser;
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
}
	