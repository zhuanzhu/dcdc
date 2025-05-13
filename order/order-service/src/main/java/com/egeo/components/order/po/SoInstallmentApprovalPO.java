package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoInstallmentApprovalPO {


	private Long id;

	/**
	 * 改价id
	 */
	private Long modifyPriceId;	

	/**
	 * 分批支付id
	 */
	private Long installment_id;	

	/**
	 * 批次号 1,2
	 */
	private Integer batchNum;	

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 支付节点 1:发货前 2:发货后
	 */
	private Integer payPrecondition;	

	/**
	 * 支付状态 0 未支付  1 已支付
	 */
	private Integer payStatus;	

	/**
	 * 支付金额
	 */
	private BigDecimal payPrice;	

	/**
	 * 客服ID
	 */
	private Long cId;	

	/**
	 * 客服名称
	 */
	private String cName;	

	/**
	 * 支付时间
	 */
	private Date payTime;	

	/**
	 * 支付备注
	 */
	private String payComment;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 改价id
	 * @return 改价id
	 */
	public Long getModifyPriceId() {
		return modifyPriceId;
	}

	/**
	 * 改价id
	 * @param modifyPriceId 改价id
	 */
	public void setModifyPriceId(Long modifyPriceId) {
		this.modifyPriceId = modifyPriceId;
	}

	/**
	 * 分批支付id
	 * @return 分批支付id
	 */
	public Long getInstallment_id() {
		return installment_id;
	}

	/**
	 * 分批支付id
	 * @param installment_id 分批支付id
	 */
	public void setInstallment_id(Long installment_id) {
		this.installment_id = installment_id;
	}

	/**
	 * 批次号 1,2
	 * @return 批次号 1,2
	 */
	public Integer getBatchNum() {
		return batchNum;
	}

	/**
	 * 批次号 1,2
	 * @param batchNum 批次号 1,2
	 */
	public void setBatchNum(Integer batchNum) {
		this.batchNum = batchNum;
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
	 * 支付节点 1:发货前 2:发货后
	 * @return 支付节点 1:发货前 2:发货后
	 */
	public Integer getPayPrecondition() {
		return payPrecondition;
	}

	/**
	 * 支付节点 1:发货前 2:发货后
	 * @param payPrecondition 支付节点 1:发货前 2:发货后
	 */
	public void setPayPrecondition(Integer payPrecondition) {
		this.payPrecondition = payPrecondition;
	}

	/**
	 * 支付状态 0 未支付  1 已支付
	 * @return 支付状态 0 未支付  1 已支付
	 */
	public Integer getPayStatus() {
		return payStatus;
	}

	/**
	 * 支付状态 0 未支付  1 已支付
	 * @param payStatus 支付状态 0 未支付  1 已支付
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 支付金额
	 * @return 支付金额
	 */
	public BigDecimal getPayPrice() {
		return payPrice;
	}

	/**
	 * 支付金额
	 * @param payPrice 支付金额
	 */
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
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
	 * 支付时间
	 * @return 支付时间
	 */
	public Date getPayTime() {
		return payTime;
	}

	/**
	 * 支付时间
	 * @param payTime 支付时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	/**
	 * 支付备注
	 * @return 支付备注
	 */
	public String getPayComment() {
		return payComment;
	}

	/**
	 * 支付备注
	 * @param payComment 支付备注
	 */
	public void setPayComment(String payComment) {
		this.payComment = payComment;
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
	