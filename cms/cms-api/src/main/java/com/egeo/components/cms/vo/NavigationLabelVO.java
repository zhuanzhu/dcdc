package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-08-02 10:33:40
 */
public class NavigationLabelVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 导航栏id
	 */
	private Long navigationBarId;
	/**
	 * 可跳转链接按钮id
	 */
	private Long linkableButtonId;
	/**
	 * 导航栏标签名称
	 */
	private String navigationLabelName;
	/**
	 * 导航栏标签备注
	 */
	private String navigationLabelRemark;
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
	 * 可跳转链接按钮id
	 * @return 可跳转链接按钮id
	 */
	public Long getLinkableButtonId() {
		return linkableButtonId;
	}

	/**
	 * 可跳转链接按钮id
	 * @param linkableButtonId 可跳转链接按钮id
	 */
	public void setLinkableButtonId(Long linkableButtonId) {
		this.linkableButtonId = linkableButtonId;
	}	
	/**
	 * 导航栏标签名称
	 * @return 导航栏标签名称
	 */
	public String getNavigationLabelName() {
		return navigationLabelName;
	}

	/**
	 * 导航栏标签名称
	 * @param navigationLabelName 导航栏标签名称
	 */
	public void setNavigationLabelName(String navigationLabelName) {
		this.navigationLabelName = navigationLabelName;
	}	
	/**
	 * 导航栏标签备注
	 * @return 导航栏标签备注
	 */
	public String getNavigationLabelRemark() {
		return navigationLabelRemark;
	}

	/**
	 * 导航栏标签备注
	 * @param navigationLabelRemark 导航栏标签备注
	 */
	public void setNavigationLabelRemark(String navigationLabelRemark) {
		this.navigationLabelRemark = navigationLabelRemark;
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
	