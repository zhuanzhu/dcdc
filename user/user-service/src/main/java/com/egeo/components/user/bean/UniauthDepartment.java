/**
 * Created By: XI
 * Created On: 2018-10-16 10:47:20
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.egeo.components.user.bean;

import java.io.Serializable;


public class UniauthDepartment implements Serializable{
	public static final int DEPARTMENT_STATE_ENABLE = 1;
	public static final int DEPARTMENT_STATE_DELETE = -1;

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/**
	  *	主键
	  */
	private Integer departmentId;

	/**
	  *	部门编号
	  */
	private String departmentCode;

	/**
	  *	部门名称
	  */
	private String departmentName;

	/**
	  *	部门父级编号
	  */
	private String parentCode;

	/**
	  *	部门所有父级编号
	  */
	private String parentCodes;

	/**
	  *	部门状态 1-启用; 0-停用; -1-删除;
	  */
	private Integer departmentState;

	/**
	  *	创建时间
	  */
	private String createTime;

	/**
	  *	修改时间
	  */
	private String updateTime;

	/**
	  *	创建人
	  */
	private String createBy;

	/**
	  *	修改人
	  */
	private String updateBy;

	/**
	  *	备注
	  */
	private String remark;

	/**
	 *	最小创建时间
	 */
	private String minCreateTime;

	/**
	 *	最大创建时间
	 */
	private String maxCreateTime;


	/**
	 *	部门父级名称
	 */
	private String parentName;
	private Integer status;


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	  *	主键
	  */
	public Integer getDepartmentId()
	{
		return departmentId;
	}
	
	/**
	  *	主键
	  */
	public void setDepartmentId(Integer departmentId)
	{
		this.departmentId = departmentId;
	}
	
	/**
	  *	部门编号
	  */
	public String getDepartmentCode()
	{
		return departmentCode;
	}
	
	/**
	  *	部门编号
	  */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}
	
	/**
	  *	部门名称
	  */
	public String getDepartmentName()
	{
		return departmentName;
	}
	
	/**
	  *	部门名称
	  */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	
	/**
	  *	部门父级编号
	  */
	public String getParentCode()
	{
		return parentCode;
	}
	
	/**
	  *	部门父级编号
	  */
	public void setParentCode(String parentCode)
	{
		this.parentCode = parentCode;
	}
	
	/**
	  *	部门所有父级编号
	  */
	public String getParentCodes()
	{
		return parentCodes;
	}
	
	/**
	  *	部门所有父级编号
	  */
	public void setParentCodes(String parentCodes)
	{
		this.parentCodes = parentCodes;
	}
	
	/**
	  *	部门状态 1-启用; 0-停用; -1-删除;
	  */
	public Integer getDepartmentState()
	{
		return departmentState;
	}
	
	/**
	  *	部门状态 1-启用; 0-停用; -1-删除;
	  */
	public void setDepartmentState(Integer departmentState)
	{
		this.departmentState = departmentState;
	}
	
	/**
	  *	创建时间
	  */
	public String getCreateTime()
	{
		return createTime;
	}
	
	/**
	  *	创建时间
	  */
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	
	/**
	  *	修改时间
	  */
	public String getUpdateTime()
	{
		return updateTime;
	}
	
	/**
	  *	修改时间
	  */
	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}
	
	/**
	  *	创建人
	  */
	public String getCreateBy()
	{
		return createBy;
	}
	
	/**
	  *	创建人
	  */
	public void setCreateBy(String createBy)
	{
		this.createBy = createBy;
	}
	
	/**
	  *	修改人
	  */
	public String getUpdateBy()
	{
		return updateBy;
	}
	
	/**
	  *	修改人
	  */
	public void setUpdateBy(String updateBy)
	{
		this.updateBy = updateBy;
	}
	
	/**
	  *	备注
	  */
	public String getRemark()
	{
		return remark;
	}
	
	/**
	  *	备注
	  */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getMinCreateTime() {
		return minCreateTime;
	}

	public void setMinCreateTime(String minCreateTime) {
		this.minCreateTime = minCreateTime;
	}

	public String getMaxCreateTime() {
		return maxCreateTime;
	}

	public void setMaxCreateTime(String maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public String toString() {
		return "UniauthDepartment{" +
				"departmentId=" + departmentId +
				", departmentCode='" + departmentCode + '\'' +
				", departmentName='" + departmentName + '\'' +
				", parentCode='" + parentCode + '\'' +
				", parentCodes='" + parentCodes + '\'' +
				", departmentState=" + departmentState +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				", createBy='" + createBy + '\'' +
				", updateBy='" + updateBy + '\'' +
				", remark='" + remark + '\'' +
				", minCreateTime='" + minCreateTime + '\'' +
				", maxCreateTime='" + maxCreateTime + '\'' +
				", parentName='" + parentName + '\'' +
				'}';
	}
}

