package com.egeo.components.finance.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-05-10 13:49:03
 */
public class ReasonCompanyPO {


	private Long id;

	/**
	 * 原因id
	 */
	private Long reasonId;	

	/**
	 * 公司id
	 */
	private Long companyId;	

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
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 原因id
	 * @return 原因id
	 */
	public Long getReasonId() {
		return reasonId;
	}

	/**
	 * 原因id
	 * @param reasonId 原因id
	 */
	public void setReasonId(Long reasonId) {
		this.reasonId = reasonId;
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
	