package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author tan
 * @date 2019-01-11 16:12:55
 */
public class CmsInstCompanyPO {


	private Long id;

	/**
	 * 实例ID
	 */
	private Long instId;	

	/**
	 * 公司ID
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
	 * 实例ID
	 * @return 实例ID
	 */
	public Long getInstId() {
		return instId;
	}

	/**
	 * 实例ID
	 * @param instId 实例ID
	 */
	public void setInstId(Long instId) {
		this.instId = instId;
	}

	/**
	 * 公司ID
	 * @return 公司ID
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司ID
	 * @param companyId 公司ID
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
	