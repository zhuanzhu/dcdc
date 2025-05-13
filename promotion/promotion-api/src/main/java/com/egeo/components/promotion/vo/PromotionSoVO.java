package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-11 19:02:51
 */
public class PromotionSoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */

	private Long promotionId;		 
	/**
	 * 子订单号
	 */

	private String orderCode;		 
	/**
	 * 用户id（userid）
	 */

	private Long customerId;		 
	/**
	 * 分单订单金额
	 */

	private BigDecimal soSalesAmount;		 
	/**
	 * 分单优惠金额
	 */

	private BigDecimal soDiscountAmount;		 
	/**
	 * 订单状态：0 正常，1 前台取消，2 后台取消，3 退货，4 回滚
	 */

	private Integer status;		 
	/**
	 * 
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
	 * 
	 * @return 
	 */
	public Long getPromotionId() {
		return promotionId;
	}

	/**
	 * 
	 * @param promotionId 
	 */
	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}	
	/**
	 * 子订单号
	 * @return 子订单号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 子订单号
	 * @param orderCode 子订单号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}	
	/**
	 * 用户id（userid）
	 * @return 用户id（userid）
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * 用户id（userid）
	 * @param customerId 用户id（userid）
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}	
	/**
	 * 分单订单金额
	 * @return 分单订单金额
	 */
	public BigDecimal getSoSalesAmount() {
		return soSalesAmount;
	}

	/**
	 * 分单订单金额
	 * @param soSalesAmount 分单订单金额
	 */
	public void setSoSalesAmount(BigDecimal soSalesAmount) {
		this.soSalesAmount = soSalesAmount;
	}	
	/**
	 * 分单优惠金额
	 * @return 分单优惠金额
	 */
	public BigDecimal getSoDiscountAmount() {
		return soDiscountAmount;
	}

	/**
	 * 分单优惠金额
	 * @param soDiscountAmount 分单优惠金额
	 */
	public void setSoDiscountAmount(BigDecimal soDiscountAmount) {
		this.soDiscountAmount = soDiscountAmount;
	}	
	/**
	 * 订单状态：0 正常，1 前台取消，2 后台取消，3 退货，4 回滚
	 * @return 订单状态：0 正常，1 前台取消，2 后台取消，3 退货，4 回滚
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 订单状态：0 正常，1 前台取消，2 后台取消，3 退货，4 回滚
	 * @param status 订单状态：0 正常，1 前台取消，2 后台取消，3 退货，4 回滚
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}	
	/**
	 * 
	 * @return 
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 
	 * @param platformId 
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
	