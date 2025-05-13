package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:00:01
 */
public class RefundmentVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 订单编号
	 */

	private String orderCode;		 
	/**
	 * 退货编码
	 */

	private String returnCode;		 
	/**
	 * 退款编码
	 */

	private String refundmentCode;		 
	/**
	 * 退款金额
	 */

	private BigDecimal amount;		 
	/**
	 * 退款渠道：与订单表的二级支付值一致
	 */

	private Integer channel;		 
	/**
	 * 申请退款时间
	 */

	private Date applyTime;		 
	/**
	 * 退款时间
	 */

	private Date refundmentTime;		 
	/**
	 * 退款凭证
	 */

	private String voucher;		 
	/**
	 * 退款状态 1：未退款  2:已退款
	 */

	private Integer refundmentStatus;		 
	/**
	 * 退款类型 1：取消订单  2.退货
	 */

	private Integer refundmentType;		 
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
	 * 退款编码
	 * @return 退款编码
	 */
	public String getRefundmentCode() {
		return refundmentCode;
	}

	/**
	 * 退款编码
	 * @param refundmentCode 退款编码
	 */
	public void setRefundmentCode(String refundmentCode) {
		this.refundmentCode = refundmentCode;
	}	
	/**
	 * 退款金额
	 * @return 退款金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 退款金额
	 * @param amount 退款金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}	
	/**
	 * 退款渠道：与订单表的二级支付值一致
	 * @return 退款渠道：与订单表的二级支付值一致
	 */
	public Integer getChannel() {
		return channel;
	}

	/**
	 * 退款渠道：与订单表的二级支付值一致
	 * @param channel 退款渠道：与订单表的二级支付值一致
	 */
	public void setChannel(Integer channel) {
		this.channel = channel;
	}	
	/**
	 * 申请退款时间
	 * @return 申请退款时间
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * 申请退款时间
	 * @param applyTime 申请退款时间
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}	
	/**
	 * 退款时间
	 * @return 退款时间
	 */
	public Date getRefundmentTime() {
		return refundmentTime;
	}

	/**
	 * 退款时间
	 * @param refundmentTime 退款时间
	 */
	public void setRefundmentTime(Date refundmentTime) {
		this.refundmentTime = refundmentTime;
	}	
	/**
	 * 退款凭证
	 * @return 退款凭证
	 */
	public String getVoucher() {
		return voucher;
	}

	/**
	 * 退款凭证
	 * @param voucher 退款凭证
	 */
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}	
	/**
	 * 退款状态 1：未退款  2:已退款
	 * @return 退款状态 1：未退款  2:已退款
	 */
	public Integer getRefundmentStatus() {
		return refundmentStatus;
	}

	/**
	 * 退款状态 1：未退款  2:已退款
	 * @param refundmentStatus 退款状态 1：未退款  2:已退款
	 */
	public void setRefundmentStatus(Integer refundmentStatus) {
		this.refundmentStatus = refundmentStatus;
	}	
	/**
	 * 退款类型 1：取消订单  2.退货
	 * @return 退款类型 1：取消订单  2.退货
	 */
	public Integer getRefundmentType() {
		return refundmentType;
	}

	/**
	 * 退款类型 1：取消订单  2.退货
	 * @param refundmentType 退款类型 1：取消订单  2.退货
	 */
	public void setRefundmentType(Integer refundmentType) {
		this.refundmentType = refundmentType;
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
	