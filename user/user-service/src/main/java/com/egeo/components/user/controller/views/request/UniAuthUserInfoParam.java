package com.egeo.components.user.controller.views.request;

import java.util.Arrays;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniAuthUserInfoParam Author: EDZ
 * Date: 2018/10/26 15:57 Description: 账户参数 History: <author> <time> <version>
 * <desc> 作者姓名 修改时间 版本号 描述
 */
public class UniAuthUserInfoParam {

	/** 账号 */
	private String userId;
	/** 操作员姓名 */
	private String userName;
	/** 0正常 1锁定-冻结2 */
	private Integer isAllot;

	/**
	 * 公司名称
	 */
	private String company;
	private Long companyId;
	private Long enterpriseId;
	private Integer type;

	/**
	 * 部门编号
	 */
	private String sysCode;

	private String parentCode;

	/**
	 * 角色ID
	 */
	private Long roleId;

	private String[] userUuids;

	private Long[] roleIds;

	private String[] roleParams;

	private Integer[] isAllots;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsAllot() {
		return isAllot;
	}

	public void setIsAllot(Integer isAllot) {
		this.isAllot = isAllot;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}


	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String[] getUserUuids() {
		return userUuids;
	}

	public void setUserUuids(String[] userUuids) {
		this.userUuids = userUuids;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Integer[] getIsAllots() {
		return isAllots;
	}

	public void setIsAllots(Integer[] isAllots) {
		this.isAllots = isAllots;
	}

	public String[] getRoleParams() {
		return roleParams;
	}

	public void setRoleParams(String[] roleParams) {
		this.roleParams = roleParams;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@Override
	public String toString() {
		return "UniAuthUserInfoParam [userId=" + userId + ", userName=" + userName + ", isAllot=" + isAllot
				+ ", company=" + company + ", sysCode=" + sysCode + ", parentCode=" + parentCode
				+ ", roleId=" + roleId + ", userUuids=" + Arrays.toString(userUuids) + ", roleIds="
				+ Arrays.toString(roleIds) + ", roleParams=" + Arrays.toString(roleParams) + ", isAllots="
				+ Arrays.toString(isAllots) + "]";
	}

}
