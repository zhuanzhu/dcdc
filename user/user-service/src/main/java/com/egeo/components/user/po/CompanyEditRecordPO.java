package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-01-12 15:44:59
 */
public class CompanyEditRecordPO {


	private Long id;

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 公司名称
	 */
	private String companyName;	

	/**
	 * 记录人id
	 */
	private Long userId;	

	/**
	 * 记录人姓名
	 */
	private String userName;	

	/**
	 * 编辑时间
	 */
	private Date editTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	public Long getId() {
		return id;
	}

	/**
	 * id 主键
	 * @param id id 主键
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
	 * 公司名称
	 * @return 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 公司名称
	 * @param companyName 公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 记录人id
	 * @return 记录人id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 记录人id
	 * @param userId 记录人id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 记录人姓名
	 * @return 记录人姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 记录人姓名
	 * @param userName 记录人姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 编辑时间
	 * @return 编辑时间
	 */
	public Date getEditTime() {
		return editTime;
	}

	/**
	 * 编辑时间
	 * @param editTime 编辑时间
	 */
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
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
}
	