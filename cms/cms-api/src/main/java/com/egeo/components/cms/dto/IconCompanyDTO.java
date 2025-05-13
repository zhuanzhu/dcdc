package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-24 17:30:36
 */
public class IconCompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * iconId
	 */
	private Long iconId;	

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
	 * iconId
	 * @return iconId
	 */
	public Long getIconId() {
		return iconId;
	}

	/**
	 * iconId
	 * @param iconId iconId
	 */
	public void setIconId(Long iconId) {
		this.iconId = iconId;
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
	