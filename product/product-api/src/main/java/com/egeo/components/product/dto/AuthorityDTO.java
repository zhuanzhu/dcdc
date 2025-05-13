package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author whf
 * @date 2018-09-06 16:12:29
 */
public class AuthorityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 权限名称
	 */
	private String authorityName;	

	/**
	 * 备注
	 */
	private String remarks;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 权限名称
	 * @return 权限名称
	 */
	public String getAuthorityName() {
		return authorityName;
	}

	/**
	 * 权限名称
	 * @param authorityName 权限名称
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注
	 * @param remarks 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	