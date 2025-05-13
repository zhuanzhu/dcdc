package com.egeo.components.user.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.egeo.components.user.common.IdGen;
import com.egeo.components.user.common.PageBean;
import com.egeo.components.user.vo.UserBaseInfoResponse;


/**
 * 登录user
 * 
 * @author zhou_k 2017年5月26日
 */
public class UniAuthUserInfo {

	private Integer id;
	/** uuid 关联 */
	private String uuId;
	/** 账号 */
	private String userId;
	/** 密码 */
	private String userPwd;
	/** 手机号 */
	private String userMobile;
	/** 状态：1：管理员 2：操作员 */
	private int status;
	/** 操作员姓名 */
	private String userName;
	/** 关联用户uuid */
	private String userUuid;
	/** 0正常 1锁定-冻结2 */
	private Integer isAllot;
	/** 阶段状态 */
	private String phaseStatus;
	private String phaseStatusShow;
	/** 操作时间 */
	private Date upDate;
	private Date createdDate;
	private String company;
	/**
	 * 创建时间
	 */
	private String createDate;

	/**
	 * 修改时间
	 */
	private String updateDate;

	/**
	 * 创建人
	 */
	private String createdBy;

	/**
	 * 修改人
	 */
	private String updateBy;

	private Set<UniAuthRoles> roles = new HashSet<UniAuthRoles>();
	private Set<UniAuthRoles> accountRoles = new HashSet<UniAuthRoles>();
	private int roleId;
	private Integer[] roleIds;// 角色数组
	private String[] phaseArr;
	private String enterpriseUuid;
	private String departmentCode;// 部门编号

	public String getEnterpriseUuid() {
		return enterpriseUuid;
	}

	public void setEnterpriseUuid(String enterpriseUuid) {
		this.enterpriseUuid = enterpriseUuid;
	}

	public Set<UniAuthRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UniAuthRoles> roles) {
		this.roles = roles;
	}

	private PageBean pageBean;
	private int pageNum;

	public UniAuthUserInfo() {
	}

	public UniAuthUserInfo(String userId, String userPwd, String userName, int status, String phaseStatus,
			String company) {
		this.uuId = IdGen.uuid();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.status = status;
		this.phaseStatus = phaseStatus;
		this.isAllot = 0; // 默认正常
		this.createdDate = new Date();
		this.company = company;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public UniAuthUserInfo(String userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	public com.egeo.components.user.common.PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuId() {
		return uuId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

	public Integer getIsAllot() {
		return isAllot;
	}

	public void setIsAllot(Integer isAllot) {
		this.isAllot = isAllot;
	}

	public String getPhaseStatus() {
		return phaseStatus;
	}

	public void setPhaseStatus(String phaseStatus) {
		this.phaseStatus = phaseStatus;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhaseStatusShow() {
		return phaseStatusShow;
	}

	public void setPhaseStatusShow(String phaseStatusShow) {
		this.phaseStatusShow = phaseStatusShow;
	}

	public UserBaseInfoResponse toUserBaseInfoResponse() {
		UserBaseInfoResponse rslt = new UserBaseInfoResponse(uuId, userId, userPwd, userName, status, phaseStatus,
				company,enterpriseUuid);
		return rslt;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Integer[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}

	public String[] getPhaseArr() {
		return phaseArr;
	}

	public void setPhaseArr(String[] phaseArr) {
		this.phaseArr = phaseArr;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Set<UniAuthRoles> getAccountRoles() {
		return accountRoles;
	}

	public void setAccountRoles(Set<UniAuthRoles> accountRoles) {
		this.accountRoles = accountRoles;
	}

	@Override
	public String toString() {
		return "UniAuthUserInfo [id=" + id + ", uuId=" + uuId + ", userId=" + userId + ", userPwd=" + userPwd
				+ ", userMobile=" + userMobile + ", status=" + status + ", userName=" + userName + ", userUuid="
				+ userUuid + ", isAllot=" + isAllot + ", phaseStatus=" + phaseStatus + ", phaseStatusShow="
				+ phaseStatusShow + ", upDate=" + upDate + ", createdDate=" + createdDate + ", company=" + company
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", createdBy=" + createdBy
				+ ", updateBy=" + updateBy + ", roles=" + roles + ", roleId=" + roleId + ", roleIds="
				+ Arrays.toString(roleIds) + ", phaseArr=" + Arrays.toString(phaseArr) + ", pageBean=" + pageBean
				+ ", pageNum=" + pageNum + "]";
	}

}
