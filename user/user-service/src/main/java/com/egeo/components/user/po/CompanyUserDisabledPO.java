package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author ziyi
 * @date 2019-01-03 19:50:28
 */
public class CompanyUserDisabledPO {


	private Long id;

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 企业账号失效时间
	 */
	private Date disabledTime;

	/**
	 * 是否恢复有效 1:是 0:否
	 */
	private Integer revalidation;

	public Integer getRevalidation() {
		return revalidation;
	}

	public void setRevalidation(Integer revalidation) {
		this.revalidation = revalidation;
	}

	public Date getDisabledTime() {
		return disabledTime;
	}

	public void setDisabledTime(Date disabledTime) {
		this.disabledTime = disabledTime;
	}
	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
	