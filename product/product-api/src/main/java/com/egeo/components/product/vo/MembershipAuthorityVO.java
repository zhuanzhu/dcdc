package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author whf
 * @date 2018-09-06 16:12:29
 */
public class MembershipAuthorityVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 会籍id
	 */
	private Long membershipId;
	/**
	 * 权限id
	 */
	private Long authorityId;
	private String authorityName;
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	/**
	 * 是否停用(0.使用中,1.已停用)
	 */
	private Integer isStop;
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
	 * 会籍id
	 * @return 会籍id
	 */
	public Long getMembershipId() {
		return membershipId;
	}

	/**
	 * 会籍id
	 * @param membershipId 会籍id
	 */
	public void setMembershipId(Long membershipId) {
		this.membershipId = membershipId;
	}	
	/**
	 * 权限id
	 * @return 权限id
	 */
	public Long getAuthorityId() {
		return authorityId;
	}

	/**
	 * 权限id
	 * @param authorityId 权限id
	 */
	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}	
	/**
	 * 是否停用(0.使用中,1.已停用)
	 * @return 是否停用(0.使用中,1.已停用)
	 */
	public Integer getIsStop() {
		return isStop;
	}

	/**
	 * 是否停用(0.使用中,1.已停用)
	 * @param isStop 是否停用(0.使用中,1.已停用)
	 */
	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
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
	