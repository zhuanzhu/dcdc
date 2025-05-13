package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoLeaseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 租赁单编号
	 */
	private String leaseCode;	

	/**
	 * 租赁单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 */
	private Integer leaseStatus;	

	/**
	 * 租赁单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 */
	private Integer leaseAuditStatus;	

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
	 * 租金
	 */
	private BigDecimal rentalPrice;	

	/**
	 * 押金
	 */
	private BigDecimal depositPrice;	

	/**
	 * 商品总价格=租金+押金
	 */
	private BigDecimal productPrice;	

	/**
	 * 商品数量
	 */
	private Integer productNum;	

	/**
	 * 商品编码
	 */
	private String code;	

	/**
	 * 租赁开始时间
	 */
	private Date leaseStartTime;	

	/**
	 * 租赁结束时间
	 */
	private Date leaseEndTime;	

	/**
	 * 买家备注
	 */
	private String leaseRemarkUser;	

	/**
	 * 客服备注
	 */
	private String leaseRemarkC;	

	/**
	 * 客服给用户备注
	 */
	private String leaseRemarkCUser;	

	/**
	 * 联系号码
	 */
	private String linkPhone;	

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
	 * 提交人id
	 */
	private Long commitUserId;	

	/**
	 * 提交人id
	 */
	private String commitUserName;	

	/**
	 * 提交时间
	 */
	private Date commitTime;	

	/**
	 * 审核人id
	 */
	private Long auditUserId;	

	/**
	 * 审核人姓名
	 */
	private String auditUserName;	

	/**
	 * 审核时间
	 */
	private Date auditTime;	

	/**
	 * 审核原因
	 */
	private String auditReason;	

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
	 * 平台id
	 */
	private Long platformId;	

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
	 * 租赁单编号
	 * @return 租赁单编号
	 */
	public String getLeaseCode() {
		return leaseCode;
	}

	/**
	 * 租赁单编号
	 * @param leaseCode 租赁单编号
	 */
	public void setLeaseCode(String leaseCode) {
		this.leaseCode = leaseCode;
	}
	/**
	 * 租赁单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 * @return 租赁单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 */
	public Integer getLeaseStatus() {
		return leaseStatus;
	}

	/**
	 * 租赁单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 * @param leaseStatus 租赁单状态 0:待报价 1:已报价 2:已取消 3:已下单
	 */
	public void setLeaseStatus(Integer leaseStatus) {
		this.leaseStatus = leaseStatus;
	}
	/**
	 * 租赁单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 * @return 租赁单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 */
	public Integer getLeaseAuditStatus() {
		return leaseAuditStatus;
	}

	/**
	 * 租赁单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 * @param leaseAuditStatus 租赁单审核状态 0:待审核 1:审核通过 -1:审核不通过
	 */
	public void setLeaseAuditStatus(Integer leaseAuditStatus) {
		this.leaseAuditStatus = leaseAuditStatus;
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
	 * 租金
	 * @return 租金
	 */
	public BigDecimal getRentalPrice() {
		return rentalPrice;
	}

	/**
	 * 租金
	 * @param rentalPrice 租金
	 */
	public void setRentalPrice(BigDecimal rentalPrice) {
		this.rentalPrice = rentalPrice;
	}
	/**
	 * 押金
	 * @return 押金
	 */
	public BigDecimal getDepositPrice() {
		return depositPrice;
	}

	/**
	 * 押金
	 * @param depositPrice 押金
	 */
	public void setDepositPrice(BigDecimal depositPrice) {
		this.depositPrice = depositPrice;
	}
	/**
	 * 商品总价格=租金+押金
	 * @return 商品总价格=租金+押金
	 */
	public BigDecimal getProductPrice() {
		return productPrice;
	}

	/**
	 * 商品总价格=租金+押金
	 * @param productPrice 商品总价格=租金+押金
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
	 * 租赁开始时间
	 * @return 租赁开始时间
	 */
	public Date getLeaseStartTime() {
		return leaseStartTime;
	}

	/**
	 * 租赁开始时间
	 * @param leaseStartTime 租赁开始时间
	 */
	public void setLeaseStartTime(Date leaseStartTime) {
		this.leaseStartTime = leaseStartTime;
	}
	/**
	 * 租赁结束时间
	 * @return 租赁结束时间
	 */
	public Date getLeaseEndTime() {
		return leaseEndTime;
	}

	/**
	 * 租赁结束时间
	 * @param leaseEndTime 租赁结束时间
	 */
	public void setLeaseEndTime(Date leaseEndTime) {
		this.leaseEndTime = leaseEndTime;
	}
	/**
	 * 买家备注
	 * @return 买家备注
	 */
	public String getLeaseRemarkUser() {
		return leaseRemarkUser;
	}

	/**
	 * 买家备注
	 * @param leaseRemarkUser 买家备注
	 */
	public void setLeaseRemarkUser(String leaseRemarkUser) {
		this.leaseRemarkUser = leaseRemarkUser;
	}
	/**
	 * 客服备注
	 * @return 客服备注
	 */
	public String getLeaseRemarkC() {
		return leaseRemarkC;
	}

	/**
	 * 客服备注
	 * @param leaseRemarkC 客服备注
	 */
	public void setLeaseRemarkC(String leaseRemarkC) {
		this.leaseRemarkC = leaseRemarkC;
	}
	/**
	 * 客服给用户备注
	 * @return 客服给用户备注
	 */
	public String getLeaseRemarkCUser() {
		return leaseRemarkCUser;
	}

	/**
	 * 客服给用户备注
	 * @param leaseRemarkCUser 客服给用户备注
	 */
	public void setLeaseRemarkCUser(String leaseRemarkCUser) {
		this.leaseRemarkCUser = leaseRemarkCUser;
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
	 * 提交人id
	 * @return 提交人id
	 */
	public Long getCommitUserId() {
		return commitUserId;
	}

	/**
	 * 提交人id
	 * @param commitUserId 提交人id
	 */
	public void setCommitUserId(Long commitUserId) {
		this.commitUserId = commitUserId;
	}
	/**
	 * 提交人id
	 * @return 提交人id
	 */
	public String getCommitUserName() {
		return commitUserName;
	}

	/**
	 * 提交人id
	 * @param commitUserName 提交人id
	 */
	public void setCommitUserName(String commitUserName) {
		this.commitUserName = commitUserName;
	}
	/**
	 * 提交时间
	 * @return 提交时间
	 */
	public Date getCommitTime() {
		return commitTime;
	}

	/**
	 * 提交时间
	 * @param commitTime 提交时间
	 */
	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	/**
	 * 审核人id
	 * @return 审核人id
	 */
	public Long getAuditUserId() {
		return auditUserId;
	}

	/**
	 * 审核人id
	 * @param auditUserId 审核人id
	 */
	public void setAuditUserId(Long auditUserId) {
		this.auditUserId = auditUserId;
	}
	/**
	 * 审核人姓名
	 * @return 审核人姓名
	 */
	public String getAuditUserName() {
		return auditUserName;
	}

	/**
	 * 审核人姓名
	 * @param auditUserName 审核人姓名
	 */
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	/**
	 * 审核时间
	 * @return 审核时间
	 */
	public Date getAuditTime() {
		return auditTime;
	}

	/**
	 * 审核时间
	 * @param auditTime 审核时间
	 */
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
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
	