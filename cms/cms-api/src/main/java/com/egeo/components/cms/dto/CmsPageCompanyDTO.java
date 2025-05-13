package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2019-01-11 14:36:43
 */
public class CmsPageCompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 页面ID
	 */
	private Long pageId;	

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
	 * 页面ID
	 * @return 页面ID
	 */
	public Long getPageId() {
		return pageId;
	}

	/**
	 * 页面ID
	 * @param pageId 页面ID
	 */
	public void setPageId(Long pageId) {
		this.pageId = pageId;
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
	