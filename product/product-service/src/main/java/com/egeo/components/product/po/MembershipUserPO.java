package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author whf
 * @date 2018-09-06 16:12:29
 */
public class MembershipUserPO {


	private Long id;

	/**
	 * 
	 */
	private Long membershipId;	

	/**
	 * 会员id
	 */
	private Long userId;	

	/**
	 * 用户的会籍状态(0.失效;1.有效)
	 */
	private Integer statusCode;	

	/**
	 * 会籍创建日(年月日)
	 */
	private Date startTime;	

	/**
	 * 会籍失效日(年月日)
	 */
	private Date endTime;	

	/**
	 * 创建时间(自动创建)
	 */
	private Date createTime;	

	/**
	 * 更新时间(自动更新)
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
	public Long getMembershipId() {
		return membershipId;
	}

	/**
	 * 
	 * @param membershipId 
	 */
	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}

	/**
	 * 会员id
	 * @return 会员id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 会员id
	 * @param userId 会员id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 用户的会籍状态(0.失效;1.有效)
	 * @return 用户的会籍状态(0.失效;1.有效)
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * 用户的会籍状态(0.失效;1.有效)
	 * @param statusCode 用户的会籍状态(0.失效;1.有效)
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * 会籍创建日(年月日)
	 * @return 会籍创建日(年月日)
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 会籍创建日(年月日)
	 * @param startTime 会籍创建日(年月日)
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 会籍失效日(年月日)
	 * @return 会籍失效日(年月日)
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 会籍失效日(年月日)
	 * @param endTime 会籍失效日(年月日)
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 创建时间(自动创建)
	 * @return 创建时间(自动创建)
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间(自动创建)
	 * @param createTime 创建时间(自动创建)
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间(自动更新)
	 * @return 更新时间(自动更新)
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间(自动更新)
	 * @param updateTime 更新时间(自动更新)
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	