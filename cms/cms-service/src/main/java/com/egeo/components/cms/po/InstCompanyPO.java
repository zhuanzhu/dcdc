package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:49
 */
public class InstCompanyPO {


	private Long id;

	/**
	 * 实例id
	 */
	private Long instId;	

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 创建时间
	 */
	private Date updateTime;	

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
	 * 实例id
	 * @return 实例id
	 */
	public Long getInstId() {
		return instId;
	}

	/**
	 * 实例id
	 * @param instId 实例id
	 */
	public void setInstId(Long instId) {
		this.instId = instId;
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
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 创建时间
	 * @param updateTime 创建时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	