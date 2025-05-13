package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-06-14 13:49:35
 */
public class CouponCompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */
	private Long id;	

	/**
	 * 优惠卷id
	 */
	private Long couponId;	

	/**
	 * 企业id  -1:所有企业
	 */
	private Long companyId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 
	 * @return 
	 */
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
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 企业id  -1:所有企业
	 * @param companyId 企业id  -1:所有企业
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	