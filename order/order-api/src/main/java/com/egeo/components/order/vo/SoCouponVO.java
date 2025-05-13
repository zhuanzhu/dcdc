package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:00:01
 */
public class SoCouponVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 订单编号
	 */

	private String orderCode;		 
	/**
	 * 父订单编号
	 */

	private String parentOrderCode;		 
	/**
	 * 优惠券编码
	 */

	private String couponCode;		 
	/**
	 * 优惠券分摊类型：0 整单;1 商品维度
	 */

	private Integer couponShareType;		 
	/**
	 * 此优惠券共优惠的金额
	 */

	private BigDecimal couponAmount;		 
	/**
	 * 优惠券使用数量
	 */

	private Integer couponNum;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
	/**
	 * 优惠券ID
	 */

	private Long couponId;		 
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
	 * 唯一主键
	 * @param id 唯一主键
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
	 * 父订单编号
	 * @return 父订单编号
	 */
	public String getParentOrderCode() {
		return parentOrderCode;
	}

	/**
	 * 父订单编号
	 * @param parentOrderCode 父订单编号
	 */
	public void setParentOrderCode(String parentOrderCode) {
		this.parentOrderCode = parentOrderCode;
	}	
	/**
	 * 优惠券编码
	 * @return 优惠券编码
	 */
	public String getCouponCode() {
		return couponCode;
	}

	/**
	 * 优惠券编码
	 * @param couponCode 优惠券编码
	 */
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}	
	/**
	 * 优惠券分摊类型：0 整单;1 商品维度
	 * @return 优惠券分摊类型：0 整单;1 商品维度
	 */
	public Integer getCouponShareType() {
		return couponShareType;
	}

	/**
	 * 优惠券分摊类型：0 整单;1 商品维度
	 * @param couponShareType 优惠券分摊类型：0 整单;1 商品维度
	 */
	public void setCouponShareType(Integer couponShareType) {
		this.couponShareType = couponShareType;
	}	
	/**
	 * 此优惠券共优惠的金额
	 * @return 此优惠券共优惠的金额
	 */
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	/**
	 * 此优惠券共优惠的金额
	 * @param couponAmount 此优惠券共优惠的金额
	 */
	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}	
	/**
	 * 优惠券使用数量
	 * @return 优惠券使用数量
	 */
	public Integer getCouponNum() {
		return couponNum;
	}

	/**
	 * 优惠券使用数量
	 * @param couponNum 优惠券使用数量
	 */
	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
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
	 * 优惠券ID
	 * @return 优惠券ID
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * 优惠券ID
	 * @param couponId 优惠券ID
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
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
	