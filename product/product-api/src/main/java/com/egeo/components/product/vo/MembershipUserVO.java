package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author whf
 * @date 2018-09-06 16:12:29
 */
public class MembershipUserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */
	private Long membershipId;
	private String membershipCategoryName;

	public String getMembershipCategoryName() {
		return membershipCategoryName;
	}

	public void setMembershipCategoryName(String membershipCategoryName) {
		this.membershipCategoryName = membershipCategoryName;
	}

	/**
	 * 会员id
	 */
	private Long userId;
	private String userName;
	private String mail;
	private String mobile;
	private Integer sex;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private Date birthday;
	private Long companyId;
	private String companyName;
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
	