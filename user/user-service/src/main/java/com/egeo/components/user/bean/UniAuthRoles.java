package com.egeo.components.user.bean;

import java.util.List;

public class UniAuthRoles {
	public static final String ADMIN_ROLE_CODE = "admin";
	public static final int ROLE_STATE_ENABLE = 1;
	public static final int ROLE_STATE_DELETE = -1;

	/**
	 * 主键
	 */
	private Integer rid;

	/**
	 * 角色编号
	 */
	private String rcode;

	/**
	 * 角色名称
	 */
	private String rname;

	/**
	 * 角色状态 1-启用; 0-停用; -1-删除;
	 */
	private Integer rstate;

	/**
	 * 部门编号
	 */
	private String departmentCode;

	/**
	 * 角色组数组
	 */
	private String roleGroupArr;

	/**
	 * 角色组list
	 */
	private List<String> roleGroups;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 修改时间
	 */
	private String updateTime;

	/**
	 * 创建人
	 */
	private String createBy;

	/**
	 * 修改人
	 */
	private String updateBy;

	/**
	 *
	 */
	private String desc;

	/**
	 *
	 */
	private Integer appId;

	/**
	 *
	 */
	private String appCode;

	/**
	 * 所属部门名称
	 */
	private String departmentName;

	/**
	 * 最小创建时间
	 */
	private String minCreateTime;

	/**
	 * 最大创建时间
	 */
	private String maxCreateTime;

	/**
	 * 部门父级编号
	 */
	private String parentCode;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Integer getRstate() {
		return rstate;
	}

	public void setRstate(Integer rstate) {
		this.rstate = rstate;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getRoleGroupArr() {
		return roleGroupArr;
	}

	public void setRoleGroupArr(String roleGroupArr) {
		this.roleGroupArr = roleGroupArr;
	}

	public List<String> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(List<String> roleGroups) {
		this.roleGroups = roleGroups;
	}

}
