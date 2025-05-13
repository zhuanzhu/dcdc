package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-09-11 20:07:35
 */
public class CouponStoreDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 优惠卷id
	 */
	private Long couponId;	

	/**
	 * 企业id  -1:所有企业
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
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 优惠卷id
	 * @return 优惠卷id
	 */
	public Long getCouponId() {
		return couponId;
	}

	/**
	 * 优惠卷id
	 * @param couponId 优惠卷id
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	/**
	 * 企业id  -1:所有企业
	 * @return 企业id  -1:所有企业
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 企业id  -1:所有企业
	 * @param storeId 企业id  -1:所有企业
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
	