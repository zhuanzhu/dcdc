package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:25
 */
public class SoReturnDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 父单id
	 */
	private String parentOrderCode;	

	/**
	 * 格式：时间(精确到毫秒20170801155019916)+用户id
	 */
	private String orderCode;	

	/**
	 * 用户ID
	 */
	private Long userId;	

	/**
	 * 商家编号
	 */
	private Long merchantId;	

	/**
	 * 退货状态 1:退货待审核 2:退货待寄件 3:退货审核失败 4:已转退货单待收件 5:已收件待转退款单 6:退货验货不通过 8:退货已退款
	 */
	private Integer returnStatus;	

	/**
	 * 退款状态
	 */
	private Integer refundStatus;	

	/**
	 * 实际退款金额
	 */
	private BigDecimal actualReturnAmount;	

	/**
	 * 用户申请退款金额
	 */
	private BigDecimal applyReturnAmount;	

	/**
	 * 退款方式，和支付方式一样
	 */
	private Integer returnType;	

	/**
	 * 退货用户描述
	 */
	private String returnRemark;	

	/**
	 * 客服的用户编号
	 */
	private Long serviceUserId;	

	/**
	 * 收件客服描述
	 */
	private String serviceDesc;	

	/**
	 * 客服选择的退货原因
	 */
	private String serviceReturnReason;	

	/**
	 * 退货申请时间
	 */
	private Date applyTime;	

	/**
	 * 退货原因
	 */
	private String returnReason;	

	/**
	 * 退款时间
	 */
	private Date refundTime;	

	/**
	 * 收件地址
	 */
	private String consigneeAddress;	

	/**
	 * 收件人姓名
	 */
	private String consigneeName;	

	/**
	 * 收件人的联系电话/手机
	 */
	private String consigneeMobile;	

	/**
	 * 快递单号/运单号
	 */
	private String courierNumber;	

	/**
	 * 物流公司
	 */
	private String logisticsCompany;	

	/**
	 * 回填退款单号，确认退款的用户编号
	 */
	private Long refundConfirmUserId;	

	/**
	 * 退款单号
	 */
	private String refundNo;	

	/**
	 * 审核原因
	 */
	private String auditReason;	

	/**
	 * 审核人id
	 */
	private Long auditUserId;	

	/**
	 * 审核人姓名
	 */
	private String auditUserName;	

	/**
	 * 运费
	 */
	private BigDecimal freight;	

	/**
	 * 订单的取消状态  0 ：正常； 1:用户取消待审核 2:已取消待退款 ；3：已取消已退款
	 */
	private Integer cancelStatus;	

	/**
	 * 审核时间
	 */
	private Date auditTime;	

	/**
	 * 类型 1:订单取消退款  2:退货退款
	 */
	private Integer type;	

	/**
	 * 下单时间
	 */
	private Date orderCreateTime;	

	/**
	 * 退款原因编号，多个用英文逗号分隔
	 */
	private String returnReasonId;	

	/**
	 * 退货编码
	 */
	private String returnCode;	

	/**
	 * 是否取货：0 否  1 是
	 */
	private Integer isPickUp;	

	/**
	 * 验货不通过返件快递单号/运单号
	 */
	private String userCourierNumber;	

	/**
	 * 验货不通过返件物流公司id
	 */
	private String userLogisticsCompanyId;	

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
	 * 父单id
	 * @return 父单id
	 */
	public String getParentOrderCode() {
		return parentOrderCode;
	}

	/**
	 * 父单id
	 * @param parentOrderCode 父单id
	 */
	public void setParentOrderCode(String parentOrderCode) {
		this.parentOrderCode = parentOrderCode;
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
	 * 商家编号
	 * @return 商家编号
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家编号
	 * @param merchantId 商家编号
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * 退货状态 1:退货待审核 2:退货待寄件 3:退货审核失败 4:已转退货单待收件 5:已收件待转退款单 6:退货验货不通过 8:退货已退款
	 * @return 退货状态 1:退货待审核 2:退货待寄件 3:退货审核失败 4:已转退货单待收件 5:已收件待转退款单 6:退货验货不通过 8:退货已退款
	 */
	public Integer getReturnStatus() {
		return returnStatus;
	}

	/**
	 * 退货状态 1:退货待审核 2:退货待寄件 3:退货审核失败 4:已转退货单待收件 5:已收件待转退款单 6:退货验货不通过 8:退货已退款
	 * @param returnStatus 退货状态 1:退货待审核 2:退货待寄件 3:退货审核失败 4:已转退货单待收件 5:已收件待转退款单 6:退货验货不通过 8:退货已退款
	 */
	public void setReturnStatus(Integer returnStatus) {
		this.returnStatus = returnStatus;
	}
	/**
	 * 退款状态
	 * @return 退款状态
	 */
	public Integer getRefundStatus() {
		return refundStatus;
	}

	/**
	 * 退款状态
	 * @param refundStatus 退款状态
	 */
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}
	/**
	 * 实际退款金额
	 * @return 实际退款金额
	 */
	public BigDecimal getActualReturnAmount() {
		return actualReturnAmount;
	}

	/**
	 * 实际退款金额
	 * @param actualReturnAmount 实际退款金额
	 */
	public void setActualReturnAmount(BigDecimal actualReturnAmount) {
		this.actualReturnAmount = actualReturnAmount;
	}
	/**
	 * 用户申请退款金额
	 * @return 用户申请退款金额
	 */
	public BigDecimal getApplyReturnAmount() {
		return applyReturnAmount;
	}

	/**
	 * 用户申请退款金额
	 * @param applyReturnAmount 用户申请退款金额
	 */
	public void setApplyReturnAmount(BigDecimal applyReturnAmount) {
		this.applyReturnAmount = applyReturnAmount;
	}
	/**
	 * 退款方式，和支付方式一样
	 * @return 退款方式，和支付方式一样
	 */
	public Integer getReturnType() {
		return returnType;
	}

	/**
	 * 退款方式，和支付方式一样
	 * @param returnType 退款方式，和支付方式一样
	 */
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}
	/**
	 * 退货用户描述
	 * @return 退货用户描述
	 */
	public String getReturnRemark() {
		return returnRemark;
	}

	/**
	 * 退货用户描述
	 * @param returnRemark 退货用户描述
	 */
	public void setReturnRemark(String returnRemark) {
		this.returnRemark = returnRemark;
	}
	/**
	 * 客服的用户编号
	 * @return 客服的用户编号
	 */
	public Long getServiceUserId() {
		return serviceUserId;
	}

	/**
	 * 客服的用户编号
	 * @param serviceUserId 客服的用户编号
	 */
	public void setServiceUserId(Long serviceUserId) {
		this.serviceUserId = serviceUserId;
	}
	/**
	 * 收件客服描述
	 * @return 收件客服描述
	 */
	public String getServiceDesc() {
		return serviceDesc;
	}

	/**
	 * 收件客服描述
	 * @param serviceDesc 收件客服描述
	 */
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	/**
	 * 客服选择的退货原因
	 * @return 客服选择的退货原因
	 */
	public String getServiceReturnReason() {
		return serviceReturnReason;
	}

	/**
	 * 客服选择的退货原因
	 * @param serviceReturnReason 客服选择的退货原因
	 */
	public void setServiceReturnReason(String serviceReturnReason) {
		this.serviceReturnReason = serviceReturnReason;
	}
	/**
	 * 退货申请时间
	 * @return 退货申请时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * 退货申请时间
	 * @param applyTime 退货申请时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 退货原因
	 * @return 退货原因
	 */
	public String getReturnReason() {
		return returnReason;
	}

	/**
	 * 退货原因
	 * @param returnReason 退货原因
	 */
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	/**
	 * 退款时间
	 * @return 退款时间
	 */
	public Date getRefundTime() {
		return refundTime;
	}

	/**
	 * 退款时间
	 * @param refundTime 退款时间
	 */
	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
	/**
	 * 收件地址
	 * @return 收件地址
	 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	/**
	 * 收件地址
	 * @param consigneeAddress 收件地址
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/**
	 * 收件人姓名
	 * @return 收件人姓名
	 */
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * 收件人姓名
	 * @param consigneeName 收件人姓名
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	/**
	 * 收件人的联系电话/手机
	 * @return 收件人的联系电话/手机
	 */
	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	/**
	 * 收件人的联系电话/手机
	 * @param consigneeMobile 收件人的联系电话/手机
	 */
	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}
	/**
	 * 快递单号/运单号
	 * @return 快递单号/运单号
	 */
	public String getCourierNumber() {
		return courierNumber;
	}

	/**
	 * 快递单号/运单号
	 * @param courierNumber 快递单号/运单号
	 */
	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}
	/**
	 * 物流公司
	 * @return 物流公司
	 */
	public String getLogisticsCompany() {
		return logisticsCompany;
	}

	/**
	 * 物流公司
	 * @param logisticsCompany 物流公司
	 */
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	/**
	 * 回填退款单号，确认退款的用户编号
	 * @return 回填退款单号，确认退款的用户编号
	 */
	public Long getRefundConfirmUserId() {
		return refundConfirmUserId;
	}

	/**
	 * 回填退款单号，确认退款的用户编号
	 * @param refundConfirmUserId 回填退款单号，确认退款的用户编号
	 */
	public void setRefundConfirmUserId(Long refundConfirmUserId) {
		this.refundConfirmUserId = refundConfirmUserId;
	}
	/**
	 * 退款单号
	 * @return 退款单号
	 */
	public String getRefundNo() {
		return refundNo;
	}

	/**
	 * 退款单号
	 * @param refundNo 退款单号
	 */
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
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
	 * 运费
	 * @return 运费
	 */
	public BigDecimal getFreight() {
		return freight;
	}

	/**
	 * 运费
	 * @param freight 运费
	 */
	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}
	/**
	 * 订单的取消状态  0 ：正常； 1:用户取消待审核 2:已取消待退款 ；3：已取消已退款
	 * @return 订单的取消状态  0 ：正常； 1:用户取消待审核 2:已取消待退款 ；3：已取消已退款
	 */
	public Integer getCancelStatus() {
		return cancelStatus;
	}

	/**
	 * 订单的取消状态  0 ：正常； 1:用户取消待审核 2:已取消待退款 ；3：已取消已退款
	 * @param cancelStatus 订单的取消状态  0 ：正常； 1:用户取消待审核 2:已取消待退款 ；3：已取消已退款
	 */
	public void setCancelStatus(Integer cancelStatus) {
		this.cancelStatus = cancelStatus;
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
	 * 类型 1:订单取消退款  2:退货退款
	 * @return 类型 1:订单取消退款  2:退货退款
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 1:订单取消退款  2:退货退款
	 * @param type 类型 1:订单取消退款  2:退货退款
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 下单时间
	 * @return 下单时间
	 */
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}

	/**
	 * 下单时间
	 * @param orderCreateTime 下单时间
	 */
	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	/**
	 * 退款原因编号，多个用英文逗号分隔
	 * @return 退款原因编号，多个用英文逗号分隔
	 */
	public String getReturnReasonId() {
		return returnReasonId;
	}

	/**
	 * 退款原因编号，多个用英文逗号分隔
	 * @param returnReasonId 退款原因编号，多个用英文逗号分隔
	 */
	public void setReturnReasonId(String returnReasonId) {
		this.returnReasonId = returnReasonId;
	}
	/**
	 * 退货编码
	 * @return 退货编码
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * 退货编码
	 * @param returnCode 退货编码
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	/**
	 * 是否取货：0 否  1 是
	 * @return 是否取货：0 否  1 是
	 */
	public Integer getIsPickUp() {
		return isPickUp;
	}

	/**
	 * 是否取货：0 否  1 是
	 * @param isPickUp 是否取货：0 否  1 是
	 */
	public void setIsPickUp(Integer isPickUp) {
		this.isPickUp = isPickUp;
	}
	/**
	 * 验货不通过返件快递单号/运单号
	 * @return 验货不通过返件快递单号/运单号
	 */
	public String getUserCourierNumber() {
		return userCourierNumber;
	}

	/**
	 * 验货不通过返件快递单号/运单号
	 * @param userCourierNumber 验货不通过返件快递单号/运单号
	 */
	public void setUserCourierNumber(String userCourierNumber) {
		this.userCourierNumber = userCourierNumber;
	}
	/**
	 * 验货不通过返件物流公司id
	 * @return 验货不通过返件物流公司id
	 */
	public String getUserLogisticsCompanyId() {
		return userLogisticsCompanyId;
	}

	/**
	 * 验货不通过返件物流公司id
	 * @param userLogisticsCompanyId 验货不通过返件物流公司id
	 */
	public void setUserLogisticsCompanyId(String userLogisticsCompanyId) {
		this.userLogisticsCompanyId = userLogisticsCompanyId;
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
}
	