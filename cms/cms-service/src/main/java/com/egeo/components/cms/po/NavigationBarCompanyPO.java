package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-08-01 17:43:32
 */
public class NavigationBarCompanyPO {


	private Long id;

	/**
	 * 导航栏id
	 */
	private Long navigationBarId;	

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

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
	 * 导航栏id
	 * @return 导航栏id
	 */
	public Long getNavigationBarId() {
		return navigationBarId;
	}

	/**
	 * 导航栏id
	 * @param navigationBarId 导航栏id
	 */
	public void setNavigationBarId(Long navigationBarId) {
		this.navigationBarId = navigationBarId;
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
	