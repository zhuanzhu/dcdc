package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author wyy
 * @date 2018-08-01 17:43:31
 */
public class NavigationBarDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 导航栏类型 0:顶部导航栏 1:底部信息栏
	 */
	private Integer navigationBarType;	

	/**
	 * 导航栏主题名称
	 */
	private String navigationBarName;	

	/**
	 * 导航栏状态(未使用,备用字段)
	 */
	private Integer navigationBarStatus;	

	private Long updateUser;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 创建时间
	 */
	private Date createTime;	
	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 公司类型 0:正式公司(默认值) 1:测试公司 2:竞品公司
	 */
	private Integer companyType;
	
	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	private List<Long> navigationLabelIdList;	//导航栏标签的id集合
	
	private List<Long> companyIdList;	// 导航栏关联公司id集合

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

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
	 * 导航栏类型 0:顶部导航栏 1:底部信息栏
	 * @return 导航栏类型 0:顶部导航栏 1:底部信息栏
	 */
	public Integer getNavigationBarType() {
		return navigationBarType;
	}

	/**
	 * 导航栏类型 0:顶部导航栏 1:底部信息栏
	 * @param navigationBarType 导航栏类型 0:顶部导航栏 1:底部信息栏
	 */
	public void setNavigationBarType(Integer navigationBarType) {
		this.navigationBarType = navigationBarType;
	}
	/**
	 * 导航栏主题名称
	 * @return 导航栏主题名称
	 */
	public String getNavigationBarName() {
		return navigationBarName;
	}

	/**
	 * 导航栏主题名称
	 * @param navigationBarName 导航栏主题名称
	 */
	public void setNavigationBarName(String navigationBarName) {
		this.navigationBarName = navigationBarName;
	}
	/**
	 * 导航栏状态(未使用,备用字段)
	 * @return 导航栏状态(未使用,备用字段)
	 */
	public Integer getNavigationBarStatus() {
		return navigationBarStatus;
	}

	/**
	 * 导航栏状态(未使用,备用字段)
	 * @param navigationBarStatus 导航栏状态(未使用,备用字段)
	 */
	public void setNavigationBarStatus(Integer navigationBarStatus) {
		this.navigationBarStatus = navigationBarStatus;
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

	public List<Long> getNavigationLabelIdList() {
		return navigationLabelIdList;
	}

	public void setNavigationLabelIdList(List<Long> navigationLabelIdList) {
		this.navigationLabelIdList = navigationLabelIdList;
	}

	public List<Long> getCompanyIdList() {
		return companyIdList;
	}

	public void setCompanyIdList(List<Long> companyIdList) {
		this.companyIdList = companyIdList;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
}
	