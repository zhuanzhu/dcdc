package com.egeo.components.user.controller.views.request;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Copyright (C), 2017-2018, 仁辉科技有限公司 FileName: UniAuthUserInfoParam Author: EDZ
 * Date: 2018/10/26 15:57 Description: 账户参数 History: <author> <time> <version>
 * <desc> 作者姓名 修改时间 版本号 描述
 */
public class UniAuthUserInfoAddParam {
	private Long id;
	/** 账号 */
	private String userId;
	/** 操作员姓名 */
	private String userName;
	/** 密码 */
	private String userPwd;
	/** 手机号 */
	private String userMobile;
	/** 手机号 */
	private String userMail;

	/**
	 * 公司名称
	 */
	private String company;

	private Long enterpriseId;
	private Long companyId;
	
	/**
	 * 部门编号
	 */
	private String departmentCode;

	/**
	 * 角色ID
	 */
	private String roles;
	public Set<Long> getRoleSet(){
		if(roles!=null && roles.length()>0) {
			String[] roleStrs = roles.split(",");
			Set<Long> rslt = new HashSet<Long>();
			for(String roleStr:roleStrs) {
				rslt.add(Long.valueOf(roleStr));
			}
			return rslt;
		}
		return null;
	}
	/**
	 * 管理员账号新增账号时可选的角色
	 */
	private String accountAddRoles;

	/** 阶段状态 */
	private String phaseStatus;
	/** 状态：1：管理员 2：操作员 */
	private int status;
	

	/** 1.平台用户，2.代理商用户 3.企业用户 4.商城用户*/
	private Integer type;

	private String financeProductType;// 金融产品类型
	private String repayMode;// 还款方式
	private String termType;// 借款期限类型

	/**
	 * 助贷机构数据限制：0不限制 1限制
	 */
	private int datapermLoanFlag;
	private List<String> datapermLoanCodes;

	/**
	 * 资金方数据限制：0不限制 1限制
	 */
	private int datapermCapitalFlag;
	private List<String> datapermCapitalCodes;

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * 业务类型数据限制：0不限制 1限制
	 */
	private int datapermBustypeFlag;
	private List<String> datapermBustypeCodes;

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

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPhaseStatus() {
		return phaseStatus;
	}

	public void setPhaseStatus(String phaseStatus) {
		this.phaseStatus = phaseStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFinanceProductType() {
		return financeProductType;
	}

	public void setFinanceProductType(String financeProductType) {
		this.financeProductType = financeProductType;
	}

	public String getRepayMode() {
		return repayMode;
	}

	public void setRepayMode(String repayMode) {
		this.repayMode = repayMode;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public int getDatapermLoanFlag() {
		return datapermLoanFlag;
	}

	public void setDatapermLoanFlag(int datapermLoanFlag) {
		this.datapermLoanFlag = datapermLoanFlag;
	}

	public List<String> getDatapermLoanCodes() {
		return datapermLoanCodes;
	}

	public void setDatapermLoanCodes(List<String> datapermLoanCodes) {
		this.datapermLoanCodes = datapermLoanCodes;
	}

	public int getDatapermCapitalFlag() {
		return datapermCapitalFlag;
	}

	public void setDatapermCapitalFlag(int datapermCapitalFlag) {
		this.datapermCapitalFlag = datapermCapitalFlag;
	}

	public List<String> getDatapermCapitalCodes() {
		return datapermCapitalCodes;
	}

	public void setDatapermCapitalCodes(List<String> datapermCapitalCodes) {
		this.datapermCapitalCodes = datapermCapitalCodes;
	}

	public int getDatapermBustypeFlag() {
		return datapermBustypeFlag;
	}

	public void setDatapermBustypeFlag(int datapermBustypeFlag) {
		this.datapermBustypeFlag = datapermBustypeFlag;
	}

	public List<String> getDatapermBustypeCodes() {
		return datapermBustypeCodes;
	}

	public void setDatapermBustypeCodes(List<String> datapermBustypeCodes) {
		this.datapermBustypeCodes = datapermBustypeCodes;
	}

	public String getAccountAddRoles() {
		return accountAddRoles;
	}

	public void setAccountAddRoles(String accountAddRoles) {
		this.accountAddRoles = accountAddRoles;
	}

	@Override
	public String toString() {
		return "UniAuthUserInfoAddParam [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userPwd="
				+ userPwd + ", userMobile=" + userMobile + ", company=" + company + ", departmentCode=" + departmentCode
				+ ", roles=" + roles + ", phaseStatus=" + phaseStatus + ", status=" + status + ", financeProductType="
				+ financeProductType + ", repayMode=" + repayMode + ", termType=" + termType + "]";
	}
}
