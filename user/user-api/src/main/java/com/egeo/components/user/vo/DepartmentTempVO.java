package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-01-10 15:05:33
 */
public class DepartmentTempVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 父类id
	 */
	private Long pId;
	/**
	 * 递归路径
	 */
	private String path;
	/**
	 * 部门等级
	 */
	private Integer level;
	/**
	 * 部门名称
	 */
	private String departmentName;
	/**
	 * 部门描述
	 */
	private String departmentContent;
	/**
	 * 排序
	 */
	private Integer sortValue;
	/**
	 * 是否有子集：0 否、1是
	 */
	private Integer isSubset;
	/**
	 * 公司id
	 */
	private Long companyId;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	/**
	 * 平台id
	 */
	private Long platformId;

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
	 * 父类id
	 * @return 父类id
	 */
	public Long getPId() {
		return pId;
	}

	/**
	 * 父类id
	 * @param pId 父类id
	 */
	public void setPId(Long pId) {
		this.pId = pId;
	}	
	/**
	 * 递归路径
	 * @return 递归路径
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 递归路径
	 * @param path 递归路径
	 */
	public void setPath(String path) {
		this.path = path;
	}	
	/**
	 * 部门等级
	 * @return 部门等级
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 部门等级
	 * @param level 部门等级
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}	
	/**
	 * 部门名称
	 * @return 部门名称
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * 部门名称
	 * @param departmentName 部门名称
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}	
	/**
	 * 部门描述
	 * @return 部门描述
	 */
	public String getDepartmentContent() {
		return departmentContent;
	}

	/**
	 * 部门描述
	 * @param departmentContent 部门描述
	 */
	public void setDepartmentContent(String departmentContent) {
		this.departmentContent = departmentContent;
	}	
	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}	
	/**
	 * 是否有子集：0 否、1是
	 * @return 是否有子集：0 否、1是
	 */
	public Integer getIsSubset() {
		return isSubset;
	}

	/**
	 * 是否有子集：0 否、1是
	 * @param isSubset 是否有子集：0 否、1是
	 */
	public void setIsSubset(Integer isSubset) {
		this.isSubset = isSubset;
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
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
}
	