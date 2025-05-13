package com.egeo.components.user.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.swagger.annotations.ApiModelProperty;

/**
 * 登录user
 * 
 * @author zhou_k 2017年5月26日
 */
public class UserBaseInfoResponse {

	private Integer id;
	/** uuid 关联 */
	private String uuId;
	/** 账号 */
	private String userId;
	/** 密码 */
	private String userPwd;
	/** 状态：1：管理员 2：操作员 */
	private int status;
	/** 操作员姓名 */
	private String userName;
	/** 手机号 */
	private String userMobile;
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
	private String enterpriseUuid;
	private String company;
	
	private int roleId;
	@ApiModelProperty(value = "角色集合")
	private Set<Roles> roles = new HashSet<Roles>();
	@ApiModelProperty(value = "角色组集合")
	private Map<String, Set<Roles>> roleGroups = new HashMap<>();

	public UserBaseInfoResponse() {
	}


	public UserBaseInfoResponse(String uuId, String userId, String userPwd, String userName, int status,
			String phaseStatus, String company,String enterpriseUuid) {
		this.uuId = uuId;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.status = status;
		this.phaseStatus = phaseStatus;
		this.isAllot = 0; // 默认正常
		this.createdDate = new Date();
		this.company = company;
		this.enterpriseUuid = enterpriseUuid;
	}

	public String getEnterpriseUuid() {
		return enterpriseUuid;
	}

	public void setEnterpriseUuid(String enterpriseUuid) {
		this.enterpriseUuid = enterpriseUuid;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public UserBaseInfoResponse(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
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

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
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

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Map<String, Set<Roles>> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(Map<String, Set<Roles>> roleGroups) {
		this.roleGroups = roleGroups;
	}

}
