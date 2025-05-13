package com.egeo.components.promotion.po;


import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-06-20 13:30:32
 */
public class CouponBatchCompanyPO {


	private Long id;

	/**
	 * 优惠卷批次id
	 */
	private Long couponBatchId;	

	/**
	 * 公司id
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
	 * 优惠卷批次id
	 * @return 优惠卷批次id
	 */
	public Long getCouponBatchId() {
		return couponBatchId;
	}

	/**
	 * 优惠卷批次id
	 * @param couponBatchId 优惠卷批次id
	 */
	public void setCouponBatchId(Long couponBatchId) {
		this.couponBatchId = couponBatchId;
	}

	/**
	 * 公司id
	 * @return 公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id
	 * @param companyId 公司id
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
	