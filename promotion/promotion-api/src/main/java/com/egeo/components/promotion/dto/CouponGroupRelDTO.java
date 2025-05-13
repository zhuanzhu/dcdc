package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-06-14 18:02:28
 */
public class CouponGroupRelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 优惠卷分组id
	 */
	private Long couponGroupId;	

	/**
	 * 优惠卷id
	 */
	private Long couponId;	

	/**
	 * 
	 */
	private Date createTime;	

	/**
	 * 
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
	 * 优惠卷分组id
	 * @return 优惠卷分组id
	 */
	public Long getCouponGroupId() {
		return couponGroupId;
	}

	/**
	 * 优惠卷分组id
	 * @param couponGroupId 优惠卷分组id
	 */
	public void setCouponGroupId(Long couponGroupId) {
		this.couponGroupId = couponGroupId;
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
	 * 
	 * @return 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 
	 * @return 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	