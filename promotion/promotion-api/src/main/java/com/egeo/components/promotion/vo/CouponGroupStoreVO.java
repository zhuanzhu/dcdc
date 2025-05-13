package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author paul
 * @date 2018-09-18 11:29:53
 */
public class CouponGroupStoreVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 优惠券组Id
	 */
	private Long couponGroupId;
	/**
	 * 门店标识id
	 */
	private Long storeId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	/**
	 * 主键标识
	 * @param id 主键标识
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 优惠券组Id
	 * @return 优惠券组Id
	 */
	public Long getCouponGroupId() {
		return couponGroupId;
	}

	/**
	 * 优惠券组Id
	 * @param couponGroupId 优惠券组Id
	 */
	public void setCouponGroupId(Long couponGroupId) {
		this.couponGroupId = couponGroupId;
	}	
	/**
	 * 门店标识id
	 * @return 门店标识id
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 门店标识id
	 * @param storeId 门店标识id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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
}
	